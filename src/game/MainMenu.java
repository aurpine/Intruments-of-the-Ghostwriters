package game;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.print.*;

/**
 * The main menu class that extends JPanel, this class includes all menu flow throughout the game except for the pause menu.
 * 
 * <b> Total Time Spent: </b> 30+ hours by Siavash Samiei
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> playButton </b> CustomButton to open play menu.  
 * <p>
 * <b> instructionsButton </b> CustomButton to show instructions.  
 * <p>
 * <b> highScoresButton </b> CustomButton to show highscore selection menu.
 * <p>
 * <b> exitButton </b> CustomButton to exit the game.
 * <p>
 * <b> worldSelectButton </b> CustomButton to open world selection menu.
 * <p>
 * <b> newButton </b> CustomButton to start a new game. 
 * <p>
 * <b> backButton </b> CustomButton  to go back to different parts of the menu and navigate with ease.
 * <p>
 * <b> world1Button </b> CustomButton to start a game from world 1.
 * <p>
 * <b> world2Button </b> CustomButton to start a game from world 2.
 * <p>
 * <b> world3Button </b> CustomButton to start a game from world 3.
 * <p>
 * <b> world4Button </b> CustomButton to start a game from world 4.
 * <p>
 * <b> world5Button </b> CustomButton to start a game from world 5.
 * <p>
 * <b> printButton </b> CustomButton to print highscores.
 * <p>
 * <b> blackButton </b> CustomButton to show the character with a black primary color model
 * <p>
 * <b> whiteButton </b> CustomButton to show the character with a white primary color model
 * <p>
 * <b> blueButton </b> CustomButton to show the character with a blue secondary color model.
 * <p>
 * <b> greenButton </b> CustomButton to show the character with a green secondary color model.
 * <p>
 * <b> invertedButton </b> CustomButton to show the character with a inverted secondary color model.
 * <p>
 * <b> magentaButton </b> CustomButton to show the character with a magenta secondary color model.
 * <p>
 * <b> purpleButton </b> CustomButton to show the character with a purple secondary color model.
 * <p>
 * <b> yellowButton </b> CustomButton to show the character with a yellow secondary color model.
 * <p>
 * <b> selectButton </b> CustomButton to select the visible character model with specified color.
 * <p>
 * <b> level1Button </b> CustomButton to show highscores for level 1.
 * <p>
 * <b> level2Button </b> CustomButton to show highscores for level 2.
 * <p>
 * <b> level3Button </b> CustomButton to show highscores for level 3.
 * <p>
 * <b> level4Button </b> CustomButton to show highscores for level 4.
 * <p>
 * <b> level5Button </b> CustomButton to show highscores for level 5.
 * <p>
 * <b> background </b> Image, the background image of the main menu panel.
 * <p>
 * <b> instrctions </b> Image, the instructions image.
 * <p>
 * <b> paint </b> Boolean, determines which image to paint based on the purpose of the call of the paintComponent override method.
 * <p>
 * <b> characterPicture </b> JLabel, shows the character model.
 * <p>
 * <b> playerName </b> String, containins the player name.
 * <p>
 * <b> playerColour </b> String, containins the color of the character model that was chosen by the user.
 * <p>
 * <b> printer </b> Printer, instance of the printer class. 
 * <p> 
 * <b> menu </b> Int, value used to specifiy which menu is currently being used and therefore what action should be take when the back button is pressed.
 * <p>
 * <b> highScoresFileName </b> String, contains the highscore file name.
 * <p>
 * 
 * <b>Total hours spent:</b>
 * <p>
 * Siavash Samiei: 30 hours.
 * 
 * @author Siavash Samiei
 * @version 5 06.08.15
 * 
 */ 
public class MainMenu extends JPanel implements ActionListener {
    
