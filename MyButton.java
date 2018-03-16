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
public class MyButton extends JButton{
  //location of the button
  private String location=null;
  //the number showing of the button
  String showing=null;
  //new JButton
  JButton btn;
  /*
   * create new JButton with location and showing 
   * 
   * @param location of the button
   * @param showing
   */
  public MyButton(String location, String showing){
    super(showing);
    this.location=location;
    this.showing=showing;
  }
  /*
   * get the location of the button
   * 
   * @throw if the location is null
   * @return the location of the button
   */
  public String getLoc(){
   if(location==null)
      {
           throw new NullPointerException("return value is null at method AAA");
      }
    return this.location;
  }
  /*
   * get the showing number of the button
   * 
   * @throw if the showing num is null
   * @return the showing number of the button
   */
  public String getShowing(){
    if(showing==null)
      {
           throw new NullPointerException("return value is null at method AAA");
      }
    return this.showing;
  }
}