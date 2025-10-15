package experiment;
import java.util.Set;
import java.util.HashSet;

//assistance from TA Thomas

public class TestBoardCell {
	//initializes items
	private int row;
	private int column;
	private Set<TestBoardCell> adjList;
	private boolean isRoom;
	private boolean isOccupied;
	
	//adds adjacent cells
	public void addAdjacency (TestBoardCell cell) {
		adjList.add(cell);
	}


//returns all adjacent cells
	public Set<TestBoardCell> getAdjList(){
		
	}
	

	//constructor of the board cells
	public TestBoardCell(int row, int column) {
		this.row =row;
		this.column = column;
		this.isRoom = false;
		this.isOccupied = false;
		adjList = new HashSet<TestBoardCell>();
	}

//returns if is room
	public boolean isRoom() {
		return isRoom;
	}

	//sets whether room
	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}

	//checks if occupied
	public boolean isOccupied() {
		return isOccupied;
	}

	//sets as occupied
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	//returns row
	public int getRow() {
		return row;
	}
	
	//returns column
	public int getColumn() {
		return column;
	}


	
	
	
	
	
}
