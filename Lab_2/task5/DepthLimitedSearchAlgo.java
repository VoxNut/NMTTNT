package Lab_2.task5;


import Lab_2.Node;

public class DepthLimitedSearchAlgo {
	public Node depthLimitedSearch(Node root, String goal, int limit) {
		return recursive_DLS(root, goal, limit);
	}

	public Node recursive_DLS(Node node, String goal, int limit) {
		// TODO
		return null;
	}

	// if result == cutoff then cutoff_occurred? return true
	// else if result is not failure then return result
	// if cutoff_occurred? then return cutoff else return failure
	public void execute(Node tree, int maxDepth) {
		// TODO
	}

	public Node execute(Node tree, String goal, int maxDepth) {
		// TODO
		return null;
	}
}
