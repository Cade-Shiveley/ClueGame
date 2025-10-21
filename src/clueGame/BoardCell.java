package clueGame;
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

}
