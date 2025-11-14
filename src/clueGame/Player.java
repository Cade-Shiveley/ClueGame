package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private BoardCell location;
	private Set<Card> hand = new HashSet<>();
	
	private int startRow;
	private int startCol;
	
	
	public Player(String name, Color color, int startRow, int startCol) {
		super();
		this.name = name;
		this.color = color;
		this.startRow = startRow;
		this.startCol = startCol;
	}

	public void initializeLocation(BoardCell cell) {
		this.location = cell;
	}
	
	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	public void updateHand(Card card) {
		hand.add(card);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public BoardCell getLocation() {
		return location;
	}


	public void setLocation(BoardCell location) {
		this.location = location;
	}


	public Set<Card> getHand() {
		return hand;
	}


	public void setHand(Set<Card> hand) {
		this.hand = hand;
	}
	
	
}
