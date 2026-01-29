package Lab_6.Task_1;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public void move() {
		row++; 
	}
	
	public boolean isConflict(Queen q) {
		if(this.getRow() == q.getRow()) {
			return true;
		}
		else if(this.getColumn() == q.getColumn()) {
			return true;
		}
		else if(Math.abs(this.getRow() - q.getRow()) == Math.abs(this.getColumn() - q.getColumn())){
			return true;
		}
		else {
			return false;
		}
	}
}
