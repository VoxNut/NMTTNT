package Lab_1.Lab_1_3;

import java.util.*;

public class GridEnvironment {

    public enum CellState { CLEAN, DIRTY, OBSTACLE }

    private final int rows;
    private final int cols;
    private final CellState[][] grid;
    private final Random rand = new Random();
    private final Agent agent;
    private int agentRow, agentCol;
    private int score = 0;

    public GridEnvironment(int rows, int cols,
                           double dirtRate, double wallRate,
                           Agent agent) {
        this.rows = rows;
        this.cols = cols;
        this.agent = agent;
        this.grid = new CellState[rows][cols];

        // Randomly assign states
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double x = rand.nextDouble();
                if (x < wallRate)
                    grid[r][c] = CellState.OBSTACLE;
                else if (x < wallRate + dirtRate)
                    grid[r][c] = CellState.DIRTY;
                else
                    grid[r][c] = CellState.CLEAN;
            }
        }

        // Place agent in a random non-obstacle cell
        do {
            agentRow = rand.nextInt(rows);
            agentCol = rand.nextInt(cols);
        } while (grid[agentRow][agentCol] == CellState.OBSTACLE);

        agent.setLocation("("+agentRow+","+agentCol+")");
    }

    /** Return true if all non-obstacle cells are clean */
    private boolean isAllClean() {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (grid[r][c] == CellState.DIRTY)
                    return false;
        return true;
    }

    /** Percept for the agent at its current location */
    private Percept getPercept() {
        return new Percept(agent.getLocation(),
                grid[agentRow][agentCol] == CellState.DIRTY
                        ? Environment.LocationState.DIRTY
                        : Environment.LocationState.CLEAN);
    }

    /** Attempt to perform an action */
    private void execute(Action a) {
        String act = a.getName();
        switch (act) {
            case "SUCK":
                if (grid[agentRow][agentCol] == CellState.DIRTY) {
                    grid[agentRow][agentCol] = CellState.CLEAN;
                    score += 500;
                }
                break;

            case "UP":
                move(-1, 0); break;
            case "DOWN":
                move(1, 0); break;
            case "LEFT":
                move(0, -1); break;
            case "RIGHT":
                move(0, 1); break;
        }
    }

    /** Movement helper */
    private void move(int dr, int dc) {
        int newR = agentRow + dr;
        int newC = agentCol + dc;

        if (newR < 0 || newC < 0 || newR >= rows || newC >= cols
                || grid[newR][newC] == CellState.OBSTACLE) {
            score -= 100; // invalid move
            return;
        }

        agentRow = newR;
        agentCol = newC;
        agent.setLocation("(" + agentRow + "," + agentCol + ")");
        score -= 10; // valid move
    }

    /** Main simulation loop */
    public void run(int maxSteps) {
        System.out.println("Initial grid:");
        printGrid();

        for (int step = 1; step <= maxSteps; step++) {
            if (isAllClean()) {
                System.out.println("All cells clean!");
                break;
            }
            Percept p = getPercept();
            Action a = agent.execute(p);
            execute(a);
            System.out.printf("Step %d: %s -> %s | Score=%d%n",
                    step, p, a, score);
        }
        System.out.println("Final grid:");
        printGrid();
        System.out.println("Final Score: " + score);
    }

    /** Helper to visualize grid */
    private void printGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == agentRow && c == agentCol)
                    System.out.print("[A] ");
                else
                    switch (grid[r][c]) {
                        case CLEAN -> System.out.print(" .  ");
                        case DIRTY -> System.out.print(" D  ");
                        case OBSTACLE -> System.out.print(" X  ");
                    }
            }
            System.out.println();
        }
        System.out.println();
    }
}

