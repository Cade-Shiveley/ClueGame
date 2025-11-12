package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private BoardCell location;
	private Set<Card> hand = new HashSet<>();
	
	
	public Player(String name, Color color, BoardCell startingCell) {
		super();
		this.name = name;
		this.color = color;
		this.location = startingCell;
	}


	public void updateHand(Card card) {
		
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
	
	
}
