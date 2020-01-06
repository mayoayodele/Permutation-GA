/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepermutationga;
import java.util.ArrayList;
/**
 *
 * @author mayoayodele
 */
public class SimplePermutationGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int number_of_generations = 10;
        int problem_size = 4;
        int population_size = 30;
        float crossover_probability = 0.7f;
        float mutation_probability = 0.01f;
        GAOperators operators = new GAOperators(population_size, problem_size, crossover_probability, mutation_probability);

        ArrayList<ArrayList<Integer>> population = operators.getInitialSolutions();

        for (int i = 0; i < number_of_generations; i++) {
            ArrayList<ArrayList<Integer>> new_population = operators.performSelection(population);
            population = (ArrayList<ArrayList<Integer>>) new_population.clone();
        }

        ArrayList<Integer> best_solution = population.get(0);
        int best_fitness = operators.evaluationSolution(best_solution);
        for (int i = 0; i < population_size; i++) {
            ArrayList<Integer> solution = population.get(i);

            int fitness = operators.evaluationSolution(solution);

            if (best_fitness < fitness) {
                best_fitness = fitness;
                best_solution = (ArrayList<Integer>) solution.clone();

            }

        }
        System.out.println(best_solution);
        System.out.println(best_fitness);

    }

    
    
}
