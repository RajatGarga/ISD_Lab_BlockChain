package votingSystem;
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;

import tests.JTableExamples;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.AbstractAction;
import javax.swing.Action; 

public class VoterApp {
	
	  // frame 
    JFrame f;
    String id = null;
    private JTextField textField;
    private JTextField textField_1;
  
    // Constructor 
    VoterApp() 
    { 
        // Frame initiallization 
        f = new JFrame(); 
  
        // Frame Title 
        f.setTitle("Login"); 
  

		f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // Frame Size 
        f.setSize(500, 200);
        f.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("VoterID");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(116, 21, 80, 17);
        f.getContentPane().add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(233, 19, 135, 20);
        f.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(116, 63, 81, 14);
        f.getContentPane().add(lblPassword);
        
        textField_1 = new JTextField();
        textField_1.setBounds(233, 60, 135, 20);
        f.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		id = textField.getText();
        		f.dispose();
        	}
        });
        btnLogin.setBounds(190, 127, 89, 23);
        f.getContentPane().add(btnLogin);
        // Frame Visible = true 
        f.setVisible(true); 
}
    public static void main(String[] args) 
    { 
        new VoterApp(); 
    } 
	
}
