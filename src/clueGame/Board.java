package clueGame;

import java.util.Set;
import experiment.TestBoardCell;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Color;
import java.io.FileReader;
import clueGame.Room;

// grid: BoardCell[][]
// numRows:int
// numColumns: int
// layoutConfigFIle:string
// setupConfigFile: string
// roomMap: Map<Character,Room>
// the: static Board

// +initialize():void
// +loadSetupConfig():void
// +loadLayoutConfig(): void

// https://www.geeksforgeeks.org/java/handle-an-ioexception-in-java/

public class Board {

    private BoardCell[][] grid; //
    private int numRows;
    private int numColumns;
    private String layoutConfigFile;
    private String setupConfigFile;
    // make final?
    private Map<Character, Room> roomMap;
    private Set<BoardCell> visitedCells;
    Set<BoardCell> targets;
    private static final char WALKWAY = 'W';
    private static final char UNUSED = 'X';
    private List<Card> deck = new ArrayList<>();
    private Solution answer;
    private List<Player> players = new ArrayList<>();
    
    private int currentPlayerIndex = 0;
    private boolean humanFinishedTurn = true;
    private GUI gui = null;
    private Random rand = new Random();
    private BoardGUI boardGUI;

    /*
     * variable and methods used for singleton pattern
     */
    private static Board theInstance = new Board();

    // constructor is private to ensure only one can be created
    private Board() {
        super();
        targets = new HashSet<>();
        visitedCells = new HashSet<>();
    }

    // this method returns the only Board
    public static Board instance() {
        return theInstance;
    }

    /*
     * initialize the board (since we are using singleton pattern)
     */
    public void initialize() {
        try {
            loadSetupConfig();
            loadLayoutConfig();
            
            for (Player p : players) {
            	p.setLocation(p.getStartRow(), p.getStartCol());
            }
            
            calcAdjacencies();
            deal();
        } catch (Exception e) {
        	System.out.println("Error with initialization.");
        }
    }

    public void calcTargets(BoardCell startCell, int pathlength) {
        targets.clear();
        visitedCells.clear();
        visitedCells.add(startCell);
        findAllTargets(startCell, pathlength);
    }

    private void findAllTargets(BoardCell startCell, int numSteps) {
    	//assistance from TA Brandon
        for (BoardCell adjCell : startCell.getAdjList()) {
            // check if visited skip rest
        	
        	//check if vistied, occupied, and when you're in a room turn over
            if (visitedCells.contains(adjCell) || adjCell.isOccupied() && !adjCell.isRoom()) {
                continue;
            }
            


            visitedCells.add(adjCell);
            
            if (numSteps == 1 || adjCell.isRoom()) {
            	targets.add(adjCell);
            } else {
            	findAllTargets(adjCell,numSteps-1);
            }

            // stack overflow for syntax how to remove stuff from setlist
            visitedCells.remove(adjCell);
        }
    }

