package clueGame;
import java.util.Set;

import experiment.TestBoardCell;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;

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

	private BoardCell[][] grid; //
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private String setupConfigFile;
	//make final?
	private Map<Character, Room> roomMap;
	private Set<BoardCell> visited;
	Set<BoardCell> targets; 


	



	/*
	* variable and methods used for singleton pattern
	*/
	private static Board theInstance = new Board();
	
	// constructor is private to ensure only one can be created
	private Board() {
	       super() ;
	       targets = new HashSet<>();
	       visited = new HashSet<>();
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
	
	public void calcTargets(BoardCell startCell, int pathlength) {
		targets.clear();
		visited.clear();
		visited.add(startCell);
		findAllTargets(startCell,pathlength);
	}
	
	private void findAllTargets(BoardCell startCell, int numSteps) {
		for(BoardCell adjCell:  startCell.getAdjList()) {
			//check if visited skip rest
			if (visited.contains(adjCell) || adjCell.isOccupied()){
				continue;
			}
			
			visited.add(adjCell);
				
			
		
			if (adjCell.getInitial() == 'W' || adjCell.getInitial() == 'X') {
				adjCell.setRoom(false);
			} else {
				adjCell.setRoom(true);
			}
			//stack overflow for syntax how to remove stuff from setlist
			visited.remove(adjCell);
		}
		
		
	}

	
	public void calcAdjacencies() {
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numColumns; c++) {
				BoardCell cell = grid[r][c];
				
				if (r > 0) cell.addAdj(grid[r-1][c]);
				if (r < numRows-1) cell.addAdj(grid[r+1][c]);
				if (c > 0) cell.addAdj(grid[r][c-1]);
				if (c < numColumns-1) cell.addAdj(grid[r][c+1]);
				
			
			}
		}
	}
	
	public void helpingCalc(BoardCell from,  int row, int col, DoorDirection dir, Set<BoardCell> adjList) {
		if(from.isWalkway() && toCell.isWalkway) {
			adjLIst.add(toCell);
		}
		
	}
	
	public void setConfigFiles(String layoutConfigFile, String setupConfigFile) {
		this.layoutConfigFile = layoutConfigFile;
		this.setupConfigFile = setupConfigFile;
	}
	
	public void loadSetupConfig() throws Exception {
		//throw badconfig setup;
		roomMap = new HashMap<>();
		try {
			Scanner scanner = new Scanner(new FileReader(setupConfigFile));		
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (line.isEmpty() || line.startsWith("//")) {
					continue;
				}
				
				String parts[] = line.split(",");
				if (parts.length != 3) {
					scanner.close();
					throw new BadConfigFormatException("Invalid line in setup file");
				}
				
				String type = parts[0].trim();
				String name = parts[1].trim();
				char initial = parts[2].trim().charAt(0);
				
				if (type.equalsIgnoreCase("Room") || type.equalsIgnoreCase("Space") || type.equalsIgnoreCase("Walkway")) {
					Room room = new Room();
					room.setName(name);
					roomMap.put(initial, room);
				}
			}
		} catch (Exception e) {
			throw new BadConfigFormatException();
		}
	}

	public void loadLayoutConfig() throws BadConfigFormatException {
		//throw badconfig setup
		try {

			Scanner scanner = new Scanner(new FileReader(layoutConfigFile));
			List<String[]> rows = new ArrayList<>();
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] cols = line.split("\\s*,\\s*");
				rows.add(cols);
			}
						
			numRows = rows.size();
			numColumns = rows.get(0).length;
			grid = new BoardCell[numRows][numColumns];
				
			for (int r = 0; r < numRows; r++) {
				
				String[] cols = rows.get(r);
				if (cols.length != numColumns) {
					scanner.close();
					throw new BadConfigFormatException("Column number not consistent in layout file");
				}
				
				for (int c = 0; c < numColumns; c++) {
					String token = cols[c];
					
					BoardCell cell = new BoardCell();
					cell.setRow(r);
					cell.setCol(c);
						
					char initial = token.charAt(0);
					cell.setInitial(initial);
					cell.setRoom((initial != 'W' && initial != 'X'));
					
					cell.setDoorDirection(DoorDirection.NONE);
					cell.setDoorway(false);
					
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
							case '#':
								cell.setRoomLabel(true);
								Room labelRoom = getRoom(initial);
								if (labelRoom != null) {
									labelRoom.setLabelCell(cell);
								}
								cell.setDoorDirection(DoorDirection.NONE);
								break;
							case '*':
								cell.setRoomCenter(true);
								Room centerRoom = getRoom(initial);
								if (centerRoom != null) {
									centerRoom.setCenterCell(cell);
								}
								cell.setDoorDirection(DoorDirection.NONE);
								break;
							default:
								if (Character.isLetter(dir)) {
									cell.setSecretPassage(dir);
								}
								break;
						}
						//set so it's not none if dir != doordirection.1
						if (dir == '<' || dir == '>' || dir == 'v' || dir == '^') {
							cell.setDoorway(true);
						}
					}
				
					grid[r][c] = cell;
				}
				
			}
			
			scanner.close();

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
			return null;
		}
		
		return getRoom(cell.getInitial());
	}
	
	public Room getRoom(char initial) {
		return roomMap.get(initial);
	}
}