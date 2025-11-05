package Lab_2_3.task5;


import Lab_2_3.Node;

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

	}

	public Node execute(Node tree, String goal, int maxDepth) {
        if(tree.getLabel().equals(goal)) {
            return tree;
        }
        else if(maxDepth == 0) {
            return null;
        }
       else {
            for (Node node : tree.getChildrenNodes()) {
                Node res = execute(node, goal, maxDepth - 1);
                node.setParent(tree);
                if (res != null) {
                    return res;
                }
            }
        }

        return null;

	}
}
