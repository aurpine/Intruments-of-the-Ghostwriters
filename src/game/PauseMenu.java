package game;


/**
 * The pause menu of the game that is used during gameplay.
 * <b> Total Time Spent: </b> 3 hours By Siavash Samiei
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> resumeButton </b> CustomButton, resumes game.
 * <p>
 * <b> exitButton </b> CustomButton, exits the game and returns to main menu. 
 * <p>
 * <b> background </b> Image, the background image of this JPanel.
 * <p>
 * <b>Total hours spent:</b>
 * <p>
 * Siavash Samiei: 2 hours.
 * 
 * @author Siavash Samiei
 * @version 5 06.08.15
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PauseMenu extends JPanel implements ActionListener{
  private CustomButton resumeButton, 
    exitButton;
  private Image background = Toolkit.getDefaultToolkit().createImage(Paths.GUI + "pauseBack.png");
  
  /**
   * This method calls the resumeGame method in the dirver class. 
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  private void resumeGame() {
    InstrumentsOfTheGhostwriters.resumeGame();
  }
  
  /**
   * This method calls the exitToMain method in the dirver class. 
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  private void exitToMain(){
    InstrumentsOfTheGhostwriters.exitToMain(false);
  }
  
  
  /**
   * This method initializes all buttons in the pause menu. 
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  private void init() {
    resumeButton= new CustomButton("Resume Game",getWidth()/2-81,175,20);
    exitButton= new CustomButton("Exit to Menu",getWidth()/2-81,400,20);  
    resumeButton.addActionListener(this);
    exitButton.addActionListener(this);
  }
  
  
  /**
   * This method overrides the actionPerformed method in ActionListener.
   * This method determines what each button does when clicked calling the different
   * methods based on each click.
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ( ).equals("Resume Game"))
    {
      resumeGame();
    }
    else if (ae.getActionCommand ( ).equals( "Exit to Menu"))
    {
      exitToMain();
    }
  }
  
  /**
   * This method overrides the paintComponent method in JComponent.
   * This method draws the background image for the pause menu.
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(background, 0, 0, null);
    repaint();
    revalidate();
  }
  
  
  /**
   * This method draws all components of the pause menu.
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  public void makeMenu(){
    setLayout(null);
    setSize(800,600);
    init();
    add(resumeButton);
    add(exitButton);
    revalidate ();
    repaint ();
  }
  
  
}