package Lab_1.Task_3;

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

  
        do {
            agentRow = rand.nextInt(rows);
            agentCol = rand.nextInt(cols);
        } while (grid[agentRow][agentCol] == CellState.OBSTACLE);

        agent.setLocation("("+agentRow+","+agentCol+")");
    }

    private boolean isAllClean() {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (grid[r][c] == CellState.DIRTY)
                    return false;
        return true;
    }

    private Percept getPercept() {
        return new Percept(agent.getLocation(),
                grid[agentRow][agentCol] == CellState.DIRTY
                        ? Environment.LocationState.DIRTY
                        : Environment.LocationState.CLEAN);
    }

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

    private void move(int dr, int dc) {
        int newR = agentRow + dr;
        int newC = agentCol + dc;

        if (newR < 0 || newC < 0 || newR >= rows || newC >= cols
                || grid[newR][newC] == CellState.OBSTACLE) {
            score -= 100;
            return;
        }

        agentRow = newR;
        agentCol = newC;
        agent.setLocation("(" + agentRow + "," + agentCol + ")");
        score -= 10;
    }

    public void run(int maxSteps) {
        printGrid();

        for (int step = 1; step <= maxSteps; step++) {
            if (isAllClean()) {
                break;
            }
            Percept p = getPercept();
            Action a = agent.execute(p);
            execute(a);
        }
        printGrid();
    }

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

