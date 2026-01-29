package Lab_5.student.Task_3;

import java.util.*;

import Lab_5.student.Node;
import Lab_5.student.Task_1.Puzzle;

public class AStarSearchAlgo {

    // Task 3.
    public Node execute(Puzzle p) {
        Node iNode = p.getInitialState();
        Node gNode = p.getGoalState();
        // PriorityQueue sắp xếp theo f(n)
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        Set<Node> frontierSet = new HashSet<>(); // Để kiểm tra nhanh O(1)
        Map<Node, Integer> gScores = new HashMap<>(); // Lưu g-value tốt nhất cho mỗi node

        iNode.setH(p.computeH2(iNode));
        iNode.setG(0);
        frontier.add(iNode);
        frontierSet.add(iNode);
        gScores.put(iNode, 0);

        int iterations = 0;

        while (!frontier.isEmpty()) {
            iterations++;

            Node current = frontier.poll();
            frontierSet.remove(current);

            if (current.equals(gNode)) {
                return current; // tìm thấy lời giải
            }

            List<Node> children = p.getSuccessors(current);
            for (Node child : children) {
                int tentativeG = current.getG() + 1;

                // Nếu tìm được đường đi tốt hơn đến child
                if (!gScores.containsKey(child) || tentativeG < gScores.get(child)) {
                    child.setG(tentativeG);
                    child.setH(p.computeH2(child));
                    gScores.put(child, tentativeG);

                    if (frontierSet.contains(child)) {
                        // Node đã có trong frontier với score cũ (tệ hơn)
                        // Phải remove và re-add để PriorityQueue cập nhật thứ tự
                        frontier.remove(child); // O(n)
                    }
                    frontier.add(child);
                    frontierSet.add(child);
                }
            }
        }

        return null;
    }
}
