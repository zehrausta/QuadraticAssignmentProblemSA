package mbo;

import java.util.ArrayList;
import java.util.Random;

public class Bird {
    private int[] mainsol;
    private ArrayList<int[]> sols;
    private final int id;
    private static int counter = 1;
    public Bird(){
        id = counter++;
    }
    public void setMainsol(int[] mainsol) {
        this.mainsol = mainsol.clone();
        sols = new ArrayList<int[]>();
    }
    public void gensol(int x){
        Random r = new Random();
        for(int i = 0 ; i < x ; i++){
           
            int rand1 ;
            int rand2 ;
            do{
                rand1 = r.nextInt(mainsol.length);
                rand2 = r.nextInt(mainsol.length);
            } while(rand1 == rand2);
            int[] tempsol = mainsol.clone();
            int temp = tempsol[rand1];
            tempsol[rand1] = tempsol[rand2];
            tempsol[rand2] = temp;
            sols.add(tempsol.clone());
            /*
            int rand = r. nextInt(mainsol.length);
            int[] tempsol = new int[mainsol.length];
            int counter = 0 ;
            for(int j = rand ; j < mainsol.length ; j++){
                tempsol[j] = mainsol[counter++];
            }
            for(int j = 0 ; j < rand ; j++){
                tempsol[j] = mainsol[counter++];
            }
            sols.add(tempsol.clone());*/
        }   
    }
    public ArrayList<int[]> AllSolutions(){
        ArrayList<int[]> allsoll = new ArrayList<int[]>(sols);
        allsoll.add(mainsol);
        return allsoll;
    }
    public void setsols(ArrayList<int[]> solll){
        sols.clear();
        sols = new ArrayList<int[]>(solll);
    }
    public int[] share(){
        return sols.remove(0);
    }
    public void takesol(int[] sol1){
        sols.add(sol1.clone());
    }
    public void clearsols(){
        sols.clear();
    }
    public int[] getMainsol() {
        return mainsol;
    }
    public int getId(){
        return id;
    }
    
}
 