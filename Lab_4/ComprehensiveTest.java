package Lab_4;

import Lab_4.Task_2_3_5.AStarSearchAlgo;

public class ComprehensiveTest {

    public static void main(String[] args) {
        // Create the same graph as in TestAStar
        Node s = new Node("S", 6);
        Node b = new Node("B", 4);
        Node a = new Node("A", 4);
        Node c = new Node("C", 4);
        Node d = new Node("D", 3.5);
        Node e = new Node("E", 1);
        Node f = new Node("F", 1);
        Node g = new Node("G", 0);

        s.addEdge(b, 3);
        s.addEdge(a, 2);
        a.addEdge(c, 3);
        b.addEdge(d, 3);
        b.addEdge(c, 1);
        c.addEdge(e, 3);
        c.addEdge(d, 1);
        d.addEdge(f, 2);
        f.addEdge(g, 1);
        e.addEdge(g, 2);

        AStarSearchAlgo algo = new AStarSearchAlgo();

        System.out.println("=".repeat(70));
        System.out.println("A* SEARCH ALGORITHM COMPREHENSIVE TEST");
        System.out.println("=".repeat(70));

        // Task 2: Execute A* from root (S) to goal (G)
        System.out.println("\nTask 2: A* Search from S to G");
        System.out.println("-".repeat(70));
        Node result = algo.execute(s, "G");
        if (result != null) {
            System.out.println("Path found: " + NodeUtils.printPath(result));
            System.out.println("Total cost: " + result.getG());
        } else {
            System.out.println("No path found!");
        }

        // Reset nodes for next test
        resetNodes(s, b, a, c, d, e, f, g);

        // Task 3: Check if heuristic is admissible
        System.out.println("\nTask 3: Check if Heuristic is Admissible");
        System.out.println("-".repeat(70));
        boolean isAdmissible = algo.isAdmissibleH(s, "G");
        System.out.println("Is heuristic admissible? " + (isAdmissible ? "YES" : "NO"));
        System.out.println("h(S) = " + s.getH() + ", Actual shortest cost from S to G should be checked");

        // Reset nodes for next test
        resetNodes(s, b, a, c, d, e, f, g);

        // Task 5: Execute A* from any start node to goal
        System.out.println("\nTask 5: A* Search from B to G (using root S)");
        System.out.println("-".repeat(70));
        Node result2 = algo.execute(s, "B", "G");
        if (result2 != null) {
            System.out.println("Path found: " + NodeUtils.printPath(result2));
            System.out.println("Total cost: " + result2.getG());
        } else {
            System.out.println("No path found!");
        }

        // Reset nodes for another test
        resetNodes(s, b, a, c, d, e, f, g);

        System.out.println("\nTask 5: A* Search from C to G (using root S)");
        System.out.println("-".repeat(70));
        Node result3 = algo.execute(s, "C", "G");
        if (result3 != null) {
            System.out.println("Path found: " + NodeUtils.printPath(result3));
            System.out.println("Total cost: " + result3.getG());
        } else {
            System.out.println("No path found!");
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("Task 2: Standard A* from root to goal - COMPLETED");
        System.out.println("Task 3: Admissibility check - COMPLETED");
        System.out.println("Task 5: A* from any start to goal - COMPLETED");
        System.out.println("=".repeat(70));
    }

    private static void resetNodes(Node... nodes) {
        for (Node node : nodes) {
            node.setG(0);
            node.setTotalCost(0);
            node.setParent(null);
        }
    }
}
