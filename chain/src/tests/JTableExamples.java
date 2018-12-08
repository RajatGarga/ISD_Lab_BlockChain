package tests;

import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 
  
public class JTableExamples { 
    // frame 
    JFrame f; 
  
    // Constructor 
    JTableExamples() 
    { 
        // Frame initiallization 
        f = new JFrame(); 
  
        // Frame Title 
        f.setTitle("Confirm!"); 
  
        // Data to be displayed in the JTable 
        String[][] data = { 
            { "Kundan Kumar Jha", "4031", "CSE" }, 
            { "Anand Jha", "6014", "IT" } 
        }; 
  
        // Column Names 
        String[] columnNames = { "Name", "Roll Number", "Department" };
        // Frame Size 
        f.setSize(500, 200); 
        f.getContentPane().setLayout(null);
        
        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        	}
        });
        btnSubmit.setBounds(211, 64, 78, 26);
        f.getContentPane().add(btnSubmit);
        // Frame Visible = true 
        f.setVisible(true); 
    } 
  
    // Driver  method 
    public static void main(String[] args) 
    { 
        new JTableExamples(); 
    } 
} 