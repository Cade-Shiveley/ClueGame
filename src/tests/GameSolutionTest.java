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
import clueGame.Player;
import clueGame.Solution;
import experiment.TestBoard;

public class GameSolutionTest {

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
			Solution incorrect = new Solution("incorrect", correct.weapon(), correct.room());
			assertFalse(board.checkAccusation(incorrect));
		}
		
		@Test
		public void testAccusationWrongWeapon() {
			Solution solution = board.getSolution();
			Solution incorrect = new Solution(correct.person(), "incorrect", correct.room());
			assertFalse(board.checkAccusation(incorrect));
		}
		
		@Test
		public void testAccusationWrongRoom() {
			Solution solution = board.getSolution();
			Solution incorrect = new Solution(correct.person(), correct.weapon(), "incorrect");
			assertFalse(board.checkAccusation(incorrect));
		}
		
		//test for disprve suggestion
		@Test
		public void testOneMatchedCard() {
			Player player = new Player("Steve", Color.blue, 0,0);
			
			Card weapon = new Card("Bow", CardType.WEAPON);
			Card room = new Card("Nether", CardType.ROOM);
			Card person = new Card("Villager", CardType.PERSON);
			
			player.giveCard(room);
			
			Solution attempt = new Solution("Diamond Sword", "Nether", "Enderman");

			
			Card returned = player.disproveSuggestion(attempt);
			
			assertEquals(weapon,returned);
			
			
		}
		
		@Test
		public void testMultipleMatchedCard() {
			Player player = new Player("Steve", Color.blue, 0,0);
			
			Card weapon = new Card("Bow", CardType.WEAPON);
			Card room = new Card("Nether", CardType.ROOM);
			Card person = new Card("Villager", CardType.PERSON);
			
			player.giveCard(room);
			player.giveCard(weapon);
			player.giveCard(person);
			
			Solution attempt = new Solution("Bow", "Nether", "Enderman");
			
			Card result = player.disproveSuggestion(attempt);
			
			assertTrue(result == weapon || result == room);
			
				
			
		}
		
		@Test
		public void testNoMatchedCard() {
			Player player = new Player("Steve", Color.BLUE, 0,0);
			
			player.giveCard(new Card("Bow", CardType.WEAPON));
			player.giveCard(new Card("Nether", CardType.ROOM));
			player.giveCard(new Card("Enderman", CardType.PERSON));
			
			
			Solution attempt = new Solution("Villager", "Diamond Sword", "Mansion");
			
			assertNull(player.disproveSuggestion(attempt);)
			
		}
		
		//test for handle suggestion
		@Test
		public void testNoDisprove() {
			Player players1= new Player("Enderman", Color.BLACK, 20,23);
			Player players2 = new Player("Herobrine", Color.MAGENTA, 0,17);
			
			players1.giveCard(new Card("Diamond Sword", CardType.WEAPON);
			players2.giveCard(new Card("Nether", CardType.ROOM));
			
			
			List<Player> players = new ArrayList<>();
			
			players.add(players1);
			players.add(players2);
			
			board.TestPlayers(players);


			Solution attempt = new Solution("Enderman", "Bow", "Cave");
			
			assertNull(board.handleSuggestion(players1, attempt));
			
		}
		
		@Test
		public void testSuggestingPlayer() {
			Player players1 = new Player("Steve", Color.BLUE, 4,0);
			Player players2 = new Player("Enderman", Color.BLACK, 20,23);
			Player players3 = new Player("Herobrine", Color.MAGENTA, 0,17);
			
			players1.giveCard("Bow", CardType.WEAPON);
			players2.giveCard("Nether", CardType.ROOM);
			players3.giveCard("Villager", CardType.PERSON);
			
			List<Player> players = new ArrayList<>();
			
			players.add(players1);
			players.add(players2);
			players.add(players3);
			
			board.setPlayers(players);
			
			Solution attempt = new Solution("Steve", "Bow", "End");

		}
		
		@Test
		public void testSuggestingHuman() {
			Player human = new Player("Steve", Color.BLUE, 4,0);
			Player players2 = new Player("Enderman", Color.BLACK, 20,23);
			Player players3 = new Player("Herobrine", Color.MAGENTA, 0,17);
			
			human.giveCard(new Card("End", CardType.ROOM);
			
			players2.giveCard(new Card("Bow", CardType.WEAPON);
			players3.giveCard(new Card("Enderman", CardType.PERSON);
			
			List<Player> players = new ArrayList<>();
			
			players.add(human);
			players.add(players2);
			players.add(players3);
			
			board.setPlayers(players);
			
			Solution suggestion = new Solution("Zombie", "Diamond Sword", "End");
			
			assertNull(board.handleSuggestion(human, suggestion));
		}
		
		@Test
		public void testTwoPlayerDisprove() {
			Player players1 = new Player("Steve", Color.BLUE, 4,0);
			Player players2 = new Player("Enderman", Color.BLACK, 20,23);
			Player players3 = new Player("Herobrine", Color.MAGENTA, 0,17);
			
			Card matchWeapon = new Card("Bow", CardType.WEAPON);
			Card matchRoom = new Card("End", CardType.ROOM);
			
			players1.giveCard(matchWeapon);
			players2.giveCard(matchRoom);
			
			
			List<Player> players = new ArrayList<>();
			
			players.add(players1);
			players.add(players2);
			players.add(players3);
			
			board.setPlayers(players);
			
			Solution attempt = new Solution("Steve", "Bow", "End");
			
			assertEquals(matchWeapon, board.handleSuggestion(players1, attempt));
			
		}
}
