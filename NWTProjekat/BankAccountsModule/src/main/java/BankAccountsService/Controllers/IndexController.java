package BankAccountsService.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Home page of BankAccounts Microservice";
    }
}