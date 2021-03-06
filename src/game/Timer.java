package game;


/**
 * Used for Timing gameplay. This is factored into the highscores. 
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> pausedTime </b> Int storing the of total amount of time that the timer has been paused for.
 * <p>
 * <b> latestPause </b> Long storing the latest pause start time.
 * <p>
 * <b> start </b> Long storing the starting time of the Timer.
 * <p>
 * <b> pause </b> Boolean whether the timer is paused or not. 
 * <p>
 * Total time spent:
 * <p>
 * Justin Pu: 2 hours
 * 
 * @author Justin Pu Modified by Siavash Samiei
 * @version 3 06.08.15 
 */
class Timer { 
    
    private int pausedTime;
    private long latestPause;
    private long start;
    private boolean paused = true;
    
    
    /**
     * Initializes the timer. 
     * <p>
     * @author Justin Pu June 2, 2015.
     */
    public void start() {
        start = System.currentTimeMillis();
        paused = false;
    }
    
    
    /**
     * Returns the amount of time that the timer has been running for.
     * <p>
     * @return time in milliseconds that the Timer has recorded.
     * @author Justin Pu June 2, 2015.
     */
    public int getTime() {
        if (paused) {
            return (int) (latestPause - start - pausedTime);
        }
        return (int) (System.currentTimeMillis() - start - pausedTime);
    }
    
    
    /**
     * Pauses the timer.
     * <p>
     * @author Justin Pu June 2, 2015.
     */
    public void pause() {
        if (!paused) {
            paused = true;
            latestPause = System.currentTimeMillis();
        }
    }
    
    
    /**
     * Unpauses the timer. 
     * <p>
     * @author Justin Pu June 2, 2015.
     */
    public void unpause() {
        if (paused) {
            paused = false;
            pausedTime += (int) (System.currentTimeMillis() - latestPause);
        }
    }
    
    
    /**
     * Returns the time formatted as a String.
     * <p>
     * @return the time formatted into HH:MM.S format.
     * @author Justin Pu June 6, 2015
     * 
     */
    public String getFormattedTime() {
        int time = getTime();
        int hr = time / 3600000 % 24;
        int min = time / 60000 % 60;
        int sec = time / 1000 % 60;
        int mil = time % 1000 / 10;
        return (hr < 10 ? "0" : "") + hr + ":" + (min < 10 ? "0" : "") + min + ":" + (sec < 10 ? "0" : "") + sec + "." + (mil < 10 ? "0" : "") + mil;
    }
}