package Lab_8;

public class MiniMaxSearchAlgo {

    // function MINIMAX-DECISION(state) returns an action
    // inputs: state, current state in game
    // v <- MAX-VALUE(state)
    // return the action in SUCCESSORS(state) with value v
    public void execute(Node node) {
        // Compute the best value for the root using minimax (root is a MAX node)
        int bestValue = maxValue(node);
        // Find the child/action whose value equals the best value
        Node bestAction = null;
        for (Node child : node.getChildren()) {
            int childValue = minValue(child);
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
        // Print the decision
        System.out.println("Minimax decision: selected action = " + (bestAction == null ? "<none>" : bestAction.getLabel()) + ", value = " + bestValue);
    }

    // function MAX-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MIN_VALUE
    // for each s in SUCCESSORS(state) do
    //   v <- MAX(v, MIN-VALUE(s))
    // return v
    public int maxValue(Node node) {
        // If terminal node, return its utility/value
        if (node.isTerminal()) {
            return node.getValue();
        }

        int v = Integer.MIN_VALUE;
        for (Node child : node.getChildren()) {
            int childValue = minValue(child);
            v = Math.max(v, childValue);
        }
        node.setValue(v);
        return v;
    }
    // function MIN-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MAX_VALUE
    // for each s in SUCCESSORS(state) do
    //   v <- MIN(v, MAX-VALUE(s))
    // return v

    public int minValue(Node node) {
        // If terminal node, return its utility/value
        if (node.isTerminal()) {
            return node.getValue();
        }

        int v = Integer.MAX_VALUE;
        for (Node child : node.getChildren()) {
            int childValue = maxValue(child);
            v = Math.min(v, childValue);
        }
        node.setValue(v);
        return v;
    }
}
