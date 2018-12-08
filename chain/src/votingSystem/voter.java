package votingSystem;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BlockChain.Block;
import BlockChain.BlockChain;
import BlockChain.Transaction;
import election.candidate;
import election.position;
import utils.Crypto;
import utils.general;

public class voter {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String publicKey;
		String privateKey;
		VoterApp loginWindow = new VoterApp();
		waitTillComplete(loginWindow.f);
		String id = loginWindow.id;
		URL url = new URL("http://localhost:8080/test?voter="+id);
		System.out.println("Making connection to :: " + url.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int response_Code = conn.getResponseCode();
		if (response_Code == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(response.toString());
			JsonArray arr = parser.parse(jsonObject.get("positions").getAsString()).getAsJsonArray();
			Iterator<JsonElement> it = arr.iterator();
			publicKey = jsonObject.get("publicKey").getAsString();
			privateKey = jsonObject.get("privateKey").getAsString();
			BlockChain chain = BlockChain.fromJSON(jsonObject.get("chain").getAsString());
			int balance = chain.getWalletAmount(publicKey);
			if(balance<=0) {
				System.out.println("Already Registered!");
				return;
			}
			Block block = new Block(general.getTimeStamp(), chain.head.hashBlock());
			while (it.hasNext()) {
				position pos = position.fromJSON(it.next().getAsString());
				int maxPos = pos.getMaxWinners();
				ArrayList<candidate> can = pos.getCandidates();
				
				for (int wow = 1; wow <= pos.getMaxWinners(); wow++) {
					VoterList window =  new VoterList(pos.getName(), can, wow);
					System.out.println("Starting Waiting!" + wow);
					waitTillComplete(window.f);
					System.out.println("Done Waiting!" + wow);
					int n = window.returnindex;
					candidate cand = can.get(n);
					Transaction tran = new Transaction(publicKey, cand.getPublicKey(), maxPos--,
							general.getTimeStamp());
					tran.sign(privateKey);
					block.addTransaction(tran.toJSON());
				}
				
				new ConfirmationWindow(chain, block);
			}
		}
	}
	
	private static void waitTillComplete(JFrame frame) throws InterruptedException {
		Object lock = new Object();
		Thread t = new Thread() {
	        public void run() {
	            synchronized(lock) {
	                while (frame.isVisible())
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                //System.out.println("Working now");
	            }
	        }
	    };
	    t.start();

	    frame.addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosed(java.awt.event.WindowEvent windowEvent)  {
	            synchronized (lock) {
	                frame.setVisible(false);
	                lock.notify();
	            }
	        }

	    });

	    t.join();
	}
}