    private CustomButton playButton, 
        instructionsButton, 
        highScoresButton, 
        exitButton, 
        worldSelectButton,
        newButton,
        backButton, 
        world1Button, 
        world2Button, 
        world3Button, 
        world4Button, 
        world5Button, 
        printButton,
        blackButton,
        whiteButton,
        blueButton,
        greenButton,    
        invertedButton,
        magentaButton,
        purpleButton,
        yellowButton,
        selectButton,
        level1Button,
        level2Button,
        level3Button,
        level4Button,
        level5Button;
    private Image background = Toolkit.getDefaultToolkit().createImage(Paths.GUI + "panelBack.png");
    private Image instructions  =  Toolkit.getDefaultToolkit().createImage(Paths.TEXTURES + "instructions.png");
    private boolean paint = false;
    private JLabel characterPicture;
    private String playerName;
    private String playerColour;
    private Printer printer;
    private int menu = 1;
    private String highScoresFileName;
    private boolean music = true;
    //private Clip clip = true;
    
    /**
     * This method initializes all the base components of the main menu class, adding them to the panel
     * and adding actionListeners to them.
     * <p>
     * @author Siavash Samiei May 28, 2015
     */
    private void init() {
        playerColour = "bb";
        playButton = new CustomButton("Play Game", getWidth() / 2 - 90, 175, 25);
        instructionsButton = new CustomButton("Instructions", getWidth() / 2 - 90, 250, 23);
        highScoresButton = new CustomButton("Highscores", getWidth() / 2 - 90, 325, 25);
        exitButton = new CustomButton("Exit Game", getWidth() / 2 - 90, 400, 25);  
        playButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        highScoresButton.addActionListener(this);
        exitButton.addActionListener(this);
        addKeyBindings(this);
        
    }
    
    /**
     * This method resets the main menu, removing the components on the screen and redrawing the main menu.
     * <p>
     * @author Siavash Samiei
     */
    private void resetMenu() {
        remove (newButton);
        remove (worldSelectButton);
        remove (backButton);
        remove (playButton);
        remove (highScoresButton);
        remove (instructionsButton);
        remove (exitButton);
        makeMenu();
    }
    
    /**
     * This method resets the main menu, removing the components on the screen and redrawing the main menu.
     * This method then calls play leading to the play menu.
     * <p>
     * @author Siavash Samiei
     */
    private void resetPlayMenu() {
        removeAll();
        makeMenu();
        play();
    }
    
    
    /**
     * This method resets the main menu, removing the components on the screen and redrawing the main menu.
     * This method then calls highscoreSelection leading to the highscore selection menu.
     * <p>
     * @author Siavash Samiei
     */
    private void resetHighscoreMenu() {
        removeAll();
        makeMenu();
        highscoreSelection();
    }
    
    
    /**
     * This method resets the main menu, removing all the components on the screen and redrawing the main menu.
     * <p>
     * @author Siavash Samiei
     */
    private void backToMain(){
        menu = 1;
        removeAll();
        makeMenu();
        paint = false;
    }
    
    /**
     * This method creates the entire main menu, the first screen.
     * <p> 
     * @author Siavash Samiei
     */
    public void makeMenu(){
        setLayout(null);
        this.setSize(800,600);
        init();
        add(playButton);
        add(instructionsButton);
        add(highScoresButton);
        add(exitButton);
        revalidate ();
        repaint ();
    }
    /**
     * This method creates the play menu, moving the main options and setting then enabled(false) and adding
     * all options for the play menu.
     * <p>
     * @author Siavash Samiei
     */
    private void play() {
        menu = 1;
        newButton = new CustomButton("New Game", getWidth() / 2 - 90, 175, 25);
        worldSelectButton= new CustomButton("World Select", getWidth() / 2 - 90, 250, 23);
        backButton= new CustomButton("Back", getWidth() / 2 - 90, 400, 25);
        playButton.setBounds(75, 175, 162, 64);
        instructionsButton.setBounds(75, 250, 162, 64);
        highScoresButton.setBounds(75, 325, 162, 64);
        exitButton.setBounds(75, 400, 162, 64);
        playButton.setEnabled(false);
        instructionsButton.setEnabled(false);
        highScoresButton.setEnabled(false);
        exitButton.setEnabled(false);
        add(newButton);
        add(worldSelectButton);
        add(backButton);
        backButton.addActionListener(this);
        newButton.addActionListener(this);
        worldSelectButton.addActionListener(this);
    }
    
