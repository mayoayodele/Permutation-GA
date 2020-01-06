/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepermutationga;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author mayoayodele
 */


public class GAOperators {
    int problem_size;
    int population_size;
    float crossover_probability;
    float mutation_probability;
    Random r;

    public GAOperators(int population_size, int problem_size, float crossover_probability, float mutation_probability) {
        this.problem_size = problem_size;
        this.population_size = population_size;
        this.crossover_probability = crossover_probability;
        this.mutation_probability = mutation_probability;
        r = new Random();

    }


    public ArrayList<ArrayList<Integer>> getInitialSolutions() {
        ArrayList<Integer> all_genes = new ArrayList<>();
        for (int i = 0; i < problem_size; i++) {
              all_genes.add(i);
        }

        ArrayList<ArrayList<Integer>> population = new ArrayList<>();


        for (int i = 0; i < population_size; i++) {
            List<Integer> temp_gene = new ArrayList<>(all_genes);
            java.util.Collections.shuffle(all_genes);
            population.add(all_genes);
        }
        return population;
    }


    public ArrayList<ArrayList<Integer>> performSelection(ArrayList<ArrayList<Integer>> population)
    {
        ArrayList<ArrayList<Integer>> new_population = new ArrayList<>();
        for (int i = 0; i < population_size; i++) {
            ArrayList<ArrayList<Integer>> parents = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                ArrayList<Integer> parent_temp1 = population.get(r.nextInt(population_size));
                ArrayList<Integer> parent_temp2 = population.get(r.nextInt(population_size));
                int fitness_1 = this.evaluationSolution(parent_temp1);
                int fitness_2 = this.evaluationSolution(parent_temp2);
                ArrayList<Integer> parent = parent_temp1;
                if (fitness_1 < fitness_2) {
                    parent = parent_temp2;
                }
                parents.add(parent);
            }
           ArrayList<Integer> offspring = new ArrayList<>();
           if(r.nextFloat() < crossover_probability)
                offspring = this.performCrossover(parents.get(0), parents.get(1));
           else
               offspring = parents.get(0);

           if(r.nextFloat() < mutation_probability)
                offspring = this.performMutation(offspring);

          new_population.add((ArrayList<Integer>) offspring.clone());
        }
        return new_population;


    }

    public int evaluationSolution(ArrayList<Integer> Solution){
        int fitness = 0;
        for (int i = 0; i < Solution.size(); i++) {
            fitness+=(i*Solution.get(i));

        }
        return fitness;
    }

    public ArrayList<Integer> performCrossover(ArrayList<Integer> Parent1, ArrayList<Integer> Parent2){
        ArrayList<Integer> crossedList = new ArrayList<Integer>();
        int crossover_point = 0;
        while (crossover_point == 0) {
            crossover_point = r.nextInt(Parent1.size());
        }
        for (int i = 0; i < crossover_point; i++) {
            crossedList.add(Parent1.get(i));
        }
        for (int i = 0; i < Parent2.size(); i++) {
            if (!crossedList.contains(Parent2.get(i))) {
                crossedList.add(Parent2.get(i));
            }
        }
        return crossedList;

    }



       public ArrayList<Integer> performMutation(ArrayList<Integer> Solution){
        ArrayList<Integer> mutatedList = new ArrayList<>();
        int mutation_point = 0;
        int insertion_point = 0;
        while (mutation_point == 0) {
            mutation_point = r.nextInt(Solution.size());
        }
        while (insertion_point == 0|| insertion_point == mutation_point) {
            insertion_point = r.nextInt(Solution.size());
        }

        mutatedList.addAll(Solution);
        int value_to_displace = Solution.get(mutation_point);
        mutatedList.remove(mutation_point);
        mutatedList.add(insertion_point, value_to_displace);


        return mutatedList;

    }



}