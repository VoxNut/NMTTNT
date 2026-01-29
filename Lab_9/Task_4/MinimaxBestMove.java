package Lab_9.Task_4;

import Lab_9.Task_1_2.Node;
import Lab_9.Task_3.MinimaxAlgo;

import java.util.List;

public class MinimaxBestMove {
    public static void showBestMoveForMin(Node root) {
        List<Node> successors = root.getSuccessors();
        if (successors.isEmpty()) {
            return;
        }
        MinimaxAlgo algo = new MinimaxAlgo();
        int bestValue = Integer.MAX_VALUE;
        Node bestMove = null;
        for (Node child : successors) {
            int value = algo.maxValue(child);
            if (value < bestValue) {
                bestValue = value;
                bestMove = child;
            }
        }
        System.out.println("Best move for MIN at root: " + bestMove + ", Value: " + bestValue);
    }
}
