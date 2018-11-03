package tests;

import java.security.KeyPair;
import java.util.Arrays;

import utils.Crypto;

public class KeyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeyPair keys = Crypto.generateKeys();
		if(keys != null) {
			if(Arrays.equals(keys.getPrivate().getEncoded(),Crypto.loadKey(Crypto.getPrivateKeyasString(keys)))) {
				System.out.println("accepted Private!");
			}else {
				System.out.println("Denied Private");
			}
			if(Arrays.equals(keys.getPublic().getEncoded(),Crypto.loadKey(Crypto.getPublicKeyasString(keys)))) {
				System.out.println("accepted Public!");
			}else {
				System.out.println("Denied Public");
			}
		}
	}

}
