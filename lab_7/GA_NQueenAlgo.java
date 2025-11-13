package lab_7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*
 GA for N-Queens
 - initPopulation(): create POP_SIZE random individuals
 - execute(): run main GA loop, returns best Node found (stops if h==0)
 - mutate(Node): move one random queen to a random row
 - reproduce(Node,Node): single-point crossover on columns
 - getParentByTournamentSelection(): tournament of TOURNAMENT_SIZE
 - getParentByRandomSelection(): random pick
 */
public class GA_NQueenAlgo {

    private static final int POP_SIZE = 100;
    private static final double MUTATION_RATE = 0.1;
    private static final int TOURNAMENT_SIZE = 5;
    private static final int MAX_GENERATIONS = 1000;

    private List<Node> population;
    private Random rnd = new Random();

    public GA_NQueenAlgo() {
        initPopulation();
    }

    // initPopulation(): Tạo quần thể ban đầu gồm 100 cá thể
    public void initPopulation() {
        population = new ArrayList<>(POP_SIZE);
        for (int i = 0; i < POP_SIZE; i++) {
            population.add(new Node());
        }
    }

    // getParentByTournamentSelection(): Chọn cha mẹ bằng Tournament Selection
    public Node getParentByTournamentSelection() {
        Node best = null;
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            Node candidate = population.get(rnd.nextInt(population.size()));
            if (best == null || candidate.getH() < best.getH()) {
                best = candidate;
            }
        }
        // return a copy to avoid accidental modification of population members
        return new Node(best);
    }

    // getParentByRandomSelection(): Chọn cha mẹ ngẫu nhiên
    public Node getParentByRandomSelection() {
        Node pick = population.get(rnd.nextInt(population.size()));
        return new Node(pick);
    }

    // reproduce(Node x, Node y): Lai ghép 2 cá thể cha mẹ để sinh con
    public Node reproduce(Node x, Node y) {
        int N = Node.N;
        // single-point crossover on column index
        int cross = 1 + rnd.nextInt(N - 1); // between 1 and N-1
        Queen[] childState = new Queen[N];
        Queen[] xs = x.getState();
        Queen[] ys = y.getState();
        for (int col = 0; col < N; col++) {
            if (col < cross) {
                childState[col] = new Queen(xs[col].getRow(), xs[col].getColumn());
            } else {
                childState[col] = new Queen(ys[col].getRow(), ys[col].getColumn());
            }
        }
        return new Node(childState);
    }

    // mutate(Node node): Thực hiện đột biến bằng cách di chuyển 1 quân hậu ngẫu nhiên
    public void mutate(Node node) {
        int N = Node.N;
        int col = rnd.nextInt(N);
        int newRow = rnd.nextInt(N);
        // ensure some change (optional)
        node.setRow(col, newRow);
    }

    // execute(): Hàm chính: thực hiện thuật toán GA, lặp lại qua các thế hệ
    // Returns the best Node found (heuristic minimal). Stops early if h == 0.
    // public Node execute() {
    //     // ensure initialized
    //     if (population == null || population.size() != POP_SIZE) {
    //         initPopulation();
    //     }
    //     List<Node> newPop = new ArrayList<>(POP_SIZE);
    //     for (int gen = 0; gen < MAX_GENERATIONS; gen++) {
    //         // sort by heuristic ascending (lower is better)
    //         Collections.sort(population);
    //         Node best = population.getFirst();
    //         int bestH = best.getH();
    //         // early stop if solution found
    //         if (bestH == 0) {
    //             return new Node(best);
    //         }
    //         // Elitism: carry top 1 to next generation
    //         newPop.clear();
    //         newPop.add(new Node(best));
    //         // fill rest of new population
    //         while (newPop.size() < POP_SIZE) {
    //             // select parents (use tournament selection for better pressure)
    //             Node parent1 = getParentByTournamentSelection();
    //             Node parent2 = getParentByTournamentSelection();
    //             // reproduce
    //             Node child = reproduce(parent1, parent2);
    //             // mutate with some probability
    //             if (rnd.nextDouble() < MUTATION_RATE) {
    //                 mutate(child);
    //             }
    //             newPop.add(child);
    //         }
    //         // replace population
    //         population = new ArrayList<>(newPop);
    //     }
    //     // return best found after max generations
    //     Collections.sort(population);
    //     return new Node(population.getFirst());
    // }
    public Node execute() {

        //init
        if (population == null || population.size() != POP_SIZE) {
            initPopulation();
        }

        //select
        List<Node> newPop = new ArrayList<>();
        for (int i = 0; i < MAX_GENERATIONS; i++) {
            Node best = population.stream().sorted().findFirst().get();
            int bestH = best.getH();
            // early stop
            if (bestH == 0) {
                return new Node(best);
            }

            newPop.clear();
            newPop.add(best);

            //repopulate
            while (newPop.size() < POP_SIZE) {

                Node parent_1 = getParentByTournamentSelection();
                Node parent_2 = getParentByTournamentSelection();

                //reproduce
                Node child = reproduce(parent_1, parent_2);

                //mutate
                if (rnd.nextDouble() < MUTATION_RATE) {
                    mutate(child);
                }

                newPop.add(child);

            }

            population = new ArrayList<>(newPop);

        }

        return population.stream().sorted().findFirst().get();

    }

    /**
     * Simple runner / entry point to execute the GA and show results.
     */
    public static void main(String[] args) {
        System.out.println("GA N-Queens: starting...");
        GA_NQueenAlgo ga = new GA_NQueenAlgo();
        Node best = ga.execute();
        System.out.println("Best heuristic (number of conflicts): " + best.getH());
        System.out.println("Board configuration:");
        best.displayBoard();
        System.out.println("Done.");
    }
}
