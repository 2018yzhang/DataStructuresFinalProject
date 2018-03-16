/*
 * File: GameLayout.java
 */
import zhstructures.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/*
 * A JUnit test case class for the GameLayout.class
 * 
 * @author Yidan Zhang
 * @version Dec. 1, 2016
 */
public class GameLayoutDriver{
  //the new gameLayout
  private GameLayout game = null;
  //current location
  private LocationDescription currentLoc = null;
  //create new gamelayout and defalut the value
  public GameLayoutDriver() throws IOException{
    JOptionPane.showMessageDialog(null,"Welcome to Number Game");
    String infoName = "Properties.game";
    String conName = "connection.game";
    game = new GameLayout(infoName, conName);
    currentLoc = game.getDescription("S");
    game.writeToObjectFile("newGame.game");
  }
  /*
   * list the names of all possible locations
   */
  public void locationNames(){
    Iterator<String> locations = game.iteratorLoc();
    System.out.println("The names of all locations:");
    while(locations.hasNext()){
      System.out.println(locations.next());
    }
    System.out.println();
    System.out.println();
    System.out.println("*********************************************");
  }
  
  /*
   * list the properties at the current location
   * 
   */
  public void locationInfo(String currentLoc){
    System.out.println("The properties of current location:");
    System.out.println(game.getDescription(currentLoc));
    System.out.println();
    System.out.println();
    System.out.println("*********************************************");
  }
  
  /*
   * list all locations connected to the current location
   * @param currentLoc the current location
   */
  public void conLocation(String currentLoc){
    System.out.println("The connections of current locations:");
    Iterator<String> connections = game.iteratorCon(currentLoc);
    while(connections.hasNext()){
      System.out.println(connections.next());
    }
    System.out.println();
    System.out.println();
    System.out.println("*********************************************");
  }
  /*
   * move to one of the adjacent locations through a connection
   * 
   */
  public void moveNextLoc(String currentL){
    System.out.println("The locations you can move to for all possible:");
    Iterator<String> locations = game.iteratorLoc();
    while(locations.hasNext()){
      String current = locations.next();
      System.out.println("The location name: "+current);
      Iterator<String> connections = game.iteratorCon(current);
      while(connections.hasNext()){
        System.out.println("The locations you can move:");
        System.out.println(connections.next());
      }
    }
    System.out.println();
    System.out.println("*********************************************");
    String s = JOptionPane.showInputDialog("Your current position is at "+currentL+" and which location you want to move?");
    char next = s.charAt(0);
    String newLoc=String.valueOf(next);
    this.currentLoc = game.getDescription(newLoc);
    System.out.println("Your current position: ");
    locationInfo(this.currentLoc.getLocation());
    
  }
  /*
   * check search method
   * 
   * @param currentLoc
   */
  public void search(String currentL){
    System.out.println();
    String s = JOptionPane.showInputDialog("What showing number you want to search? 2,4,8,16");
    int showingNum=Integer.parseInt(s);
    LocationDescription loc = game.breadthFirstSearch(game.getDescription(currentL),showingNum);
    System.out.println("The first time this number showing is at:");
    System.out.println(loc.toString());
     String st = JOptionPane.showInputDialog("What showing number you want to search? 2,4,8,16");
    int showingNums=Integer.parseInt(st);
    LocationDescription locs = game.breadthFirstSearch(game.getDescription(currentL),showingNums);
    System.out.println("The first time this number showing is at:");
    System.out.println(locs.toString());
  }
  /*
   * run the whole driver
   */
  public void runDriver(){
    locationNames();
    locationInfo(currentLoc.getLocation());
    conLocation(currentLoc.getLocation());
    moveNextLoc(currentLoc.getLocation());
    //System.out.println(currentLoc.toString());
    search(currentLoc.getLocation());
    
  }
  /*
   * main method of the class
   */
  public static void main(String args[])throws IOException{
    GameLayoutDriver gd = new GameLayoutDriver();
    gd.runDriver();
  }
  
}