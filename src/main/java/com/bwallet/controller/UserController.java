package com.bwallet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bwallet.dataLayer.model.Userdetails;
import com.bwallet.mappings.request.UserRequest;
import com.bwallet.services.serviceInterface.WalletServices;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	WalletServices walletServices;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Userdetails addNewUser(@RequestBody UserRequest request) {
		Userdetails userDetails = null;
		
		try {

			logger.info("Register Wallet => Request :" + request.toString());
			userDetails = new Userdetails();
			userDetails.setName(request.getName());
			userDetails.setPin(request.getPin());
			userDetails.setUsername(request.getUserName());
			
			userDetails = walletServices.registerUser(userDetails);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userDetails;
	}

}
