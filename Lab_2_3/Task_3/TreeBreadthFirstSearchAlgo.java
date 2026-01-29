package Lab_2_3.Task_3;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Lab_2_3.Node;

public class TreeBreadthFirstSearchAlgo {

	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			List<Node> children = current.getChildrenNodes();
			for (Node child : children) {
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
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.getLabel().equals(label))
				return current;
			for (Node child : current.getChildrenNodes()) {
				queue.add(child);
			}
		}
		return null;
	}
}
