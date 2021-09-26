package mbo;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException{
        int flocksize=5;int sharedsolution=2;int totalsolution = 3;int totaltour =100 ; int iterationnum =3;
        MigratingBirds mbo = new MigratingBirds(flocksize,sharedsolution,totalsolution,totaltour,iterationnum);
        ArrayList<Node> nodes = new ArrayList<Node>(DataReader.readNodes());
        mbo.setDistancematrix(generatedistancematrix(nodes));
        mbo.setDemands(demandgenerate(nodes));
        mbo.start();
    }
    public static double[][] generatedistancematrix(ArrayList<Node> nodes){
        double[][] temp = new double[nodes.size()][nodes.size()];
        for(int i = 0 ; i < nodes.size();i++){
            for(int j = 0 ; j < nodes.size();j++){
                double dist = (nodes.get(i).getX()-nodes.get(j).getX())*(nodes.get(i).getX()-nodes.get(j).getX());
                dist+=(nodes.get(i).getY()-nodes.get(j).getY())*(nodes.get(i).getY()-nodes.get(j).getY());
                dist = Math.sqrt(dist);
                temp[nodes.get(i).getId()-1][nodes.get(j).getId()-1] = dist ;
            }
        }
        return temp;
    }
    public static double[] demandgenerate(ArrayList<Node> nodes){
        double[] result = new double[nodes.size()];
        result[0] = 0 ;
        for(int i = 1 ; i < nodes.size() ; i++){
            result[i] = nodes.get(i).getDemand();
        }
        return result;
    }
}
