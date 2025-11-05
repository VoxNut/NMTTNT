package Lab_5.student;

import java.util.*;

public class Task4_BFSDFSHillClimbingAlgo {

    //	Task 4.1
    // run Breath first search
    public Node executeBFS(Puzzle p) {
        Node initialState = p.getInitialState();
        Node goalState = p.getGoalState();

        // Queue for BFS (FIFO)
        Queue<Node> frontier = new LinkedList<>();
        Set<Node> explored = new HashSet<>();
        Set<Node> frontierSet = new HashSet<>();

        frontier.add(initialState);
        frontierSet.add(initialState);

        int iterations = 0;
        int maxIterations = 50000; // Limit to prevent memory issues

        while (!frontier.isEmpty() && iterations < maxIterations) {
            iterations++;

            Node current = frontier.poll();
            frontierSet.remove(current);

            if (current.equals(goalState)) {
                System.out.println("BFS: Found solution in " + iterations + " iterations, explored " + explored.size() + " states");
                return current;
            }

            explored.add(current);

            List<Node> children = p.getSuccessors(current);
            for (Node child : children) {
                if (!explored.contains(child) && !frontierSet.contains(child)) {
                    frontier.add(child);
                    frontierSet.add(child);
                }
            }
        }

        if (iterations >= maxIterations) {
            System.out.println("BFS: Reached iteration limit (" + maxIterations + "), explored " + explored.size() + " states");
        } else {
            System.out.println("BFS: No solution found after " + iterations + " iterations");
        }
        return null;
    }    //	Task 4.2
    // run Depth first search

    public Node executeDFS(Puzzle p) {
        Node initialState = p.getInitialState();
        Node goalState = p.getGoalState();

        // Stack for DFS (LIFO)
        Stack<Node> frontier = new Stack<>();
        Set<Node> explored = new HashSet<>();
        Set<Node> frontierSet = new HashSet<>();

        frontier.push(initialState);
        frontierSet.add(initialState);

        int iterations = 0;
        int maxIterations = 50000; // Limit for DFS to prevent infinite loops

        while (!frontier.isEmpty() && iterations < maxIterations) {
            iterations++;

            Node current = frontier.pop();
            frontierSet.remove(current);

            if (current.equals(goalState)) {
                System.out.println("DFS: Found solution in " + iterations + " iterations, explored " + explored.size() + " states");
                return current;
            }

            explored.add(current);

            List<Node> children = p.getSuccessors(current);
            for (Node child : children) {
                if (!explored.contains(child) && !frontierSet.contains(child)) {
                    frontier.push(child);
                    frontierSet.add(child);
                }
            }
        }

        if (iterations >= maxIterations) {
            System.out.println("DFS: Reached iteration limit (" + maxIterations + "), explored " + explored.size() + " states");
        } else {
            System.out.println("DFS: No solution found after " + iterations + " iterations");
        }
        return null;
    }

    //	Task 4.3
    // run Hill climbing search
    public Node executeHillClimbing(Puzzle p) {
        Node current = p.getInitialState();
        Node goalState = p.getGoalState();

        current.setH(p.computeH2(current));

        int iterations = 0;
        int maxIterations = 100000;

        while (iterations < maxIterations) {
            iterations++;

            if (current.equals(goalState)) {
                System.out.println("Hill Climbing: Found solution in " + iterations + " iterations");
                return current;
            }

            List<Node> children = p.getSuccessors(current);

            // Find the best child (lowest h-value)
            Node bestChild = null;
            int bestH = Integer.MAX_VALUE;

            for (Node child : children) {
                child.setH(p.computeH2(child));
                if (child.getH() < bestH) {
                    bestH = child.getH();
                    bestChild = child;
                }
            }

            // If no improvement, we're stuck at a local minimum
            if (bestChild == null || bestChild.getH() >= current.getH()) {
                System.out.println("Hill Climbing: Stuck at local minimum after " + iterations + " iterations, h=" + current.getH());
                return null;
            }

            current = bestChild;
        }

        System.out.println("Hill Climbing: Reached iteration limit (" + maxIterations + ")");
        return null;
    }
}