    /**
     * This method creates the world selection menu, moving the play options and setting then enabled(false) and adding
     * all options for the world selection menu.
     * <p>
     * @author Siavash Samiei
     */
    private void worldSelection (){
        menu = 2;
        newButton.setBounds(550, 175, 162, 64);
        worldSelectButton.setBounds(550, 250, 162, 64);
        backButton.setBounds(550, 400, 162, 64);
        newButton.setEnabled(false);
        worldSelectButton.setEnabled(false);
        backButton.setEnabled(false);
        world1Button = new CustomButton("World 1 Tutorial", getWidth() / 2 - 90, 100, 16);
        world2Button= new CustomButton("World 2 Easy", getWidth() / 2 - 90, 175, 16);
        world3Button = new CustomButton("World 3 Medium",getWidth () / 2 - 90, 250, 16);
        world4Button= new CustomButton("World 4 Hard", getWidth() / 2 - 90, 325, 16);
        world5Button= new CustomButton("World 5 Nightmare", getWidth() / 2 - 90, 400, 15);
        backButton= new CustomButton("Back", getWidth() / 2 - 90, 475, 16);
        add(world1Button);
        add(world2Button);
        add(world3Button);
        add(world4Button);
        add(world5Button);
        add(backButton);
        world1Button.addActionListener(this);
        world2Button.addActionListener(this);
        world3Button.addActionListener(this);
        world4Button.addActionListener(this);
        world5Button.addActionListener(this);
        backButton.addActionListener (this);
    }
    
    /**
     * This method creates the highscore selection menu, moving the main options and setting then enabled(false) and adding
     * all options for the highscore selection menu.
     * <p>
     * @author Siavash Samiei
     */
    private void highscoreSelection (){
        menu = 3;
        level1Button = new CustomButton("World 1 Scores", getWidth() / 2 - 90, 100, 16);
        level2Button= new CustomButton("World 2 Scores", getWidth() / 2 - 90, 175, 16);
        level3Button = new CustomButton("World 3 Scores",getWidth () / 2 - 90, 250, 16);
        level4Button= new CustomButton("World 4 Scores", getWidth() / 2 - 90, 325, 16);
        level5Button= new CustomButton("World 5 Scores", getWidth() / 2 - 90, 400, 15);
        backButton= new CustomButton("Back", getWidth() / 2 - 90, 475, 16);
        playButton.setBounds(75, 175, 162, 64);
        instructionsButton.setBounds(75, 250, 162, 64);
        highScoresButton.setBounds(75, 325, 162, 64);
        exitButton.setBounds(75, 400, 162, 64);
        playButton.setEnabled(false);
        instructionsButton.setEnabled(false);
        highScoresButton.setEnabled(false);
        exitButton.setEnabled(false);
        add(level1Button);
        add(level2Button);
        add(level3Button);
        add(level4Button);
        add(level5Button);
        add(backButton);
        level1Button.addActionListener(this);
        level2Button.addActionListener(this);
        level3Button.addActionListener(this);
        level4Button.addActionListener(this);
        level5Button.addActionListener(this);
        backButton.addActionListener (this);
    }
    /**
     * This method displays the highscores for the specified world chosen.
     * <p>
     * @param world this is the world that the highscore will be displaying scores for.
     * @author Siavash Samiei
     */
    private void highscores(String world) {
        removeAll();
        menu = 4;
        add (Highscores.getLabel(new File(Paths.HIGHSCORES + world + ".txt")));
        backButton = new CustomButton("Back", 100, 500, 25);
        printButton = new CustomButton ("Print",550,500,25);
        add(printButton);
        add(backButton);
        printButton.addActionListener(this);
        backButton.addActionListener(this);
        
    }
    
