package com.bwallet.services.serviceInterface;

import org.springframework.stereotype.Component;

import com.bwallet.dataLayer.model.Userdetails;

@Component
public interface WalletServices {

	public Userdetails registerUser(Userdetails userDetails);

}
