package Lab_7;

public class Main {
	public static void main(String[] args) {
		GA_NQueenAlgo algo = new GA_NQueenAlgo();
		algo.initPopulation();
		Node result = algo.execute();
		System.out.println("H: " + result.getH());
		result.displayBoard();
	}
}
