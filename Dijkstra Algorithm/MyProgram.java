import java.util.ArrayList; 
public class MyProgram
{
    public static void main(String[] args){
       RoadGraph rg = new RoadGraph("ny.txt");
       rg.dijkstra("NYC");
       rg.printBestRouteTo("Plattsburgh");
    }
}
