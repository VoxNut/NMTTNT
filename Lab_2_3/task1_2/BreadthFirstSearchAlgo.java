package Lab_2_3.task1_2;

import Lab_2_3.Node;

import java.util.*;

public class BreadthFirstSearchAlgo {
	//Task 1.
	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {

        Queue<Node> frontier = new LinkedList<>();
//        Set<Node> visited = new HashSet<>();

        frontier.add(tree);
//        visited.add(tree);

        while(!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            List<Node> children = currentNode.getChildrenNodes();

            if(currentNode.getLabel().equals(goal)) {
                return currentNode;
            }

            for (Node child : children) {
//                if(!visited.contains(child)) {
                    child.setParent(currentNode);
                    frontier.add(child);
//                    visited.add(child);
//                }
            }

        }

        return null;
	}

//Task 2.
	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
        Node startNode = findNode(tree, start);
        if(startNode == null) return null;
        return execute(startNode, goal);
	}

    public Node findNode(Node node, String nodeLabel) {
        if(node.getLabel().equals(nodeLabel)) {
            return node;
        }

        for (Node child : node.getChildrenNodes()) {
            Node res = findNode(child, nodeLabel);
            if(res != null) {
                return res;
            }
        }

        return null;
    }


}
