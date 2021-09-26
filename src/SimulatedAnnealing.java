import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulatedAnnealing {
    private double temp = 1000;
    private double coolingRate = 0.05;
    private int iteration = 1000;
    Data data1 = new Data();

    public void optimize(List<Integer> currentSolution) throws IOException {
        List<Integer> costs = new ArrayList<Integer>();
        // Loop until system has cooled

        while (temp > 0.05) {

            for(int i = 0 ; i < iteration ; i++) {


                List<Integer> newSolution = new ArrayList<Integer>(currentSolution);

                Random rand = new Random(); //instance of random class

                int pos1 = rand.nextInt(currentSolution.size());
                int pos2 = rand.nextInt(currentSolution.size());

                while (pos1 == pos2) {
                    pos2 = rand.nextInt(currentSolution.size());
                }

                int swap1 = newSolution.get(pos1);
                int swap2 = newSolution.get(pos2);

                newSolution.set(pos2, swap1);
                newSolution.set(pos1, swap2);

                // Get costs of solutions
                int currentCost = Data.cost(currentSolution);
                int neighbourCost = Data.cost(newSolution);

                double random = rand.nextDouble();

                // Decide if we should accept the solution
                if ((acceptanceProbability(currentCost, neighbourCost, temp) > random))
                {
                    currentSolution = newSolution;
                    costs.add(neighbourCost);
                }

            }

            // Cool system
            temp = temp * (1 - coolingRate);
        }
        FileWriter writer = new FileWriter("output");
        for(Integer cost: costs) {
            writer.write(cost.toString() + System.lineSeparator());
        }
        writer.close();

        System.out.println("Final solution cost: " + Data.cost(currentSolution));
        System.out.println("Solution: " + currentSolution.toString());

    }


    public double acceptanceProbability(int currentCost, int newCost, double temperature) {
        // If the new solution is better, accept it
        if (newCost < currentCost) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((currentCost - newCost) / temperature);
    }

}
