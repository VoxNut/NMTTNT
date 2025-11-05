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

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            frontierSet.remove(current);

            if (current.equals(goalState)) {
                return current; // tìm thấy lời giải
            }

            explored.add(current);

            List<Node> children = puzzle.getSuccessors(current);
            for (Node child : children) {
                child.setH(puzzle.computeH2(child));
                if (!explored.contains(child) && !frontierSet.contains(child)) {
                    frontier.add(child);
                    frontierSet.add(child);
                }
            }
        }

        return null;
    }
}
