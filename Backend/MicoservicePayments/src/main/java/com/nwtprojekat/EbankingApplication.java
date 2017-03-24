package com.nwtprojekat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EbankingApplication {

	public static void main(String[] args) {
		final Logger logger = LogManager.getLogger(EbankingApplication.class.getName());
		SpringApplication.run(EbankingApplication.class, args);
		logger.info("Application is started!");
	}
}
