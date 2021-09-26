package mbo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    public static ArrayList<Node> readNodes() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("mbo/data/A-n32-k5.vrp"));
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0 ; i < 3;i++)temp.add(reader.readLine());
        int numberOfNodes = Integer.parseInt(reader.readLine().substring(12));
        for(int i = 0 ; i < 3;i++)temp.add(reader.readLine());
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(int i = 0 ; i < numberOfNodes;i++){
            String[] tempnode = reader.readLine().substring(1).split(" ");
            int tempid = Integer.parseInt(tempnode[0]);
            int tempx = Integer.parseInt(tempnode[1]);
            int tempy = Integer.parseInt(tempnode[2]);
            Node n = new Node(tempid,tempx,tempy);
            nodes.add(n);
        }
        temp.add(reader.readLine());
        for(Node v : nodes){
            String[] demands= reader.readLine().split(" ");
            v.setDemand(Integer.parseInt(demands[1]));
        }
        reader.close();
        return nodes;
    }
}