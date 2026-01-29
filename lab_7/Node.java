package Lab_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Node implements Comparable<Node> {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		state = new Queen[N];
		generateBoard();
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public Queen[] getState() {
		return state;
	}

	public int getRow(int i) {
		return state[i].getRow();
	}

	public void setRow(int i, int row) {
		state[i].setRow(row);
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (state[i].isConflict(state[j])) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}

	public List<Node> generateBetterCandidates() {
		List<Node> result = new ArrayList<>();
		
		Node nextState = new Node(this.state);
		Node tmpState = new Node(this.state);
		int bestH = this.getH();
		int tempH;

		for (int i = 0; i < N; i++) {
			tmpState = new Node(state);
			for (int j = 0; j < N; j++) {
				tempH = tmpState.getH();
				if (tempH < bestH) {
					bestH = tempH;
					nextState = new Node(tmpState);
					result.add(nextState);
				}
				tmpState.state[i].move();
			}
		}
		return result;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<>();
		Node nextState = null;
		Node tmpState = null;

		for (int i = 0; i < N; i++) {
			tmpState = new Node(state);
			for (int j = 0; j < N; j++) {
				tmpState.state[i].move();
				nextState = new Node(tmpState);
				result.add(nextState);
			}
		}
		return result;
	}
	
	public Node getBestCandidate() {
		List<Node> re = this.generateAllCandidates();
		Collections.sort(re);
		return re.get(0);
	}

	public Node selectNextRandomCandidate() {
		Random rd = new Random();
		int index = rd.nextInt(N);
		Node result = new Node(this.state);
		result.state[index].setRow(rd.nextInt(N));
		return result;
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q ");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
	}

	@Override
	public int compareTo(Node o) {
		return (this.getH() - o.getH());
	}
}
