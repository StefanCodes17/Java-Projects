/**
 * @author      Stefan Kolev sk893q@bhsec.bard.edu
 * @version     2         (current version number of program)
 * @since       1.2          (the version of the package this class was first added to)
*/

public class Planet
    {
    /**
      * x position of the planet 
      */
    private double xPos;
    /**
      * y position of the planet 
      */
    private double yPos;
    /**
      * horizontal (x-axis) velocity of the planet
      */
    private double xVel;
    /**
      * vertical (y-axis) velocity of the planet
      */
    private double yVel;
    /**
      * mass of the planet
      */
    private double mass;
    /**
      * filename/name of the planet from given text file
      */
    private String name;
    
    /**
     * Constructor for objects of class Planet
     * @param px initial x position of the planet
     * @param py initial y position of the planet 
     * @param vx initial x-component of the velocity 
     * @param vy initial y-component of the planet's velocity
     * @param mass planet's mass 
     * @param fileName file that stores an image of th eplanet 
     */
    public Planet(double px, double py, double vx, double vy, double m, String file)
    {
        xPos = px;
        yPos = py;
        xVel = vx;
        yVel = vy;
        mass = m;
        name = file;
    }
    
    /**
     * displays the planets using the x and y positions and filename
     * @see StdDraw
    */
   
    public void display(){
        StdDraw.picture(xPos, yPos, name); // draws planet
    }
    
    /**
      * updates the respective attributes of the planet
      * @param fx horizontal (x-axis) force acting on the planet 
      * @param fy vertical (y-axis) force acting on the planet
      * @param dt the change of time, the time step
     */
    
    public void updatePosition(double fx, double fy, double dt){
        double xAccel = fx/mass;      //acceleration in x and y position
        double yAccel = fy/mass;
        
        xVel = xVel + (dt * xAccel);  // velocity in x and y position
        yVel = yVel + (dt * yAccel); 
        
        xPos = xPos + (dt * xVel);    // updating the position
        yPos = yPos + (dt * yVel);
    }
    
    /**
       * Calculates the force in the x direction
       * @param other the other planet that is acting on the present planet
       * @return fx   the force in the x direction by the other planet
     */
    
    public double calculateFX(Planet other){
        double gravity = 6.67e-11;
        double deltaX = other.xPos - this.xPos;     // x distance
        double deltaY = other.yPos - this.yPos;     // y distance
        double r = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2)); 
        double fx = (gravity * this.mass * other.mass * deltaX)/Math.pow(r , 3);
        return fx;
    }
    
    /**
       * Calculates the force in the y direction 
       * @param other the other planet that is acting on the present planet
       * @return fy   the force in the y direction by the other planet
     */
    
    public double calculateFY(Planet other){
        double gravity = 6.67e-11;
        double deltaX = other.xPos - this.xPos;      // x distance
        double deltaY = other.yPos - this.yPos;      // y distance
        double r = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
        double fy = (gravity * this.mass * other.mass * deltaY)/Math.pow(r , 3);
        return fy;
    }
}
