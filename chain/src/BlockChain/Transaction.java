package BlockChain;

import java.security.KeyPair;

import com.google.gson.JsonObject;

import utils.Crypto;

public class Transaction {
	//public key of owner
	String owner;
	//public key of receiver
	String receiver;
	int coins;
	String id;
	String signature;
	
	public Transaction(String owner, String receiver, int coins, String id, String signature) {
		super();
		this.owner = owner;
		this.receiver = receiver;
		this.coins = coins;
		this.id = id;
		this.signature = signature;
	}
	
	public Transaction(String owner, String receiver, int coins, String id) {
		super();
		this.owner = owner;
		this.receiver = receiver;
		this.coins = coins;
		this.id = id;
	}
	
	public void sign(String key) {
		String sign = Crypto.signMessage(this.toJSON(), key);
		this.signature = sign;
	}
	
	public boolean isSignatureValid() {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public String toJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("owner", owner);
		json.addProperty("receiver", receiver);
		json.addProperty("coins", coins);
		json.addProperty("id", id);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String toString() {
		JsonObject json = new JsonObject();
		json.addProperty("owner", owner);
		json.addProperty("receiver", receiver);
		json.addProperty("coins", coins);
		json.addProperty("id", id);
		json.addProperty("signature", signature);
		return json.getAsString();
	}
	
	public static Transaction fromJSON(String obj){
		JsonObject jsonobj = new JsonObject().getAsJsonObject(obj);
		return new Transaction(jsonobj.get("owner").getAsString(),
				jsonobj.get("receiver").getAsString(),
				Integer.parseInt(jsonobj.get("coins").getAsString()),
				jsonobj.get("id").getAsString(),
				jsonobj.get("signature").getAsString()
				);
	}
	
}