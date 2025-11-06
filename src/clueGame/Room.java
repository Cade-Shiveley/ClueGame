package clueGame;
//name:String
//centerCell: BoardCell
//labelCell: BoardCell
public class Room {
	
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private Room secretPassage;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	public void setCenterCell(BoardCell centerCell) {
		this.centerCell = centerCell;
	}
	
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public void setLabelCell(BoardCell labelCell) {
		this.labelCell = labelCell;
	}
	
	public Room getSecretPassage() {
		return secretPassage;
	}
	
	public void setSecretPassage(Room secretPassage) {
		this.secretPassage = secretPassage;
	}
	
	
}