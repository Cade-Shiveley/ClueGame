package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainGUI extends JFrame{
	
	public MainGUI() {
		
		
		setSize(800,650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board board = Board.getTheInstance();
		board.initialize();
		//add(BoardGUI.BoardGUI(),BorderLayout.CENTER);
		
		
		GUI bottomGUI = new GUI();
		RightSideGUI rightGUI = new RightSideGUI();
		
		add(bottomGUI.bigPanel(),BorderLayout.SOUTH);
		add(rightGUI.rightSideGUI(), BorderLayout.EAST);
		
		//splash screen
		JOptionPane.showMessageDialog(null, "You are Steve. \n Can you find the solution \n before the computer players?");
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new MainGUI();

		
	}
	
}
