package Lab_6.Task_4;

import Lab_6.Task_2_3.Node;

public class SA_SearchAlgo {
	public Node execute(Node initialState) {
		Node current = initialState;
		Node next = null;
		int t = 1;
		double T;
		while (true) {
			T = 1000 * Math.pow(0.95, t);
			if (T < 0.00001) {
				return current;
			}
			
			next = current.selectNextRandomCandidate();
			int deltaE = current.getH() - next.getH();
			
			if (deltaE > 0) {
				current = next;
			} else {
				if (Math.exp(deltaE / T) > Math.random()) {
					current = next;
				}
			}
			t++;
		}
	}
}
