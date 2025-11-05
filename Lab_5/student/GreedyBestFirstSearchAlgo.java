package Lab_5.student;

import java.util.*;

public class GreedyBestFirstSearchAlgo {

    public Node execute(Puzzle puzzle) {
        Node initialState = puzzle.getInitialState();
        Node goalState = puzzle.getGoalState();

        // PriorityQueue sắp xếp theo h(n)
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getH));
        Set<Node> frontierSet = new HashSet<>(); // Để kiểm tra nhanh O(1)
        Set<Node> explored = new HashSet<>();

        initialState.setH(puzzle.computeH2(initialState));
        frontier.add(initialState);
        frontierSet.add(initialState);

        int iterations = 0;
        int maxIterations = 200000;

        while (!frontier.isEmpty() && iterations < maxIterations) {
            iterations++;

            Node current = frontier.poll();
            frontierSet.remove(current);

            if (current.equals(goalState)) {
                System.out.println("Greedy Best-First: Found solution in " + iterations + " iterations, explored " + explored.size() + " states");
                return current; // tìm thấy lời giải
            }

            explored.add(current);

            List<Node> children = puzzle.getSuccessors(current);
            for (Node child : children) {
                if (!explored.contains(child) && !frontierSet.contains(child)) {
                    child.setH(puzzle.computeH2(child));
                    frontier.add(child);
                    frontierSet.add(child);
                }
            }
        }

        if (iterations >= maxIterations) {
            System.out.println("Greedy Best-First: Reached iteration limit (" + maxIterations + ")");
        } else {
            System.out.println("Greedy Best-First: No solution found after " + iterations + " iterations");
        }
        return null;
    }
}
