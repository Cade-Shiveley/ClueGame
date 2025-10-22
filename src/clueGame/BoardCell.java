package clueGame;
import java.util.Set;
import java.util.HashSet;

//row: int
//col: int
//initial :char
//doorDirection: Door Direction -> door direction
//roomLabel:bool
//roomCenter: bool
//secretPassage: char
//adjList: Set<BoardCell>

//addAdj (adj:BoardCell): void
public class BoardCell {
	
	private int row;
	private int col;
	private DoorDirection doorDirection;
	private char initial;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private Set<BoardCell> adjList;
	
	
	public void addAdj(BoardCell adj) {
		
		
		
	}


	public int getRow() {
		return row;
		
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public DoorDirection getDoorDirection() {
		return doorDirection;
	}


	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}


	public char getInitial() {
		return initial;
	}


	public void setInitial(char initial) {
		this.initial = initial;
	}


	public boolean isRoomLabel() {
		return roomLabel;
	}


	public void setRoomLabel(boolean roomLabel) {
		this.roomLabel = roomLabel;
	}


	public boolean isRoomCenter() {
		return roomCenter;
	}


	public void setRoomCenter(boolean roomCenter) {
		this.roomCenter = roomCenter;
	}


	public char getSecretPassage() {
		return secretPassage;
	}


	public void setSecretPassage(char secretPassage) {
		this.secretPassage = secretPassage;
	}


	public Set<BoardCell> getAdjList() {
		return adjList;
	}


	public void setAdjList(Set<BoardCell> adjList) {
		this.adjList = adjList;
	}
	
	

}
