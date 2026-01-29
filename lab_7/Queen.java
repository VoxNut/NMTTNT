package Lab_7;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
		if (row < Node.N - 1) {
			row++;
		} else
			this.row = 0;
	}

	public boolean isConflict(Queen q) {
		if (row == q.getRow() || column == q.getColumn())
			return true;
		else if (Math.abs(column - q.getColumn()) == Math.abs(row - q.getRow()))
			return true;
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
