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

public class GameSolutionTest {

	public GameSolutionTest() {
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
		
		
		//test for accusation
		@Test
		public void testAccusationCorrect() {
			Solution solution = board.getSolution();
			assertTrue(board.checkAccusation(solution));
		}
		
		@Test
		public void testAccusationWrongPerson() {
			Solution solution = board.getSolution();
		}
		
		@Test
		public void testAccusationWrongWeapon() {
			
		}
		
		@Test
		public void testAccusationWrongRoom() {
			
		}
		
		//test for disprve suggestion
		@Test
		public void testOneMatchedCard() {
			
		}
		
		@Test
		public void testMultipleMatchedCard() {
			
		}
		
		@Test
		public void testNoMatchedCard() {
			
		}
		
		//test for handle suggestion
		@Test
		public void testNoDisprove() {
			
		}
		
		@Test
		public void testSuggestingPlayer() {
			
		}
		
		@Test
		public void testSuggestingHuman() {
			
		}
		
		@Test
		public void testTwoPlayerDisprove() {
			
		}
}
