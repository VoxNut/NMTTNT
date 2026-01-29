package Lab_5.student.Task_4;

import Lab_5.student.Node;
import Lab_5.student.Task4_BFSDFSHillClimbingAlgo;
import Lab_5.student.Task_1.Puzzle;
import Lab_5.student.Task_2.GreedyBestFirstSearchAlgo;
import Lab_5.student.Task_3.AStarSearchAlgo;

public class ComparisonTest {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("Lab_5/txt/PuzzleMap.txt", "Lab_5/txt/PuzzleGoalState.txt");

		System.out.println("Initial State:\n" + p.getInitialState());
		System.out.println("Goal State:\n" + p.getGoalState());

		testBFS(p);
		testDFS(p);
		testHillClimbing(p);
		testGreedy(p);
		testAStar(p);
	}

	private static void testBFS(Puzzle p) {
		System.out.println("--- BFS ---");
		Task4_BFSDFSHillClimbingAlgo algo = new Task4_BFSDFSHillClimbingAlgo();
		long start = System.currentTimeMillis();
		Node res = algo.executeBFS(p);
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start) + "ms");
		System.out.println("Result: " + (res != null ? "Found" : "Not Found"));
	}

	private static void testDFS(Puzzle p) {
		System.out.println("--- DFS ---");
		Task4_BFSDFSHillClimbingAlgo algo = new Task4_BFSDFSHillClimbingAlgo();
		long start = System.currentTimeMillis();
		Node res = algo.executeDFS(p);
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start) + "ms");
		System.out.println("Result: " + (res != null ? "Found" : "Not Found"));
	}

	private static void testHillClimbing(Puzzle p) {
		System.out.println("--- Hill Climbing ---");
		Task4_BFSDFSHillClimbingAlgo algo = new Task4_BFSDFSHillClimbingAlgo();
		long start = System.currentTimeMillis();
		Node res = algo.executeHillClimbing(p);
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start) + "ms");
		System.out.println("Result: " + (res != null ? "Found" : "Not Found"));
	}

	private static void testGreedy(Puzzle p) {
		System.out.println("--- Greedy ---");
		GreedyBestFirstSearchAlgo algo = new GreedyBestFirstSearchAlgo();
		long start = System.currentTimeMillis();
		Node res = algo.execute(p);
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start) + "ms");
		System.out.println("Result: " + (res != null ? "Found" : "Not Found"));
	}

	private static void testAStar(Puzzle p) {
		System.out.println("--- A* ---");
		AStarSearchAlgo algo = new AStarSearchAlgo();
		long start = System.currentTimeMillis();
		Node res = algo.execute(p);
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start) + "ms");
		System.out.println("Result: " + (res != null ? "Found" : "Not Found"));
	}
}
