package Lab_4.Task_2_3_5;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import Lab_4.Edge;
import Lab_4.Node;

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
       
        PriorityQueue<Node> frontier = new PriorityQueue<>(
                Comparator.comparing(Node::getG).thenComparing(Node::getLabel)
        );
        Set<Node> explored = new HashSet<>();

        java.util.Map<String, Double> actualCosts = new java.util.HashMap<>();

        root.setG(0);
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();

            if (explored.contains(currentNode)) {
                continue;
            }

            explored.add(currentNode);

       
            actualCosts.put(currentNode.getLabel(), currentNode.getG());

            for (Edge edge : currentNode.getChildren()) {
                Node child = edge.getEnd();
                double edgeCost = edge.getWeight();
                double totalEdgeCost = currentNode.getG() + edgeCost;

                if (!explored.contains(child)) {
                    if (!frontier.contains(child) || totalEdgeCost < child.getG()) {
                        child.setG(totalEdgeCost);
                        frontier.remove(child);
                        frontier.add(child);
                    }
                }
            }
        }


        if (!actualCosts.containsKey(goal)) {
            return true;
        }

        double goalActualCost = actualCosts.get(goal);

       
        return root.getH() <= goalActualCost;
    }

    //Task 5
    public Node execute(Node root, String start, String goal) {

        Node startNode = findNode(root, start, new HashSet<>());
        if (startNode == null) {
            return null; 
        }

 
        PriorityQueue<Node> frontier = new PriorityQueue<>(
                Comparator.comparing(Node::getTotalCost).thenComparing(Node::getLabel)
        );
        Set<Node> explored = new HashSet<>();

        startNode.setG(0);
        startNode.setTotalCost(startNode.getG() + startNode.getH());
        startNode.setParent(null); 
        frontier.add(startNode);

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

    private Node findNode(Node current, String label, Set<Node> visited) {
        if (current == null || visited.contains(current)) {
            return null;
        }

        if (current.getLabel().equals(label)) {
            return current;
        }

        visited.add(current);

        for (Edge edge : current.getChildren()) {
            Node found = findNode(edge.getEnd(), label, visited);
            if (found != null) {
                return found;
            }
        }

        return null;
    }
}
