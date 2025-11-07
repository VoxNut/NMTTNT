package Lab_6;

/**
 * Test class to compare all three algorithms for 8-Queens problem
 */
public class Test {

    public static void main(String[] args) {
        int boardSize = 8;

        System.out.println("=".repeat(70));
        System.out.println("8-QUEENS PROBLEM - ALGORITHM COMPARISON");
        System.out.println("=".repeat(70));
        System.out.println();

        // Test 1: Hill Climbing
        System.out.println("1. HILL CLIMBING SEARCH");
        System.out.println("-".repeat(70));
        Queen initialState1 = new Queen(boardSize);
        System.out.println("Initial state:");
        System.out.println(initialState1);
        System.out.println();

        HillClimbingSearch hc = new HillClimbingSearch(1000);
        long startTime = System.currentTimeMillis();
        Queen hcResult = hc.execute(initialState1);
        long endTime = System.currentTimeMillis();

        System.out.println("\nResult:");
        System.out.println(hcResult);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Success: " + (hcResult.isGoal() ? "YES" : "NO"));
        System.out.println();

        // Test 2: Simulated Annealing
        System.out.println("2. SIMULATED ANNEALING");
        System.out.println("-".repeat(70));
        Queen initialState2 = new Queen(boardSize);
        System.out.println("Initial state:");
        System.out.println(initialState2);
        System.out.println();

        SimulatedAnnealingSearch sa = new SimulatedAnnealingSearch(1000.0, 0.995, 10000);
        startTime = System.currentTimeMillis();
        Queen saResult = sa.execute(initialState2);
        endTime = System.currentTimeMillis();

        System.out.println("\nResult:");
        System.out.println(saResult);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Success: " + (saResult.isGoal() ? "YES" : "NO"));
        System.out.println();

        // Test 3: Genetic Algorithm
        System.out.println("3. GENETIC ALGORITHM");
        System.out.println("-".repeat(70));

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.1, 1000);
        startTime = System.currentTimeMillis();
        Queen gaResult = ga.execute(boardSize);
        endTime = System.currentTimeMillis();

        System.out.println("\nResult:");
        System.out.println(gaResult);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Success: " + (gaResult.isGoal() ? "YES" : "NO"));
        System.out.println();

        // Summary
        System.out.println("=".repeat(70));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("1. Hill Climbing: " + (hcResult.isGoal() ? "SUCCESS" : "FAILED")
                + " - Conflicts: " + hcResult.getConflicts());
        System.out.println("2. Simulated Annealing: " + (saResult.isGoal() ? "SUCCESS" : "FAILED")
                + " - Conflicts: " + saResult.getConflicts());
        System.out.println("3. Genetic Algorithm: " + (gaResult.isGoal() ? "SUCCESS" : "FAILED")
                + " - Conflicts: " + gaResult.getConflicts());
        System.out.println("=".repeat(70));

        System.out.println("\nNOTE: Hill Climbing may get stuck in local minima.");
        System.out.println("Simulated Annealing and Genetic Algorithm have better chances of finding a solution.");
    }
}
