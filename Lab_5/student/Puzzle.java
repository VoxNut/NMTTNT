package Lab_5.student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {

    public static final int MAX_ROW = 3;// 3x3: Dimension of the puzzle map
    public static final int MAX_COL = 3;
    public static final char[] operators = {'u', 'r', 'd', 'l'};

    private Node initialState;
    private Node goalState;

    public Puzzle() {
        this.initialState = new Node(MAX_ROW, MAX_COL);
        this.goalState = new Node(MAX_ROW, MAX_COL);
    }

    // Load initial state node and goal node state from files
    public void readInput(String INITIAL_STATE_MAP_PATH, String GOAL_STATE_MAP_PATH) {
        try {
            // 1 - Import map
            BufferedReader bufferedReader = new BufferedReader(new FileReader(INITIAL_STATE_MAP_PATH));

            String line;
            int row = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tile = line.split("\\s+");
                for (int col = 0; col < tile.length; col++) {
                    initialState.updateTile(row, col, Integer.parseInt(tile[col]));
                }
                row++;
            }

            bufferedReader.close();

            // 2 - Import goal state
            bufferedReader = new BufferedReader(new FileReader(GOAL_STATE_MAP_PATH));

            row = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tile = line.split(" ");
                for (int col = 0; col < tile.length; col++) {
                    goalState.updateTile(row, col, Integer.parseInt(tile[col]));
                }
                row++;
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Node> getSuccessors(Node currentState) {
        ArrayList<Node> result = new ArrayList<>();

        for (char operator : operators) {
            Node tmp = moveWhiteTile(currentState, operator);
            if (tmp != null) {
                result.add(tmp);
            }
        }

        return result;
    }

    // Task 1c)
    public Node moveWhiteTile(Node currentState, char operator) {
        Node result = new Node(currentState);
        int[] whiteTile = currentState.getLocation(0);// get white tile
        switch (operator) {
            case 'u' -> {
                // Case-1: Move tile UP
                // New postion of tile if move UP
                int row = whiteTile[0] - 1;
                int col = whiteTile[1];
                if (row >= 0) {
                    int tmp = currentState.getTile(row, col);
                    result.updateTile(row, col, 0);
                    result.updateTile(whiteTile[0], whiteTile[1], tmp);
                    return result;
                }
            }
            case 'd' -> {
                // Case-2: Move tile DOWN
                // New postion of tile if move Down
                int row = whiteTile[0] + 1;
                int col = whiteTile[1];
                if (row < currentState.getRow()) {
                    int tmp = currentState.getTile(row, col);
                    result.updateTile(row, col, 0);
                    result.updateTile(whiteTile[0], whiteTile[1], tmp);
                    return result;
                }
            }
            case 'l' -> {
                // Case-3: Move tile LEFT
                // New postion of tile if move Left
                int row = whiteTile[0];
                int col = whiteTile[1] - 1;
                if (col >= 0) {
                    int tmp = currentState.getTile(row, col);
                    result.updateTile(row, col, 0);
                    result.updateTile(whiteTile[0], whiteTile[1], tmp);
                    return result;
                }
            }
            case 'r' -> {
                // Case-4: Move tile RIGHT
                // New postion of tile if move Right
                int row = whiteTile[0];
                int col = whiteTile[1] + 1;
                if (col < currentState.getColumn()) {
                    int tmp = currentState.getTile(row, col);
                    result.updateTile(row, col, 0);
                    result.updateTile(whiteTile[0], whiteTile[1], tmp);
                    return result;
                }
            }
            default -> {
            }
        }
        return null;
    }

    // Task 1b)
    // Using manhattanDistance to compute H (sum of Manhattan distances of all tiles)
    public int computeH2(Node currentState) {
        int output = 0;
        for (int i = 0; i < currentState.getRow(); i++) {
            for (int j = 0; j < currentState.getColumn(); j++) {
                int tile = currentState.getTile(i, j);
                if (tile != 0) {
                    int[] goalPosition = goalState.getLocation(tile);
                    output += PuzzleUtils.manhattanDistance(new int[]{i, j}, goalPosition);
                }
            }
        }
        return output;
    }

    //Task 1a)
    // The total number of misplaced tiles
    public int computeH1(Node currentState) {
        int result = 0;
        for (int i = 0; i < currentState.getRow(); i++) {
            for (int j = 0; j < currentState.getColumn(); j++) {
                int tile = currentState.getTile(i, j);
                if (tile != 0 && tile != goalState.getTile(i, j)) {
                    result++;
                }
            }
        }
        return result;
    }

    public Node getInitialState() {
        return initialState;
    }

    public Node getGoalState() {
        return goalState;
    }
}
