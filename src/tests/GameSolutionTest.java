package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;
import experiment.TestBoard;

public class GameSolutionTest {

		private static Board board;
		private static List<Player> players;
		private static Card roomCard;
		private static Card personCard;
		private static Card weaponCard;
		
		@BeforeAll
		public static void setUp() {
			// Board is singleton, get the only instance
			board = Board.instance();
			// set the file names to use my config files
			board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");		
			// Initialize will load config files 
			board.initialize();
			players = board.getPlayers();
			
			for (Card c : board.getDeck()) {
				switch (c.getCardType()) {
					case ROOM: roomCard = c;
					case PERSON: personCard = c;
					case WEAPON: weaponCard = c;
				}
			}
		}
		
		
		
		//test for accusation
		@Test
		public void testAccusationCorrect() {
			Solution solution = board.getSolution();
			assertTrue(board.checkAccusation(solution));
		}
		
		@Test
		public void testAccusationWrongPerson() {
			Solution correct = board.getSolution();
			Solution incorrect = new Solution(correct.getRoom(), new Card("I", CardType.PERSON), correct.getWeapon());
			assertFalse(board.checkAccusation(incorrect));
		}
		
		@Test
		public void testAccusationWrongWeapon() {
			Solution correct = board.getSolution();
			Solution incorrect = new Solution(correct.getRoom(), correct.getPerson(), new Card("F", CardType.WEAPON));
			assertFalse(board.checkAccusation(incorrect));
		}
		
		@Test
		public void testAccusationWrongRoom() {
			Solution correct = board.getSolution();
			Solution incorrect = new Solution(new Card("H", CardType.ROOM), correct.getPerson(), correct.getWeapon());
			assertFalse(board.checkAccusation(incorrect));
		}
		
		//test for disprve suggestion
		@Test
		public void testOneMatchedCard() {
			Player steve = players.get(0);
			
			steve.getHand().clear();
			Card match = new Card("Nether", CardType.ROOM);
			steve.updateHand(match);
			
			Solution suggestion = new Solution(match, personCard, weaponCard);
			Card result = steve.disproveSuggestion(suggestion);
			
			assertEquals(match, result);
		}
		
		@Test
		public void testMultipleMatchedCard() {
			Player steve = players.get(0);
			
			steve.getHand().clear();
			Card match1 = new Card("Nether", CardType.ROOM);
			Card match2 = new Card("Bow", CardType.WEAPON);
			steve.updateHand(match1);
			steve.updateHand(match2);
			
			Solution suggestion = new Solution(match1, personCard, match2);
			boolean foundMatch1 = false;
			boolean foundMatch2 = false;
			
			for (int i = 0; i < 100; i++) {
				Card result = steve.disproveSuggestion(suggestion);
				if (result.equals(match1)) {
					foundMatch1 = true;
				}
				if (result.equals(match2)) {
					foundMatch2 = true;
				}
				if (foundMatch1 && foundMatch2) {
					break;
				}
			}
			
			assertTrue(foundMatch1 && foundMatch2);
		}
		
		@Test
		public void testNoMatchedCard() {
			Player steve = players.get(0);
			
			steve.getHand().clear();
			
			Solution suggestion = new Solution(roomCard, personCard, weaponCard);
			Card result = steve.disproveSuggestion(suggestion);
			
			assertNull(result);
		}
		
		//test for handle suggestion
		@Test
		public void testNoDisprove() {
			Player players1 = new ComputerPlayer("Enderman", Color.BLACK, 20,23);
			Player players2 = new ComputerPlayer("Herobrine", Color.MAGENTA, 0,17);
			
			players1.updateHand(new Card("Diamond Sword", CardType.WEAPON));
			players2.updateHand(new Card("Nether", CardType.ROOM));
			
			List<Player> testPlayers = new ArrayList<>();
			testPlayers.add(players1);
			testPlayers.add(players2);
			
			board.getPlayers().clear();
			board.getPlayers().addAll(testPlayers);
			
			Solution suggestion = new Solution(roomCard, personCard, weaponCard);

			assertNull(board.handleSuggestion(players1, suggestion));
			
		}
		
		@Test
		public void testSuggestingPlayer() {
			Player players1 = new ComputerPlayer("Enderman", Color.BLACK, 20,23);
			Player players2 = new ComputerPlayer("Herobrine", Color.MAGENTA, 0,17);
			
			Card match = new Card("Bow", CardType.WEAPON);
			//player one has the card
			players1.updateHand(match);
			
			players2.getHand().clear();
			
			
			board.getPlayers().clear();
			board.getPlayers().add(players1);
			board.getPlayers().add(players2);
			
			Solution suggestion = new Solution(match, personCard, weaponCard);
			assertNull(board.handleSuggestion(players1, suggestion));
		}
		
		@Test
		public void testSuggestingHuman() {
			Player humanPlayer = new HumanPlayer("Steve", Color.BLUE, 4,0);
			Player players2 = new ComputerPlayer("Herobrine", Color.MAGENTA, 0,17);
			
			Card match = new Card("Bow", CardType.WEAPON);
			humanPlayer.updateHand(match);
			players2.getHand().clear();
			
			board.getPlayers().clear();
			board.getPlayers().add(humanPlayer);
			board.getPlayers().add(players2);
			
			Solution suggestion = new Solution(roomCard, match, personCard);
			
			assertNull(board.handleSuggestion(humanPlayer, suggestion));

		}
		
		@Test
		public void testTwoPlayerDisprove() {
			Player players1 = new ComputerPlayer("Enderman", Color.BLACK, 20,23);
			Player players2 = new ComputerPlayer("Herobrine", Color.MAGENTA, 0,17);
			Player players3 = new ComputerPlayer("Steve", Color.BLUE, 4,0);
			
			Card matchWeapon = new Card("Bow", CardType.WEAPON);
			Card matchRoom = new Card("Nether", CardType.ROOM);
			
			players1.updateHand(matchWeapon);
			players2.updateHand(matchRoom);
			
			board.getPlayers().clear();
			board.getPlayers().add(players1);
			board.getPlayers().add(players2);
			board.getPlayers().add(players3);
			
			Solution suggestion = new Solution(matchRoom, personCard, matchWeapon);
			
			Card result = board.handleSuggestion(players1, suggestion);
			//even though there's two disproves, the room should disprove first
			assertEquals(matchRoom, result);
		}
}