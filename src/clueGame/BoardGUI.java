package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardGUI extends JPanel{
	private Board board;
	private int cellSize;
	
	public BoardGUI(Board board) {
		this.board = board;
		addMouseListener(new MouseClickerTargets(board, this));
		
	}

	public int getCellSize() {
		return cellSize;
	}
	

	
	public void promptSuggestion(BoardCell roomcell) {
		Room room = board.getRoom(roomcell);
		if(room == null) {
			return;
		}
		//System.out.println(current.getName()+"make suggestion"+room.getName());
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int boardW = getWidth();
		int boardH = getHeight();
		int numRows = board.getNumRows();
		int numCols = board.getNumColumns();
		
		cellSize = Math.min(boardW/numCols, boardH/numRows);
		
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				BoardCell cell = board.getCell(r, c);
				int x = c * cellSize;
				int y = r * cellSize;
				
				cell.draw(g, x, y, cellSize);
			}
		}
		
		for(int r = 0; r < numRows; r++) {
			for(int c=0; c <numCols; c++) {
				BoardCell cell = board.getCell(r, c);
				if(cell.isRoomLabel()) {
					int x = c * cellSize;
					int y = r * cellSize;
					
					String roomName = board.getRoom(cell.getInitial()).getName();
					g.setColor(Color.BLACK);
					g.drawString(roomName, x,y);
					
				}
			}
		}
		
		for (Player p : board.getPlayers()) {
			BoardCell cell = p.getLocation();
			int x = cell.getCol() * cellSize;
			int y = cell.getRow() * cellSize;
			
			g.setColor(p.getColor());
			
			int diameter = cellSize - 8;
			g.fillOval(x + 4,  y + 4,  diameter,  diameter);
			
			g.setColor(Color.BLACK);
			g.drawOval(x + 4, y + 4, diameter, diameter);
		}
	}
}
