package tests;

import BlockChain.Block;
import BlockChain.Transaction;

public class blockJSONtest {
	public static void main(String[] args) {
		Transaction trans = new Transaction("Owner", "Receiver", 10, "id");
		Block block = new Block("timestamp", "previousddHash");
		block.addTransaction(trans.toJSON());
		block.addTransaction(new Transaction("Owner1", "Receive1r", 10, "id1").toJSON());
		
		String jsonorig = block.toJSON();
		String jsonretrived;
		jsonretrived = Block.fromJSON(jsonorig).toJSON();
		System.out.println(jsonorig);
		System.out.println(jsonretrived);
		
	}
}
