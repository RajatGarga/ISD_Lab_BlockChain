import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import BlockChain.Block;
import BlockChain.BlockChain;
import BlockChain.Constants;
import BlockChain.Transaction;
import utils.Crypto;
import utils.general;

public class testServ extends HttpServlet {
	
	KeyPair keys1, keys2;
	BlockChain chain;

	/**
	 * AutoGenerated
	 */
	private static final long serialVersionUID = -7286803566302157939L;
	
	public void init() throws ServletException {
		System.out.println("INIT TEST SERVER!");
		
		keys1 = Crypto.generateKeys();
		keys2 = Crypto.generateKeys();
		chain = new BlockChain(null);
		Transaction tran1 = new Transaction(Crypto.getPublicKeyasString(keys1),
				Crypto.getPrivateKeyasString(keys2),
				300, "genesis");
		tran1.sign(Crypto.getPrivateKeyasString(keys1));
		Block block = new Block(general.getTimeStamp(), chain.head.hashBlock());
		block.addTransaction(tran1.toJSON());
		block.setHeight(chain.head.getHeight()+1);
		if(block.mine()) {
			Constants.addCode code =  chain.addBlock(block, true);
			if(code == Constants.addCode.SUCCESS) {
				System.out.println("Successfully mined");
				//System.out.println(chain.toJSON());
			}else {
				System.out.println("An error occured : " + code);
			}
		}
		}

	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	      
	      // Set response content type
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println(chain.getBlocks());
	   }

	   public void destroy() {
	      // do nothing.
	   }
}