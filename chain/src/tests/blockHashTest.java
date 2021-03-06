package tests;

import java.security.KeyPair;
import BlockChain.Block;
import BlockChain.Transaction;
import utils.Crypto;

public class blockHashTest {
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		KeyPair keys = Crypto.generateKeys();
		Transaction trans = new Transaction("Owner", "Receiver", 10, "id");
		trans.sign(Crypto.getPrivateKeyasString(keys));
		Block block = new Block("timestamp", "previousddHash");
		block.addTransaction(trans.toJSON());
		while(!block.isvalid()) {
			block.incNonce();
		}
		long endtime = System.currentTimeMillis();
		double time = (endtime-starttime)/1000.0;
		System.out.println("Time taken = "+time);
	}
}
