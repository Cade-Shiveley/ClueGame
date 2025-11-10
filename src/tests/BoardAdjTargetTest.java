package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesRooms()
	{
		// we want to test a couple of different rooms.
		Set<BoardCell> testList = board.getAdjList(2, 21);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(4, 20)));
		assertTrue(testList.contains(board.getCell(23, 1)));
		
		testList = board.getAdjList(21, 11);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(17, 8)));
		
		testList = board.getAdjList(15, 21);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(15, 19)));
		assertTrue(testList.contains(board.getCell(17, 22)));
	}

	
	// Ensure door locations include their rooms and also additional walkways
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacencyDoor()
	{
		Set<BoardCell> testList = board.getAdjList(4, 3);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(4, 2)));
		assertTrue(testList.contains(board.getCell(5, 3)));
		assertTrue(testList.contains(board.getCell(4, 4)));

		testList = board.getAdjList(17, 15);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(21, 11)));
		assertTrue(testList.contains(board.getCell(17, 16)));
		assertTrue(testList.contains(board.getCell(16, 15)));
		assertTrue(testList.contains(board.getCell(17, 14)));
		
		testList = board.getAdjList(8, 19);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(9, 19)));
		assertTrue(testList.contains(board.getCell(8, 18)));
		assertTrue(testList.contains(board.getCell(7, 19)));
		assertTrue(testList.contains(board.getCell(9, 21)));
	}
	
	// Test a variety of walkway scenarios
	// These tests are Dark Orange on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		Set<BoardCell> testList = board.getAdjList(8, 11);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(8, 10)));
		assertTrue(testList.contains(board.getCell(8, 12)));
		assertTrue(testList.contains(board.getCell(7, 11)));

		// Test near a door but not adjacent
		testList = board.getAdjList(21, 4);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(20, 4)));
		assertTrue(testList.contains(board.getCell(22, 4)));
		assertTrue(testList.contains(board.getCell(21, 5)));

		// Test adjacent to walkways
		testList = board.getAdjList(7, 15);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(6, 15)));
		assertTrue(testList.contains(board.getCell(8, 15)));
		assertTrue(testList.contains(board.getCell(7, 14)));
		assertTrue(testList.contains(board.getCell(7, 16)));

		testList = board.getAdjList(15, 13);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(15, 12)));
		assertTrue(testList.contains(board.getCell(15, 14)));
		assertTrue(testList.contains(board.getCell(16, 13)));
	
	}
	
	
	// Tests out of room center, 1, 3 and 4
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsInSwamp() {
		// test a roll of 1
		board.calcTargets(board.getCell(9, 21), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(6, 22)));
		assertTrue(targets.contains(board.getCell(8, 19)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(9, 21), 3);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(10, 19)));
		assertTrue(targets.contains(board.getCell(8, 17)));	
		assertTrue(targets.contains(board.getCell(4, 22)));
		assertTrue(targets.contains(board.getCell(5, 23)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(9, 21), 4);
		targets= board.getTargets();
		assertEquals(18, targets.size());
		assertTrue(targets.contains(board.getCell(11, 19)));
		assertTrue(targets.contains(board.getCell(7, 17)));	
		assertTrue(targets.contains(board.getCell(4, 21)));
		assertTrue(targets.contains(board.getCell(6, 19)));	
	}
	
	@Test
	public void testTargetsInCave() {
		// test a roll of 1
		board.calcTargets(board.getCell(23, 1), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(2, 21)));
		assertTrue(targets.contains(board.getCell(20, 3)));	
		
		
		// test a roll of 3
		board.calcTargets(board.getCell(23, 1), 3);
		targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(20, 1)));
		assertTrue(targets.contains(board.getCell(21, 4)));	
		assertTrue(targets.contains(board.getCell(2, 21)));
		
		// test a roll of 4
		board.calcTargets(board.getCell(23, 1), 4);
		targets= board.getTargets();
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCell(20, 0)));
		assertTrue(targets.contains(board.getCell(22, 4)));	
		assertTrue(targets.contains(board.getCell(2, 21)));
	}

	// Tests out of room center, 1, 3 and 4
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsAtDoor() {
		// test a roll of 1, at door
		board.calcTargets(board.getCell(3, 6), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(3, 11)));
		assertTrue(targets.contains(board.getCell(2, 6)));	
		assertTrue(targets.contains(board.getCell(4, 6)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(3, 6), 3);
		targets= board.getTargets();
		assertEquals(10, targets.size());
		assertTrue(targets.contains(board.getCell(2, 4)));
		assertTrue(targets.contains(board.getCell(3, 11)));
		assertTrue(targets.contains(board.getCell(5, 5)));	


	
	}

	@Test
	public void testTargetsInWalkway1() {
		// test a roll of 1
		board.calcTargets(board.getCell(0, 5), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(0, 6)));
		assertTrue(targets.contains(board.getCell(1, 5)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(0, 5), 3);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(2, 6)));
		assertTrue(targets.contains(board.getCell(3, 5)));
		assertTrue(targets.contains(board.getCell(2, 4)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(0, 5), 4);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 4)));
		assertTrue(targets.contains(board.getCell(4, 5)));
	}

	@Test
	public void testTargetsInWalkway2() {
		// test a roll of 1
		board.calcTargets(board.getCell(11, 23), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(11, 22)));
		assertTrue(targets.contains(board.getCell(12, 23)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(11, 23), 3);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(11, 20)));
		assertTrue(targets.contains(board.getCell(12,21)));
		
		// test a roll of 4
		board.calcTargets(board.getCell(11, 23), 4);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(11, 19)));
		assertTrue(targets.contains(board.getCell(12, 22)));
		assertTrue(targets.contains(board.getCell(11, 21)));	
	}

	@Test
	// test to make sure occupied locations do not cause problems
	public void testTargetsOccupied() {
		// test a roll of 4 blocked 2 down
		board.getCell(15, 7).setOccupied(true);
		board.calcTargets(board.getCell(13, 7), 4);
		board.getCell(15, 7).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(10, targets.size());
		assertTrue(targets.contains(board.getCell(15, 9)));
		assertTrue(targets.contains(board.getCell(9, 7)));	
		assertFalse( targets.contains( board.getCell(15, 7))) ;
		//assertFalse( targets.contains( board.getCell(17, 7))) ;
	
		// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(12, 2).setOccupied(true);
		board.calcTargets(board.getCell(9, 7), 1);
		board.getCell(12, 2).setOccupied(false);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(12, 2)));	
		assertTrue(targets.contains(board.getCell(9, 8)));	
		
		// check leaving a room with a blocked doorway
		board.getCell(8, 3).setOccupied(true);
		board.calcTargets(board.getCell(12, 2), 1);
		board.getCell(8, 3).setOccupied(false);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(9, 7)));
		assertTrue(targets.contains(board.getCell(14, 7)));	
		assertTrue(targets.contains(board.getCell(15, 3)));

	}
}

