package Lab_6;

/**
 * Represents the state of the 8-Queens problem Each state is represented by
 * positions of queens on the board
 */
public class Queen {

    // Position of queens: board[i] = j means queen in column i is at row j
    private int[] board;
    private int size;

    public Queen(int size) {
        this.size = size;
        this.board = new int[size];
        // Initialize with random positions or empty
        initializeRandom();
    }

    public Queen(int[] board) {
        this.size = board.length;
        this.board = board.clone();
    }

    // Initialize board with random queen positions (one per column)
    private void initializeRandom() {
        for (int i = 0; i < size; i++) {
            board[i] = (int) (Math.random() * size);
        }
    }

    // Initialize board with specific positions
    public void setPosition(int col, int row) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            board[col] = row;
        }
    }

    public int getPosition(int col) {
        return board[col];
    }

    public int[] getBoard() {
        return board.clone();
    }

    public int getSize() {
        return size;
    }

    // Calculate number of conflicts (attacking pairs)
    public int getConflicts() {
        int conflicts = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                // Check if queens at column i and j attack each other
                if (board[i] == board[j]) { // Same row
                    conflicts++;
                }
                // Check diagonal
                if (Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }

    // Create a copy of this state
    public Queen copy() {
        return new Queen(this.board);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[col] == row) {
                    sb.append("Q ");
                } else {
                    sb.append(". ");
                }
            }
            sb.append("\n");
        }
        sb.append("Conflicts: ").append(getConflicts());
        return sb.toString();
    }

    // Check if this is a goal state (no conflicts)
    public boolean isGoal() {
        return getConflicts() == 0;
    }
}
