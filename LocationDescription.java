/*
 * File: LocationDescription.java
 */

import java.awt.*;
/*
 * A class to decription the location
 * 
 * @author Yidan Zhang
 * @version Dec. 1, 2016
 */
public class LocationDescription{
  // the location name
  private String location;
  // the number
  private int showingNum;
  //color
  private String color;
  //the number it could be from
  private int fromNum;
  //the number it could be go
  private int goNum;
  //the frequency it shows up
  private double rating;
  //check if it is visited
  public boolean isVisited = false;
  
  /*
   * Creates a new location description with the specified location name and showing number
   * 
   * @param location the lable of location
   * @param showingNum the number showing at this location
   */
  public LocationDescription(String location, int showingNum){
    this.location = location;
    this.showingNum = showingNum;
  }
  
  /*
   * Creates a new location description with all instance
   * 
   * @param location the lable of location
   * @param showingNum the number showing at this location
   * @param color the color it showing
   * @param fromNum the number it could be from
   * @param goNum the number it could be go
   * @param rating the frequency it shows up
   * 
   */
  public LocationDescription(String location, int showingNum, String color, int fromNum,
                             int goNum, double rating){
    this.location = location;
    this.showingNum = showingNum;
    this.color = color;
    this.fromNum = fromNum;
    this.goNum = goNum;
    this.rating = rating;
  }
  /*
   * get the location label
   */
  public String getLocation(){
   if(location==null)
      {
           throw new NullPointerException("return value is null at method AAA");
      }
    return this.location;
  }
  /*
   * get the showing number
   */
  public int getShowingNum(){
    return this.showingNum;
  }
  /*
   * get the color showing
   */
  public String getColor(){
    return this.color;
  }
  /*
   * get the number it is from
   */
  public int getFromNum(){
    return this.fromNum;
  }
  /*
   * get the number it is going
   */
  public int getGoNum(){
    return this.goNum;
  }
  /*
   * get the frequency of this num
   */
  public double getRating(){
    return this.rating;
  }
  
  /*
   * set the color showing
   * 
   * @param color the new showing color
   * @return new color
   */
  public String setColor (String color){
    this.color = color;
    return this.color;
  }
  
  /*
   * set number it showing
   * 
   * @param showingNum the new number it showing
   * @return new showing number
   */
  public int setShowingNum(int showingNum){
    this.showingNum = showingNum;
    return this.showingNum;
  }
  /*
   * set number it is from
   * 
   * @param fromNum the number it is from
   * @return new from num
   */
  public int setFromNum(int fromNum){
    this.fromNum = fromNum;
    return this.fromNum;
  }
  /*
   * set number it is going
   * 
   * @param goNum the num it is going
   * @return new going number
   */
  public int setGoNum(int goNum){
    this.goNum = goNum;
    return this.goNum;
  }
  /*
   * set the frequency it showing
   * 
   * @param rating the frequency it showing
   * @return frequency
   */
  public double setRating(double rating){
    this.rating = rating;
    return this.rating;
  }
  /*
   * show the status of visited
   * 
   * @return true if it is visted
   */
  public boolean isVisited(){
    return this.isVisited;
  }
  
  /*
   * mark location isVisited
   * 
   * @return true if it is marked
   */
  public boolean mark(){
    this.isVisited = true;
    return true;
  }
  
  /*
   * unmark location
   * 
   * return true if it is unmarked
   */
  public boolean unmark(){
    this.isVisited = false;
    return true;
  }
  /*
   * print description
   */
  public String toString(){
    return (getLocation()+"\n"+getShowingNum()+"\n"+getColor()+"\n"+getFromNum()+"\n"+getGoNum()+"\n"+getRating()+"\n_");
  }
}