package Lab_8.Task_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Lab_8.Node;

public class AlphaBetaRightToLeftSearchAlgo {
	private List<Node> pruned = new ArrayList<Node>();

	public void execute(Node node) {
		int v = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println("Best value: " + v);
		
		// Find best move
		List<Node> children = new ArrayList<>(node.getChildren());
		Collections.sort(children, Node.LabelComparatorReverse); // Sort logic for right-to-left
		for (Node child : children) {
			// This heuristic check is simplistic. In a real game,
			// we would re-evaluate or store the best move.
			// But for this assignment, we likely want to just find which child returns v.
			// However with alpha-beta, we might not have explored all.
		}
	}

	public int maxValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}
		int v = Integer.MIN_VALUE;
		List<Node> children = new ArrayList<>(node.getChildren());
		Collections.sort(children, Node.LabelComparatorReverse); // Right to Left order

		for(int i = 0; i < children.size(); i++) {
			Node child = children.get(i);
			v = Math.max(v, minValue(child, alpha, beta));
			if (v >= beta) {
				// Prune remaining siblings
				for(int j = i + 1; j < children.size(); j++) {
					pruned.add(children.get(j));
				}
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}

	public int minValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}
		int v = Integer.MAX_VALUE;
		List<Node> children = new ArrayList<>(node.getChildren());
		Collections.sort(children, Node.LabelComparatorReverse); // Right to Left order

		for(int i = 0; i < children.size(); i++) {
			Node child = children.get(i);
			v = Math.min(v, maxValue(child, alpha, beta));
			if (v <= alpha) {
				// Prune remaining siblings
				for(int j = i + 1; j < children.size(); j++) {
					pruned.add(children.get(j));
				}
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}

	public void printPruned() {
		System.out.print("Pruned nodes: ");
		for(Node n : pruned) {
			System.out.print(n.getLabel() + " ");
		}
		System.out.println();
	}
}
