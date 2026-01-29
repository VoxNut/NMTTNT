package Lab_8.Task_2_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Lab_8.Node;

public class AlphaBetaSearchAlgo {
	private List<Node> pruned = new ArrayList<Node>();

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	public void execute(Node node) {
		int v = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println("Best value: " + v);
		for (Node child : node.getChildren()) {
			if (minValue(child, Integer.MIN_VALUE, Integer.MAX_VALUE) == v) {
				System.out.println("Best move for " + node.getLabel() + ": " + child.getLabel());
				break;
			}
		}
		printPruned(pruned);
	}

	public String findPath(Node node, int value) {
		if (node.getValue() == value) {
			return node.toString();
		}
		for (Node s : node.getChildren()) {
			String childPath = findPath(s, value);
			if (childPath != null) {
				return node.getLabel() + " -> " + childPath;
			}
		}
		return null;
	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- MIN_VALUE;
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v >= beta then return v
	// alpha <- MAX(alpha, v)
	// return v

	public int maxValue(Node node, int alpha, int beta) {
		int v = Integer.MIN_VALUE;
		if (node.isTerminal()) {
			return node.getValue();
		} else {
			for(int i = 0; i < node.getChildren().size(); i ++) {
				v = Math.max(v, minValue(node.getChildren().get(i), alpha, beta));
				if(v >= beta) {
					for(int j = i + 1; j < node.getChildren().size(); j++) {
						pruned.add(node.getChildren().get(j));
					}
					return v;
				}
				alpha = Math.max(alpha, v);
			}
		}
		return v;
	}
	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v

	public int minValue(Node node, int alpha, int beta) {
		int v = Integer.MAX_VALUE;
		if (node.isTerminal()) {
			return node.getValue();
		} else {
			for(int i = 0; i < node.getChildren().size(); i++) {
				v = Math.min(v, maxValue(node.getChildren().get(i), alpha, beta));
				if(v <= alpha) {
					for(int j = i + 1; j < node.getChildren().size(); j++) {
						pruned.add(node.getChildren().get(j));
					}
					return v;
				}
				beta = Math.min(beta, v);
			}
		}
		return v;
	}
	
	public void printPruned(List<Node> list) {
		for(Node n : list) {
			System.out.println(n);
		}
	}
}
