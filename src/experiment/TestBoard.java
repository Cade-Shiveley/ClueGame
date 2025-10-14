package experiment;
import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	//initializes
private int pathlength;
private int startCell;
private int row;
private int column;
//sets targets
Set<TestBoardCell> getTargets;
private int targets;
//assistance from TA Thomas


public void board() {

}

//calculates the cells a player can go to
public void calcTargets(TestBoardCell startCell, int pathlength) {
	
}

//returns current cell 
public TestBoardCell getCell(int row, int column) {
	return new TestBoardCell(0,0);
}

//returns the list of targets that were found in calc
public Set<TestBoardCell> getTargets(){
	return new HashSet<TestBoardCell>();
	
}


}
