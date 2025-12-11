package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseClickerTargets implements MouseListener{
	private Board board;
	private BoardGUI gui;
	
	public MouseClickerTargets(Board board, BoardGUI gui) {
		this.board = board;
		this.gui = gui;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Player current = board.getCurrentPlayer();
		if (current == null || !current.isHuman() || board.humanTurnFinished()) {
			return;
		}
		int cellSize = gui.getCellSize();
		int col = e.getX()/cellSize;
		int row = e.getY()/cellSize;
		
		if(row<0 || row>= board.getNumRows()||col<0||col >=board.getNumColumns()) {
			return;
		}
		
		BoardCell clickedCell = board.getCell(row, col);

		
		if(board.getTargets().contains(clickedCell)) {
			current.setLocation(clickedCell);
			
			board.clearTargets();
			board.humanTurnFinished();
			
			if(clickedCell.isRoomCenter()) {
				gui.promptSuggestion(clickedCell);
			}
			
			gui.repaint();
			
		}
}

}
