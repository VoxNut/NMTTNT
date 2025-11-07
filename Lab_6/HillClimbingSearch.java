package Lab_6;

import java.util.ArrayList;
import java.util.List;

/**
 * Hill Climbing algorithm for 8-Queens problem
 */
public class HillClimbingSearch {

    private int maxIterations;

    public HillClimbingSearch() {
        this.maxIterations = 1000;
    }

    public HillClimbingSearch(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public Queen execute(Queen initialState) {
        Queen current = initialState.copy();
        int iterations = 0;

        while (iterations < maxIterations) {
            iterations++;

            // Check if we found the goal
            if (current.isGoal()) {
                System.out.println("Hill Climbing: Found solution in " + iterations + " iterations");
                return current;
            }

            // Generate all neighbors
            List<Queen> neighbors = generateNeighbors(current);

            // Find the best neighbor
            Queen bestNeighbor = findBestNeighbor(neighbors);

            // If no better neighbor exists, we're stuck at local minimum
            if (bestNeighbor.getConflicts() >= current.getConflicts()) {
                System.out.println("Hill Climbing: Stuck at local minimum after " + iterations
                        + " iterations, conflicts = " + current.getConflicts());
                return current;
            }

            // Move to the best neighbor
            current = bestNeighbor;
        }

        System.out.println("Hill Climbing: Reached max iterations (" + maxIterations
                + "), conflicts = " + current.getConflicts());
        return current;
    }

    // Generate all possible neighbor by moving each queen to different rows
    private List<Queen> generateNeighbors(Queen state) {
        List<Queen> neighbors = new ArrayList<>();
        int size = state.getSize();

        for (int col = 0; col < size; col++) {
            int currentRow = state.getPosition(col);
            for (int row = 0; row < size; row++) {
                if (row != currentRow) {
                    Queen neighbor = state.copy();
                    neighbor.setPosition(col, row);
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }

    // Find the neighbor with minimum conflicts
    private Queen findBestNeighbor(List<Queen> neighbors) {
        Queen best = neighbors.get(0);
        int minConflicts = best.getConflicts();

        for (Queen neighbor : neighbors) {
            int conflicts = neighbor.getConflicts();
            if (conflicts < minConflicts) {
                minConflicts = conflicts;
                best = neighbor;
            }
        }

        return best;
    }

    /**
     * Hill Climbing Search with Random Restart Restarts with a new random state
     * when stuck at local minimum
     *
     * @param boardSize Size of the board (e.g., 8 for 8-Queens)
     * @param maxRestarts Maximum number of random restarts allowed
     * @return The best Queen state found (ideally a solution)
     */
    public Queen executeWithRandomRestart(int boardSize, int maxRestarts) {
        int restart = 0;
        int totalIterations = 0;
        Queen bestState = null;
        int bestConflicts = Integer.MAX_VALUE;

        System.out.println("Starting Hill Climbing with Random Restart...");
        System.out.println("Board Size: " + boardSize + ", Max Restarts: " + maxRestarts);
        System.out.println("=".repeat(60));

        while (restart <= maxRestarts) {
            restart++;
            System.out.println("\nRestart #" + restart);

            // Create a new random initial state
            Queen initialState = new Queen(boardSize);
            System.out.println("Initial conflicts: " + initialState.getConflicts());

            // Run standard hill climbing
            Queen result = execute(initialState);

            totalIterations++;

            // Check if we found a solution
            if (result.isGoal()) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("SUCCESS! Solution found after " + restart + " restarts");
                System.out.println("Total iterations: " + totalIterations);
                System.out.println("=".repeat(60));
                return result;
            }

            // Track the best state found so far
            if (result.getConflicts() < bestConflicts) {
                bestConflicts = result.getConflicts();
                bestState = result.copy();
                System.out.println("New best state found with " + bestConflicts + " conflicts");
            }
        }

        // If no solution found after all restarts, return the best state
        System.out.println("\n" + "=".repeat(60));
        System.out.println("No solution found after " + maxRestarts + " restarts");
        System.out.println("Best state has " + bestConflicts + " conflicts");
        System.out.println("Total iterations: " + totalIterations);
        System.out.println("=".repeat(60));
        return bestState;
    }

    /**
     * Overloaded method with custom initial state
     *
     * @param initialState The initial state to start from
     * @param maxRestarts Maximum number of random restarts allowed
     * @return The best Queen state found
     */
    public Queen executeWithRandomRestart(Queen initialState, int maxRestarts) {
        int restart = 0;
        int totalIterations = 0;
        Queen bestState = null;
        int bestConflicts = Integer.MAX_VALUE;
        int boardSize = initialState.getSize();

        System.out.println("Starting Hill Climbing with Random Restart...");
        System.out.println("Board Size: " + boardSize + ", Max Restarts: " + maxRestarts);
        System.out.println("=".repeat(60));

        // First attempt with provided initial state
        System.out.println("\nAttempt #1 (with provided initial state)");
        System.out.println("Initial conflicts: " + initialState.getConflicts());

        Queen result = execute(initialState);
        totalIterations++;

        if (result.isGoal()) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("SUCCESS! Solution found on first attempt");
            System.out.println("=".repeat(60));
            return result;
        }

        bestConflicts = result.getConflicts();
        bestState = result.copy();

        // Continue with random restarts
        while (restart < maxRestarts) {
            restart++;
            System.out.println("\nRestart #" + restart);

            // Create a new random initial state
            Queen newState = new Queen(boardSize);
            System.out.println("Initial conflicts: " + newState.getConflicts());

            // Run standard hill climbing
            result = execute(newState);
            totalIterations++;

            // Check if we found a solution
            if (result.isGoal()) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("SUCCESS! Solution found after " + restart + " restarts");
                System.out.println("Total iterations: " + totalIterations);
                System.out.println("=".repeat(60));
                return result;
            }

            // Track the best state found so far
            if (result.getConflicts() < bestConflicts) {
                bestConflicts = result.getConflicts();
                bestState = result.copy();
                System.out.println("New best state found with " + bestConflicts + " conflicts");
            }
        }

        // If no solution found after all restarts, return the best state
        System.out.println("\n" + "=".repeat(60));
        System.out.println("No solution found after " + maxRestarts + " restarts");
        System.out.println("Best state has " + bestConflicts + " conflicts");
        System.out.println("Total iterations: " + totalIterations);
        System.out.println("=".repeat(60));
        return bestState;
    }
}
