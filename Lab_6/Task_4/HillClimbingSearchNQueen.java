package Lab_6.Task_4;

import java.util.List;
import Lab_6.Task_2_3.Node;

public class HillClimbingSearchNQueen {
	int stepClimbed = 0;
	int stepClimbedAfterRandomRestart = 0;
	int randomRestarts = 0;

	public Node execute(Node initialState) {
		stepClimbed = 0;
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

			stepClimbed++;
			current = bestSuccessor;
		}
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		stepClimbedAfterRandomRestart = 0;
		randomRestarts = 0;
		Node state = execute(initialState);
		stepClimbedAfterRandomRestart += stepClimbed;
		
		while(state.getH() != 0) {
			state = state.generateBoard();
			randomRestarts++;
			state = execute(state);
			stepClimbedAfterRandomRestart += stepClimbed;
		}
		return state;
	}

    public int getStepClimbed() {
        return stepClimbed;
    }

    public int getStepClimbedAfterRandomRestart() {
        return stepClimbedAfterRandomRestart;
    }

    public int getRandomRestarts() {
        return randomRestarts;
    }
}
