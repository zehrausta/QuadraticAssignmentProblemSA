import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SimAnn {

    private double temp = 100000;
    private double coolingRate = 0.003;


    public void optimize(List<Integer> currentSolution) throws IOException {

        // Loop until system has cooled
        while (temp > 1) {


            List<Integer> newSolution = new ArrayList<Integer>(currentSolution);

            Random rand = new Random(); //instance of random class

            int pos1 = rand.nextInt(12);
            int pos2 = rand.nextInt(12);

            while(pos1 == pos2)
            {
                pos2 = rand.nextInt(12);
            }

            int swap1 = newSolution.get(pos1);
            int swap2 = newSolution.get(pos2);

            newSolution.set(pos2, swap1);
            newSolution.set(pos1, swap2);

            // Get energy of solutions
            int currentDistance   = cost(currentSolution);
            int neighbourDistance = cost(newSolution);

            // Decide if we should accept the solution
            double random = rand.nextDouble();
            if (acceptanceProbability(currentDistance, neighbourDistance, temp) > random) {
                currentSolution = newSolution;
            }


            // Cool system
            temp *= 1 - coolingRate;
        }

        System.out.println("Final solution distance: " + cost(currentSolution));
        System.out.println("Solution: " + currentSolution.toString());


    }

    public double acceptanceProbability(int currentDistance, int newDistance, double temperature) {
        // If the new solution is better, accept it
        if (newDistance < currentDistance) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((currentDistance - newDistance) / temperature);
    }



    public static int cost(List<Integer> solution) throws IOException {
        Data data1 = new Data();
        int[][] flow1 = data1.readTxtFlow("chr12a.txt");
        int[][] distance1 = data1.readTxtDistance("chr12a.txt");
        int cost = 0;

        for(int i = 0 ; i < flow1.length ; i++){
            for(int j = 0 ; j < flow1[0].length ; j++){
                if (flow1[i][j] != 0){
                    int dist = distance1[solution.indexOf(i)][solution.indexOf(j)];
                    cost = cost + (dist * flow1[i][j]);

                }
            }
        }
        return cost;
    }


}
