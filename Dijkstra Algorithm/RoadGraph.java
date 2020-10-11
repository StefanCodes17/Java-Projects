/**
 * NOTE: For HW 16R, NO coding is required in this class. 
 * 
 */
import java.util.*;
import java.io.*;  
public class RoadGraph
{
    
    //HashMap containing all cities.  The key is a String like "Albany" and the value is the corresponding City object.
    private HashMap<String, City> allCities;
    
    //this is the initial city where the Dijkstra's algorithm will start
    private City initialCity; 
    
    public RoadGraph(String fileName){
        allCities = new HashMap<String, City>();  //Initialize an empty HashMap
        this.readCityFile(fileName);    //Read in the city file
        initialCity = null;  //initialCity for Dijkstra's Algorithm.  Set to null initially; this will get set within the dijkstra() method.
    }
    
    private void readCityFile(String fileName){
        //Initializing the Scanner object
        //try...catch is for handling errors and is required in case Java can't find file 
        Scanner reader;
        try{
            reader = new Scanner(new File(fileName)); 
            
        }catch(FileNotFoundException e){
            System.out.println("Error while reading file."); 
            return; 
        } 
        
        //Going through the input file one road at a time
        while(reader.hasNext()){
            String s = reader.nextLine(); 
            String[] parts = s.trim().split("\\s+"); //trim based on spacing
            
            //skip over any bad lines by continuing the while loop (going back to the top)
            if(parts.length != 4){continue;}
            
            String city1Name = parts[0]; 
            String city2Name = parts[1]; 
            int d = Integer.parseInt(parts[2]); 
            String roadName = parts[3];
            
            City city1;
            City city2;
          
            if(allCities.containsKey(city1Name)){
                city1 = allCities.get(city1Name);
            }else{
                city1 = new City (city1Name);
                allCities.put(city1Name, city1);
            }
            
            if(allCities.containsKey(city2Name)){
                city2 = allCities.get(city2Name);
            }else{
                city2 = new City(city2Name);
                allCities.put(city2Name, city2);
            }
            Road road = new Road(city1, city2, d, roadName);
            allCities.get(city1Name).addRoad(road);
            allCities.get(city2Name).addRoad(road);
        }
        
    }
    
    public String toString(){
        String out = ""; 
        for(String cityName: allCities.keySet()){
            out+= allCities.get(cityName).toString(); 
        }
        return out;  
    }
    
    
    public City getAndRemoveClosestCity(ArrayList<City> cityList){
        int minDist = cityList.get(0).getDistanceFromInitial();
        int currDist = 0;
        City minCity = cityList.get(0);
        for(int i = 0; i < cityList.size(); i++){
            currDist = cityList.get(i).getDistanceFromInitial();
            if( currDist < minDist ) {
                minDist = currDist;
                minCity = cityList.get(i);
            }
        }
        cityList.remove(minCity);
        return minCity;
    }
    
    public void dijkstra(String initialCityName){
        //Step 1: Get the initial city from hash map and set its distance to 0 (because it is 0 away from itself)
        initialCity = allCities.get(initialCityName);
        initialCity.setDistanceFromInitial(0);
        
        //Step 2: Fill the unvisited set with all of the cities (which will include initial)
        ArrayList<City> unvisitedSet = new ArrayList<City>(); 
        for(String cityName : allCities.keySet()){
            City cityAdd = allCities.get(cityName);
            unvisitedSet.add(cityAdd);
        }
        
        //Step 3: Run the algorithm as long as there are still cities in the univisted set 
        while(unvisitedSet.size() > 0){       
            //Important ideas here:
            //Set the current city to the unvisited city which is closest to the initial.
            City currentCity = getAndRemoveClosestCity(unvisitedSet);
            currentCity.markAsVisited();
            //Loop through all the roads of the city to update distances for neighboring cities
            ArrayList<Road> tempList = currentCity.getRoadList();
            for(Road road : tempList){
                int tempDist = 0;
                City neighborCity = road.getNeighborCity(currentCity);
                if(!neighborCity.hasBeenVisited()){
                    tempDist = currentCity.getDistanceFromInitial() 
                    + road.getDistance();
                    if(tempDist < neighborCity.getDistanceFromInitial()){
                        neighborCity.setDistanceFromInitial(tempDist);
                    }
                }
            }
            //Mark the current city as visited. 
        }   
 
    }
    
    public void printDijkstraResults(){
         for(String cityName : allCities.keySet()){
             City currCity = allCities.get(cityName);
             System.out.println(currCity.getName() 
             + ": "
             + currCity.getDistanceFromInitial() 
             + " miles from " + initialCity.getName());
         }
    }

    
     public void printBestRouteTo(String destName){
        City currCity = allCities.get(destName);
        City nextCity = null;
        ArrayList<String> trip = new ArrayList<String>();
        int totalMile = 0;
        int i = 0;
        while(true){
          ArrayList<Road> tempList = currCity.getRoadList();
          for(Road road : tempList){
              City tempCity = road.getNeighborCity(currCity);
              if(tempCity.getDistanceFromInitial() + road.getDistance() == 
              currCity.getDistanceFromInitial()){
                  totalMile += road.getDistance();
                  nextCity = tempCity;
                  trip.add(i, "Take " + road);
                  i++;
              }
          }
          currCity = nextCity;
          if(currCity.getName().equals(initialCity.getName())){
              break;
          }
        }
        System.out.println("The best route from " + initialCity.getName() 
        + " to " + destName + ":");
        for(int j = trip.size() - 1, num = 1; j >= 0; j--){
            System.out.println(num + ".  " + trip.get(j));
            num++;
        }
        System.out.println("Total mileage: " + totalMile);
    }
    

    
    
    
}
