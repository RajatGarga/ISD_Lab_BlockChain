package tests;

import java.security.KeyPair;

import utils.Crypto;

public class SignTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeyPair keys = Crypto.generateKeys();
		String message = "Hello World!";
		String signature = Crypto.signMessage(message, Crypto.getPrivateKeyasString(keys));
		if(Crypto.verifyMessageSignature(message, signature, Crypto.getPublicKeyasString(keys))) {
			System.out.println("PASS!");
		}else {
			System.out.println("FAILED!");
		}
	}

}
