package game;

import blocks.*;
import java.util.*;
import java.io.*;

/**
 * The level class of the game. This class generates all the worlds based on the file passed in with an array of blocks.
 * <p>
 * Created by Justin Pu May 20, 2015
 * Modified by Siavash Samiei May 24, May 25, May 26, 2015
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> blocks </b> ArrayList of Block, all blocks on the level.
 * <p>
 * <b> name </b> String, name of the level.
 * <p>
 * <b> length </b> Int, stores length of the level.
 * <p>
 * <b> author </b> String, author that the level is based on.
 * <p>
 * <b> checkpointLocations </b> Int [], array that stores all checkpoint x values of the world.
 * <p>
 * 
 * <b>Total hours spent:</b>
 * <p>
 * Justin Pu: 5 hours
 * 
 * @author Justin Pu
 * @version 7 06.08.15
 * 
 */
public class Level {
  private ArrayList <Block> blocks;  
  private String name;
  private int length;
  private String author;
  private int[] checkpointLocations;
  
  
  /**
   * Modified Justin Pu May 30, 31, June 1
   * @author Justin Pu May 24
   * @param file the name of the world being genereated
   */
  public Level (String file) {
    blocks = new ArrayList <Block>();
    try {
      BufferedReader in = new BufferedReader (new FileReader (file));
      name = in.readLine();
      // Checkpoint locations
      final String[] TEMP = in.readLine().split(" ");
      checkpointLocations = new int [TEMP.length];
      for (int i = 0; i < TEMP.length; i++) {
        checkpointLocations[i] = Integer.parseInt(TEMP[i]);
      }
      length = Integer.parseInt(in.readLine());  
      author = in.readLine();
      in.readLine();
      in.readLine();
      in.readLine();
      for(int line = 0; line < 19; line++) {
        String s = in.readLine();
        for(int b = 0; b < length; b++) {
          char c = s.charAt(b);
          if(c == '0') {
            blocks.add(new Terrain(b, line, (line > 0 && getBlock(b, line - 1) == null) ? 6 : 0));
          } else if(c == '1') {
            blocks.add(new Terrain(b, line, (Math.random() < 0.05 ? 7 : 1)));
          } else if(c == '2') {
            blocks.add(new Obstacle(b, line, 2));
          } else if (c == 'S') {
            blocks.add(new Terrain(b, line, 3));
          } else if (c == 'A') {
            blocks.add(new Checkpoint(b, line));
          } else if (c == 'B') {
            blocks.add(new Obstacle(b, line, 9));
          } else if (c == 'L') {
            blocks.add(new Letter(b, line));
          } else if (c == 'W') {
            blocks.add(new Obstacle(b, line, 4));
          } else if ('a' <= c && c <= 'z') {
            blocks.add(new Text(b, line, c));
          } else if (c == '_') {
            blocks.add(new Terrain(b, line, 10));
          } else { }
        }
      }
      in.close();
    } catch (IOException e) { }
    // Assigns the letterNum value to the letters.
    ArrayList<Letter> letters = new ArrayList<Letter>();
    for(Block b: blocks) {
      if (b.getType().equals("letter"))
        letters.add((Letter) b);
    }
    letters.sort(new Comparator<Letter>() {
      public int compare(Letter o1, Letter o2) {
        return Integer.compare(o1.getX(), o2.getX());
      }  
      public boolean equals(Object obj) {
        return equals(obj);
      }
    });
    
    for(int n = 0; n < letters.size(); n++) {
      letters.get(n).setLetterNum(n + 1);
    }
    
  }
  
  
  /**
   * Modified Justin Pu May 30, 31, June 1
   * @author Justin Pu May 24
   * @param forEnd overload to distinguish from other contructor
   */
  public Level(boolean forEnd) {
    blocks = new ArrayList <Block>();
    try {
      BufferedReader in = new BufferedReader (new FileReader (Paths.LEVELS + "end.txt"));   
      length = 25;
      for(int line = 0; line < 19; line++) {
        String s = in.readLine();
        for(int b = 0; b < length; b++) {
          char c = s.charAt(b);
          if(c == '0') {
            blocks.add(new Terrain(b, line, (line > 0 && getBlock(b, line - 1) == null) ? 6 : 0));
          } else if(c == '1') {
            blocks.add(new Terrain(b, line, (Math.random() < 0.05 ? 7 : 1)));
          } else if(c == '2') {
            blocks.add(new Obstacle(b, line, 2));
          } else if (c == 'S') {
            blocks.add(new Terrain(b, line, 3));
          } else if (c == 'A') {
            blocks.add(new Checkpoint(b, line));
          } else if (c == 'B') {
            blocks.add(new Obstacle(b, line, 9));
          } else if (c == 'L') {
            blocks.add(new Letter(b, line));
          } else if (c == 'W') {
            blocks.add(new Obstacle(b, line , 4));
          } else if ('a' <= c && c <= 'z') {
            blocks.add(new Text(b, line, c));
          } else if (c == '_') {
            blocks.add(new Terrain(b, line, 10));
          } else { }
        }
      }
      in.close();
    } catch (IOException e) { }
  }
  
  /**
   * Returns the block at the specified location. 
   * 
   * @return the block at the specified location, null if it doesn't exit
   * @author Justin Pu May 24
   * @param x desired x coordinate
   * @param y desired y coordinate
   */
  public Block getBlock(int x, int y) {
    for(Block b : blocks) {          
      if (b.getX() == x && b.getY() == y){ 
        return b;
      }
    }
    return null;
  }
  
  /**
   * 
   * 
   * @param x the start x coordinate of the screen in relation to the level (pixels).
   * @return an ArrayList of Block containing all the visible blocks in view. 
   * @author Justin Pu May 24, 2015
   */
  public ArrayList <Block> getVisible(int x) {
    ArrayList <Block> visible = new ArrayList <Block>();
    for(Block b : blocks) {
      if (b.getX() * 32 + 32 > x && b.getX() * 32 < x + 800)
        visible.add(b);
    }
    return visible;
  }
  
  /**
   * Removes the block passed.
   * @param block the Block to be removed from the Level.
   * @return whether the block has been successfully removed or not.
   */
  public boolean remove(Block block) {
    return blocks.remove(block);
  }
  
  /**
   * Returns the x block location of the checkpoint specified.
   * 
   * @author Justin Pu June 1, 2015
   * @return the x location of the block of the checkpoint specified.
   * @param checkpointReached the latest checkpoint reached by the player to set respawn
   */
  public int getCheckpointLocation(int checkpointReached) {
    return checkpointLocations[checkpointReached];
  }
  
  /**
   * Returns the length of the level. 
   * 
   * @return the length of this level.
   * @author Justin Pu June 6, 2015
   */
  public int getLength() {
    return length;
  }
  
  /**
   * Returns the name of the author of this level.
   * 
   * @author Justin Pu
   * @return the author of the level.
   */
  public String getAuthor() {
    return author;
  }
  
  /**
   * Returns the name of this level.
   * 
   * @author Siavash Samiei
   * @return the name of the level.
   */
  public String getName() {
    return name;
  }
  
  
  
  
}
