package Lab_8;

import java.util.List;

public class AlphaBetaSearchAlgo {

    // Counters for statistics
    private int nodesVisited = 0;
    private int prunedBranchesCount = 0; // number of pruned immediate children
    private int prunedNodesCount = 0; // total nodes pruned (including descendants)

    // function ALPHA-BETA-SEARCH(state) returns an action
    // inputs: state, current state in game
    // v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
    // return the action in SUCCESSORS(state) with value v
    public void execute(Node node) {
        // Reset counters
        nodesVisited = 0;
        prunedBranchesCount = 0;
        prunedNodesCount = 0;

        // Compute the best value for the root using alpha-beta (root is a MAX node)
        int bestValue = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        // Find the child/action whose value equals the best value
        Node bestAction = null;
        for (Node child : node.getChildren()) {
            int childValue = minValue(child, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
            if (childValue == bestValue) {
                if (bestAction == null) {
                    bestAction = child;
                } else {
                    if (child.getLabel().compareTo(bestAction.getLabel()) < 0) {
                        bestAction = child;
                    }
                }
            }
        }
        System.out.println("Alpha-Beta decision: selected action = " + (bestAction == null ? "<none>" : bestAction.getLabel()) + ", value = " + bestValue);
        System.out.println("Nodes visited: " + nodesVisited + ", pruned branches: " + prunedBranchesCount + ", pruned nodes: " + prunedNodesCount);
    }

    // function MAX-VALUE(state, alpha, beta) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- MIN_VALUE;
    // for each s in SUCCESSORS(state) do
    //   v <- MAX(v, MIN-VALUE(s, alpha, beta))
    //   if v >= beta then return v
    //   alpha <- MAX(alpha, v)
    // return v
    public int maxValue(Node node, int alpha, int beta) {
        return maxValue(node, alpha, beta, 0);
    }

    private int maxValue(Node node, int alpha, int beta, int depth) {
        if (node.isTerminal()) {
            nodesVisited++;
            return node.getValue();
        }
        nodesVisited++;
        int v = Integer.MIN_VALUE;
        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(i);
            int childValue = minValue(child, alpha, beta, depth + 1);
            v = Math.max(v, childValue);
            if (v >= beta) {
                node.setValue(v);
                // Count and show pruned branches: the remaining children
                int remaining = node.getChildren().size() - (i + 1);
                if (remaining > 0) {
                    List<Node> children = node.getChildren();
                    int prunedThis = 0;
                    for (int j = i + 1; j < children.size(); j++) {
                        prunedBranchesCount++;
                        int nodes = subtreeNodeCount(children.get(j));
                        prunedNodesCount += nodes;
                        prunedThis += nodes;
                    }
                    System.out.println(indent(depth) + "Prune at MAX node '" + node.getLabel() + "' after child '" + child.getLabel() + "' -> pruned children: " + getChildLabels(node, i + 1) + " (" + prunedThis + " nodes pruned, " + prunedNodesCount + " total)");
                }
                return v; // beta cutoff
            }
            alpha = Math.max(alpha, v);
        }
        node.setValue(v);
        return v;
    }
    // function MIN-VALUE(state, alpha , beta) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MAX_VALUE
    // for each s in SUCCESSORS(state) do
    //   v <- MIN(v, MAX-VALUE(s, alpha , beta))
    //   if v <= alpha then return v
    //   beta <- MIN(beta ,v)
    // return v

    public int minValue(Node node, int alpha, int beta) {
        return minValue(node, alpha, beta, 0);
    }

    private int minValue(Node node, int alpha, int beta, int depth) {
        if (node.isTerminal()) {
            nodesVisited++;
            return node.getValue();
        }
        nodesVisited++;
        int v = Integer.MAX_VALUE;
        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(i);
            int childValue = maxValue(child, alpha, beta, depth + 1);
            v = Math.min(v, childValue);
            if (v <= alpha) {
                node.setValue(v);
                int remaining = node.getChildren().size() - (i + 1);
                if (remaining > 0) {
                    List<Node> children = node.getChildren();
                    int prunedThis = 0;
                    for (int j = i + 1; j < children.size(); j++) {
                        prunedBranchesCount++;
                        int nodes = subtreeNodeCount(children.get(j));
                        prunedNodesCount += nodes;
                        prunedThis += nodes;
                    }
                    System.out.println(indent(depth) + "Prune at MIN node '" + node.getLabel() + "' after child '" + child.getLabel() + "' -> pruned children: " + getChildLabels(node, i + 1) + " (" + prunedThis + " nodes pruned, " + prunedNodesCount + " total)");
                }
                return v; // alpha cutoff
            }
            beta = Math.min(beta, v);
        }
        node.setValue(v);
        return v;
    }

    // Helper: get comma-separated labels of children from startIndex to end
    private String getChildLabels(Node node, int startIndex) {
        StringBuilder sb = new StringBuilder();
        for (int j = startIndex; j < node.getChildren().size(); j++) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(subtreeLabels(node.getChildren().get(j)));
        }
        return sb.toString();
    }

    // Helper: return subtree labels in a compact form e.g. O(W,X)
    private String subtreeLabels(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node.getLabel());
        if (!node.isTerminal()) {
            sb.append("(");
            boolean first = true;
            for (Node c : node.getChildren()) {
                if (!first) {
                    sb.append(",");
                }
                first = false;
                sb.append(subtreeLabels(c));
            }
            sb.append(")");
        }
        return sb.toString();
    }

    // Helper: indentation string
    private String indent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    // Return the number of nodes in the subtree rooted at 'node'
    private int subtreeNodeCount(Node node) {
        int count = 1; // count itself
        for (Node child : node.getChildren()) {
            count += subtreeNodeCount(child);
        }
        return count;
    }
}
