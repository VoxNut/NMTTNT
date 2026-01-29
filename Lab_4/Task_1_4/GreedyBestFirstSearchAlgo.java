package Lab_4.Task_1_4;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import Lab_4.Node;
import Lab_4.NodeComparatorByHn;

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

    //Task 4.
    public Node execute(Node root, String start, String goal) {
        Node startNode = findNode(root, start);
        if (startNode == null)
            return null;
        startNode.setParent(null);
        return execute(startNode, goal);
    }

    private Node findNode(Node root, String label) {
        Set<Node> visited = new HashSet<>();
        return findNode(root, label, visited);
    }

    private Node findNode(Node node, String label, Set<Node> visited) {
        if (node.getLabel().equals(label))
            return node;
        visited.add(node);
        for (Node child : node.getChildrenNodes()) {
            if (!visited.contains(child)) {
                Node res = findNode(child, label, visited);
                if (res != null)
                    return res;
            }
        }
        return null;
    }
}
