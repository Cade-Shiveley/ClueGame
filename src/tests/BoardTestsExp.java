package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import experiment.*;

import java.util.Set;

public class BoardTestsExp {
	private TestBoard board;
	
	@BeforeEach
	public void setUp() {
		board = new TestBoard();
	}
	
	@Test
	public void AdjacencyTopLeft() {
		TestBoardCell cell = board.getCell(0, 0);
		Set<TestBoardCell> testList = cell.getAdjList();
		
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void AdjacencyBottomRight() {
		TestBoardCell cell = board.getCell(3,  3);
		Set<TestBoardCell> testList = cell.getAdjList();
		
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void AdjacencyRightEdge() {
		TestBoardCell cell = board.getCell(1,  3);
		Set<TestBoardCell> testList = cell.getAdjList();
		
		Assert.assertTrue(testList.contains(board.getCell(0, 3)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void AdjacencyLeftEdge() {
		TestBoardCell cell = board.getCell(2,  0);
		Set<TestBoardCell> testList = cell.getAdjList();
		
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(3, 0)));
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void AdjacencyCenter() {
		TestBoardCell cell = board.getCell(2,  2);
		Set<TestBoardCell> testList = cell.getAdjList();
		
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void NormalRoll() {
		TestBoardCell start = board.getCell(0,  0);
		board.calcTargets(start, 3);
		Set<TestBoardCell> targets = board.getTargets();
		
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3,  0)));
		Assert.assertTrue(targets.contains(board.getCell(0,  3)));
	}
	
	@Test
	public void VaryingSteps() {
		TestBoardCell start = board.getCell(1, 1);
		board.calcTargets(start, 2);
		Set<TestBoardCell> roll2 = board.getTargets();
		
		board.calcTargets(start, 4);
		Set<TestBoardCell> roll4 = board.getTargets();
		
		Assert.assertNotEquals(roll2.size(), roll4.size());
	}
	
	@Test
	public void WithRoom() {
		board.getCell(1, 2).setRoom(true);
		TestBoardCell start = board.getCell(0,  2);
		board.calcTargets(start, 2);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
	}
	
	@Test
	public void OccupiedWalkway() {
		board.getCell(1,  0).setOccupied(true);
		TestBoardCell start = board.getCell(0, 0);
		board.calcTargets(start, 2);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertFalse(targets.contains(board.getCell(1, 0)));
	}
}