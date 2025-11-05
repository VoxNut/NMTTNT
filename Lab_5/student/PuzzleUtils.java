package Lab_5.student;

import java.util.Comparator;

public class PuzzleUtils {

    // Compare 2 nodes by heuristic values
    public static Comparator<Node> HeuristicComparatorByH = Comparator.comparing(Node::getH);
    // Compare 2 nodes by F values
    public static Comparator<Node> HeuristicComparatorByF = Comparator.comparing(Node::getF);

    // distance between P1(x1, y1) and P2(x2, y2)
    public static int manhattanDistance(int[] current, int[] target) {
        return Math.abs(target[0] - current[0]) + Math.abs(target[1] - current[1]);
    }
}
