import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Data {

    public static int[][] readTxtFlow(String path) throws IOException {

        BufferedReader buf = new BufferedReader(new FileReader(path));

        int size = Integer.parseInt(buf.readLine());

        int[][] flow = new int[size][size];

        String currentLine = null;
        int[] values = new int[size];

        int count = 0;
        buf.readLine();

        boolean isDist = false;

        while (true) {
            currentLine = buf.readLine();
            if (count == size) {

                break;

            } else {

                int index = 0;
                for (String s : currentLine.split("\\s+")) {
                    if (!s.isEmpty() && !isDist) {
                        flow[count][index] = Integer.parseInt(s);
                        index++;
                    }

                }


            }
            count = count + 1;

        }
        return flow;
    }
    public static int[][] readTxtDistance(String path) throws IOException {

        BufferedReader buf = new BufferedReader(new FileReader(path));

        int size = Integer.parseInt(buf.readLine());

        int[][] distance = new int[size][size];

        String currentLine = null;
        int[] values = new int[size];

        int count = 0;
        buf.readLine();

        for(int i = 0 ; i < size + 1 ; i++){
            buf.readLine();
        }

        boolean isDist = false;

        while (true) {
            currentLine = buf.readLine();
            if (count == size) {

                break;

            } else {

                int index = 0;
                for (String s : currentLine.split("\\s+")) {
                    if (!s.isEmpty() && !isDist) {
                        distance[count][index] = Integer.parseInt(s);
                        index++;
                    }

                }

            }
            count = count + 1;

        }
        return distance;
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