import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) throws IOException {

        Data data1 = new Data();

        int[][] flow = data1.readTxtFlow("chr12a.txt");
        // System.out.println(Arrays.deepToString(flow));
        int[][] distance = data1.readTxtDistance("chr12a.txt");
        //System.out.println(Arrays.deepToString(distance));


        InitialSolution1 solution1 = new InitialSolution1();
        int[] result = solution1.Solve(flow.clone(),distance.clone());

//        System.out.println(result.length);
//
//        System.out.println(cost(result));


        SimulatedAnnealing sim = new SimulatedAnnealing();


        long start = System.currentTimeMillis();
        sim.optimize(Arrays.stream(result).boxed().collect(Collectors.toList() ));
        long stop = System.currentTimeMillis() - start;
        System.out.println("run time: " + stop);


    }
    public static int cost(int[] solution) throws IOException {
        Data data1 = new Data();
        int[][] flow1 = data1.readTxtFlow("chr12a.txt");
        int[][] distance1 = data1.readTxtDistance("chr12a.txt");
        List<Integer> sol = Arrays.stream(solution).boxed().collect(Collectors.toList() );
        int cost = 0;
        for(int i = 0 ; i < flow1.length ; i++){
            for(int j = 0 ; j < flow1[0].length ; j++){
                if (flow1[i][j] != 0){
                    int dist = distance1[sol.indexOf(i)][sol.indexOf(j)];
                    cost = cost + (dist * flow1[i][j]);

                }
            }
        }
        return cost;
    }
}


