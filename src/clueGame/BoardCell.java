package clueGame;
import java.util.Set;
import java.awt.Color;
import java.awt.Graphics;
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
	private boolean isRoom;
	private boolean isOccupied;
	private boolean isDoorway;
	

	
	
	
	public BoardCell() {
		super();
		adjList = new HashSet<>();
		doorDirection = DoorDirection.NONE;
		roomLabel = false;
		roomCenter = false;
		secretPassage = ' ';
		
	}
	
	public void draw(Graphics g, int x, int y, int cellSize) {
		if (isWalkway() || isDoorway) {
			g.setColor(new Color(222, 204, 155));
			g.fillRect(x,  y,  cellSize, cellSize);
			
			g.setColor(Color.BLACK);
			g.drawRect(x, y, cellSize, cellSize);
		}
		else if (isRoom) {
			g.setColor(Color.GRAY);
			g.fillRect(x,  y,  cellSize, cellSize);
		}
		else {
			g.setColor(Color.BLACK);
			g.fillRect(x,  y,  cellSize, cellSize);
		}
		
		if (isDoorway) {
			g.setColor(Color.GREEN);
			int thickness = cellSize / 10;
			
			switch (doorDirection) {
				case DOWN:
					g.fillRect(x, y + cellSize - thickness, cellSize, thickness);
					break;
				case UP:
					g.fillRect(x, y, cellSize, thickness);
					break;
				case RIGHT:
					g.fillRect(x + cellSize - thickness, y, thickness, cellSize);
					break;
				case LEFT:
					g.fillRect(x, y, thickness, cellSize);
					break;
				default:
					break;
			}
		}
	}

	public void addAdj(BoardCell adj) {
		adjList.add(adj);

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


	public boolean isDoorway() {
		return isDoorway;
	}


	public void setDoorway(boolean isDoorway) {
		this.isDoorway = isDoorway;
	}


	public boolean isWalkway() {
		return initial == 'W';
	}
	
	

}