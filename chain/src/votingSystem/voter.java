package votingSystem;

import java.security.KeyPair;

import utils.Crypto;

public class voter {

	String publicKey;
	String PrivateKey;
	
	public voter(String PrivateKey) {
		this.PrivateKey = PrivateKey;
		
	}
}
