package Lab_8.Task_1;


import java.util.List;

import Lab_8.Node;

public class MiniMaxSearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	public void execute(Node node) {
		int v = maxValue(node);
		System.out.println("Best value: " + v);
		for(Node child : node.getChildren()) {
			if (minValue(child) == v) {
				System.out.println("Best move for " + node.getLabel() + ": " + child.getLabel());
				break;
			}
		}
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	//   v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		int v = Integer.MIN_VALUE;
		if(node.isTerminal()) {
			return node.getValue();
		}
		else {
			for(Node s : node.getChildren()) {
				v = Math.max(v, minValue(s));
			}
		}
		return v;
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	//   v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		int v = Integer.MAX_VALUE;
		if(node.isTerminal()) {
			return node.getValue();
		}
		else {
			for(Node s : node.getChildren()) {
				v = Math.min(v, maxValue(s));
			}
		}
		return v;
	}
}
