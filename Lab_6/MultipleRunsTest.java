package Lab_6;

/**
 * Multiple test runs to evaluate success rate of each algorithm
 */
public class MultipleRunsTest {

    public static void main(String[] args) {
        int boardSize = 8;
        int numRuns = 10;

        System.out.println("=".repeat(70));
        System.out.println("8-QUEENS PROBLEM - MULTIPLE RUNS TEST (" + numRuns + " runs each)");
        System.out.println("=".repeat(70));
        System.out.println();

        // Test Hill Climbing
        System.out.println("Testing Hill Climbing...");
        int hcSuccess = 0;
        long hcTotalTime = 0;

        for (int i = 0; i < numRuns; i++) {
            Queen initialState = new Queen(boardSize);
            HillClimbingSearch hc = new HillClimbingSearch(1000);

            long startTime = System.currentTimeMillis();
            Queen result = hc.execute(initialState);
            long endTime = System.currentTimeMillis();

            if (result.isGoal()) {
                hcSuccess++;
            }
            hcTotalTime += (endTime - startTime);
        }

        System.out.println("Success rate: " + hcSuccess + "/" + numRuns
                + " (" + (hcSuccess * 100.0 / numRuns) + "%)");
        System.out.println("Average time: " + (hcTotalTime / numRuns) + " ms");
        System.out.println();

        // Test Simulated Annealing
        System.out.println("Testing Simulated Annealing...");
        int saSuccess = 0;
        long saTotalTime = 0;

        for (int i = 0; i < numRuns; i++) {
            Queen initialState = new Queen(boardSize);
            SimulatedAnnealingSearch sa = new SimulatedAnnealingSearch(1000.0, 0.995, 10000);

            long startTime = System.currentTimeMillis();
            Queen result = sa.execute(initialState);
            long endTime = System.currentTimeMillis();

            if (result.isGoal()) {
                saSuccess++;
            }
            saTotalTime += (endTime - startTime);
        }

        System.out.println("Success rate: " + saSuccess + "/" + numRuns
                + " (" + (saSuccess * 100.0 / numRuns) + "%)");
        System.out.println("Average time: " + (saTotalTime / numRuns) + " ms");
        System.out.println();

        // Test Genetic Algorithm
        System.out.println("Testing Genetic Algorithm...");
        int gaSuccess = 0;
        long gaTotalTime = 0;

        for (int i = 0; i < numRuns; i++) {
            GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.1, 1000);

            long startTime = System.currentTimeMillis();
            Queen result = ga.execute(boardSize);
            long endTime = System.currentTimeMillis();

            if (result.isGoal()) {
                gaSuccess++;
            }
            gaTotalTime += (endTime - startTime);
        }

        System.out.println("Success rate: " + gaSuccess + "/" + numRuns
                + " (" + (gaSuccess * 100.0 / numRuns) + "%)");
        System.out.println("Average time: " + (gaTotalTime / numRuns) + " ms");
        System.out.println();

        // Summary comparison
        System.out.println("=".repeat(70));
        System.out.println("COMPARISON SUMMARY");
        System.out.println("=".repeat(70));
        System.out.printf("%-25s | %-15s | %-15s%n", "Algorithm", "Success Rate", "Avg Time (ms)");
        System.out.println("-".repeat(70));
        System.out.printf("%-25s | %6.1f%%         | %-15d%n",
                "Hill Climbing", (hcSuccess * 100.0 / numRuns), (hcTotalTime / numRuns));
        System.out.printf("%-25s | %6.1f%%         | %-15d%n",
                "Simulated Annealing", (saSuccess * 100.0 / numRuns), (saTotalTime / numRuns));
        System.out.printf("%-25s | %6.1f%%         | %-15d%n",
                "Genetic Algorithm", (gaSuccess * 100.0 / numRuns), (gaTotalTime / numRuns));
        System.out.println("=".repeat(70));
    }
}
