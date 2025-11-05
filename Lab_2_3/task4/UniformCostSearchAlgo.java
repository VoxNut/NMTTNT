package Lab_2_3.task4;

import java.util.*;

import Lab_2_3.Edge;
import Lab_2_3.Node;

public class UniformCostSearchAlgo {
	public static Comparator<Node> getComparator() {
		return Comparator.comparing(Node::getPathCost).thenComparing(Node::getLabel);
	}

	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {

        PriorityQueue<Node> frontier = new PriorityQueue<>(getComparator());

        Set<Node> explored = new HashSet<>();

        tree.setPathCost(0);
        frontier.add(tree);

        while(!frontier.isEmpty()) {

            Node current = frontier.poll();


            if(current.getLabel().equals(goal)) return current;
            explored.add(current);


            List<Edge> edges = current.getChildren();

            for(Edge edge : edges) {
                Node child = edge.getEnd();
                double childCost = current.getPathCost() + edge.getWeight();

                if(!explored.contains(child) &&  !frontier.contains(child)) {
                    child.setParent(current);
                    child.setPathCost(childCost);
                    frontier.add(child);
                } else if(frontier.contains(child) && childCost < child.getPathCost()) {
                    frontier.remove(child);
                    child.setPathCost(childCost);
                    child.setParent(current);
                    frontier.add(child);
                }
            }
        }

		return null;
	}

	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {

        Node startNode = findNode(tree, start);
        if(startNode == null)    {
            throw new IllegalArgumentException("start cannot found");
        }

        return execute(startNode, goal);

	}

    public Node findNode(Node root, String node) {

        if(root.getLabel().equals(node)) return root;

        List<Node> children = root.getChildrenNodes();

        for(Node child : children) {

            Node res = findNode(child, node);
            if(res != null) {
                return res;
            }
        }

        return null;

    }
}
