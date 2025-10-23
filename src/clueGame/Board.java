package clueGame;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileReader;

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

//https://www.geeksforgeeks.org/java/handle-an-ioexception-in-java/
//


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
	public void initialize() {
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} catch (Exception e) {
			
		}
	}
	
	public void setConfigFiles(String layoutConfigFile, String setupConfigFile) {
		this.layoutConfigFile = layoutConfigFile;
		this.setupConfigFile = setupConfigFile;
	}
	
	public void loadSetupConfig() throws Exception {
		//throw badconfig setup;
	
		roomMap = new HashMap<>();
		List<String> lines = Files.readAllLines(Paths.get(setupConfigFile));
		
		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty() || line.startsWith("//")) continue;
			
			String[] parts  = line.split(",");
			if (parts.length < 3) continue;
			
			String type = parts[0].trim();
			String name = parts[1].trim();
			char initial = parts[2].trim().charAt(0);
			
			if (type.equalsIgnoreCase("Room") || type.equalsIgnoreCase("Space")) {
				Room room = new Room();
				room.setName(name);
				roomMap.put(initial, room);
			}
		}
	}
		
		public void loadLayoutConfig() {
			//throw badconfig setup
			try {
			Scanner scanner = new Scanner(new FileReader(layoutConfigFile));

			List<String[]> rows = new ArrayList<>();
			while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(",");
			rows.add(tokens);
			}
			numRows = rows.size();
			numColumns = rows.get(0).length;
			grid = new BoardCell[numRows][numColumns];
			for (int r = 0; r < numRows; r++) {
			String[] cols = rows.get(r);
			for (int c = 0; c < numColumns; c++) {
			String token = cols[c];
			BoardCell cell = new BoardCell();
			cell.setRow(r);
			cell.setCol(c);
			char init = token.charAt(0);
			cell.setInitial(init);
			if (token.length() > 1) {
			char dir = token.charAt(1);
			switch (dir) {
			case '<':
			cell.setDoorDirection(DoorDirection.LEFT);
			break;
			case '>':
			cell.setDoorDirection(DoorDirection.RIGHT);
			break;
			case '^':
			cell.setDoorDirection(DoorDirection.UP);
			break;
			case 'v':
			cell.setDoorDirection(DoorDirection.DOWN);
			break;
			default:
			cell.setDoorDirection(DoorDirection.NONE);
			break;
			}
			} else {
			cell.setDoorDirection(DoorDirection.NONE);
			}
			grid[r][c] = cell;
			}
			}

		} catch (Exception e) {
			throw new BadConfigFormatException();
			
	}
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
	
	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public Room getRoom(BoardCell cell) {
		if (cell == null) {
			Room dummy = new Room();
			dummy.setName("Unknown");
			return dummy;
		}
		return getRoom(cell.getInitial());
	}
	
	public Room getRoom(char initial) {
		if (roomMap == null) {
			roomMap = new HashMap<>();
		}
		
		Room room = roomMap.get(initial);
		if (room == null) {
			room = new Room();
			room.setName("Unknown");
			roomMap.put(initial, room);
		}
		return room;
	}
}



