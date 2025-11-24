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

public class RightSideGUI extends JFrame {
	public JPanel rightSideGUI() {
		JPanel panel = new JPanel();
		panel.add(KnownCards());
		return panel;
		
	}
	
	public RightSideGUI() {
		setTitle("right side gui");
		setSize(150,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(rightSideGUI());
		setVisible(true);
	}
	
	public JPanel rightsidegui() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(KnownCards());
		return panel;
		
	}
	private JPanel KnownCards() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Known Cards)")));
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
		panel.setLayout(new GridLayout(2,1));
		
		
		JLabel peopleInHand = new JLabel("In Hand: ");
		JLabel peopleSeen = new JLabel("Seen: ");
		peopleInHand.setPreferredSize(new Dimension(200,75));
		peopleSeen.setPreferredSize(new Dimension(200,75));

		
		panel.add(peopleInHand);
		panel.add(peopleSeen);
		
		return panel;
	}
	
	private JPanel rooms() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Rooms")));
		panel.setLayout(new GridLayout(2,1));

		
		JLabel roomsInHand = new JLabel("In Hand: ");
		JLabel roomsSeen = new JLabel("Seen: ");
		
		roomsInHand.setPreferredSize(new Dimension(200,75));
		roomsSeen.setPreferredSize(new Dimension(200,75));
		

		panel.add(roomsInHand);
		panel.add(roomsSeen);
		
		return panel;
	}
	
	private JPanel weapons() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), ("Weapons")));
		panel.setLayout(new GridLayout(2,1));

		
		JLabel weaponsInHand = new JLabel("In Hand: ");
		JLabel weaponsSeen = new JLabel("Seen: ");
		
		weaponsInHand.setPreferredSize(new Dimension(200,75));
		weaponsSeen.setPreferredSize(new Dimension(200,75));



		panel.add(weaponsInHand);
		panel.add(weaponsSeen);
		
		return panel;
	}
	
	public static void main(String[] args) {
		new RightSideGUI();

		
	}
	
	
}
