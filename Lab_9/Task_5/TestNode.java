package Lab_9.Task_5;

import Lab_9.Task_1_2.Node;
import Lab_9.Task_3.MinimaxAlgo;
import Lab_9.Task_4.MinimaxBestMove;

import java.util.Arrays;

public class TestNode {
	public static void main(String[] args) { 
		Node node = new Node();
		Integer[] data = { 7 };
		node.addAll(Arrays.asList(data));

		MinimaxAlgo algo = new MinimaxAlgo();
		algo.execute(node);
	
		MinimaxBestMove.showBestMoveForMin(node);
		
	
		Node node8 = new Node();
		Integer[] data8 = { 8 };
		node8.addAll(Arrays.asList(data8));
		MinimaxBestMove.showBestMoveForMin(node8);

		
		Node node9 = new Node();
		Integer[] data9 = { 9 };
		node9.addAll(Arrays.asList(data9));
		MinimaxBestMove.showBestMoveForMin(node9);
	}
}