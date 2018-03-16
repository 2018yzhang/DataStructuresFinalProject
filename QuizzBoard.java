/*
 * File: QuizzBoard.java
 */
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.applet.*;
import java.lang.reflect.Field;
import acm.program.*;
import acm.graphics.*;
import javax.imageio.*;
/*
 * A class to review what the user learning and quiz themselves
 * 
 * @author Yidan Zhang
 * @version Dec. 1, 2016
 */
public class QuizzBoard extends JFrame{
  //one MyButton for general button
  private MyButton btn;
  //four buttons for different function in this game board
  JButton sbtn= new JButton("Review");
  JButton mbtn=new JButton("Quiz");
  JButton gbtn=new JButton("Your Score");
  JButton rbtn = new JButton("Restart");
  //the score of the user
  int score=0;
  //the new gameLayout
  private GameLayout game;
  private LocationDescription loc = null;
  private String s;
  //the number of showing
  int showingNum = 0;
  //initial location
  String curL = "S";
  //get the information from the user
  String cloc;
  private PrimitiveIterator.OfInt random = new Random().ints(0,5).iterator();
  //a set of buttons
  Set<MyButton> buttons = new HashSet<MyButton>();
  /*
   * Creates quiz board for the game with review, quiz, score, and restart
   * 
   * @param title of the quiz board
   * @param game the GameLayout which will be depended on 
   */
  public QuizzBoard(String title,GameLayout game){
    super(title);
    this.game=game;
    GridLayout layout = new GridLayout(4,4);
    setLayout(layout);
    init();
  }
    /*
   * Creates quiz board for the game with review, quiz, score, and restart
   * 
   * @param title of the quiz board
   */
  public QuizzBoard(String title)throws IOException{
    super(title);
    
    String infoName = "Properties.game";
    String conName = "connection.game";
    game = new GameLayout(infoName, conName);
    GridLayout layout = new GridLayout(4,4);
    setLayout(layout);
    
  }
  /*
   * init status of the quiz board
   */
  public void init() {
  //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.black);
    cloc="";
    Iterator<String> locations =game.iteratorLoc();
    int i = 0;
    while(locations.hasNext()){
      String loc = locations.next();
      btn = new MyButton(loc,Integer.toString(game.getDescription(loc).getShowingNum()));
      String c = game.getDescription(loc).getColor();
      Color color = stringToColor(c);
      btn.setBackground(color);
      buttons.add(btn);
      getContentPane().add(btn); 
    }
    
    //review button set
    sbtn.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent e){
        startAt();
      }});
    getContentPane().add(sbtn);
    
    //Quiz button set
    getContentPane().add(mbtn);
    ActionListener l = new AddScoreListener();
    mbtn.addActionListener(l);
    gbtn.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent ei){
            JDialog.setDefaultLookAndFeelDecorated(true);
        JOptionPane.showMessageDialog(null,"Your score is "+score,"SCORE",JOptionPane.INFORMATION_MESSAGE);
      }});
    getContentPane().add(gbtn);
    //restart button set
    rbtn.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent ei){
        restart();
        score=0;
      }});
    getContentPane().add(rbtn);
    
  }
  /*
   * a method start at what the user want
   */
  
  public void startAt(){
    s = JOptionPane.showInputDialog("Choose where you want to start? 1,2,4,8,16");

    //System.out.println(s);
    showingNum=Integer.parseInt(s);
    loc = game.breadthFirstSearch(game.getDescription(curL),showingNum);

    System.out.println(loc);
    cloc= loc.getLocation();
    System.out.println(cloc);
    for(MyButton m : buttons){
      if(m.getLoc().equals(cloc)){
        m.setVisible(true);
        startGame(m); 
      }
      else{
        m.setVisible(false);
      }
    }
    }
  
  /*
   * a method to show the next number when the user click
   * 
   * @param b the button the user click
   */
  public void startGame(MyButton b){
    b.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent e){
        String location = b.getLoc();
        if(b.getShowing().equals("32")){
            JDialog.setDefaultLookAndFeelDecorated(true);
          int re = JOptionPane.showConfirmDialog(null,"Sorry, you are not able to keep going on. Do you want to restart?","Ending",
                                                 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if(re==JOptionPane.NO_OPTION){
            System.exit(0);
          }
          else if(re==JOptionPane.YES_OPTION){
            restart();
          }
        }else{
        Iterator<String> connections = game.iteratorCon(location);
        Set<String> neis= new HashSet<String>();
        while(connections.hasNext()){
          String nei = connections.next();
          neis.add(nei);
        }
        for(MyButton m:buttons){
          for(String n :neis){
            if(m.getLoc().equals(n)){
              m.setVisible(true);
              startGame(m);
            }
            else{
              m.setVisible(false);
            }
          }
        }
      }
      }
    });
  }
  
  /*
   * inner class to show how the quiz button work
   */
  class AddScoreListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      
      int n = random.nextInt();
          JDialog.setDefaultLookAndFeelDecorated(true);
      JOptionPane.showMessageDialog(null,"Which number is 2 of "+n+" power? Click it!","Question",JOptionPane.INFORMATION_MESSAGE);
      scoreAdd(n,e);      
    }
    public void scoreAdd(int n,ActionEvent e){
      System.out.println(n);
      String showing = Integer.toString((int)Math.pow(2,n));
      System.out.println(showing);
      boolean clicked =false;
      if(clicked==true){
        for(MyButton m:buttons){
          if(m.getShowing().equals(showing)){
                JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showMessageDialog(null,"Good Job","Congrats",JOptionPane.INFORMATION_MESSAGE);
            score+=10;
            restart();
          }
          else{
                JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showMessageDialog(null,"Wrong! Think more, please","Warning",JOptionPane.INFORMATION_MESSAGE);
            scoreAdd(n,e);
          }
        }
      }
      for(MyButton b : buttons){
        b.addMouseListener(new MouseAdapter(){
          @Override
          public void mousePressed(MouseEvent ei){
            
            if(b.getShowing().equals(showing)){
                  JDialog.setDefaultLookAndFeelDecorated(true);
              JOptionPane.showMessageDialog(null,"Good Job","Congrats",JOptionPane.INFORMATION_MESSAGE);
              score+=10;
              restart();
            }
            else{
                  JDialog.setDefaultLookAndFeelDecorated(true);
              JOptionPane.showMessageDialog(null,"Wrong! Think more, please","Warning",JOptionPane.INFORMATION_MESSAGE);
              scoreAdd(n,e);
            }
          }
        });
        clicked=true;
      }
    }
  }
  
  /*
   * transfer string to color
   * 
   * @color which is needed to transfer
   * @return color
   */
  public static Color stringToColor(String color){
    try{
      return Color.decode(color);
    }catch(NumberFormatException nfe){
      try{
        final Field f = Color.class.getField(color);
        return (Color)f.get(null);
      }catch(Exception ce){
        return Color.white;
      }
    }
  }
  
  /*
   * showing the number which is the property of next location
   * 
   * @param currentShowing the number of current showing
   */
  public void numShowing(int currentShowing){
    String showing = Integer.toString(currentShowing *2);
    for(JButton b : buttons){
      if(b.getText().equals(showing)){
        b.setVisible(true);
      }
    }
  }
  /*
   * restart everything
   */
  public void restart(){
    getContentPane().removeAll();
    repaint();
     init();
     run();
  }
  /*
   * start run the quiz board
   */
  public void run(){ 
    setSize(800,600);
    setVisible(true);
  }
}