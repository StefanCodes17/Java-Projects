public class Road
{
    //a road connects two cities
    private City city1;   //one of the cities connected   
    private City city2;   //one of the cities connected
    private int distance; //distance between the two cities along the roadway
    private String name;  //name of roadway like "I87"
    
    
    public Road(City city1, City city2, int distance, String name){
        this.city1 = city1; 
        this.city2 = city2; 
        this.distance = distance; 
        this.name = name; 
    }
    
    /**
     * Accessor method for the length of the road
     * 
     * @return the length of the road in miles
     */
    public int getDistance(){
        return distance;
    }
    
    /**
     * A road connects two cities. This method has as an input one of 
     * the two cities. It returns the OTHER city that the road connects.
     * 
     * @param c One of the two cities connected by the road
     * @return The other city connected by the road
     */
    public City getNeighborCity(City c){
        if(city1.equals(c))
            return city2;
        return city1;
    }
    
    public String toString(){
        return name + " for " + distance + " miles between " + city1.getName() + " and " + city2.getName(); 
    }
    

    
}
