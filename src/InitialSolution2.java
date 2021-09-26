import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InitialSolution2 {

    public static int[] SolveImproved(int[][] flow, int[][] distance){
        int [][] temp_flow = flow;
        int [][] temp_distance = distance;
        int size = flow.length;

        int[] solution = new int[size];

        for (int i = 0 ; i < size/2 ; i++){
            
                int max_flow_value = getMaxValue(temp_flow);
                int min_distance_value = getMinValue(temp_distance);
                int[] max_flow = getIndex(flow,max_flow_value);
                int[] min_distance = getIndex(distance,min_distance_value);
                solution[min_distance[0]] = max_flow[0];
                solution[min_distance[1]] = max_flow[1];

                if(i != size/2 - 1) {
                    changeRow(temp_flow,max_flow[0],true);
                    changeColumn(temp_flow,max_flow[0],true);
                    changeRow(temp_flow,max_flow[1],true);
                    changeColumn(temp_flow,max_flow[1],true);

                    changeRow(temp_distance,min_distance[0],false);
                    changeColumn(temp_distance,min_distance[0],false);
                    changeRow(temp_distance,min_distance[1],false);
                    changeColumn(temp_distance,min_distance[1],false);
                }


            }
        
        return solution;
    }
    public static int[] Solve1(int[][] flow, int[][] distance){
        int [][] temp_flow = flow;
        int [][] temp_distance = distance;
        int size = flow.length;

        int[] solution = new int[size];

        for (int i = 0 ; i < size/2 ; i++){

            int max_flow_value = getMaxValue(temp_flow);
            int min_distance_value = getMinValue(temp_distance);
            int[] max_flow = getIndex(flow,max_flow_value);
            int[] min_distance = getIndex(distance,min_distance_value);
            solution[min_distance[0]] = max_flow[0];
            solution[min_distance[1]] = max_flow[1];

            if(i != size/2 - 1) {
                changeRow(temp_flow,max_flow[0],true);
                changeColumn(temp_flow,max_flow[0],true);
                changeRow(temp_flow,max_flow[1],true);
                changeColumn(temp_flow,max_flow[1],true);

                changeRow(temp_distance,min_distance[0],false);
                changeColumn(temp_distance,min_distance[0],false);
                changeRow(temp_distance,min_distance[1],false);
                changeColumn(temp_distance,min_distance[1],false);
            }


        }

        return solution;
    }

    public static int getMaxValue(int[][] numbers) {
        int maxValue = numbers[0][0];
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < numbers[j].length; i++) {
                if (numbers[j][i] > maxValue) {
                    maxValue = numbers[j][i];
                }
            }
        }
        return maxValue;
    }

    public static int[] getIndex(int[][] numbers, int value) {
        int[] index = new int[2];
        
        for (int j = 0 ; j < numbers.length ; j++){
            for (int i = 0 ; i < numbers[j].length ; i++){
                if((numbers[j][i] == value) && (i != j)){
                    index[0] = j;
                    index[1] = i;
                }
            }
        }
        return index;
    }

    public static int getMinValue(int[][] numbers) {
        int minValue = 1000000000;
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < numbers[j].length; i++) {
                if ((numbers[j][i] < minValue) && (i != j)) {
                    minValue = numbers[j][i];
                }
            }
        }
        return minValue ;
    }

    public static int[][] changeRow(int[][] a,int number, boolean isFlow){
        for(int i = 0 ; i < a.length ; i++){
            if(isFlow){
                a[number][i] = -1;
            }else{
                a[number][i] = Integer.MAX_VALUE;
            }
        }
        return a;
    }
    public static int[][] changeColumn(int[][] a,int number, boolean isFlow){
        for(int i = 0 ; i < a.length ; i++){
            if(isFlow){
                a[i][number] = -1;
            }else{
                a[i][number] = Integer.MAX_VALUE;
            }
        }
        return a;
    }


}
