package BankAccountsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@PropertySource({"classpath:application.properties"})
@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class BankAccountsApplication{
    @Value("${user.role}")
    private String role;

    public static void main(String[] args) {
        Logger log= LoggerFactory.getLogger(BankAccountsApplication.class);
        SpringApplication.run(BankAccountsApplication.class, args);
        log.info("BankAccountsService.Application is started");
    }

    @RequestMapping(
            value = "/whoami/{username}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String whoami(@PathVariable("username") String username) {
        return String.format("Hello! You're %s and you'll become a(n) %s...\n", username, role);
    }
}

