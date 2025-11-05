package Lab_5.student;

public class ComparisonTest {

    public static void main(String[] args) {
        Puzzle p = new Puzzle();
        p.readInput("Lab_5/txt/PuzzleMap.txt", "Lab_5/txt/PuzzleGoalState.txt");

        System.out.println("Initial State:");
        System.out.println(p.getInitialState());

        System.out.println("Goal State:");
        System.out.println(p.getGoalState());

        System.out.println("\n" + "=".repeat(70));
        System.out.println("ALGORITHM COMPARISON FOR 8-PUZZLE PROBLEM");
        System.out.println("=".repeat(70) + "\n");

        // Track results
        long startTime, endTime, duration;
        Node result;

        // 1. Breadth-First Search (BFS)
        System.out.println("1. BREADTH-FIRST SEARCH (BFS)");
        System.out.println("-".repeat(70));
        Task4_BFSDFSHillClimbingAlgo task4 = new Task4_BFSDFSHillClimbingAlgo();
        startTime = System.currentTimeMillis();
        result = task4.executeBFS(p);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Running time: " + duration + " ms");
        System.out.println("Solution found: " + (result != null ? "YES" : "NO"));
        if (result != null) {
            System.out.println("Goal state:\n" + result);
        }
        System.out.println();

        // 2. Depth-First Search (DFS)
        System.out.println("2. DEPTH-FIRST SEARCH (DFS)");
        System.out.println("-".repeat(70));
        startTime = System.currentTimeMillis();
        result = task4.executeDFS(p);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Running time: " + duration + " ms");
        System.out.println("Solution found: " + (result != null ? "YES" : "NO"));
        if (result != null) {
            System.out.println("Goal state:\n" + result);
        }
        System.out.println();

        // 3. Hill Climbing
        System.out.println("3. HILL CLIMBING SEARCH");
        System.out.println("-".repeat(70));
        startTime = System.currentTimeMillis();
        result = task4.executeHillClimbing(p);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Running time: " + duration + " ms");
        System.out.println("Solution found: " + (result != null ? "YES" : "NO"));
        if (result != null) {
            System.out.println("Goal state:\n" + result);
        }
        System.out.println();

        // 4. Greedy Best-First Search
        System.out.println("4. GREEDY BEST-FIRST SEARCH");
        System.out.println("-".repeat(70));
        GreedyBestFirstSearchAlgo gbfs = new GreedyBestFirstSearchAlgo();
        startTime = System.currentTimeMillis();
        result = gbfs.execute(p);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Running time: " + duration + " ms");
        System.out.println("Solution found: " + (result != null ? "YES" : "NO"));
        if (result != null) {
            System.out.println("Goal state:\n" + result);
        }
        System.out.println();

        // 5. A* Search
        System.out.println("5. A* SEARCH");
        System.out.println("-".repeat(70));
        AStarSearchAlgo astar = new AStarSearchAlgo();
        startTime = System.currentTimeMillis();
        result = astar.execute(p);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Running time: " + duration + " ms");
        System.out.println("Solution found: " + (result != null ? "YES" : "NO"));
        if (result != null) {
            System.out.println("Goal state:\n" + result);
        }
        System.out.println();

        System.out.println("=".repeat(70));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("1. BFS: Guarantees shortest path, explores states level by level");
        System.out.println("2. DFS: May find long paths, can get stuck in deep branches");
        System.out.println("3. Hill Climbing: Fast but can get stuck in local minima");
        System.out.println("4. Greedy Best-First: Uses heuristic, not optimal");
        System.out.println("5. A*: Optimal and efficient, uses both cost and heuristic");
        System.out.println("=".repeat(70));
    }
}
