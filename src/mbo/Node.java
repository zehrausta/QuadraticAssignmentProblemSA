package mbo;

public class Node {
    private int id ;
    private int[] coordinates;
    private int demand ;

    public Node(int id,int coordinatex,int coordinatey){
        this.id = id;
        int[] temp = new int[]{coordinatex,coordinatey};
        coordinates = temp.clone();
    }
    public void setDemand(int demand){
        this.demand = demand;
    }
    public int getDemand(){
        return demand;
    }
    public int getId(){
        return id;
    }
    public int getX(){
        return coordinates[0];
    }
    public int getY(){
        return coordinates[1];
    }
}
