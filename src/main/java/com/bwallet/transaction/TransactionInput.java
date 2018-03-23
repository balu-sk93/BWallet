package com.bwallet.transaction;

/**
 * This class will be used to reference TransactionOutputs that have not yet
 * been spent.The transactionOutputId will be used to find the relevant
 * TransactionOutput, allowing miners to check your ownership.
 **/

public class TransactionInput {

  public String transactionOutputId;

  public TransactionOutputs UTXO; // Contains the Unspent transaction output

  public TransactionInput(String transactionOutputId) {
    this.transactionOutputId = transactionOutputId;
  }
}
