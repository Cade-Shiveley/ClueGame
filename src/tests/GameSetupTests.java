package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;
import clueGame.Solution;
import experiment.TestBoard;

public class GameSetupTests {
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.instance();
		// set the file names to use my config files
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
	
	@Test
	public void peopleLoadedIn() {
		assertEquals(6, board.getPlayers().size());
		
		assertEquals("Steve", board.getPlayers().get(0).getName());
		assertEquals("Chicken Jockey", board.getPlayers().get(5).getName());
	}
	
	@Test
	public void humanInitialized() {
		Player human = board.getPlayers().get(0);
		
		assertEquals("Steve", human.getName());
		assertEquals(Color.blue, human.getColor());
		
		BoardCell expectedStart = board.getCell(4, 0);
		assertEquals(expectedStart, human.getLocation());
	}
	
	@Test
	public void computerInitialized() {
		Player computer1 = board.getPlayers().get(1);
		
		assertEquals("Herobrine", computer1.getName());
		assertEquals(Color.MAGENTA, computer1.getColor());
		
		BoardCell expectedStart1 = board.getCell(0, 17);
		assertEquals(expectedStart1, computer1.getLocation());
		
		Player computer2 = board.getPlayers().get(2);
		
		assertEquals("Villager", computer2.getName());
		assertEquals(Color.ORANGE, computer2.getColor());
		
		BoardCell expectedStart2 = board.getCell(13, 23);
		assertEquals(expectedStart2, computer2.getLocation());
	}
	
	@Test
	public void deckCreated() {
		int deckSize = 21;
		
		assertEquals(deckSize, board.getDeck().size());
	}
	
	@Test
	public void solutionDealt() {
		Solution answer = board.getSolution();
		
		assertTrue(answer.getPerson().getCardType() == CardType.PERSON);
		assertTrue(answer.getRoom().getCardType() == CardType.ROOM);
		assertTrue(answer.getWeapon().getCardType() == CardType.WEAPON);
	}
	
	@Test
	public void cardsDealt() {
		List<Player> players = board.getPlayers();
		List<Card> deck = board.getDeck();
		
		for (Player p : players) {
			assertEquals(3, p.getHand().size());
		}
		
		List<Card> dealtCards = new ArrayList<>();
		int countDealt = 0;
		
		for (Player p : players) {
			for (Card c : p.getHand()) {
				dealtCards.add(c);
				countDealt++;
			}
		}
		
		assertEquals(countDealt, dealtCards.size());
		assertEquals(18, dealtCards.size());
		
	}
}
