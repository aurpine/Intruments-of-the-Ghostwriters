package game;

/**
 * The player class that holds all values related to the player. Main physics of the game are created in this class.
 * <p>
 * <b> name </b> String, the name of the player
 * <p>
 * <b> colour </b> String, the colour scheme of the player 
 * <p>
 * <b> vSpeed </b> Double, the vertical speed of the player // speed is in pixels per second.
 * <p>
 * <b> hSpeed </b> Double, the horizontal speed of the player
 * <p>
 * <b> V_MAX_SPEED </b> Static final int, the maximum vertical speed.
 * <p>
 * <b> H_MAX_SPEED </b> Static final int, the maximum horizontal speed.
 * <p>
 * <b> x </b> Double, the x coordinate of the player.
 * <p>
 * <b> y </b> Double, the y coordinate of the player.
 * <p>
 * <b> inAir </b> Boolean, whether the player is in air or not.
 * <p>
 * <b> deaths </b> Int, how many times the player has died.
 * <p>
 * <b> collisionX </b> Int, X distance for collision.
 * <p>
 * <b> collisionY </b> Int, Y distance for collision.
 * <p>
 * <b> acceleration </b> Int, acceleration value;
 * <p>
 * <b> gravity </b> Int, gravity strength.
 * <p>
 * <b> fps </b> Int, frame refresh rate.
 * <p>
 * <b> dead </b> Boolean, value of if player is dead.
 * <p>
 * <b> reviving </b> Boolean, value of if player is reiving.
 * <p>
 * 
 * <b>Total hours:</b>
 * <p>
 * Siavash Samiei - 3 hours
 * <p>
 * Justin Pu - 5 hours
 * 
 * @author Justin Pu and Siavash Samiei
 * @version 7 06.08.15
 */

public class Player  {
  
