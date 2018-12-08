package votingSystem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import election.candidate;
import election.position;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JCheckBox;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VoterList {
	JFrame f;
	private JTextField textField;
    private JTextField textField_1;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    int returnindex;
	VoterList(String name,ArrayList<candidate> can, int Votenum){
		f = new JFrame(); 
		f.setTitle(name);
		f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		f.setSize(525, 254);
		f.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 58, 394, 110);
		f.getContentPane().add(scrollPane);
		
		Box verticalBox = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBox);
		ArrayList<JRadioButton> radioList = new ArrayList<JRadioButton>();
		
		for (int i = 0; i < can.size(); i++) {
			candidate candidate = can.get(i);
			JRadioButton rdbtnNewRadioButton = new JRadioButton(candidate.getName());
			buttonGroup.add(rdbtnNewRadioButton);
			verticalBox.add(rdbtnNewRadioButton);
			radioList.add(rdbtnNewRadioButton);
		}
		
		JLabel lblPosition = new JLabel(name);
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPosition.setBounds(41, 11, 124, 14);
		f.getContentPane().add(lblPosition);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i=0; i<radioList.size(); i++) {
					if(radioList.get(i).isSelected()) {
						returnindex = i;
						f.dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(208, 179, 89, 23);
		f.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Select your vote number " + Votenum);
		lblNewLabel.setBounds(41, 33, 354, 14);
		f.getContentPane().add(lblNewLabel);
		
		
	    f.setVisible(true); 
	}
}
