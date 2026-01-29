package Lab_6.Task_2_3;
import java.util.ArrayList;
import Lab_6.Task_1.Queen;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Node implements Comparator<Node>{
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		state = new Queen[N];
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

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < state.length; i++) {
			for (int j = i + 1; j < state.length; j++) {
					if (state[i].isConflict(state[j])) {
						heuristic++;
					}
			}
		}
		return heuristic;
	}
	
	public void moveOverN(Queen q) {
		if(q.getRow() + 1 >= N) {
			q.setRow(0);
		}
		else {
			q.move();
		}
	}
	
	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();
		for (int i = 0; i < state.length; i++) {
			Node sucessor = new Node(state);
			Queen[] sucessState = sucessor.getState();
			moveOverN(sucessState[i]);
			sucessor.setState(sucessState);
			result.add(sucessor);
		}
		return result;
	}

	public Queen[] getState() {
		return state;
	}

	public void setState(Queen[] state) {
		this.state = state;
	}

	@Override
	public int compare(Node o1, Node o2) {
		return o1.getH() - o2.getH();
	}
	
	public Node generateBoard() {
		Node newNode = new Node();
		Queen[] queens = new Queen[N];
		Random rd = new Random();
		for (int i = 0; i < N; i++) {
			queens[i] = new Queen(rd.nextInt(N), i);
		}
		newNode.setState(queens);
		return newNode;
	}
	public Node selectNextRandomCandidate() {
		Node result = new Node(this.state);
		Random rd = new Random();
		int col = rd.nextInt(N);
		int row = rd.nextInt(N);
		result.state[col].setRow(row);
		return result;
	}
}
