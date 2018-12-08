package votingSystem;

import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;

import BlockChain.Block;
import BlockChain.BlockChain;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map; 
  
public class ConfirmationWindow { 
    // frame 
    JFrame f; 
  
    // Constructor 
    ConfirmationWindow(BlockChain chain, Block block) 
    { 
        // Frame initiallization 
        f = new JFrame(); 
  
        // Frame Title 
        f.setTitle("Confirm!");
        // Frame Size 
        f.setSize(500, 200); 
        f.getContentPane().setLayout(null);
        
        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		try {
        		block.setHeight(chain.head.getHeight()+1);
				System.out.println("Starting to mine!");
				if (block.mine()) {
					Map<String, Object> params = new LinkedHashMap<>();
					params.put("block", block.toJSON());
					StringBuilder postData = new StringBuilder();
					for (Map.Entry<String, Object> param : params.entrySet()) {
						if (postData.length() != 0)
							postData.append('&');
						postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
						postData.append('=');
						postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
					}
					byte[] postDataBytes = postData.toString().getBytes("UTF-8");
					URL url2 = new URL("http://localhost:8080/test");
					HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
					conn.setDoOutput(true);
					conn.getOutputStream().write(postDataBytes);
					Reader in1 = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					StringBuilder sb = new StringBuilder();
			        for (int c; (c = in1.read()) >= 0;)
			            sb.append((char)c);
			        String response2 = sb.toString();
			        System.out.println(response2);
			        
				} else {
					System.out.println("Test Failed!");
				}}catch(Exception e) {
					
				}
        		System.exit(0);
        		f.dispose();
        	}
        });
        btnSubmit.setBounds(211, 64, 78, 26);
        f.getContentPane().add(btnSubmit);
        // Frame Visible = true 
        f.setVisible(true); 
    } 
} 