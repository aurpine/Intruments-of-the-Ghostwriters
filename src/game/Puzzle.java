package game;


//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * The purpose of this sclass is to create the puzzle that the user is presented with at
 * the end of each level. The puzzle class reads in all information about the puzzle from
 * the world file that is for each level. The world file contains all the names of the hint
 * images, the question image as well as the answer to the puzzle for that specific world.
 * These information is read in and used to create a puzzle.
 * Extends JPanel Implements ActionListener.
 * <b> Total Time Spent: </b> 10 hours by Siavsh Samiei
 * <p>
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> hints </b> JLabel Array of labels for the different hints the user will find available.
 * <p>
 * <b> question </b> JLabel that is the question image for the specified puzzle.
 * <p>
 * <b> answer </b> String that holds the answer of the puzzle.
 * <p>
 * <b> answerButton </b> CustomButton that the user clicks to answer the puzzle.
 * <p>
 * <b> giveUpButton </b> CustomButton that the user clicks to give up on answering the puzzle.
 * <p>
 * <b> contButton </b> CustomButton that the user clicks to navigate back to main menu after winning the game.
 * <p>
 * <b> answerInput </b> String that holds the answer inputted by the user.
 * <p>
 * <b> background </b> Image of the background for this panel.
 * <p>
 * <b> vicScreen </b> Image of the victory screen of the game, seen when the 5th puzzle is answered correctly.
 * <p>
 * <b> letters </b> Int that stores the number of letters the user has picked up in order to know how many hints to display.
 * <p>
 * <b> win </b> Boolean stores whether or not the user has finished the game successfully.
 * <p>
 * <b> game </b> The instance of game in this puzzle that is passed through the constructor in order to have the details from the level.
 * <p>
 * <b>Time spent:</b> 
 * <p>
 * Siavash Samiei: 6 hours
 * <p>
 * @author Siavash Samiei 
 * @version 3 06.08.30
 */
public class Puzzle extends JPanel implements ActionListener {
  
