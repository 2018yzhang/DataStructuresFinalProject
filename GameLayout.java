/*
 * File: GameLayout.java
 */
import java.util.*;
import java.io.*;
import javax.swing.*;
import zystructures.*;

/**
 * Authors: Yidan Zhang
 * Version: 12/1/16
 * Description: location collection for number object
 */
public class GameLayout{
  //associates a given location label with a set of connected locations
  private HashMap<String, Set<String>> connections;
  //associates a given location with its description object
  private HashMap<String, LocationDescription> descriptions;
  
  /**
   * This constructor will read in all connections and information for numbers and
   * insert the location labels associated with this number entry into the
   * descriptions. 
   * 
   * @param infoName the path to the file containing the location information
   * @param conName the path to the file containing the connections between locations
   * @throws FILL IN APPROPRIATE EXCEPTION INFORMATION.
   */
  public GameLayout(String infoName, String conName) throws IOException{
    descriptions = new HashMap<String, LocationDescription>();
    Scanner sc = new Scanner(new File(infoName));
    while(sc.hasNext()){
      String location = sc.nextLine();
      int showingNum = Integer.parseInt(sc.nextLine());
      String color = sc.nextLine();
      int fromNum = Integer.parseInt(sc.nextLine());
      int goNum = Integer.parseInt(sc.nextLine());
      double rating =Double.parseDouble(sc.nextLine());
      LocationDescription lod = new LocationDescription(location, showingNum,color, fromNum, goNum, rating);
      addInfo(lod);
    }
    sc.close();
    
    connections = new HashMap<String, Set<String>>();
    Scanner in = new Scanner (new File(conName));
    while(in.hasNext()){
      Set<String> s = new HashSet<String>();
      String label = in.nextLine();
      String conNum = in.nextLine();
      String line = in.nextLine();
      while(!line.equals("_")){
        s.add(line);
        line=in.nextLine();
      }
      connections.put(label,s);
    }
    in.close();
  }
  
  /*
   * Iterator for connections
   * 
   * @return interator of the key of connections
   */
  public Iterator<String> iteratorCon(String location){
    HashSet<String> s = (HashSet<String>)connections.get(location);
    if(s==null)
    {
      
      return new ArrayList().iterator();
    }
    return s.iterator();
  }
  
  /*
   * Iterator for descriptions
   * 
   * @return interator of the key of location label
   */
  public Iterator<String> iteratorLoc(){
    return descriptions.keySet().iterator();
  }
  
  /*
   * get description of the location for a given location name
   * 
   * @param location the name of location
   * @return the whole description of the location
   */
  public LocationDescription getDescription(String location){
    return descriptions.get(location);
  }
  
  /*
   * add a new location to descriptions
   * 
   * @ param lod the desciption of the location
   * @ return true if add sucessefully
   */
  public boolean addInfo(LocationDescription lod){
    if(descriptions.containsKey(lod.getLocation())){
      return false;
    }
    else{
      descriptions.put(lod.getLocation(),lod);
    }
    return true;
  }
  
  /*
   * remove a location for given location name
   * 
   * @param location the name of removed location
   */
  public void remove(String location){
    descriptions.remove(location);
  }
  
  /*
   * update the description of the location for given location name
   * 
   * @param location the name of the location needed to be changed
   * @param des new description of the book 
   */
  public void update(String location){
    if(descriptions.get(location).getRating()==0.0)
      JOptionPane.showMessageDialog(null,"It cannot be updated anymore.");
    int showingNum = descriptions.get(location).getShowingNum();
    int fromNum = showingNum;
    showingNum = showingNum*2;
    int goNum = showingNum*2;
    double rating = descriptions.get(location).getRating()-0.1;
    String color;
    if(fromNum==2){
      color = "yellow";
    }
    else if(fromNum==4){
      color="light red";
    }
    else if(fromNum==8){
      color = "red";
    }
    else{
      color ="purple";
    }
    descriptions.get(location).setShowingNum(showingNum);
    descriptions.get(location).setFromNum(fromNum);
    descriptions.get(location).setGoNum(goNum);
    descriptions.get(location).setColor(color);
    descriptions.get(location).setRating(rating);
  }
  /*
   * write to the file whose name is given
   * 
   * @param name of the file to write
   */ 
  public void writeToObjectFile(String name) throws IOException{
    File file = new File(name);
    PrintWriter pw = new PrintWriter(file);
    Iterator<String> iter = this.iteratorLoc();
    String location;
    while(iter.hasNext()){
      location = iter.next();
      pw.println(descriptions.get(location).toString());
    }
    pw.close();
  }
  
  /*
   * search the location of the property showing first time
   * 
   * @param location current location
   * @param showingNum the number want to find
   */
  public LocationDescription breadthFirstSearch(LocationDescription location, int showingNum){
    Queue<LocationDescription> q = new LinkedList<LocationDescription>();
    q.offer(location);
    location.mark();
    while(!q.isEmpty()){
      LocationDescription current = q.remove();
      int showing = current.getShowingNum();
      if(showing==showingNum){
        for(LocationDescription n: q){
         n.isVisited=false;
        }
        return current;
      }
      else{
        Iterator<String> iter = iteratorCon(current.getLocation());
        while(iter.hasNext()){
          String neightbor = iter.next();
          LocationDescription nei = getDescription(neightbor);
          if(!nei.isVisited()){
            nei.mark();
            q.offer(nei);
          }
        }
      }
    }
    for(LocationDescription d: q){
      d.isVisited=false;
    }
    return null;
  } 
}