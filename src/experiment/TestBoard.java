package experiment;
import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	//initializes
	//sets targets
	Set<TestBoardCell> targets;
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;
	
	//assistance from TA Thomas
	
	
	public TestBoard() {
		grid = new TestBoardCell[ROWS][COLS];
		targets = new HashSet<>();
		visited = new HashSet<>();
		
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				grid[r][c] = new TestBoardCell(r, c);
			}
		}
		
		calcAdjacencies();
		
	}
	
	private void findAllTargets(TestBoardCell thisCell, int numSteps) {
		for(TestBoardCell adjCell:  thisCell.getAdjList()) {
			//check if visited skip rest
			if (visited.contains(adjCell) || adjCell.isOccupied()){
				continue;
			}
			
			visited.add(adjCell);
			
		
			if (numSteps == 1 || adjCell.isRoom()) {
				targets.add(adjCell);
			} else {
				findAllTargets(adjCell, numSteps-1);
			}
			//stack overflow for syntax how to remove stuff from setlist
			visited.remove(adjCell);
		}
		
		
	}
	
	public void calcAdjacencies() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				TestBoardCell cell = grid[r][c];
				
				if (r > 0) cell.addAdjacency(grid[r-1][c]);
				if (r < ROWS-1) cell.addAdjacency(grid[r+1][c]);
				if (c > 0) cell.addAdjacency(grid[r][c-1]);
				if (c < COLS-1) cell.addAdjacency(grid[r][c+1]);
				
			}
		}
	}
	
	//calculates the cells a player can go to
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		//call findAllTargets in this - done
		//make sure visited list is empty and targets
		//add start cell to visited list
		
		targets = new HashSet<>();
		visited = new HashSet<>();
		findAllTargets(startCell, pathlength);
	}
	
	//returns current cell 
	public TestBoardCell getCell(int row, int column) {
		return grid[row][column];
	}
	
	//returns the list of targets that were found in calc
	public Set<TestBoardCell> targets(){
		return targets;
	}

	
}
