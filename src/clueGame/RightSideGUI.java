package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RightSideGUI {
	
	private Player player;
	private JPanel peoplePanel;
	private JPanel roomPanel;
	private JPanel weaponPanel;
	private JPanel mainPanel;
	
	public RightSideGUI(Board board) {
		this.player = board.getPlayers().get(0);
		mainPanel = KnownCards();
		update();
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
	
	private void update() {
		//remove everything from all panels
		//add labels in hand 
		//adds in hand
		//add seen label
		//add seen
		peoplePanel.removeAll();
		roomPanel.removeAll();
		weaponPanel.removeAll();
		
		peoplePanel.add(new JLabel("In Hand:"));
		for (Card c : player.getHand()) {
			if (c.getCardType() == CardType.PERSON) {
				JTextField card = new JTextField(c.getCardName());
				card.setEditable(false);
				peoplePanel.add(card);
			}
		}
		
		peoplePanel.add(new JLabel("Seen:"));
		for (Map.Entry<Card, Player> entry : player.getSeenCards().entrySet()) {
			Card c = entry.getKey();
			Player shower = entry.getValue();
			
			if (c.getCardType() == CardType.PERSON) {
				JTextField card = new JTextField(c.getCardName());
				card.setEditable(false);
				card.setBackground(shower.getColor());
				peoplePanel.add(card);
			}
		}
		
		roomPanel.add(new JLabel("In Hand:"));
		for (Card c : player.getHand()) {
			if (c.getCardType() == CardType.ROOM) {
				JTextField card = new JTextField(c.getCardName());
				card.setEditable(false);
				roomPanel.add(card);
			}
		}
		
		roomPanel.add(new JLabel("Seen:"));
		for (Map.Entry<Card, Player> entry : player.getSeenCards().entrySet()) {
			Card c = entry.getKey();
			Player shower = entry.getValue();
			
			if (c.getCardType() == CardType.ROOM) {
				JTextField card = new JTextField(c.getCardName());
				card.setEditable(false);
				card.setBackground(shower.getColor());
				roomPanel.add(card);
			}
		}
		
		weaponPanel.add(new JLabel("In Hand:"));
		for (Card c : player.getHand()) {
			if (c.getCardType() == CardType.WEAPON) {
				JTextField card = new JTextField(c.getCardName());
				card.setEditable(false);
				weaponPanel.add(card);
			}
		}
		
		weaponPanel.add(new JLabel("Seen:"));
		for (Map.Entry<Card, Player> entry : player.getSeenCards().entrySet()) {
			Card c = entry.getKey();
			Player shower = entry.getValue();
			
			if (c.getCardType() == CardType.WEAPON) {
				JTextField card = new JTextField(c.getCardName());
				card.setEditable(false);
				card.setBackground(shower.getColor());
				weaponPanel.add(card);
			}
		}
		//check through all and call none
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private JPanel KnownCards() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Known Cards")));
		panel.setLayout(new GridLayout(3,1));
		
		
		panel.setPreferredSize(new Dimension(150,400));
		
		panel.add(people(),BorderLayout.NORTH);
		panel.add(rooms(), BorderLayout.CENTER);
		panel.add(weapons(), BorderLayout.SOUTH);
		
		return panel;
				
	}
	
	private JPanel people() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("People")));
		panel.setLayout(new GridLayout(0, 1));
		
		peoplePanel = new JPanel();
		peoplePanel.setLayout(new GridLayout(0, 1));
		
		panel.add(peoplePanel);
		
		return panel;
	}
	
	private JPanel rooms() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Rooms")));
		panel.setLayout(new GridLayout(0, 1));
		
		roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(0, 1));
		
		panel.add(roomPanel);
		
		return panel;
	}
	
	private JPanel weapons() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Weapons")));
		panel.setLayout(new GridLayout(0, 1));
		
		weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(0, 1));
		
		panel.add(weaponPanel);

		
		//weaponsInHand.setPreferredSize(new Dimension(200,75));
		//weaponsSeen.setPreferredSize(new Dimension(200,75));
		
		return panel;
	}
	

	/*public static void main(String[] args) {
		new RightSideGUI();//set visible here

		
	}*/
	
	
	
}
