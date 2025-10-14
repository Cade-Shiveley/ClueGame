package experiment;
import java.util.Set;

public class TestBoardCell {
	//initializes items
	private int row;
	private int column;
	private Set<TestBoardCell> adjList;
	private boolean isRoom;
	private boolean isOccupied;
	
	//adds adjacent cells
	public void addAdjacency (TestBoardCell cell) {

	}
	
//returns all adjacent cells
	public Set<TestBoardCell> getAdjList(){
		return null;
	}
	
	//constructor of the board cells
	public TestBoardCell(int row, int column) {
		this.row =row;
		this.column = column;
	}

//returns if is room
	public boolean isRoom() {
		return false;
	}

	//sets whether room
	public void setRoom(boolean isRoom) {
	}

	//checks if occupied
	public boolean isOccupied() {
		return false;
	}

	//sets as occupied
	public void setOccupied(boolean isOccupied) {
	}
	
	//returns row
	public int getRow() {
		return 0;
	}
	
	//returns column
	public int getColumn() {
		return 0;
	}


	
	
	
	
	
}
