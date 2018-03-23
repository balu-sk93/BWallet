package com.bwallet.wallet;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bwallet.transaction.Transaction;
import com.bwallet.transaction.TransactionInput;
import com.bwallet.transaction.TransactionOutputs;

public class Wallet {

  /** Address **/
  public PublicKey publicKey;
  /** signature **/
  public PrivateKey privateKey;
  /** only UTXOs owned by this wallet. **/
  public HashMap<String,TransactionOutputs> UTXOs = new HashMap<String,TransactionOutputs>(); 

  public Wallet() {
    generateKeyPair();
  }

  /** Elliptic-curve cryptography **/
  public void generateKeyPair() {
    try {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
      // Initialize the key generator and generate a KeyPair
      keyGen.initialize(ecSpec, random); // 256 bytes provides an acceptable
                                         // security level
      KeyPair keyPair = keyGen.generateKeyPair();
      // Set the public and private keys from the keyPair
      privateKey = keyPair.getPrivate();
      publicKey = keyPair.getPublic();
    } catch (Exception e) {
    	e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  // returns balance and stores the UTXO's owned by this wallet in this.UTXOs
  public float getBalance() {
    float total = 0;
    for (Map.Entry<String, TransactionOutputs> item : NoobChain.UTXOs
        .entrySet()) {
      TransactionOutputs UTXO = item.getValue();
      if (UTXO.isMine(publicKey)) { // if output belongs to me ( if coins belong
                                    // to me )
        UTXOs.put(UTXO.id, UTXO); // add it to our list of unspent transactions.
        total += UTXO.value;
      }
    }
    return total;
  }

  // Generates and returns a new transaction from this wallet.
  public Transaction sendFunds(PublicKey _recipient, float value) {
    if (getBalance() < value) { // gather balance and check funds.
      System.out.println(
          "#Not Enough funds to send transaction. Transaction Discarded.");
      return null;
    }
    // create array list of inputs
    ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();

    float total = 0;
    for (Map.Entry<String, TransactionOutputs> item : UTXOs.entrySet()) {
      TransactionOutputs UTXO = item.getValue();
      total += UTXO.value;
      inputs.add(new TransactionInput(UTXO.id));
      if (total > value)
        break;
    }

    Transaction newTransaction = new Transaction(publicKey, _recipient, value,
        inputs);
    newTransaction.generateSignature(privateKey);

    for (TransactionInput input : inputs) {
      UTXOs.remove(input.transactionOutputId);
    }
    return newTransaction;
  }

@Override
public String toString() {
	return "Wallet [publicKey=" + publicKey + ", privateKey=" + privateKey + ", UTXOs=" + UTXOs + "]";
}

  
}
