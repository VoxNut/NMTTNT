package Lab_6.Task_4;

import Lab_6.Task_2_3.Node;

public class SA_SearchAlgo {
	public Node execute(Node initialState) {
		Node current = initialState;
		Node next = null;
		int t = 1;
		double T;
		while (true) {
			T = 1000 * Math.pow(0.95, t); // Schedule: T0 = 1000, cooling rate = 0.95
			if (T < 0.00001) { // Stop when T is close to 0
				return current;
			}
			
			next = current.selectNextRandomCandidate();
			// deltaE = Value[next] - Value[current] = (-H_next) - (-H_current) = H_curr - H_next
			int deltaE = current.getH() - next.getH();
			
			if (deltaE > 0) { // Found better neighbor
				current = next;
			} else {
				// Accept worse neighbor with probability e^(deltaE/T)
				if (Math.exp(deltaE / T) > Math.random()) {
					current = next;
				}
			}
			t++;
		}
	}
}
