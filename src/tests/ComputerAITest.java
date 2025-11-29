package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

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

public class ComputerAITest {

		private static Board board;
		private static ComputerPlayer Villager;
		
		@BeforeAll
		public static void setUp() {
			// Board is singleton, get the only instance
			board = Board.instance();
			// set the file names to use my config files
			board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");		
			// Initialize will load config files 
			board.initialize();
			
			for (Player p : board.getPlayers()) {
				if (p instanceof ComputerPlayer) {
					Villager = (ComputerPlayer) p;
					break;
				}
			}

			assertNotNull(Villager);
		}
		
		@Test
		public void testRoomMatchesLocation() {
			Villager.setLocation(2, 1);
			
			Solution s = Villager.createSuggestion();
			
			assertEquals(board.getRoom(board.getCell(2, 1)).getName(), s.getRoom().getCardName());
		}
		
		@Test
		public void testNotSeenWeaponSelected() {
			Villager.getSeenCards().clear();
			Villager.getHand().clear();

			Villager.updateSeen(new Card("Diamond Sword", CardType.WEAPON), null);
			Villager.updateSeen(new Card("Trident", CardType.WEAPON), null);
			Villager.updateSeen(new Card("Netherite Axe", CardType.WEAPON), null);
			Villager.updateSeen(new Card("Lava Bucket", CardType.WEAPON), null);
			Villager.updateSeen(new Card("Fist", CardType.WEAPON), null);
			
			List<String> unseenWeapons = new ArrayList<>();
			for (Card c : board.getDeck()) {
				if ( c.getCardType() == CardType.WEAPON && !Villager.getSeenCards().containsKey(c)) {
					unseenWeapons.add(c.getCardName());
				}
			}
			
			Solution s = Villager.createSuggestion();
			
			assertEquals(unseenWeapons.get(0), s.getWeapon().getCardName());
		}
		
		@Test
		public void testNotSeenPersonSelected() {
			Villager.getSeenCards().clear();
			Villager.getHand().clear();
			
			Villager.updateSeen(new Card("Steve", CardType.PERSON), null);
			
			List<String> unseenPeople = new ArrayList<>();
			for (Card c : board.getDeck()) {
				if (c.getCardType() == CardType.PERSON && !Villager.getSeenCards().containsKey(c)) {
					unseenPeople.add(c.getCardName());
				}
			}
			
			Solution s = Villager.createSuggestion();
						
			assertTrue(unseenPeople.contains(s.getPerson().getCardName()));
		}
		
		@Test
		public void testMultipleWeaponsOneSelected() {
					
			Villager.getSeenCards().clear();
			Villager.getHand().clear();
			
			Villager.updateSeen(new Card("Diamond Sword", CardType.WEAPON), null);
			Villager.updateSeen(new Card("Bow", CardType.WEAPON), null);
			
			Villager.setLocation(2, 1);
			
			Set<String> unseenWeapons = new HashSet<>();
			for (Card c : board.getDeck()) {
				if (c.getCardType() == CardType.WEAPON && !Villager.getSeenCards().containsKey(c)) {
					unseenWeapons.add(c.getCardName());
				}
			}
			
			Solution s = Villager.createSuggestion();
		
			assertTrue(unseenWeapons.contains(s.getWeapon().getCardName()));
		}
		
		@Test
		public void testMultiplePeopleOneSelected() {
			Villager.getSeenCards().clear();
			Villager.getHand().clear();

			Villager.updateSeen(new Card("Steve", CardType.PERSON), null);
			
			Villager.setLocation(2, 1);
			
			Set<String> unseenPeople = new HashSet<>();
			for (Card c : board.getDeck()) {
				if (c.getCardType() == CardType.PERSON && !Villager.getSeenCards().containsKey(c)) {
					unseenPeople.add(c.getCardName());
				}
			}
			
			Card picked = Villager.createSuggestion().getPerson();
		
			assertTrue(unseenPeople.contains(picked.getCardName()));
		}
		
		@Test
		public void testNoRoomRandomSelect() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);
			
			Set<BoardCell> targets = new HashSet<>();
			targets.add(board.getCell(0, 0));
			targets.add(board.getCell(0, 1));
			targets.add(board.getCell(1, 0));
			
			board.getTargets().clear();
			board.getTargets().addAll(targets);
			
			BoardCell picked = Villager.selectTarget();
			
			assertTrue(targets.contains(picked));
		}
		
		@Test 
		public void testRoomNotSeenSelected() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);
			
			BoardCell roomCenter = board.getCell(1,2);
			Set<BoardCell> targets = new HashSet<>();
			
			targets.add(board.getCell(0, 0));
			targets.add(roomCenter);
			
			board.getTargets().clear();
			board.getTargets().addAll(targets);
			
			BoardCell picked = Villager.selectTarget();
			assertTrue(targets.contains(picked));
		}
		
		@Test
		public void testTargetSelectedRandomly() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);
			
			Set<BoardCell> targets = new HashSet<>();
			targets.add(board.getCell(0, 0));
			targets.add(board.getCell(0, 1));
			targets.add(board.getCell(1, 0));
			
			board.getTargets().clear();
			board.getTargets().addAll(targets);
			
			BoardCell picked = Villager.selectTarget();
			assertTrue(targets.contains(picked));
			

		}
}	
