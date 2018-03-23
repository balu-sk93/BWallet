package com.bwallet.services.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bwallet.dataLayer.crud.UserCRUD;
import com.bwallet.dataLayer.model.Userdetails;
import com.bwallet.services.serviceInterface.WalletServices;
import com.bwallet.wallet.Wallet;

@Service
public class WalletServicesImpl implements WalletServices {
	
	@Autowired
	UserCRUD userCrud;

	@Override
	public Userdetails registerUser(Userdetails userDetails) {
		
		Wallet newWallet = null;
		
		try {
			
			newWallet = new Wallet();
			
			Random rand = new Random();
			int  n = rand.nextInt(10000) + 100;
			
			userDetails.setUserid(String.valueOf(n));
			userDetails.setPrivatekey(newWallet.privateKey.toString().replace("\n", "").replace("\r", ""));
			userDetails.setPublickey(newWallet.publicKey.toString().replace("\n", "").replace("\r", ""));
			
			userCrud.save(userDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userDetails;
	}

}
