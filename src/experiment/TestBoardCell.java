package experiment;

public class TestBoardCell {
	private int row;
	private int column;
	private Set<TestBoardCell> adjList;
	private boolean isRoom;
	private boolean isOccupied;
	
	public void addAdjacency (TestBoardCell cell) {
		adjList.add(cell);
	}
	

	public Set<TestBoardCell> getAdjList(){
		return adjList;
	}
	
	public TestBoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}


	public boolean isRoom() {
		return isRoom;
	}

	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}


	
	
	
	
	
}
