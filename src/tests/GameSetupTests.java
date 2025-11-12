package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
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
	
	@BeforeEach
	public void setUp() {
		board = new TestBoard();
	}
	
	
	@Test
	public void peopleLoadedIn() {
		
	}
	
	@Test
	public void humanInitialized() {
		
	}
	
	@Test
	public void computerInitialized() {
		
	}
	
	@Test
	public void deckCreated() {
		
	}
	
	@Test
	public void solutionDealt() {
		
	}
	
	@Test
	public void cardsDealt() {
		
	}
}
