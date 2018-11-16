package election;

import java.util.ArrayList;

import com.google.gson.Gson;

import BlockChain.BlockChain;

public class position {
	String name;
	int maxWinners;
	ArrayList<candidate> candidates;
	ArrayList<Integer> votes;
	
	public position(String name, int maxWinners) {
		this.name = name;
		this.maxWinners = maxWinners;
		candidates = new ArrayList<candidate>();
	}
	
	public ArrayList<String> getVoteKeys(){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<votes.size(); i++) {
			list.add(candidates.get(votes.get(i)).getPublicKey());
		}
		return list;
	}
	
	public void addCandidate(candidate can) {
		this.candidates.add(can);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxWinners() {
		return maxWinners;
	}
	
	public void setMaxWinners(int maxWinners) {
		this.maxWinners = maxWinners;
	}
	public void setVotes(ArrayList<Integer> votes) {
		this.votes = votes;
	}
	
	public ArrayList<candidate> getCandidates() {
		return candidates;
	}

	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static position fromJSON(String jsonString) {
		return new Gson().fromJson(jsonString, position.class);
	}
}
