package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import utils.Crypto;

public class filetest {
	public static void main(String[] args) {
		initVoters();
	}
	private static void initVoters() {
		   //TODO Must have a separate endpoint and to be done well before the date of voting
		   try {
			   FileWriter fw = new FileWriter("myfile.csv");
			   PrintWriter writer = new PrintWriter(fw);
			    for(int i=0; i<10; i++) {
			    	KeyPair key = Crypto.generateKeys();
			    	writer.println(Crypto.getPrivateKeyasString(key)+","+Crypto.getPublicKeyasString(key)+"\n");
			    }
			    writer.close();
			}
			catch (IOException ex) {
			    System.out.println("An error occured :: " + ex.getMessage());
			}
		   }
	private static HashMap<String, String> loadVoters() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("myfile.csv"));
			String line = null;
			Scanner scanner = null;
			int index = 0;
			HashMap<String, String> voters = new HashMap<String, String>();
			while((line=reader.readLine()) != null) {
				scanner = new Scanner(line);
				scanner.useDelimiter(",");
				String privatekey = scanner.next();
				String publicKey = scanner.next();
				voters.put(privatekey, publicKey);
			}
			return voters;
		} catch (FileNotFoundException e) {
			System.out.println("An error occured :: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	}