  private JLabel[] hints = new JLabel[5];
  private JLabel question;
  private String answer;
  private CustomButton answerButton = new CustomButton ("Answer!",189,500,25);
  private CustomButton giveUpButton = new CustomButton ("Give up.",449,500,25);
  private CustomButton contButton = new CustomButton ("Continue", 300,120,25);
  private String answerInput;
  private Image background = Toolkit.getDefaultToolkit().createImage(Paths.GUI + "panelBack2.png");
  private Image vicScreen = Toolkit.getDefaultToolkit().createImage (Paths.TEXTURES+ "victory.png");
  private int letters;
  private boolean win = false;
  private Game game;
  
  
  /**
   * Constrcutor of the puzzle classes that initializes the game and letters values
   * in order to create the rest of the interface of the puzzle. This method also
   * sets the boundaries of the puzzle panel.
   * <p>
   * @author Siavash Samiei June 1, 2015
   * @param game the instance of game this puzzle is getting information from
   * @param letters the number of letters picked up by the user.
   */
  public Puzzle(Game game, int letters) {
    super();
    this.letters = letters;
    this.game = game;
    setSize(800,600);
    setLayout(null);
    this.game = game;
    readHints();
    addAnswer();
    drawHints();
    drawQuestion();
    repaint();
    revalidate();
  }
  
  
  /**
   * This method is the method that reads in all the information about the puzzle, 
   * including the names of the hint images, the name of the question image, and the answer
   * to the puzzle and assigns those values to the correct variables.
   * The try catch statement is for catching any IOExceptions that may arrise when useing FileReader.
   * <p>
   * @author Siavash Samiei June 1, 2015
   */
  private void readHints() {
    try {
      BufferedReader in = new BufferedReader (new FileReader (Paths.LEVELS + game.getLevel().getName() + ".txt"));
      in.readLine();
      in.readLine();
      in.readLine();
      in.readLine();
      // Hint images
      final String[] TEMP = in.readLine().split(" ");
      hints = new JLabel [TEMP.length];
      for(int i = 0; i < TEMP.length; i++) {
        hints[i] = new JLabel(new ImageIcon (Paths.AUTHORS + TEMP[i]));
      }
      question = new JLabel(new ImageIcon (Paths.AUTHORS + in.readLine()));
      answer = in.readLine(); 
      in.close();
    } catch (IOException e) {}
  }
  
  
  /**
   * The method that draws all the hint images onto specified sections of the screen
   * based on the number of letters that the user has picked up. These hints are used
   * to solve the puzzle and are saved in png format.
   * <p> 
   * @author Siavash Samiei June 1, 2015
   */
  private void drawHints() {
    for (int i = 0; i < letters ; i++ ){
      add(hints[i]);
      hints[i].setSize(165,200);
      hints[i].setLocation((letters == 5) ? 0+i*160: (letters == 4) ? 20 + i * 200 : (letters==3) ? 80+i*240: (letters == 2) ? 160 + i * 320 : 320, 250);
      hints[i].setVisible(true);
      
    }
  }
  
  
  /**
   * This method adds the question JLabel to the screen.
   * <p>
   * @author Siavash Samiei 
   */
  private void drawQuestion() {
    add(question);
    question.setSize (600,300);
    question.setLocation(100,-20);
  }
  
  
  /**
   * This method is used to add the answer and give up buttons to the Puzzle JPanel
   * and to add the actionListeners to said buttons. 
   * <p>
   * @author Siavash Samiei June 2, 2015 
   */
  private void addAnswer() {
    add(answerButton);
    add(giveUpButton);
    answerButton.addActionListener(this);
    giveUpButton.addActionListener(this);
  }
  
  
  /**
   * This method overrides the painComponent method in JPanel and draws the desired background
   * image as the background of this JPanel.
   * <p>
   * @author Siavash Samiei 
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!win)
      g.drawImage(background, 0, 0, null);
    else
      g.drawImage(vicScreen, 0, 0, null);
    
    revalidate();
    repaint();
  }
  
  
  /**
   * This is the method that shows the answer messages when the user clicks answer. First it askes
   * them what their answer is, if it is correct it asks them if they'd like to proceed, if so it loads
   * the next level, if they user has won it loads the victory scree. If the answer is incorrect it prompts
   * the user to try again. The user can press cancel to return to the original puzzle screen. If the user enters
   * a correct answer and does not choose the continue, the game ends and the user is returned to the main menu of
   * the program.
   * <p>
   * @author Siavash Samiei June 2, 2015 
   * @param right boolean parameter to determine whether the answer entered by the user was right or wrong.
   */
  private void showAnswerMessage(boolean right){
    if (right) {
      Player p = game.getPlayer();
      int points = Highscores.Entry.points(game.getNumLetters(), game.getTime(), p.getDeaths()); 
      if (!game.getLevel().getName().equals("World 5")) {
        int proceed = JOptionPane.showConfirmDialog (this, "Correct! Good job, "+p.getName()+", you have finished this level with a score of : "+ points +"! Would you like to go on to the next level?","Proceed?",JOptionPane.YES_NO_OPTION);
        if (proceed == JOptionPane.YES_OPTION) {
          InstrumentsOfTheGhostwriters.newGame(Integer.parseInt(game.getLevel().getName().substring(6))+1,true, p.getName(), p.getColour());
        }
        else {
          proceed = JOptionPane.showConfirmDialog (this, "Are you sure you want to exit? All unsaved data will be lost.","Proceed?",JOptionPane.YES_NO_OPTION);
          if (proceed == JOptionPane.YES_OPTION) {
            InstrumentsOfTheGhostwriters.exitToMain(true);
          }
          else return;
        }
      }
      else {
        victoryScreen();
      }
      Highscores.add(new File(Paths.HIGHSCORES + game.getLevel().getName() + ".txt"), p.getName(), points);
    }
    else{
      JOptionPane.showMessageDialog (this, "Incorrect, try again!");
      answerDialog();
    } 
  }
  
  
  /**
   * The purpose of this method is to show either the correct answer dialog or the
   * inccorrect answer dialog based on the answer the user enters. This also assigns
   * the answer the user entered to the answerInput variable.
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  private void answerDialog(){
    try{
      answerInput = JOptionPane.showInputDialog(this, "Enter your answer:");
      if (answerInput.replaceAll("\\s+","").toLowerCase().contains(answer)) {
        showAnswerMessage(true);
      } else if (!answerInput.replaceAll("\\s+","").toLowerCase().contains(answer))
        showAnswerMessage(false);
    }catch (NullPointerException e) {}
  }
  
  
  /**
   * The purpose of this method is to show a confirm dialog to check if the user is 
   * sure that they want to give up on the puzzle. It is called when the give up button
   * is pressed. The user can choose to say no and stay on the puzzle screen for more attempts
   * at solving the puzzle or they can give up and return to the main menu.
   * <p>
   * @author Siavash Samiei June 2, 2015
   */
  private void giveUpDialog(){
    int dialogResponse = JOptionPane.showConfirmDialog (this, "Are you sure you want to quit?","Quit?",JOptionPane.YES_NO_OPTION);
    if (dialogResponse == JOptionPane.YES_OPTION)
      InstrumentsOfTheGhostwriters.exitToMain(true);
    else
      return;
  }
  
  
  /**
   * This method displays a victory screen image if the user has won the game (answer puzzle 5 correctly).
   * <p>
   * @author Siavash Samiei June 3, 2015
   */
  private void victoryScreen(){
    removeAll();
    win = true;
    add(contButton);
    contButton.addActionListener(this);  
  }
  
  
  /**
   * This method is the actionPerformed method of the class that overrides the actionPerformed method
   * in ActionListener. This method checks the different button inputs throughout the puzzle checking
   * if the user has pressed any buttons and if so, it does the specified task if the click is detected.
   * <p>
   * @author Siavash Samiei June 3, 2015 
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ( ).equals( "Answer!")){
      answerDialog();
    } else if (ae.getActionCommand ( ).equals( "Give up.")){
      giveUpDialog();
    } else if (ae.getActionCommand ( ).equals ("Continue")){
      InstrumentsOfTheGhostwriters.exitToMain(true);
    }
  }
}