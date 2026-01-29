package Lab_2_3.Task_1_2;

import Lab_2_3.Node;

import java.util.HashSet;
import java.util.Set;

public class DepthFirstSearchAlgo {
	// Task 1.
// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
        Set<Node> visited = new HashSet<>();
        visited.add(tree);
        return executeHelper(tree, visited, goal);

	}


    public Node executeHelper(Node node, Set<Node> visited, String goal) {
        if(node.getLabel().equals(goal)) {
            return node;
        }

        for(Node child : node.getChildrenNodes()) {
            if(!visited.contains(child)) {
                child.setParent(node);
                visited.add(child);
                Node res = executeHelper(child, visited, goal);
                if(res != null) {
                    return res;
                }
            }
        }

        return null;

    }



	// Task 2.
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