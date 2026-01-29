package Lab_6.Task_2_3;
import java.util.List;

public class HillClimbing {
	public Node excute(Node initialState) {
		Node current = initialState;
		while (true) {
			List<Node> successors = current.generateAllCandidates();
			if (successors.isEmpty()) {
				return current;
			}

			Node bestSuccessor = successors.get(0);
			for (Node s : successors) {
				if (bestSuccessor.getH() > s.getH()) {
					bestSuccessor = s;
				}
			}

			if (bestSuccessor.getH() >= current.getH()) {
				return current;
			}

			current = bestSuccessor;
		}
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		// Enter your code here.
		Node state = excute(initialState);
		while(state.getH() != 0) {
			state = state.generateBoard();
			state = excute(state);
		}
		return state;
	}
}
