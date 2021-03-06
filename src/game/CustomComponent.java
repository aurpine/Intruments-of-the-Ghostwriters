package game;

import javax.swing.*;
import java.awt.*;
/**
 * The purpose of this method is to create a component to set as the background of the JFrame class. This is done because JComponents draw over everything if set as background so in this class I overried painComponent and I am therefore able to set the background of the JFrame as the JComponent without drawing over everything.
 * <b> Total Time Spent: </b> 30 minutes by Siavash Samiei
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> background </b> Image, background image import to be set as the background image for this JComponent.
 * 
 * <p>
 * <b>Total time spent:</b>
 * <p>
 * Siavash Samiei: 0.25 hours.
 * 
 * @author Siavsh Samiei June 1, 2015
 * @version 4 06.08.15
 */
public class CustomComponent extends JComponent{
    private Image background = Toolkit.getDefaultToolkit().createImage(Paths.GUI + "window.png");
    
    /**
     * Overrides the paintComponent method to draw the background image of the JComponent.
     * <p>
     * @author Siavash Samiei
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}