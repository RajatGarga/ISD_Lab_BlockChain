package votingSystem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class testWindow {
	
	private static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	}
	
	private static void waitTillComplete(JFrame frame) throws InterruptedException {
		Thread t = new Thread() {
	        public void run() {
	            synchronized(lock) {
	                while (frame.isVisible())
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                System.out.println("Working now");
	            }
	        }
	    };
	    t.start();

	    frame.addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosing(WindowEvent arg0) {
	            synchronized (lock) {
	                frame.setVisible(false);
	                lock.notify();
	            }
	        }

	    });

	    t.join();
	}
	
}
