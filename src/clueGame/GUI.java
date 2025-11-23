package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {
	private JTextField myName;
	public GUI() {
		setTitle("ClueGame");
		setSize(300,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
		
	}
	
	private void createLayout() {
		JLabel nameLabel = new JLabel("something");
		myName = new JTextField(20);
		add(nameLabel,BorderLayout.NORTH);
		add(myName,BorderLayout.CENTER);
		
		JButton nameButton = new JButton("Next");
		add(nameButton,BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
	}
	
}

