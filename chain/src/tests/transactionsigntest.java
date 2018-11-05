package tests;

import java.security.KeyPair;

import BlockChain.BlockChain;
import BlockChain.Transaction;
import utils.Crypto;

public class transactionsigntest {
	
	public static void main(String[] args) {
		KeyPair keys1, keys2;
		keys1 = Crypto.generateKeys();
		keys2 = Crypto.generateKeys();
		Transaction tran1 = new Transaction(Crypto.getPublicKeyasString(keys1),
				Crypto.getPrivateKeyasString(keys2),
				300, "genesis");
		tran1.sign(Crypto.getPrivateKeyasString(keys1));
		if(tran1.isSignatureValid()) {
			System.out.println("PASS!");
		}else {
			System.out.println("FAILED!");
		}
	}

}
