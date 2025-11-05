package Lab_5.student;

public class Test {

    public static void main(String[] args) {
        Puzzle p = new Puzzle();
        p.readInput("Lab_5/txt/PuzzleMap.txt", "Lab_5/txt/PuzzleGoalState.txt");

        System.out.println(p.getInitialState());

        System.out.println(p.getGoalState());

        System.out.println("\n --- Greedy Best First Search ---");
        // GreedyBestFirstSearchAlgo gbfs = new GreedyBestFirstSearchAlgo();
        // System.out.println(gbfs.execute(p));
        System.out.println("\n --- A Star Search ---");
        AStarSearchAlgo astar = new AStarSearchAlgo();
        System.out.println(astar.execute(p));

    }
}