  private String name;
  private String colour;
  private double vSpeed; 
  private double hSpeed;
  private static final int V_MAX_SPEED = 500;
  private static final int H_MAX_SPEED = 300;
  private double x;
  private double y;
  private boolean inAir = true;
  private int deaths;
  private int collisionX = 12;
  private int collisionY = 0;
  private int acceleration = 720;
  private int gravity = 1500;
  private int fps = 30;
  private boolean dead = false;
  private boolean reviving = false;
  
  
  /**
   * Constructor with a name and colour. 
   * @author Justin Pu
   * @param name name of the player
   * @param colour colour of the player (to load png image)
   */
  public Player (String name, String colour) {
    this.x = 48;
    this.name = name;
    this.colour = colour;
  }
  
  
  /**
   * Returns the colour of this Player.
   * <p>
   * @return the colour
   * @author Justin Pu May 30, 2015
   */
  public String getColour() {
    return colour;
  }
  
  
  /**
   * Speeds up the player horizontally. 
   * <p>
   * @author Justin Pu
   * 
   */
  public void hRight() {
    hSpeed += acceleration / fps;
    if (hSpeed < 0)
      hSpeed += 2 * acceleration / fps;
    if(hSpeed > H_MAX_SPEED)
      hSpeed = H_MAX_SPEED;
  }
  
  
  /**
   * Speeds up the player in the opposite direction.
   * <p>
   * @author Justin PU May 28
   */
  public void hLeft() {
    hSpeed -= acceleration / fps;
    if (hSpeed > 0)
      hSpeed -= 2 * acceleration / fps;
    if(-hSpeed > H_MAX_SPEED)
      hSpeed = -H_MAX_SPEED;
  }
  
  
  /**
   * Slows the player down horizontally. 
   * <p>
   * @author Justin Pu May 21, 2015
   */
  public void hDecelerate() {
    if(hSpeed > 0) {
      hSpeed -= acceleration / fps;
      if(hSpeed < 0)
        hSpeed = 0;
    } else {
      hSpeed += acceleration / fps;
      if(hSpeed > 0)
        hSpeed = 0;
    }
  }
  
  
  /**
   * Sets the vertical speed of the character to max.
   * @author Justin Pu May 24, 2015
   */
  public void jump () {
    vSpeed = -V_MAX_SPEED;
    inAir = true;
  }
  
  
  /**
   * This method makes the player fall when they are in air.
   * <p>
   * @author Siavash May 28, modified by Justin Pu May 30
   */
  public void drop() {
    vSpeed += gravity / fps;
  }
  
  
  /**
   * Returns whether the player is in air or not
   * <p>
   * @return whether the player is in air
   * @author Justin Pu May 24, 2015
   */
  public boolean inAir() {
    return inAir;
  }
  
  
  /**
   * Returns the y coordinate of the player.
   * <p>
   * @return the x coordinate of the player.
   * @author Justin Pu May 24, 2015
   */
  public double getX() {
    return x;
  }
  
  
  /**
   * Returns the y coordinate of the player.
   * <p>
   * @return the y coordinate of the player.
   * @author Justin Pu May 24, 2015
   */
  public double getY() {
    return y;
  }
  
  
  /**
   * This method updates the position of the player.
   * <p>
   * @author Justin Pu May 28,2015
   */
  public void update() {
    x += hSpeed / fps;
    y += vSpeed / fps;
  }
  
  
  /**
   * Returns the horizontal speed of the player.
   * 
   * @return the horizontal speed of the player.
   * @author Siavash Samiei May 24, 2015
   */
  public double getHSpeed() {
    return hSpeed;
  }
  
  
  /**
   * Returns the vertical speed of the player.
   * <p>
   * @return the horizontal speed of the player.
   * @author Siavash Samiei May 24, 2015
   */
  public double getVSpeed() {
    return vSpeed;
  }
  
  
  /**
   * Sets the horizontal speed of the player.
   * <p>
   * @param speed desired hSpeed.
   * @author Siavash Samiei May 24, 2015
   */
  public void setHSpeed(double speed) {
    hSpeed = speed;
  }
  
  
  /**
   * Sets the horizontal speed of the player.
   * <p>
   * @param speed desired hSpeed.
   * @author Siavash Samiei May 24, 2015
   */
  public void setVSpeed(double speed) {
    vSpeed = speed;
  }
  
  
  /**
   * Sets the horizontal coordinate of the player.
   * 
   * @param x desired x.
   * @author Siavash Samiei May 24, 2015
   */
  public void setX(double x) {
    this.x = x;
  }
  
  
  /**
   * Sets the vertical coordinate of the player.
   * <p>
   * @param y desired y.
   * @author Siavash Samiei May 24, 2015
   */
  public void setY(double y) {
    this.y = y;
  }
  
  
  /**
   * Sets the in air value of the player.
   * <p>
   * @param a desired inAir value.
   * @author Siavash Samiei May 24, 2015
   */
  public void setInAir(boolean a) {
    this.inAir = a;
  }
  
  
  /**
   * Returns the collisionX length of the character.
   * <p>
   * @return collisionX.
   * @author Siavash Samiei May 28, 2015
   */
  public int getCollisionX() {
    return collisionX;
  }
  
  
  /**
   * Returns the collisionY length of the character.
   * <p>
   * @return collisionY.
   * @author Siavash Samiei May 28, 2015
   */
  public int getCollisionY() {
    return collisionY;
  }
  
  
  /**
   * Sets the FPS of the player.
   * <p>
   * @author Justin Pu May 31, 2015
   * @param fps desired fps
   */
  public void setFps(int fps) {
    this.fps = fps;
  }
  
  
  /**
   * Returns the next vertical speed for collision prediction.
   * <p>
   * @return the upcoming vertical speed (after one frame).
   * @author Justin Pu
   */
  public double getNextYIncrease() {
    if (inAir)
      return (vSpeed + gravity / fps) / fps;
    return vSpeed;
  }
  
  
  /**
   * Sets dead to true.
   * <p>
   * @author Siavash Samiei
   */
  public void die(){
    dead = true;
    deaths++;
  }
  
  
  /**
   * Returns if the player is dead.
   * <p>
   * @return whether the player is dead or not
   * @author Justin Pu May 31, 2015
   */
  public boolean isDead() {
    return dead;
  }
  
  
  /**
   * Returns if the player is dead.
   * <p>
   * @return whether the player is dead or not
   * @author Justin Pu May 31, 2015
   */
  public boolean isReviving() {
    return reviving;
  }
  
  
  /**
   * Respawns the player to the checkpoint.
   * <p>
   * @author Justin Pu May 31, 2015
   * @param x the x position to respawn to.
   */
  public void respawn(int x) {
    dead = false;
    reviving = true;
    this.x = x * 32 + 16;
    this.y = 224;
    vSpeed = 0;
    hSpeed = 0;
    inAir = true;
  }
  
  
  /**
   * Accessor method for number of deaths.
   * <p>
   * @return deaths - the number of deaths accumulated by the user. 
   * @author Justin Pu June 6, 2015.
   */
  public int getDeaths() {
    return deaths;
  }
  
  
  /**
   * Mutator method for revviving.
   * <p>
   * @param b the desired boolean value for reviving
   * @author Siavash Samiei June 6,2015
   */
  public void setReviving (boolean b){
    reviving  = b;
  }
  
  
  /**
   * Accessor method for the name of the player.
   * <p>
   * @return the name of the character.
   * @author Siavash Samiei June 7, 2015.
   * 
   */
  public String getName() {
    return name;
  }
  
}