package mbo;

import java.util.ArrayList;
import java.util.Collections;


public class MigratingBirds {
    
    private Bird Leader;
    private int totaltour;
    private int iterationnum;
    private ArrayList<Bird> Left;
    private ArrayList<Bird> Right;
    private int flocksize;
    private int sharenumber;
    private int totalsol;
    private static double[][] distancematrix;
    private static double[] demands;
    private static double cap = 100;
    private static boolean updateLeft = true;

    private void flocking(){
        Leader = new Bird();
        Left = new ArrayList<Bird>();
        Right = new ArrayList<Bird>();
        boolean isLeft=true;
        for(int i = 0 ; i < flocksize*2;i++) {
            Bird b = new Bird();
            if(isLeft){
                Left.add(b);
                isLeft = false;
            }
            else{
                Right.add(b);
                isLeft = true;
            }
        }
    }
    private void updateflock(){
        if(updateLeft){
            Left.add(Leader);
            Leader = Left.remove(0);
            updateLeft = false;
        }
        else{
            Right.add(Leader);
            Leader = Right.remove(0);
            updateLeft = true;
        }
    }
    private void initializebirds(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 1 ; i < distancematrix.length;i++){
            temp.add(i);
        }
        Collections.shuffle(temp);
        Leader.setMainsol(listtoArray(temp));
        for(Bird b : Left){
            Collections.shuffle(temp);
            b.setMainsol(listtoArray(temp));
        }
        for(Bird b : Right){
            Collections.shuffle(temp);
            b.setMainsol(listtoArray(temp));
        }
    }
    private void GenerateSolutions(){
        Leader.gensol(totalsol);
        Right.forEach(b -> b.gensol(totalsol-sharenumber));
        Left.forEach(b -> b.gensol(totalsol-sharenumber));
    }
    private void PickandShare(){
        setBest(Leader);
        for(int i = 0  ; i < sharenumber ; i++){
            int leftindex = 0 ;
            int rightindex = 0;
            if(updateLeft){
                Left.get(leftindex).takesol(Leader.share());
                updateLeft = false ; 
                leftindex++;
            }
            else{
                Right.get(rightindex).takesol(Leader.share());
                updateLeft = true ; 
                rightindex++;
            }
        }
        for(int i = 0 ; i < Left.size()-1;i++){
            setBest(Left.get(i));
            for(int j = 0 ; j < sharenumber;j++)
                Left.get(i+1).takesol(Left.get(i).share());
        }
        setBest(Left.get(Left.size()-1));
        for(int i = 0 ; i < Right.size()-1;i++){
            setBest(Right.get(i));
            for(int j = 0 ; j < sharenumber;j++)
            Right.get(i+1).takesol(Right.get(i).share());
        }
        setBest(Right.get(Right.size()-1));
    }
    private void setBest(Bird b){
        ArrayList<int[]> temp = new ArrayList<int[]>(b.AllSolutions());
        temp = new ArrayList<int[]>(sortofsols(temp));
        b.setMainsol(temp.remove(0).clone()); // TODO : .clone()?
        b.setsols(temp);

    }
    private void ClearAdditionalSols(){
        Leader.clearsols();
        Left.forEach(b -> b.clearsols());
        Right.forEach(b -> b.clearsols());
    }
    public MigratingBirds(int fl,int sh,int ts,int tt,int in){
        flocksize = fl;
        sharenumber = sh;
        totalsol = ts;
        totaltour = tt;
        iterationnum = in;
        flocking();
    }
    public void setDistancematrix(double[][] distancematrix) {
        this.distancematrix = distancematrix;
    }  
    public void setDemands(double[] demands) {
        this.demands = demands;
    }
    public double[] getDemands() {
        return demands;
    }
    public void start(){
        initializebirds();
        for(int i = 0 ; i < totaltour;i++){

            for(int j = 0 ; j < iterationnum ; j++){
                GenerateSolutions();
                PickandShare();
                ClearAdditionalSols();
            }
            System.out.println("Iteration"+ i);
            System.out.println("---------------");
            System.out.println("Leader"+Leader.getId()+": " + TotalDist(Leader.getMainsol()));
            for(Bird b : Left)
                System.out.println("Left"+b.getId()+" : " + TotalDist(b.getMainsol()));
            System.out.println("---------------");
            for(Bird b : Right)
                System.out.println("Right"+b.getId()+" : " + TotalDist(b.getMainsol()));
            
            System.out.println("---------------");
            updateflock();
        }
        
    }
    public static int[] listtoArray(ArrayList<Integer> a){
        int[] result = new int[a.size()];
        for(int i = 0 ; i <a.size();i++ ) result[i] = a.get(i);
        return result;
    }
    public static double TotalDist(int[] route){
        ArrayList<int[]> vehicles = new ArrayList<int[]>();
        for(int i = 0 ; i < route.length ;){
            ArrayList<Integer> v = new ArrayList<Integer>();
            v.add(0);
            double tempcap = cap;
            while(tempcap-demands[route[i]]>0){
                    v.add(route[i]);
                    tempcap = tempcap - demands[route[i]];
                    i++;
                    if(i == route.length)break;
            }
            v.add(0);
            vehicles.add(listtoArray(v));
        }
        double result = 0 ;
        for(int[] i : vehicles){
            for(int j = 0 ;  j < i.length-1;j++){
                result += distancematrix[i[j]][i[j+1]];
            }
        }
        return result;
    }
    public ArrayList<int[]> sortofsols(ArrayList<int[]> target){
        ArrayList<int[]> target2 = new ArrayList<int[]>(target);
        ArrayList<int[]> result = new ArrayList<int[]>();
        while(!target2.isEmpty()){
            double dist = 0;
            double mindist = TotalDist(target2.get(0)); int minindex = 0;
            for(int i = 0 ; i<target2.size();i++){
                dist = TotalDist(target2.get(i));
                if(mindist>dist){
                    mindist = dist;
                    minindex = i;
                    
                }
            }
            result.add(target2.remove(minindex).clone());
        }
        return result;
    }
}
