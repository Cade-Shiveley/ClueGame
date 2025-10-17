package experiment;
import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	//initializes
//sets targets
Set<TestBoardCell> targets;
private TestBoardCell[][] grid;
private Set<TestBoardCell> visited;
final static int COLS =4;
final static int ROWS = 4;

//assistance from TA Thomas


public void board() {

}

private void findAllTargets(TestBoardCell thisCell, int numSteps) {
	for(TestBoardCell adjCell:  adjList(thisCell)) {
		//check if visited skip rest
		if (visited.contains(thisCell)==true){
			return;
			
		}
		visited.add(thisCell);
		
	
		if (numSteps == 1) {
			targets.add(thisCell);
		}else {
			findAllTargets(adjCell, numSteps -1);
		}
		//stack overflow for syntax how to remove stuff from setlist
		visited.remove(thisCell);
	}
	
	
}

//calculates the cells a player can go to
public void calcTargets(TestBoardCell startCell, int pathlength) {
	//call findAllTargets in this - done
	//make sure visisted list is empty and targets
	//add start cell to visited list
	
	visited.add(startCell);
	findAllTargets(startCell, pathlength);
	visited.remove();
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
