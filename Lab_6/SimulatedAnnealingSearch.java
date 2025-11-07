package Lab_6;

/**
 * Simulated Annealing algorithm for 8-Queens problem
 */
public class SimulatedAnnealingSearch {

    private double initialTemperature;
    private double coolingRate;
    private int maxIterations;

    public SimulatedAnnealingSearch() {
        this.initialTemperature = 1000.0;
        this.coolingRate = 0.995;
        this.maxIterations = 10000;
    }

    public SimulatedAnnealingSearch(double initialTemperature, double coolingRate, int maxIterations) {
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
        this.maxIterations = maxIterations;
    }

    public Queen execute(Queen initialState) {
        Queen current = initialState.copy();
        Queen best = current.copy();
        double temperature = initialTemperature;
        int iterations = 0;

        while (iterations < maxIterations && temperature > 0.1) {
            iterations++;

            // Check if we found the goal
            if (current.isGoal()) {
                System.out.println("Simulated Annealing: Found solution in " + iterations
                        + " iterations, temperature = " + String.format("%.2f", temperature));
                return current;
            }

            // Generate a random neighbor
            Queen neighbor = generateRandomNeighbor(current);

            int currentConflicts = current.getConflicts();
            int neighborConflicts = neighbor.getConflicts();
            int delta = neighborConflicts - currentConflicts;

            // Accept better solutions always, worse solutions with probability
            if (delta < 0 || Math.random() < Math.exp(-delta / temperature)) {
                current = neighbor;

                // Update best solution found
                if (current.getConflicts() < best.getConflicts()) {
                    best = current.copy();
                }
            }

            // Cool down
            temperature *= coolingRate;
        }

        System.out.println("Simulated Annealing: Completed " + iterations
                + " iterations, best conflicts = " + best.getConflicts());
        return best;
    }

    // Generate a random neighbor by moving one random queen to a random row
    private Queen generateRandomNeighbor(Queen state) {
        Queen neighbor = state.copy();
        int size = state.getSize();

        int col = (int) (Math.random() * size);
        int row = (int) (Math.random() * size);

        neighbor.setPosition(col, row);
        return neighbor;
    }
}
