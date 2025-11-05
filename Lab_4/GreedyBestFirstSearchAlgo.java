package Lab_4;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class GreedyBestFirstSearchAlgo {
    // Task 1.
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByHn());
        Set<Node> explored = new HashSet<>();
        frontier.add(root);
        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (currentNode.getLabel().equals(goal)) {
                return currentNode;
            }
            explored.add(currentNode);

            List<Node> children = currentNode.getChildrenNodes();
            for (Node child : children) {
                if (!frontier.contains(child) && !explored.contains(child)) {
                    child.setParent(currentNode);
                    frontier.add(child);
                }
            }
        }
        return null;
    }

    //Task 3.
    public Node execute(Node root, String start, String goal) {
        // TODO
        return null;
    }
}
