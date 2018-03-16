/*
 * File: StartPage.java
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.applet.*;
import java.lang.reflect.Field;
import acm.program.*;
import acm.graphics.*;

/*
 * A class to decription the location
 * 
 * @author Yidan Zhang
 * @version Dec. 1, 2016
 */
public class StartPage extends JFrame{
  //create four funtion buttons for board
  private JButton play = new JButton("Learning");
  JButton instruction = new JButton("Instruction");
  JButton exit = new JButton("Exit");
  JButton quizz = new JButton("Quiz");
  //create a new GameLayout instance
  GameLayout game=null;
  /*
   * Creates a start page for the whole game with learning, quiz, instructions, and exit
   * 
   * @param title of the StartPage
   */
  public StartPage(String title) throws IOException{
    super(title);
    String infoName = "Properties.game";
    String conName = "connection.game";
    game = new GameLayout(infoName, conName);
  }
  /*
   * initial statue of the startPage
   */
  public void init(){
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    //create box to make buttons organized
    Box box = Box.createVerticalBox();
    
    
    play.setPreferredSize(new Dimension(300,300));
    play.setForeground(Color.WHITE);
    play.setBackground(Color.black);
    box.add(play);
    play.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e){
        LearningBoard lb = new LearningBoard("Learning",game);
        lb.run();
      }
    });
    
    
    box.add(Box.createVerticalStrut(80));
    
    quizz.setPreferredSize(new Dimension(300,300));
    quizz.setForeground(Color.WHITE);
    quizz.setBackground(Color.black);
    box.add(quizz);
    quizz.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent e){
        QuizzBoard qb = new QuizzBoard("Quiz",game);
        qb.run();
      }
    });
    
    
    box.add(Box.createVerticalStrut(80));
    
    instruction.setPreferredSize(new Dimension(300,300));
    instruction.setForeground(Color.WHITE);
    instruction.setBackground(Color.black);
    box.add(instruction);
    instruction.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent e){
        String msg = "<html>Welcome to Number Learning Game!<br>This is a game about the power of 2.<br>There are two different part. One of is learning board, and the other is quiz game."+
          "<br>For learning board, you are able to click the colorful buttons which show up and see what the next number is."+
          "<br>For quiz game board, you will find different ways to review and quiz yourself."+
          "<br>HAVE FUN!!";
        JOptionPane op = new NarrowOptionPane();
        op.setMessage(msg);
        op.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog d = op.createDialog(null, "Width 150");
        d.setVisible(true);
      }
    });
    
    
    box.add(Box.createVerticalStrut(80));
    
    exit.setPreferredSize(new Dimension(300,300));
    exit.setForeground(Color.WHITE);
    exit.setBackground(Color.black);
    box.add(exit);
    exit.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent e){
        System.exit(0);
      }
    });
    
    
    add(box,gbc);
  }
  
  /*
   * inner class for message box
   * 
   * @extends JOptionPane
   */
  class NarrowOptionPane extends JOptionPane{
    NarrowOptionPane(){}
    public int getMaxCharactersPerLineCount(){
      return 150;
    }
  }
  
  /*
   * set up the start page
   */
  public static void main (String [] args) throws IOException{
    StartPage s = new StartPage("Number Game");
    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    s.setLocationRelativeTo(null);
    
    try{
      s.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("game.png")))));
    }catch(IOException e){
      e.printStackTrace();
    }
    
    s.init();
    s.pack();
    s.setVisible(true);
  }
  
}
