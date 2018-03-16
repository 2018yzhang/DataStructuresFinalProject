/*
 * File: GameLayout.java
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
 * A class to decription the location
 * 
 * @author Yidan Zhang
 * @version Dec. 1, 2016
 */
public class LearningBoard extends JFrame{
  
  //a two dimensional grid of buttons
  private MyButton btn,btnS;
  
  //the new gameLayout
  private GameLayout game;
  
  //the position of number button 
  private Point point, point1;
  
  //a set of buttons
  Set<MyButton> buttons = new HashSet<MyButton>();
  
   /*
   * Creates learning board for the game 
   * 
   * @param title of the quiz board
   * @param game the GameLayout which will be depended on 
   */
  public LearningBoard (String title,GameLayout game){
    super(title);
    this.game=game;
    GridLayout layout = new GridLayout(3,4);
    setLayout(layout);
    init();
  }
   /*
   * Creates learnign board for the game 
   * 
   * @param title of the quiz board
   */
  public LearningBoard(String title)throws IOException{
    super(title);
    
    String infoName = "Properties.game";
    String conName = "connection.game";
    game = new GameLayout(infoName, conName);
    GridLayout layout = new GridLayout(3,4);
    setLayout(layout);
    
  }
    /*
   * init status of the quiz board
   */
  public void init() {
   // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.black);
    Iterator<String> locations =game.iteratorLoc();
    int i = 0;
    while(locations.hasNext()){
      String loc = locations.next();
      btn = new MyButton(loc,Integer.toString(game.getDescription(loc).getShowingNum()));
      String c = game.getDescription(loc).getColor();
      Color color = stringToColor(c);
      //just showing 1
      if(game.getDescription(loc).getShowingNum()==1){
        btnS=btn;
        btnS.setBackground(color);
        btnS.addMouseListener(new MouseAdapter(){
          @Override
          public void mousePressed(MouseEvent e){
            point =e.getPoint();
            numShowing( 1);
            start();
          }
        });
        buttons.add(btnS);
        getContentPane().add(btnS); 
      }
      else{
        btn.setBackground(color);
        buttons.add(btn);
        btn.setVisible(false);
        getContentPane().add(btn); 
      }
    }
  }
  /*
   * start when click 1
   */
  public void start(){
    for(JButton b:buttons){
      b.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e){
          point1 =e.getPoint();
          if(b.getText().equals("1")||b.getText().equals("32")){
            JDialog.setDefaultLookAndFeelDecorated(true);
            int re = JOptionPane.showConfirmDialog(null,"Sorry, you are not able to keep going on. Do you want to restart?","Ending",
                                                   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(re==JOptionPane.NO_OPTION){
              System.exit(0);
            }
            else if(re==JOptionPane.YES_OPTION){
              restart();
            }
          }
          else{
            numShowing(Integer.parseInt(b.getText()));
          }
        }
      });
    }
  }
    /*
   * showing the number which is the property of next location
   * 
   * @param currentShowing number of current showing
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
   * start run the quiz board
   */
  public void run(){
    setSize(800,600);
    setVisible(true);
  }
  
}