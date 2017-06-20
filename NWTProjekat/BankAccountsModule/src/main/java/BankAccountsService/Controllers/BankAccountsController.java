package BankAccountsService.Controllers;

import BankAccountsService.Interfaces.BankAccountsService;
import BankAccountsService.Models.BankAccount;
import BankAccountsService.Templates.PaymentModel;
import BankAccountsService.Templates.Transaction;
import BankAccountsService.Templates.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/accounts")
public class BankAccountsController {

    @Autowired
    private BankAccountsService bankAccountService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BankAccount>> getAllAccounts(@RequestParam(required = false) String currency,
                                                            @RequestParam(required = false) Integer userId) {
        List<BankAccount> bankAccounts = bankAccountService.GetAllAccounts();
        if (bankAccounts.isEmpty()) {
            return new ResponseEntity<List<BankAccount>>(HttpStatus.NOT_FOUND);
        }
        if (currency != null) {

            Iterator<BankAccount> i = bankAccounts.iterator();
            if(currency.equals("domestic")){
                while (i.hasNext()) {
                    BankAccount bankAccount = i.next();
                    if (!bankAccount.getCurrency().equals("BAM"))
                        i.remove();
                }
            } else {
                while (i.hasNext()) {
                    BankAccount bankAccount = i.next();
                    if (bankAccount.getCurrency().equals("BAM"))
                        i.remove();
                }
            }

        }

        if(userId != null){
            Iterator<BankAccount> i = bankAccounts.iterator();
            while (i.hasNext()) {
                BankAccount bankAccount = i.next();
                if (bankAccount.getUser() != userId)
                    i.remove();
            }
        }
        return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable("id") int id) {
        BankAccount bankAccount = bankAccountService.FindAccountById(id);
        if (bankAccount == null) {
            return new ResponseEntity<BankAccount>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountService.SaveAccount(bankAccount);
        return new ResponseEntity<BankAccount>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/transaction", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getAllTransactionsByBankAccountId(@PathVariable("id") int id) {
        BankAccount bankAccount = bankAccountService.FindAccountById(id);

        if (bankAccount == null) {
            System.out.println("Bank Account not found!");

            return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
        }

        String url = discoveryClient.getInstances("transactions").get(0).getUri().toString();
        url += "/api/bankaccount/" + id + "/transaction";

        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        /*HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    //HttpEntity<?> entity = new HttpEntity<Object>(,headers);*/
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(url, ArrayList.class);
        //List<Transaction> transactions = (List<Transaction>) responseEntity.getBody();

        if (HttpStatus.OK != response.getStatusCode()) {
            System.out.println("Transactions not found!");

            return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);

        }

        List<Transaction> transactions = response.getBody();

        System.out.println(transactions);

        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}/user", method = RequestMethod.GET)
    public ResponseEntity<User> getBankAccountOwnerByAccountId(@PathVariable("id") int id) {
        BankAccount bankAccount = bankAccountService.FindAccountById(id);

        if (bankAccount == null) {
            System.out.println("Bank Account not found!");

            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        String url = discoveryClient.getInstances("users").get(0).getUri().toString();
        url += "/api/user/" + bankAccount.getUser() + "/account";

        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
	    /*HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    //HttpEntity<?> entity = new HttpEntity<Object>(,headers);*/
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        //List<Transaction> transactions = (List<Transaction>) responseEntity.getBody();

        if (HttpStatus.OK != response.getStatusCode()) {
            System.out.println("User not found!");

            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        }

        User user = response.getBody();

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/refreshBalance")
    public Object refreshBalance(@RequestBody PaymentModel paymentModel){

        try {
            BankAccount bankAccountFrom = bankAccountService.FindAccountByNumber(paymentModel.getSenderBankAccNumber());
            if(bankAccountFrom != null){
                bankAccountFrom.setCredit_amount(bankAccountFrom.getCredit_amount() - paymentModel.getAmount());
                bankAccountService.UpdateAccount(bankAccountFrom);
            }

            BankAccount bankAccountTo = bankAccountService.FindAccountByNumber(paymentModel.getReceiverBankAccNumber());
            if(bankAccountTo != null){
                bankAccountTo.setCredit_amount(bankAccountTo.getCredit_amount() + paymentModel.getAmount());
                bankAccountService.UpdateAccount(bankAccountTo);
            }


            return new StringResponse("Successfully transferred");
        }catch (Exception e) {
            return new StringResponse(e.getMessage());
        }

    }

    class SignData{
        private int accountFrom;
        private int accountTo;
        private Double amount;

        public int getAccountFrom() {
            return accountFrom;
        }

        public void setAccountFrom(int accountFrom) {
            this.accountFrom = accountFrom;
        }

        public int getAccountTo() {
            return accountTo;
        }

        public void setAccountTo(int accountTo) {
            this.accountTo = accountTo;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
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

}