    /**
     * This method displays the insctuctions screen by setting paint to true so that the override method will redraw the image onto the panel.
     * <p>
     * @author Siavash Samiei
     */
    private void instructions() {
        menu = 3;
        removeAll();
        paint=true;
        backButton= new CustomButton("Back", 540, 10, 25);
        add(backButton);
        backButton.addActionListener(this);
    }
    
    /**
     * This method creates the character selection screen where users can choose their name and the model of the character they will be playing as.
     * <p>
     * @author Siavsh Samiei June 7, 2015
     * */
    private void charSelect(int world){
        removeAll();
        menu = 3;
        playerName = JOptionPane.showInputDialog (this,"Enter a player name less than 40 characters","Player Name");{
            if (playerName != null){
                if (playerName.length()<40 && playerName.matches ("[A-Za-z0-1_ ]+")){
                    try{
                        characterPicture = new JLabel (new ImageIcon (ImageIO.read(new File(Paths.PLAYER+playerColour+"s.png")).getScaledInstance(240,320,Image.SCALE_FAST)));
                    }catch(IOException ie){}
                    blackButton = new CustomButton ("Black",10,210,25);
                    whiteButton = new CustomButton ("White",10,320,25);
                    blueButton = new CustomButton ("Blue",200,85,25);
                    greenButton = new CustomButton ("Green",200,160,25);
                    invertedButton = new CustomButton ("Inverted",200,235,25);
                    magentaButton = new CustomButton ("Magenta",200,310,25);
                    purpleButton = new CustomButton ("Purple",200,385,25);
                    yellowButton = new CustomButton ("Yellow",200,460,25);
                    selectButton = new CustomButton ("Select!",400,500,25);
                    backButton = new CustomButton ("Back",575,500,25);    
                    add(characterPicture);
                    add(blackButton);
                    add(whiteButton);
                    add(selectButton);
                    add(backButton); 
                    characterPicture.setLocation(450,150);
                    characterPicture.setSize(240,320);
                    blackButton.addActionListener(this);
                    whiteButton.addActionListener(this);
                    backButton.addActionListener(this);
                    blueButton.addActionListener(this);
                    greenButton.addActionListener(this);
                    invertedButton.addActionListener(this);
                    magentaButton.addActionListener(this);
                    purpleButton.addActionListener(this);
                    yellowButton.addActionListener(this);
                    selectButton.addActionListener(
                                                   new ActionListener(){
                        public void actionPerformed (ActionEvent ae){
                            InstrumentsOfTheGhostwriters.newGame (world,false,playerName,playerColour);
                        }
                    }
                    );
                    repaint();
                    revalidate();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Please enter a valid name less than 40 characters long.");
                    charSelect(world);
                } 
            }
            else
                backToMain();
        }
    }
    /**
     * This method overrides the actionPerformed method in ActionListener. This method will 
     * call specific methods and do actions based on the actionCommand recieved when a button is clicked.
     * <p>
     * @author Siavsh Samiei June 5, 2015
     * */
    public void actionPerformed (ActionEvent e)
    {
        if (e.getActionCommand().equals("Play Game")) {
            play();
        } else if (e.getActionCommand().equals("Back")) {
            if (menu==1)
                resetMenu();
            else if (menu == 2)
                resetPlayMenu();
            else if (menu ==3)
                backToMain();
            else 
                resetHighscoreMenu();
        } else if (e.getActionCommand().equals ("New Game")) {
            charSelect(1);
        } else if (e.getActionCommand().equals ("World Select")){
            worldSelection();
        }else if (e.getActionCommand().equals ("World 1 Tutorial")){
            charSelect(1);
        }else if (e.getActionCommand().equals ("World 2 Easy")){
            charSelect(2);
        }else if (e.getActionCommand().equals ("World 3 Medium")){
            charSelect(3);
        }else if (e.getActionCommand().equals ("World 4 Hard")){
            charSelect(4);
        }else if (e.getActionCommand().equals ("World 5 Nightmare")){
            charSelect(5);
        }else if (e.getActionCommand().equals("Exit Game")) {
            System.exit(0);
        }else if (e.getActionCommand().equals("Highscores")) {
            highscoreSelection();
        }else if (e.getActionCommand().equals ("World 1 Scores")||e.getActionCommand().equals ("World 2 Scores")||e.getActionCommand().equals ("World 3 Scores")||e.getActionCommand().equals ("World 4 Scores")||e.getActionCommand().equals ("World 5 Scores")){
            highScoresFileName = e.getActionCommand().substring(0,7);
            highscores(highScoresFileName); 
        }else if (e.getActionCommand().equals ("Instructions")) {
            instructions();
        }else if (e.getActionCommand().equals ("Black")||e.getActionCommand().equals ("White")){
            add(blueButton);
            add(greenButton);
            add(invertedButton);
            add(magentaButton);
            add(purpleButton);
            add(yellowButton);
            playerColour = (e.getActionCommand().charAt(0)+""+playerColour.charAt(1)+"").toLowerCase();
            remove(characterPicture);
            try{
                characterPicture = new JLabel (new ImageIcon (ImageIO.read(new File(Paths.PLAYER+playerColour+"s.png")).getScaledInstance(240,320,Image.SCALE_FAST)));
            }catch(IOException ie){}
            add(characterPicture);
            characterPicture.setLocation(450,150);
            characterPicture.setSize(240,320);
        }else if (e.getActionCommand().equals ("Blue")||e.getActionCommand().equals ("Green")||e.getActionCommand().equals ("Inverted")||e.getActionCommand().equals ("Magenta")||e.getActionCommand().equals ("Purple")||e.getActionCommand().equals ("Yellow")){
            playerColour = (playerColour.charAt(0)+""+e.getActionCommand().charAt(0)+"").toLowerCase();
            remove(characterPicture);
            try{
                characterPicture = new JLabel (new ImageIcon (ImageIO.read(new File(Paths.PLAYER+playerColour+"s.png")).getScaledInstance(240,320,Image.SCALE_FAST)));
            }catch(IOException ie){}
            add(characterPicture);
            characterPicture.setLocation(450,150);
            characterPicture.setSize(240,320);
        }
        else {
            if (e.getActionCommand().equals("Print")){
                printer = new Printer();
                printer.setHighscoresFile(new File (Paths.HIGHSCORES + highScoresFileName + ".txt"));
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(printer);
                boolean ok = job.printDialog();
                if (ok) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                    }
                }
            }
        }
        
    }
    
    /**
     * Opens the chm help dialog. 
     * <p>
     * @author Justin Pu June 8, 2015. 
     * 
     */
    private void runChm() {
        try {
            Runtime.getRuntime().exec("hh.exe " + Paths.CHM + "help.chm");
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't find Help File", "Error", JOptionPane.WARNING_MESSAGE);
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
        if (!paint)
            g.drawImage(background, 0, 0, null);
        else
            g.drawImage(instructions, 0, 0, null);
        repaint();
        revalidate();
    }
    
    /**
     * <p> Adds Key Bindings for the MainMenu for passed JComponent.
     * 
     * 
     * @param c the JComponent to add keybindings to. 
     * @author Justin Pu June 6, 2015
     * 
     */
    public void addKeyBindings(JComponent c) {
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control released P"), "Play Game");
        c.getActionMap().put("Play Game", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (menu == 1) {
                    play();
                    menu = 2;
                }
            }
        });
        
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control released B"), "Back");
        c.getActionMap().put("Back", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (menu == 2) {
                    resetMenu();
                    menu = 1;
                }
            }
        });
        
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control released N"), "New Game");
        c.getActionMap().put("New Game", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (menu == 2)
                    InstrumentsOfTheGhostwriters.newGame(1,false,playerName,playerColour);
            }
        });
        
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control released E"), "Exit Game");
        c.getActionMap().put("Exit Game", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (menu == 1)
                    System.exit(0);
            }
        });
        
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control released H"), "Highscores");
        c.getActionMap().put("Highscores", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (menu == 1)
                    highscoreSelection();
            }
        });
        
        
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released F1"), "CHM");
        c.getActionMap().put("CHM", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                runChm();
            }
        });
        
    }
    
    
}