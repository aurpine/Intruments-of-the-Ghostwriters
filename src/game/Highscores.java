package game;

import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.function.*;

/**
 * Highscores class of the game, reads and writes all highscores from the highscore file.
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> HEADER </b> final static String, the header line of the files.
 * <p>
 * <b> MAX_SCORES </b> final static String, the maximum amount of highscores stored.
 * <p>
 * </b>Total time spent:</b>
 * <p>
 * Justin Pu: 3 hours
 * 
 * @author Justin Pu June 4, 2015
 * @version 4 06.08.15
 */
public class Highscores {
    private final static String HEADER = "Instruments of the Ghostwriters � 2015 Blue Sphere Co.";
    private final static int MAX_SCORES = 10;
    
    /**
     * An entry.
     * <p>
     * <b> Instance Variables </b>
     * <p>
     * <b> name </b> String, name of the player.
     * <p>
     * <b> points </b> Int, the number of points.
     * <p>
     * @author Justin Pu June 4, 2015
     * @version 3
     */
    public static class Entry {
        
        private final String name;
        private final int points;
        
        /**
         * Creates a new highscore entry.
         * 
         * @author Justin Pu June 4, 2015.
         * @param name name of the player whos entry is being made
         * @param points the number of points the player has to enter into the entry
         */
        public Entry(String name, int points) {
            this.name = format(name);
            this.points = points;
        }
        
        /**
         * Returns the name of this entry.
         * @author Justin Pu June 4, 2015.
         * @return the name of the player
         */
        public String getName() {
            return name;
        }
        
        /**
         * Returns the number of points of this entry.
         * @return the number of points of this entry.
         * @author Justin Pu June 4, 2015.
         */
        public int getPoints() {
            return points;
        }
        
        /**
         * Returns the String representation of this Entry.
         * 
         * @return the String representation of this Entry.
         * @author Justin Pu June 4, 2015.
         */
        @Override
        public String toString() {
            return name + " " + points;
        }
        
        /**
         * Formats the username String to alpha numeric. 
         * 
         * @param name name of the player whose highscore is being entered 
         * @return the formatted name
         */
        private static String format(String name) {
            for(int x = 0; x < name.length(); x++) {
                if (!name.substring(x, x + 1).matches("[a-zA-Z0-9_]")) {
                    name = name.substring(0, x) + name.substring(x + 1, name.length());
                    x--;
                }
            }
            return name;
        }
        
        /**
         *  
         * Modified June 8, 2015 - Justin Pu and Siavash Samiei. 
         * 
         * @author Justin Pu June 7, 2015.
         * @param letters number of letters the player picked up
         * @param time the amount of time it took the player to finish the level
         * @param deaths the number of attempts it took the player
         * @return the number of points the player earned for the level. 
         */
        public static int points(int letters, double time, int deaths) {
            return (int) (Math.pow(10, letters) / time / (deaths + 1));
        }
    }
    
    /**
     * Returns a list of all the entries within a highscores file. If the file
     * is corrupt, then a new file is created and an empty list is returned. 
     * <p>
     * @return an ArrayList of Entry containing all the entries of the highscores file. 
     * @param file the file to read the entries from. 
     * @author Justin Pu June 7, 2015. 
     */
    public static ArrayList<Entry> getEntries(File file) {
        ArrayList <Entry> list = new ArrayList <Entry>();
        
        try {
            Scanner in = new Scanner (new FileReader(file));
            if (in.nextLine().equals(HEADER)) {
                int length = in.nextInt();
                for (int n = 0; n < Math.min(length, MAX_SCORES); n++) {
                    list.add(new Entry(in.next(), in.nextInt()));
                }
            }
            in.close();
        } catch (Exception e) {
            list.clear();
        }
        
        return list;
    }
    
    /**
     * Overloaded add method (doesn't require to have an entry object already 
     * created). Adds an entry to the current highscores file. If one does not 
     * exist, a new one is created. A list of entries is obtained and sorted
     * with the new entry added. 
     * 
     * @param file the file to add the new entry to.
     * @param name the name of the player.
     * @param points the number of points earned by the player. 
     * @author Justin Pu June 7, 2015. 
     */
    public static void add(File file, String name, int points) {
        add(file, new Entry(name, points));
    }
    
    /**
     * Adds an entry to the current highscores file. If one does not 
     * exist, a new one is created. A list of entries is obtained and sorted
     * with the new entry added. 
     * 
     * @param file the file to add the new entry to.
     * @param newEntry the new entry to add to the highscores. 
     * @author Justin Pu June 7, 2015. 
     */
    public static void add(File file, Entry newEntry) {
        ArrayList <Entry> entries = getEntries(file);
        
        entries.add(newEntry);
        
        entries.sort(new Comparator<Entry>() {
            public int compare(Entry a, Entry b) {
                if (a.getPoints() != b.getPoints())
                    return -Integer.compare(a.getPoints(), b.getPoints());
                return a.getName().compareTo(b.getName());
            }
            
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        });
        
        if (entries.size() > MAX_SCORES) {
            entries.remove(MAX_SCORES);
        }
        
        // Writing
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println(HEADER);
            out.println(entries.size());
            for (Entry e : entries) {
                out.println(e);
            }
            out.close();
        } catch (IOException e) { }
    }
    
    /**
     * Creates a new highscores file.
     * 
     * @param file the file to write to.
     * @author Justin Pu June 4, 2015.
     */
    private static void createFile(File file) {
        try {
            PrintWriter out = new PrintWriter(file);
            out.println(HEADER);
            out.println(0);
            out.close();
        } catch (IOException e) { }
    }
    
    
    /**
     * Creates a highscores panel. The panel has the paint method overriden to
     * draw the names and logo in 
     * 
     * @author Justin Pu June 4, 2015.
     * @param file the file that the highscores are being read from.
     * @return a label with all the highscores and names. 
     */
    public static JLabel getLabel(File file) {
        ArrayList<Entry> entries = getEntries(file);
        JLabel label = new JLabel() {
            
            
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.WHITE);
                
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g.setFont(new Font("Corbel", Font.BOLD, 24));
                
                if(entries.size() == 0) {
                    g.drawString("There are no highscores to display", 220, 200);
                } else {
                    int y = 1;
                    for (Entry e : entries) {
                        g.drawString(Integer.toString(y) + ".", 70, 100 + y * 30);
                        g.drawString(e.getName(), 100, 100 + y * 30);
                        g.drawString(Integer.toString(e.getPoints()), 600, 100 + y * 30);
                        y++;
                    }
                }
            }
        };
        label.setSize(800, 600);
        return label;  
    }
}