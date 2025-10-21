package clueGame;
//grid: BoardCell[][]
//numRows:int
//numColumns: int
//layoutConfigFIle:string
//setupConfigFile: string
//roomMap: Map<Character,Room>
//theInstance: static Board

//+initialize():void
//+loadSetupConfig():void
//+loadLayoutConfig(): void
public class Board {

	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	



/*
* variable and methods used for singleton pattern
*/
private static Board theInstance = new Board();
// constructor is private to ensure only one can be created
private Board() {
       super() ;
}
// this method returns the only Board
public static Board getInstance() {
       return theInstance;
}
/*
 * initialize the board (since we are using singleton pattern)
 */
public void initialize()
{
}

public void loadSetupConfig() {
	
}

public void loadLayoutConfig() {
	
	
}


public BoardCell[][] getGrid() {
	return grid;
}
public void setGrid(BoardCell[][] grid) {
	this.grid = grid;
}
public int getNumRows() {
	return numRows;
}
public void setNumRows(int numRows) {
	this.numRows = numRows;
}
public int getNumColumns() {
	return numColumns;
}
public void setNumColumns(int numColumns) {
	this.numColumns = numColumns;
}
public String getLayoutConfigFile() {
	return layoutConfigFile;
}
public void setLayoutConfigFile(String layoutConfigFile) {
	this.layoutConfigFile = layoutConfigFile;
}
public String getSetupConfigFile() {
	return setupConfigFile;
}
public void setSetupConfigFile(String setupConfigFile) {
	this.setupConfigFile = setupConfigFile;
}
public Map<Character, Room> getRoomMap() {
	return roomMap;
}
public void setRoomMap(Map<Character, Room> roomMap) {
	this.roomMap = roomMap;
}
public static Board getTheInstance() {
	return theInstance;
}
public static void setTheInstance(Board theInstance) {
	Board.theInstance = theInstance;
}



}