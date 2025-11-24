package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {
	private JTextField guess;
	private JTextField guessResult;
	private JTextField dieRoll;
	private JTextField currentPlayer;
	
	private JButton next;
	private JButton makeAccusation;
	
	private JTextField guessing;
	private JTextField result;
	private JTextField player;
	private JTextField number;

	
	
	public GUI() {
		setTitle("ClueGame");
		setSize(700,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(bigPanel(),BorderLayout.CENTER);
		
		
		
	}
	

	
	public JPanel bigPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		panel.add(panel1());
		panel.add(panel2());
		return panel;
	}
	
	private JPanel panel1() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,4));
		
		panel.add(currentPlayer());
		panel.add(dieRoll());
		panel.add(nextButton());
		panel.add(accusationButton());
		
		return panel;
		
	}
	
	private JPanel panel2() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		panel.add(guessMade());
		panel.add(guessResult());
		
		return panel;
	}
	
	private JPanel nextButton() {
		JPanel panel = new JPanel();
		next = new JButton("NEXT!");
		next.setPreferredSize(new Dimension(160,75));
		panel.add(next);
		return panel;
		
		
	}
	
	private JPanel accusationButton() {
		JPanel panel = new JPanel();
		makeAccusation = new JButton("Make Accusation");
		makeAccusation.setPreferredSize(new Dimension(160,75));
		panel.add(makeAccusation);
		return panel;
		
	}
	
	private JPanel currentPlayer() {
		JPanel panel = new JPanel();		
		JLabel current = new JLabel("Whose turn?");
		
		
		player = new JTextField(10);
		player.setPreferredSize(new Dimension(50,20));

		
		panel.add(current);
		panel.add(player);
		
		return panel;
		
	}
	
	private JPanel dieRoll() {
		JPanel panel = new JPanel();
		JLabel roll = new JLabel("Roll: ");
		number = new JTextField(5);
		
		panel.add(roll);
		panel.add(number);
		
		return panel;
	}
	
	private JPanel guessMade() {
		JPanel panel = new JPanel();	
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Guess")));
		
		guessing = new JTextField(20);
		guessing.setPreferredSize(new Dimension(100,50));
		panel.add(guessing);

		return panel;
		
		
	}
	
	private JPanel guessResult() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Guess Result")));
		
		result = new JTextField(20);
		result.setPreferredSize(new Dimension(100,50));

		panel.add(result);
		
		return panel;
	}
	
	

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
		//panel.setTurn(new ComputerPlayer("Enderman",Color.))
		gui.setGuess("I have no guess!");
		gui.setGuessResult("So you have nothing");
		gui.setCurrentPlayer(new ComputerPlayer("Enderman",Color.BLACK, 20,23));
		
		
	}



	public void setGuess(String text) {
		guessing.setText(text);
	}



	public void setGuessResult(String text) {
		result.setText(text);
	}



	public void setDieRoll(int roll) {
		number.setText(""+roll);
	}



	public void setCurrentPlayer(Player players) {
		player.setText(players.getName());
		player.setBackground(players.getColor());
		player.setForeground(Color.WHITE);
		player.repaint();
	}



	public void setNext(JButton next) {
		this.next = next;
	}



	public void setMakeAccusation(JButton makeAccusation) {
		this.makeAccusation = makeAccusation;
	}



	public void setGuessing(JTextField guessing) {
		this.guessing = guessing;
	}



	public void setResult(JTextField result) {
		this.result = result;
	}



	public void setPlayer(JTextField player) {
		this.player = player;
	}



	public void setNumber(JTextField number) {
		this.number = number;
	}
	
	
	
}

