package Lab_4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearchAlgo {
    //Task 2.
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(
                Comparator.comparing(Node::getTotalCost).thenComparing(Node::getLabel)
        );
        Set<Node> explored = new HashSet<>();

        root.setG(0);
        root.setTotalCost(root.getG() + root.getH());
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (currentNode.getLabel().equals(goal)) {
                return currentNode;
            }
            explored.add(currentNode);

            for (Edge edge : currentNode.getChildren()) {
                Node child = edge.getEnd();
                double edgeCost = edge.getWeight();
                double totalEdgeCost = currentNode.getG() + edgeCost;

                if (!explored.contains(child) && !frontier.contains(child)) {
                    child.setG(totalEdgeCost);
                    child.setTotalCost(totalEdgeCost + child.getH());
                    child.setParent(currentNode);
                    frontier.add(child);
                } else if (totalEdgeCost < child.getG()) {
                    frontier.remove(child);

                    child.setG(totalEdgeCost);
                    child.setTotalCost(totalEdgeCost + child.getH());
                    child.setParent(currentNode);

                    frontier.add(child);
                }
            }
        }

        return null;
    }

    //Task 3.
    public boolean isAdmissibleH(Node root, String goal) {
        // TODO
        return false;
    }

    //Task 5
    public Node execute(Node root, String start, String goal) {
        // TODO
        return null;
    }
}
