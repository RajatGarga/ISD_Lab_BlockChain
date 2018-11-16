package BlockChain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class BlockChain {
	ArrayList<Block> blocks;
	HashMap<String, Block> BlockLookup;
	public Block head;
	
	public BlockChain(String jsonStr) {
		super();
		if (jsonStr == null) {
			this.blocks = new ArrayList<Block>();
			this.blocks.add(Block.getGenesis());
			this.BlockLookup = new HashMap<String, Block>();
		}
		head = blocks.get(0);
		for (Block b : blocks) {
			this.BlockLookup.put(b.hashBlock(), b);
			if(b.height > this.head.height) {
				this.head = b;
			}
		}
		for(Block b : blocks) {
			if (b.previousHash != null) {
				if(BlockLookup.containsKey(b.previousHash)) {
					b.setParent(this.BlockLookup.get(b.previousHash));
				}
			}
		}
	}
	
	public void printBlocks() {
		Iterator<Block> it = this.blocks.iterator();
		String ret = "";
		while(it.hasNext()) {
			Block block = it.next();
			System.out.println(block.toJSON());
		}
	}
	public String getBlocks() {
		Iterator<Block> it = this.blocks.iterator();
		StringBuilder builder = new StringBuilder();
		while(it.hasNext()) {
			Block block = it.next();
			builder.append("  <---->  ");
			builder.append(block.toJSON());
		}
		return builder.toString();
	}
	public int getWalletAmount(String address) {
		//System.out.println("Rec Got: " +address);
		int total = 0;
		Block current = this.head;
		while(current != null) {
			Iterator<JsonElement> it = current.transactions.iterator();
			while(it.hasNext()) {
				Transaction transaction = Transaction.fromJSON(it.next().getAsString());
				//System.out.println("Rec Fot: "+transaction.receiver);
				if(transaction.owner.equals(address) && !transaction.receiver.equals(address)) {
					total -= transaction.coins;
				}else if(!transaction.owner.equals(address) && transaction.receiver.equals(address)) {
					total += transaction.coins;
				}
			}
			current = current.parent;
		}
		return total;
	}
	
	public Constants.addCode addBlock(Block block, boolean cheat) {
		String blockHash = block.hashBlock();
		if(this.BlockLookup.containsKey(blockHash)) {
			return Constants.addCode.KNOWN_BLOCK;
		}
		if(!this.BlockLookup.containsKey(block.previousHash)) {
			return Constants.addCode.NO_VALID_PARENT;
		}
		if(!block.isvalid()) {
			return Constants.addCode.INVALID_PROOF_OF_WORK;
		}
		JsonArray transactions = block.transactions;
		Iterator<JsonElement> it = transactions.iterator();
		while(it.hasNext()) {
			Transaction trans = Transaction.fromJSON(it.next().getAsString());
			if(!trans.isSignatureValid()) {
				return Constants.addCode.INVALID_SIGNATURE;
			}
		}
		
		Block current = this.head;
		while(current != null) {
			it = block.transactions.iterator();
			while(it.hasNext()) {
				if(current.transactions.contains(it.next())) {
					return Constants.addCode.TRANSACTION_REPLAYED;
				}
			}
			current = current.parent;
		}
		
		it = block.transactions.iterator();
		while(it.hasNext()) {
			Transaction transaction = Transaction.fromJSON(it.next().getAsString());
			if(transaction.coins<0) {
				return Constants.addCode.AMOUNT_NEGATIVE;
			}
			int ownerCoins = this.getWalletAmount(transaction.owner);
			System.out.println("OWNER HAS :: " + ownerCoins + " || Transaction has :: " + transaction.coins);
			if(ownerCoins < transaction.coins && !cheat) {
				return Constants.addCode.NOT_ENOUGH_COINS;
			}
		}
		block.setParent(this.BlockLookup.get(block.previousHash));
		this.BlockLookup.put(block.hashBlock(), block);
		this.blocks.add(block);
		int blockHeight = block.getHeight();
		int chainHeight = this.head.getHeight();
		System.out.println("BH : " + blockHeight + " || CH : " + chainHeight);
		if(blockHeight > chainHeight) {
			this.head = block;
			System.out.println("New Head!");
		}
		return Constants.addCode.SUCCESS;
	}
	
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
	
	public static BlockChain fromJSON(String jsonString) {
		return new Gson().fromJson(jsonString, BlockChain.class);
	}
}
