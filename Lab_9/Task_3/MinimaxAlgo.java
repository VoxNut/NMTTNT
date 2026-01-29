package Lab_9.Task_3;

import java.util.List;

import Lab_9.Task_1_2.Node;

public class MinimaxAlgo {
	public void execute(Node node) {
		int v = minValue(node);
		System.out.println(v);
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		if (node.isTerminal()) {
			return utility(node);
		}
		int v = Integer.MIN_VALUE;
		List<Node> list = node.getSuccessors();
		for(Node s : list) {
			v = Math.max(v, minValue(s));
		}
		return v;
	}

	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v
	public int minValue(Node node) {
		if(node.isTerminal()) {
			return utility(node);
		}
		int v = Integer.MAX_VALUE;
		List<Node> list = node.getSuccessors();
		for(Node s : list) {
			v = Math.min(v, maxValue(s));
		}
		return v;
	}

	public int utility(Node node) {
		int sum = 0;
		for (int val : node.getData()) {
			sum += val;
		}
		return sum;
	}

}
