package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String playerName, Color color, int row, int col) {
		super(playerName, color, row, col);
	}
	
	public Solution createSuggestion() {
		Board board = Board.instance();
		
		BoardCell cell = getLocation();
		Card roomCard = new Card(board.getRoom(cell).getName(), CardType.ROOM);
		
		List<Card> unseenPeople = new ArrayList<>();
		List<Card> unseenWeapons = new ArrayList<>();
		
		for (Card c : board.getDeck()) {
			if (getSeenCards().contains(c) || getHand().contains(c)) {
				continue;
			}
			
			if (c.getCardType() == CardType.PERSON) {
				unseenPeople.add(c);
			} else if (c.getCardType() == CardType.WEAPON) {
				unseenWeapons.add(c);
			}
		}
		
		Random rand = new Random();
		
		Card person = unseenPeople.get(rand.nextInt(unseenPeople.size()));
		Card weapon = unseenWeapons.get(rand.nextInt(unseenWeapons.size()));
		
		return new Solution(roomCard, person, weapon);
	}
	
	public BoardCell selectTarget() {
		Set<BoardCell> targets = Board.instance().getTargets();
		Random rand = new Random();
		
		// Computer player to visit room first if unvisited room in targets
		for (BoardCell cell : targets) {
			if (cell.isRoomCenter()) {
				Card roomCard = new Card(Board.instance().getRoom(cell).getName(), CardType.ROOM);
				
				if (!getSeenCards().contains(roomCard)) {
					return cell;
				}
			}
		}
		
		List<BoardCell> list = new ArrayList<>(targets);
		return list.get(rand.nextInt(list.size()));
	}

}
