import java.util.Arrays;

public class InitialSolution1 {

    public static int[] Solve(int[][] flow, int[][] distance) {
        int[][] temp_flow = flow;
        int[][] temp_distance = distance;
        int size = flow.length;

        int[] sum_of_flows = new int[temp_flow.length];
        int[] sum_of_distances = new int[temp_distance.length];


        int[] solution = new int[size];

        for (int i = 0; i < temp_flow.length; i++) {
            int temp_sum = 0;
            for (int j = 0; j < temp_flow[0].length; j++) {
                temp_sum = temp_sum + temp_flow[i][j];
            }
            sum_of_flows[i] = temp_sum;
        }
        for (int i = 0; i < temp_distance.length; i++) {
            int temp_sum = 0;
            for (int j = 0; j < temp_distance[0].length; j++) {
                temp_sum = temp_sum + temp_distance[i][j];
            }
            sum_of_distances[i] = temp_sum;
        }

        for (int i = 0; i < solution.length; i++) {
            solution[Math.abs(getMinIndex(sum_of_distances))] = getMaxIndex(sum_of_flows);
            sum_of_distances[Math.abs(getMinIndex(sum_of_distances))] = 10000000;
            sum_of_flows[Math.abs(getMaxIndex(sum_of_flows))] = 0;
        }


        return solution;

    }

    public static int getMaxIndex(int[] array){
        if ( array == null || array.length == 0 ) return -1; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }

    // Method for getting the minimum value
    public static int getMinIndex(int[] array){
        int index = 0;
        int min = array[index];

        for (int i = 1; i < array.length; i++){
            if (array[i] <= min){
                min = array[i];
                index = i;
            }
        }
        return index;
    }

}