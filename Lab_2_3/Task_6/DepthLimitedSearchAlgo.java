package Lab_2_3.Task_6;

import Lab_2_3.Node;

public class DepthLimitedSearchAlgo {
	public Node depthLimitedSearch(Node root, String goal, int limit) {
		return recursive_DLS(root, goal, limit);
	}

	public Node recursive_DLS(Node node, String goal, int limit) {
		if (node.getLabel().equals(goal)) {
			return node;
		} else if (limit == 0) {
			return null;
		} else {
			for (Node child : node.getChildrenNodes()) {
				child.setParent(node);
				Node result = recursive_DLS(child, goal, limit - 1);
				if (result != null) {
					return result;
				}
			}
			return null;
		}
	}

	public Node execute(Node tree, String goal, int maxDepth) {
		return depthLimitedSearch(tree, goal, maxDepth);
	}
}
