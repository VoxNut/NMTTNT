package Lab_9;

import java.util.Arrays;
import java.util.List;

import Lab_9.Task_1_2.Node;
import Lab_9.Task_3.MinimaxAlgo;

public class TestNode {
	public static void main(String[] args) { 
		Node node = new Node();
		Integer[] data = { 7 };
		node.addAll(Arrays.asList(data));

		MinimaxAlgo algo = new MinimaxAlgo();
		algo.execute(node);
		
	}
}