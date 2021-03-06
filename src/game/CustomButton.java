package game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/** 
 * This class extends JButton. This class is used to create custom buttons with a specific design to match the program.
 * 
 * <b> Total Time Spent: </b> 3 hours by Siavash Samiei
 * <p>
 * <b> Instance Variables </b> 
 * <p>
 * <b> ICON </b> static final Icon[], stores all button icons at all different states.
 * <p>
 * <b>Total time spent:</b>
 * <p>
 * Siavash Samiei: 4 hours
 * <p>
 * Justin Pu: 0.5 hours
 * 
 * @author Siavash Samiei May, 23, 2015.
 * @version 7 06.08.15
 */
public class CustomButton extends JButton {
  private final static Icon[] ICON = new Icon [] {new ImageIcon(Paths.GUI + "button.png"), new ImageIcon(Paths.GUI + "button_hover.png"), new ImageIcon(Paths.GUI + "button_click.png")};
  
  /**
   * The custom button constructor. Creates a JButton with custom 
   * 
   * @param s the text on the button
   * @param x the x location of the button. 
   * @param y the y location of the button.
   * @param fontS the font size of the text to be displayed. 
   */
  public CustomButton(String s, int x, int y, int fontS) {
    setText (s);  
    setIcon(ICON[0]);
    setBounds(x, y, 162, 64);
    setForeground (Color.WHITE);
    setVerticalTextPosition(JButton.CENTER);
    setHorizontalTextPosition(JButton.CENTER);
    setFont (new Font("Corbel", Font.BOLD, fontS));
    setOpaque(false);
    setFocusPainted(false); 
    setBorderPainted(false); 
    setContentAreaFilled(false); 
    addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        buttonMouseEntered();
      }
      public void mouseExited(MouseEvent e) {
        buttonMouseExited();
      }
      public void mousePressed(MouseEvent e) {
        buttonMousePressed();
      }
      public void mouseReleased(MouseEvent e) {
        buttonMouseReleased();
      }
    }); 
  }
  
  /**
   * Changes the icon of the button to the hover icon when the cursor enters the button's area. 
   * 
   * @author Siavash Samiei May, 23, 2015.
   */
  private void buttonMouseEntered(){
    setIcon(ICON[1]);
  }
  
  
  /**
   * Changes the icon of the button to normal when the cursor exits the button. 
   * 
   * @author Siavash Samiei May 23, 2015. 
   */
  public void buttonMouseExited(){
    setIcon(ICON[0]);
  }
  
  /**
   * 
   * Changes the icon of the button to the pressed button when the user clicks 
   * their mouse. 
   * 
   * @author Siavash Samiei May 23, 2015. 
   */
  public void buttonMousePressed(){
    setIcon(ICON[2]);
  }
  
  /**
   * Changes the icon of the button back to normal when the mouse is released. 
   * 
   * @author Siavash Samiei May 23, 2015. 
   */
  public void buttonMouseReleased(){
    setIcon(ICON[0]);
  } 
}