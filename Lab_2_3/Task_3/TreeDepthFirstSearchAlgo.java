package Lab_2_3.Task_3;

import java.util.List;
import java.util.Stack;

import Lab_2_3.Node;

public class TreeDepthFirstSearchAlgo {
	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		Stack<Node> frontier = new Stack<>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal))
				return current;
			List<Node> children = current.getChildrenNodes();
			// Push children in reverse order to explore left-to-right
			for (int i = children.size() - 1; i >= 0; i--) {
				Node child = children.get(i);
				child.setParent(current);
				frontier.add(child);
			}
		}
		return null;
	}

	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
		Node startNode = findNode(tree, start);
		if (startNode == null)
			return null;
		startNode.setParent(null);
		return execute(startNode, goal);
	}

	private Node findNode(Node node, String label) {
		Stack<Node> stack = new Stack<>();
		stack.add(node);
		while (!stack.isEmpty()) {
			Node current = stack.pop();
			if (current.getLabel().equals(label))
				return current;
			for (Node child : current.getChildrenNodes()) {
				stack.add(child);
			}
		}
		return null;
	}
}