package PaymentsService.Controllers;

import PaymentsService.Models.PaymentModel;
import PaymentsService.Models.PaymentTypeModel;
import PaymentsService.Services.PaymentService;
import PaymentsService.Services.PaymentTypeService;
import PaymentsService.Templates.Transaction;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hare on 21.03.2017..
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/payments")
public class PaymentController {
    static final org.apache.log4j.Logger logger = LogManager.getLogger(PaymentController.class.getName());

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentTypeService paymentTypeService;

	@Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    public Page<PaymentModel> getAllPayments(Pageable pageable,
                                             @RequestParam(required = false) Double amountGTE,
                                             @RequestParam(required = false) Double amountLTE,
                                             @RequestParam(required = false) String status,
                                             @RequestParam(required = false) String paymentType,
                                             @RequestParam(required = false) Integer userId) {

        logger.info("GET PAYMENTS");
        if(userId != null)
            return paymentService.getAllPaymentsForUser(pageable, userId, status);
        return paymentService.getAllPayments(pageable, status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public PaymentModel getPaymentById(@PathVariable int id) {
        logger.info("GET PAYMENT BY ID: " + id);
        return paymentService.getPaymentById(id);
    }


    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    public Object insertPayment(@RequestBody PaymentModel payment) {
        logger.info("INSERT TO PAYMENTS");

        PaymentTypeModel pType = paymentTypeService.findByPaymentTypeName(payment.getTypeDescription());
        PaymentModel p = new PaymentModel(payment);
        if(pType.getPaymentTypeName().equals("InternalPayment"))
            p.setPurpose("Internal transfer");

        p.setStatus("Waiting");

        p.setType(pType);

        try {
            p = paymentService.insertPayment(p);

        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }

        return p;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces="application/json")
    public Object updatePayment(@PathVariable long id, @RequestBody PaymentModel payment) {
        logger.info("INSERT TO PAYMENTS");
        PaymentModel p = new PaymentModel(payment);
        p.setId(id);
        try {
            p = paymentService.updatePayment(p);
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
        return p;
    }

    @RequestMapping(value = "/{id}/sign", method = RequestMethod.PUT, produces="application/json")
    public Object signPayment(@PathVariable long id) {
        logger.info("Sign PAYMENT");
        PaymentModel p;
        try {
            p = paymentService.getPaymentById(id);
            if(p != null){
                p.setStatus("Signed");
                paymentService.updatePayment(p);
                return p;
            }
            //p = paymentService.updatePayment(p);

        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
        return p;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces="application/json")
    public Object deletePaymentById(@PathVariable long id) {
        logger.info("DELETE PAYMENT BY ID: " + id);

        try {
            paymentService.deletePayment(id);
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }

        return new StringResponse("Payment successfully deleted!");

    }
    
	@RequestMapping(value="/{id}/transaction", method = RequestMethod.GET)
	public ResponseEntity<Transaction> getTransactionByPaymentId(@PathVariable("id") int id) {
		
		PaymentModel payment = paymentService.getPaymentById(id);
		
		if (payment == null) {
			
			System.out.println("Payment not found!");
			
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		
	    String url = discoveryClient.getInstances("transactions").get(0).getUri().toString();
	    url += "/api/payment/" + id + "/transaction";
	    
	    System.out.println(url);
	    
		RestTemplate restTemplate = new RestTemplate();
	    /*HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(headers);
	    //Transaction transaction = restTemplate.getForObject(url, Transaction.class);
	    ResponseEntity<Transaction> response = restTemplate.exchange(
	            url, 
	            HttpMethod.GET, 
	            entity, 
	            Transaction.class);*/
		
		ResponseEntity<Transaction> response = restTemplate.getForEntity(url, Transaction.class);
		
		if(HttpStatus.OK != response.getStatusCode())
		{
			System.out.println(response);
			
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		
		Transaction transaction = response.getBody();    
	    
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

}


class StringResponse{
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public StringResponse(String s) {
        this.message = s;
    }
}