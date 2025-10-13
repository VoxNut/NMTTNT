package Lab_2.task4;

import java.util.Comparator;

import Lab_2.Node;

public class UniformCostSearchAlgo {
	public static Comparator<Node> getComparator() {
		return Comparator.comparing(Node::getPathCost).thenComparing(Node::getLabel);
	}

	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		// TODO
		return null;
	}

	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
		// TODO
		return null;
	}
}
