package Lab_5.student.Task_3;

import java.util.*;

import Lab_5.student.Node;
import Lab_5.student.Task_1.Puzzle;

public class AStarSearchAlgo {

    // Task 3.
    public Node execute(Puzzle p) {
        Node iNode = p.getInitialState();
        Node gNode = p.getGoalState();
     
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        Set<Node> frontierSet = new HashSet<>(); 
        Map<Node, Integer> gScores = new HashMap<>(); 

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
                return current; 
            }

            List<Node> children = p.getSuccessors(current);
            for (Node child : children) {
                int tentativeG = current.getG() + 1;

        
                if (!gScores.containsKey(child) || tentativeG < gScores.get(child)) {
                    child.setG(tentativeG);
                    child.setH(p.computeH2(child));
                    gScores.put(child, tentativeG);

                    if (frontierSet.contains(child)) {
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
