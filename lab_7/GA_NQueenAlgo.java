package Lab_7;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<>();
	Random rd = new Random();

	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		int count = 0;
		List<Node> new_Population = new ArrayList<>();
		while(count < MAX_ITERATIONS) {
			for(int i = 0; i < POP_SIZE; i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();
				if(x.equals(y)) {
					y = getParentByRandomSelection();
				}
				Node child = reproduce(x, y);
				if(rd.nextDouble() < MUTATION_RATE) mutate(child);
				new_Population.add(child);
			}
			population = new ArrayList<>(new_Population);
			new_Population.clear();
			count++;
		}
		Collections.sort(population);
		return population.get(0);
	}

	public void mutate(Node node) {
		node.selectNextRandomCandidate();
	}

	public Node reproduce(Node x, Node y) {
		Node child = new Node();
		Queen[] childState = child.getState();
		for (int i = 0; i < childState.length; i++) {
			child.setRow(i, x.getRow(i));
		}
		
		int pivot = rd.nextInt(Node.N - 2) + 1;
		for (int j = pivot; j < Node.N; j++) {
			child.setRow(j, y.getRow(j));
		}
		return child;
	}

	public Node getParentByTournamentSelection() {
		int K = rd.nextInt(POP_SIZE) + 1;
		List<Node> tournament = new ArrayList<>();
		for(int i = 0; i < K; i++) {
			tournament.add(population.get(rd.nextInt(POP_SIZE)));
		}
		Collections.sort(tournament);
		return tournament.get(0);
	}

	public Node getParentByRandomSelection() {
		return population.get(rd.nextInt(POP_SIZE));
	}
}