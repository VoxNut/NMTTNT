package Lab_2_3;

import Lab_2_3.Task_3.TreeDepthFirstSearchAlgo;
import Lab_2_3.Task_3.TreeBreadthFirstSearchAlgo;

public class TestTask3 {
    public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeA = new Node("A"); Node nodeB = new Node("B");
        Node nodeC = new Node("C"); Node nodeD = new Node("D");
        Node nodeE = new Node("E"); Node nodeF = new Node("F");
        Node nodeG = new Node("G"); Node nodeH = new Node("H");

        nodeS.addEdge(nodeA, 5); nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4); nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4); nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2); nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6); nodeF.addEdge(nodeG, 1);

        System.out.println("Testing Tree DFS:");
        TreeDepthFirstSearchAlgo dfs = new TreeDepthFirstSearchAlgo();
        Node resultDFS = dfs.execute(nodeS, "G");
        System.out.println("Path S -> G: " + NodeUtils.printPath(resultDFS));
        
        Node resultDFS_Start = dfs.execute(nodeS, "A", "G");
        System.out.println("Path A -> G: " + NodeUtils.printPath(resultDFS_Start));


        System.out.println("\nTesting Tree BFS:");
        TreeBreadthFirstSearchAlgo bfs = new TreeBreadthFirstSearchAlgo();
        Node resultBFS = bfs.execute(nodeS, "G");
        System.out.println("Path S -> G: " + NodeUtils.printPath(resultBFS));
        
         Node resultBFS_Start = bfs.execute(nodeS, "A", "G");
        System.out.println("Path A -> G: " + NodeUtils.printPath(resultBFS_Start));
    }
}
