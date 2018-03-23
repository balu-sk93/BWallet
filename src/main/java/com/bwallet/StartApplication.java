package com.bwallet;

import java.security.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Balu S
 * @version 1.0.0.0
 * @since Sep 7, 2017
**/

@SpringBootApplication
@EnableAutoConfiguration
public class StartApplication {
  
    public static void main(String[] args) throws Exception {
    	
    	/** Setup Bouncey castle as a Security Provider **/
    	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
        SpringApplication.run(StartApplication.class, args);
    }
}
