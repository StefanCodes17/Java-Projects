import java.util.ArrayList; 
public class City
{
    //General attributes:
    private String name;  //name of a city, like "Albany"
    private ArrayList<Road> roads;  //list of roads that are connected to the particular city 
      
    //Attributes set during Dijkstra's Algorithm:
    private int distFromInitial;  //keeps track of total distance to initial city
    private boolean visited;    //keeps track of whether or not city has been visited in Dijkstra's algorithm
    
    
    public City(String name){
        roads = new ArrayList<Road>(); 
        this.name = name; 
        distFromInitial = Integer.MAX_VALUE;  
        visited = false; 
    }
    
    /**
     * Adds a road to the city's list of roads
     * 
     * @param r road to add to ArrayList
     */
    public void addRoad(Road r){
        roads.add(r);
    }
    
    /**
     * Get a reference to a city's list of roads. This list contains
     * all roads that can be sued to travel into or out of the city.
     * 
     * @return ArrayList containing all roads to/from the city
     */
    public ArrayList<Road> getRoadList(){
        return roads;
    }
    
    /**
     * Mutator method to set the distance from the "inital city," which
     * is the main value calcualted and updated while running
     * Dijkstra's algorithm.
     * 
     * @param newDist Updated value of the distance from the initial city
     */
    public void setDistanceFromInitial(int newDist){
        distFromInitial = newDist;
    }
    
    /**
     * Accesoor method to get the distance from the "inital city," which
     * is the main value calcualted and updated while running
     * Dijkstra's algorithm.
     * 
     * @param newDist Updated value of the distance from the initial city
     */
    public int getDistanceFromInitial(){
        return this.distFromInitial;
    }
    
    /**
     * Identifies whether or not the city has been visited 
     * as part of Dijkstra's Algorithm
     * 
     * @return true if the city has been visited, false otherwise
     */
    public boolean hasBeenVisited(){
        return visited;
    }
    
    /**
     * Mark that the city has been visited as part of Dijkstra's 
     * Algorithm
     * 
     */
    public void markAsVisited(){
        visited = true;
    }
    
    public String toString(){
        String out = name; 
        out += "\n";
        out += "Roads connecting " + name + ":\n";
        for(Road r: roads){
            out +=  r.toString() + "\n"; 
        }
        out += "\n";
        return out; 
    }
    
    public String getName(){
        return name; 
    }
    
    public boolean equals(City other){
        return this.name.equals(other.name); 
    }
    
}
