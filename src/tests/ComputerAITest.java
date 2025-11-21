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
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Solution;
import experiment.TestBoard;

public class ComputerAITest {

	public ComputerAITest() {
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
		public void testRoomMatchesLocation() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5, 5);
				BoardCell walkway = board.getCell(4, 2);
				BoardCell roomCenter = board.getCell(1, 2);
				
				Set<BoardCell> testTargets = new HashSet<>();
				testTargets.add(walkway);
				testTargets.add(roomCenter);
				
				BoardCell testpicked = Villager.setLocation(testTargets);
				assertEquals(roomCenter, testpicked);
			
		}
		
		@Test
		public void testNotSeenWeaponSelected() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);
			
			Card seen = new Card("Bow", CardType.WEAPON);
			
			List <Card> weapons = new ArrayList<>();
			weapons.add(new Card("Bow", CardType.WEAPON));
			weapons.add(new Card("Fist", CardType.WEAPON));
			weapons.add(new Card("Diamond Sword", CardType.WEAPON));
			
			Card picked = Villager.pickWeapon(weapons);
			
			assertNotEquals("Bow", picked.getName);

		}
		
		@Test
		public void testNotSeenPersonSelected() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);
			
			Card seen = new Card ("Steve", CardType.PERSON);
			Villager.updateSeen(seen);
			
			List<Card> person = new ArrayList<>();
			person.add(new Card("Zombie", CardType.PERSON));
			person.add(new Card("Herobrine", CardType.PERSON));
			person.add(new Card("Enderman", CardType.PERSON));
			
			Card picked = Villager.pickPerson(person);
			assertNotEquals("Steve", picked.getName();
			

			
		}
		
		@Test
		public void testMultipleWeaponsOneSelected() {
			
			//https://www.geeksforgeeks.org/java/initializing-a-list-in-java/
		
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);
			
			List<Card> weapons = List.of(new Card("Bow",CardType.WEAPON), new Card("Fist", CardType.WEAPON), new Card("Diamond Sword", CardType.WEAPON));
			 
			//confused on this part now

		}
		
		@Test
		public void testMultiplePeopleOneSelected() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);

			List<Card> person = List.of(new Card("Zombie", CardType.PERSON), new Card("Herobrine", CardType.PERSON), new Card("Enderman", CardType.PERSON))
		}
		
		@Test
		public void testNoRoomRandomSelect() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);
			
		}
		
		@Test 
		public void testRoomNotSeenSelected() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);

		}
		
		@Test
		public void testTargetSelectedRandomly() {
			ComputerPlayer Villager = new ComputerPlayer ("Villager", Color.ORANGE, 5,5);

		}
}	
