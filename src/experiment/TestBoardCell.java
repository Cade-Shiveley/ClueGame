package experiment;
import java.util.Set;

public class TestBoardCell {
	private int row;
	private int column;
	private Set<TestBoardCell> adjList;
	private boolean isRoom;
	private boolean isOccupied;
	
	public void addAdjacency (TestBoardCell cell) {

	}
	

	public Set<TestBoardCell> getAdjList(){
		return null;
	}
	
	public TestBoardCell(int row, int column) {
		this.row =row;
		this.column = column;
	}


	public boolean isRoom() {
		return false;
	}

	public void setRoom(boolean isRoom) {
	}

	public boolean isOccupied() {
		return false;
	}

	public void setOccupied(boolean isOccupied) {
	}
	
	public int getRow() {
		return 0;
	}
	
	public int getColumn() {
		return 0;
	}


	
	
	
	
	
}
