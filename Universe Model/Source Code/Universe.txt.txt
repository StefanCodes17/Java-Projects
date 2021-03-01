/**
 * @author      Stefan Kolev sk893q@bhsec.bard.edu
 * @version     2         (current version number of program)
 * @since       1.2          (the version of the package this class was first added to)
*/

public class Universe{
    /**
      * end time-stamp which stops the program
      */
    private double endT;
    /**
      * change in time
      */
    private double deltaT;
    /**
      * file which is used to get information
      */
    private String universeFile;
    /**
      * radius of the universe (program window)
      */
    private double radius;
    /**
      * planet array that holds planet objects
      */
    private Planet[] planets;
    
   public static void main(String[] args){
        Universe u = new Universe(157788000.0, 25000.0, "planets.txt");
        u.readUniverse();
        u.runSimulation();
    }
    
   /**
     * Constructor for objects of class Planet
     * @param endT_init initial end time stamp
     * @param deltaT_init initial change in time
     * @param universeFile_init initial text file
     */ 
   public Universe(double endT_init, double deltaT_init, String universeFile_init)
    {
      endT = endT_init;
      deltaT = deltaT_init;
      universeFile = universeFile_init;
    }
    
   /**
    * reads the text file, defines the numeber of bodies, the radius and fills in the planet array
    */
    public void readUniverse(){
        In file = new In(universeFile);                       // reads in file
        int numBodies = Integer.parseInt(file.readLine());    // reads 1st line
        radius = Double.parseDouble(file.readLine());         // reads and parses second line
        planets = new Planet[numBodies];                      // creates the array slots
        
        for(int i = 0; i < numBodies; i++){                   // fills in the array with the correct info
           String s = file.readLine();
           String[] tokens = s.trim().split("\\s+");
           planets[i] = new Planet(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),tokens[5]);
        }  
    }
    
    /**
     * displays universe and planet array of planet objects and sets canvas
    */
   public void displayUniverse(){
        double min = -radius;
        double max = radius;
        
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
        
        StdDraw.picture(0, 0, "starfield.jpg", 2 * radius, 2 * radius);   // covers the canvas with image
        for(int i = 0; i < planets.length; i++){                          // displays all the planets
            planets[i].display();
        }
        StdDraw.show();
        StdDraw.pause(10);
    }
    
   /**
    * updates positions of planets and calculates sum of forces that act on each planet and moves thems
    */
    public void updatePhysics(){
        for(int i = 0; i < planets.length; i++){
            double sumOfFX = 0; 
            double sumOfFY = 0;
            for(int j = 0; j < planets.length; j++){
                if(i != j){
                    sumOfFX = sumOfFX + planets[i].calculateFX(planets[j]);
                    sumOfFY = sumOfFY + planets[i].calculateFY(planets[j]);
                }
            }
            planets[i].updatePosition(sumOfFX, sumOfFY, deltaT); 
        }
    }
    
   /**
    * sets canvas and calls other methods above
    * @see updatePhysics()
    * @see displayUniverse()
   */
    public void runSimulation(){
        StdDraw.setCanvasSize(600, 600);
        StdDraw.enableDoubleBuffering();
          for( double t = 0; t < endT; t = t + deltaT){
             updatePhysics();
             displayUniverse();
        }
    }

}
