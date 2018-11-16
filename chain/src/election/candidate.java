package election;

public class candidate {
	String Name;
	String publicKey;
	String privateKey;

	public candidate(String name) {
		Name = name;
	}

	public candidate(String name, String publicKey, String privateKey) {
		Name = name;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public String getPublicKey() {
		return publicKey;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
}