    //calculates and assigns all valid movement adjacencies for each cell
    public void calcAdjacencies() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numColumns; c++) {
                BoardCell cell = grid[r][c];
                Set<BoardCell> adjList = cell.getAdjList();

                if (r > 0) {
                    if (grid[r - 1][c].isWalkway() || grid[r - 1][c].isDoorway()) {
                        cell.addAdj(grid[r - 1][c]);
                    }
                }

                if (r < numRows - 1) {
                    if (grid[r + 1][c].isWalkway() || grid[r + 1][c].isDoorway()) {
                        cell.addAdj(grid[r + 1][c]);
                    }
                }

                if (c > 0) {
                    if (grid[r][c - 1].isWalkway() || grid[r][c - 1].isDoorway()) {
                        cell.addAdj(grid[r][c - 1]);
                    }
                }

                if (c < numColumns - 1) {
                    if (grid[r][c + 1].isWalkway() || grid[r][c + 1].isDoorway()) {
                        cell.addAdj(grid[r][c + 1]);
                    }
                }

                if (cell.isDoorway()) {
                    BoardCell roomCell = null;
                    Room room = null;

                    switch (cell.getDoorDirection()) {
                        case UP:
                            roomCell = grid[r - 1][c];
                            room = getRoom(roomCell.getInitial());
                            cell.addAdj(room.getCenterCell());
                            room.getCenterCell().addAdj(cell);
                            break;
                        case DOWN:
                            roomCell = grid[r + 1][c];
                            room = getRoom(roomCell.getInitial());
                            cell.addAdj(room.getCenterCell());
                            room.getCenterCell().addAdj(cell);
                            break;
                        case LEFT:
                            roomCell = grid[r][c - 1];
                            room = getRoom(roomCell.getInitial());
                            cell.addAdj(room.getCenterCell());
                            room.getCenterCell().addAdj(cell);

                           break;
                        case RIGHT:
                            roomCell = grid[r][c + 1];
                            room = getRoom(roomCell.getInitial());
                            cell.addAdj(room.getCenterCell());
                            room.getCenterCell().addAdj(cell);
                            break;
                        default:
                            break;
                    }
                }
                
                    if (cell.isRoomCenter()) {
                        Room room = getRoom(cell);
                        Room destRoom = room.getSecretPassage();
                        
                        if (destRoom != null && destRoom.getCenterCell() != null) {
                        	cell.addAdj(destRoom.getCenterCell());
                        }
                    }

            }
        }
    }

    //this is a helper function in order to assist with the calculating targets
    public void helpingCalc(BoardCell from, BoardCell toCell, int row, int col, DoorDirection dir, Set<BoardCell> adjList) {
        if (from.isWalkway() && toCell.isWalkway()) {
            adjList.add(toCell);
        }
    }
    
    public void deal() {
    	List<Card> playerCards = new ArrayList<>();
    	List <Card> roomCards = new ArrayList<>();
    	List<Card> weaponCards = new ArrayList<>();
    	
    	for (Card c : deck) {
    		switch (c.getCardType()) {
    			case PERSON:
    				playerCards.add(c);
    				break;
    			case ROOM:
    				roomCards.add(c);
    				break;
    			case WEAPON:
    				weaponCards.add(c);
    				break;
    		}
    	}
    	
    	Random rand = new Random();
    	Card person = playerCards.get(rand.nextInt(playerCards.size()));
    	Card room = roomCards.get(rand.nextInt(roomCards.size()));
    	Card weapon = weaponCards.get(rand.nextInt(weaponCards.size()));
    	
    	answer = new Solution(room, person, weapon);
    	
    	List<Card> remaining = new ArrayList<>(deck);
    	remaining.remove(person);
    	remaining.remove(room);
    	remaining.remove(weapon);
    	
    	Collections.shuffle(remaining, rand);
    	
    	int pIdx = 0;
    	for (Card c : remaining) {
    		players.get(pIdx).updateHand(c);
    		pIdx = (pIdx + 1) % players.size();
    	}
    }

    public void setConfigFiles(String layoutConfigFile, String setupConfigFile) {
        this.layoutConfigFile = layoutConfigFile;
        this.setupConfigFile = setupConfigFile;
    }

    public void loadSetupConfig() throws Exception {
        roomMap = new HashMap<>();
        players = new ArrayList<>();
        deck = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new FileReader(setupConfigFile));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                String parts[] = line.split(",\\s*");
                for (int i = 0; i < parts.length; i++) {
                	parts[i] = parts[i].trim();
                }
                
                String type = parts[0];
                
                if (type.equalsIgnoreCase("Space")) {
                	char initial = parts[2].charAt(0);
                	
                	if (initial == 'X') {
                		Room unused = new Room();
                		unused.setName(parts[1]);
                		roomMap.put(initial, unused);
                	}
                	
                	continue;
                }

                if (type.equalsIgnoreCase("Room")) {
                	String name = parts[1];
                    char initial = parts[2].charAt(0);
                    
                	Room room = new Room();
                    room.setName(name);
                    roomMap.put(initial, room);
                    
                    deck.add(new Card(name, CardType.ROOM));   
                    
                    continue;
                }
                
                if (type.equalsIgnoreCase("Player")) {
                	String playerName = parts[1];
                	String colorName = parts[2];
                	String playerType = parts[3];
                	int row = Integer.parseInt(parts[4]);
                	int col = Integer.parseInt(parts[5]);
                
                
	                Color color = convertColor(colorName);
	                
	                Player p;
	                if (playerType.equalsIgnoreCase("Human")) {
	                	p = new HumanPlayer(playerName, color, row, col);
	                } else {
	                	p = new ComputerPlayer(playerName, color, row, col);
	                }
	                
	                players.add(p);
	                
	                deck.add(new Card(playerName, CardType.PERSON));
	                
	                continue;
                }
                
                if (type.equalsIgnoreCase("Weapon")) {
                	String name = parts[1];
                	deck.add(new Card(name, CardType.WEAPON));
                	continue;
                }
               
            }
            
            scanner.close();
            
        } catch (Exception e) {
            throw new BadConfigFormatException("Error loading setup configuration");
        }
    }
    
    private Color convertColor(String name) {
    	switch (name.toLowerCase()) {
    		case "blue": return Color.BLUE;
    		case "black": return Color.BLACK;
    		case "orange": return Color.ORANGE;
    		case "magenta": return Color.MAGENTA;
    		case "green": return Color.GREEN;
    		case "yellow": return Color.YELLOW;
    		default: return Color.GRAY;
    	}
    }
    
    public boolean checkAccusation(Solution accusation) {
		return accusation.equals(answer);
    	
    }
    
    
    public Card handleSuggestion(Player accusing, Solution solution) {
		int startIndex = players.indexOf(accusing);
		
		for (int i = 1; i < players.size(); i++) {
			Player current = players.get((startIndex + i) % players.size());
			Card counter = current.disproveSuggestion(solution);
			
			if (counter != null) {
				return counter;
			}
		}
		
		return null;
    }

    public void loadLayoutConfig() throws BadConfigFormatException {
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
                    cell.setRoom((initial != WALKWAY && initial != UNUSED));
                    cell.setDoorDirection(DoorDirection.NONE);
                    cell.setDoorway(false);
                    cell.setSecretPassage(' ');

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
                                	char destInitial = token.charAt(1);
                                	Room currentRoom = getRoom(initial);
                                	Room destRoom = getRoom(destInitial);
                                	if (currentRoom != null && destRoom != null) {
                                		currentRoom.setSecretPassage(destRoom);
                                	}
                                }
                                break;
                        }

                        if (isDoorDirection(dir)) {
                            cell.setDoorway(true);
                        }
                    }

                    grid[r][c] = cell;
                }
            }

            scanner.close();

        } catch (Exception e) {
            throw new BadConfigFormatException("Exception with loading the layout.");
        }
    }
    /*
    Check for board clicked on
    Get current Player
    Get cell at clicked position
    Check if current player is human
    If no do nothing
    If yes check if valid target is clicked on
    If target not valid throw error
    If target is valid move player to target
    If position is room center player makes suggestion
    Handle suggestion
    Update the results display
    */
    public void nextPlayer() {
    	Player current = getCurrentPlayer();
    	if (current.isHuman() && !humanFinishedTurn) {
    		if(gui != null) {
    			gui.showErrorMessage("You must finish your turn.");
    		}
    		return;
    	}
    	
    	currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    	Player next = players.get(currentPlayerIndex);
    	if(gui != null) {
    		gui.setCurrentPlayer(next);
    	}
    	
    	int roll = rollDie();
    	if(gui != null) {
    		gui.setDieRoll(roll);
    	}
    	
    	calcTargets(next.getLocation(), roll);
    	
    	if (next.isHuman()) {
    		highlightTargets();
    		humanFinishedTurn = false;
    		return;
    	}
    	
    	BoardCell target = next.selectTarget(getTargets());
    	next.setLocation(target);
    	
    	if (target.isRoomCenter()) {
    		Solution suggestion = ((ComputerPlayer) next).createSuggestion();
    		Card shown = handleSuggestion(next, suggestion);
    		
    		if (gui != null) {
    			gui.setGuess(suggestion.toString());
    			gui.setGuessResult(shown.getCardName());
    		}
    	}
    	
    	humanFinishedTurn = true;
    	
    	if(boardGUI != null) {
    		boardGUI.repaint();
    	}
    }
    
    public int rollDie() {
    	return (int)(Math.random() * 6) + 1;
    }
    
    public void highlightTargets() {
    	for (BoardCell cell : targets) {
    		cell.setHighlighted(true);
    	}
    	
    	
    	if(boardGUI != null) { 
    		boardGUI.repaint();
    	}
    }
    
    public void clearTargets() {
    	for (BoardCell cell : targets) {
    		cell.setHighlighted(false);
    	}
    	
    	targets.clear();
    	boardGUI.repaint();
    }
    
    public void setGUI(GUI gui) {
    	this.gui = gui;
    	if (!players.isEmpty() && gui !=null) {
    		gui.setCurrentPlayer(players.get(currentPlayerIndex));
    	}
    }
    

    
    public void setBoardGUI(BoardGUI boardGUI) {
    	this.boardGUI = boardGUI;
    }
    
    public int getCurrentPlayerIndex() {
    	return currentPlayerIndex;
    }
    
    public Player getCurrentPlayer() {
    	if (players.isEmpty()) {
    		return null;
    	}
    	
    	return players.get(currentPlayerIndex);
    }
    
    public boolean humanTurnFinished() {
    	return humanFinishedTurn;
    }
    
    public void setHumanTurnFinished(boolean finished) {
    	this.humanFinishedTurn = finished;
    }
    
    private boolean isDoorDirection(char dir) {
    	return dir == '<' || dir == '>' || dir == 'v' || dir == '^';
    	
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

    public Set<BoardCell> getAdjList(int row, int col) {
        return grid[row][col].getAdjList();
    }

    public Set<BoardCell> getTargets() {
        return targets;
    }
    
    public List<Card> getDeck() {
    	return deck;
    }
    
    public List<Player> getPlayers() {
    	return players;
    }
    
    public Solution getSolution() {
    	return answer;
    }
}