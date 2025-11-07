package Lab_6;

import java.util.ArrayList;
import java.util.List;

/**
 * Genetic Algorithm for 8-Queens problem
 */
public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private int maxGenerations;

    public GeneticAlgorithm() {
        this.populationSize = 100;
        this.mutationRate = 0.1;
        this.maxGenerations = 1000;
    }

    public GeneticAlgorithm(int populationSize, double mutationRate, int maxGenerations) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.maxGenerations = maxGenerations;
    }

    public Queen execute(int boardSize) {
        // Initialize population
        List<Queen> population = initializePopulation(boardSize);
        int generation = 0;

        while (generation < maxGenerations) {
            generation++;

            // Check if we found a solution
            Queen best = findBest(population);
            if (best.isGoal()) {
                System.out.println("Genetic Algorithm: Found solution in generation " + generation);
                return best;
            }

            // Create new generation
            List<Queen> newPopulation = new ArrayList<>();

            while (newPopulation.size() < populationSize) {
                // Selection
                Queen parent1 = selectParent(population);
                Queen parent2 = selectParent(population);

                // Crossover
                Queen child = crossover(parent1, parent2);

                // Mutation
                if (Math.random() < mutationRate) {
                    mutate(child);
                }

                newPopulation.add(child);
            }

            population = newPopulation;

            // Print progress every 100 generations
            if (generation % 100 == 0) {
                System.out.println("Generation " + generation + ", best conflicts = "
                        + findBest(population).getConflicts());
            }
        }

        Queen best = findBest(population);
        System.out.println("Genetic Algorithm: Completed " + maxGenerations
                + " generations, best conflicts = " + best.getConflicts());
        return best;
    }

    // Initialize random population
    private List<Queen> initializePopulation(int boardSize) {
        List<Queen> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Queen(boardSize));
        }
        return population;
    }

    // Find the best individual in population
    private Queen findBest(List<Queen> population) {
        Queen best = population.get(0);
        int minConflicts = best.getConflicts();

        for (Queen individual : population) {
            int conflicts = individual.getConflicts();
            if (conflicts < minConflicts) {
                minConflicts = conflicts;
                best = individual;
            }
        }

        return best.copy();
    }

    // Select a parent using tournament selection
    private Queen selectParent(List<Queen> population) {
        // Tournament selection: pick best from random subset
        int tournamentSize = 5;
        Queen best = null;
        int minConflicts = Integer.MAX_VALUE;

        for (int i = 0; i < tournamentSize; i++) {
            Queen candidate = population.get((int) (Math.random() * population.size()));
            int conflicts = candidate.getConflicts();
            if (conflicts < minConflicts) {
                minConflicts = conflicts;
                best = candidate;
            }
        }

        return best;
    }

    // Crossover: combine two parents to create a child
    private Queen crossover(Queen parent1, Queen parent2) {
        int size = parent1.getSize();
        int[] childBoard = new int[size];

        // Single-point crossover
        int crossoverPoint = (int) (Math.random() * size);

        for (int i = 0; i < size; i++) {
            if (i < crossoverPoint) {
                childBoard[i] = parent1.getPosition(i);
            } else {
                childBoard[i] = parent2.getPosition(i);
            }
        }

        return new Queen(childBoard);
    }

    // Mutate: randomly change one queen's position
    private void mutate(Queen individual) {
        int size = individual.getSize();
        int col = (int) (Math.random() * size);
        int row = (int) (Math.random() * size);
        individual.setPosition(col, row);
    }
}
