import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* Project 6 Part 2 CPSC 1210 Intro to Computer Science.
* @author Jonathan Elder Date: 7/4/2022
*/

import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EllipsoidList {
   private String list;
   private ArrayList<Ellipsoid> ellipsoidList;
/**
* Create a TriangleList object.
* constructor.
* @param listIn for listName
* @param ellipsoidListIn for ellipsoidList
*/
   public EllipsoidList(String listIn, ArrayList<Ellipsoid> ellipsoidListIn) {
      list = listIn;
      ellipsoidList = ellipsoidListIn;
   }
/**
* Gets the name of list.
* @return the list name
*/
   public String getName() {
      return list; ////
   }
/**
* Total number of Ellipsoid objects.
* @return number of Ellipsoids
*/
   public int numberOfEllipsoids() {
      int numberOfEllipsoids = 0;
      if (ellipsoidList.size() == 0) {
         return 0;
      }
      else {
         return ellipsoidList.size();
      }
   }
/**
* Total volume of all ellipsoids.
* @return totalVolume
*/
   public double totalVolume() {
      double total = 0;
   
      int index = 0;
      while (index < ellipsoidList.size()) {
         total += (ellipsoidList.get(index).volume());
         index++;
      }
      return total;
   }
/**
* counts the total surface area of all ellipsoids.
* @return totalSurfaceArea
*/
   public double totalSurfaceArea() {
      double total = 0;
   
      int index = 0;
      while (index < ellipsoidList.size()) {
         total += (ellipsoidList.get(index).surfaceArea());
         index++;
      }
      return total;
   }
/**
* finds average volume between all ellipsoids.
* @return averageVolume
*/
   public double averageVolume() {
      double total = 0;
   
      int index = 0;
      while (index < ellipsoidList.size()) {
         total += ellipsoidList.get(index).volume();
         index++;
      }
      if (index == 0) {
         total = 0;
      }
      else {
         total = total / index;
      }
      return total;
   }
/**
* finds average surface area between all ellipsoids.
* @return averageSurfaceArea
*/
   public double averageSurfaceArea() {
      double total = 0;
      int index = 0;
      while (index < ellipsoidList.size()) {
         total += ellipsoidList.get(index).surfaceArea();
         index++;
      }
      if (index == 0) {
         total = 0;
      }
      else {
         total = total / index;
      }
      return total;
   }
/**
* gets a summary of the ellipsoid in the list.
* @return summaryInfo
*/
   public String toString() {
      String result = getName() + "\n";
      int index = 0;
      while (index < ellipsoidList.size()) {
         result += "\n" + ellipsoidList.get(index) + "\n";
         index++;
      }
      return result;
   }
/**
* gets a summary of the ellipsoid in the list.
* @return summaryInfo
*/

   public String summaryInfo() {
      DecimalFormat decFt = new DecimalFormat("#,##0.###");
      String result = "";
      result += "\n";
      result += "---- Summary for " + getName() + "----";
      result += "\nNumber of Ellipsoid Objects: " + numberOfEllipsoids();
      result += "\nTotal Volume: " + decFt.format(totalVolume())
         + " cubic units";
      result += "\nTotal Surface Area: " + decFt.format(totalSurfaceArea())
         + " square units";
      result += "\nAverage Volume: " + decFt.format(averageVolume())
         + " cubic units";
      result += "\nAverage Surface Area: " + decFt.format(averageSurfaceArea())
         + " square units";
   
      return result;
   }
   /**
   * Gets a list of all Ellipsoid objects.
   * @return ellipsoidList
   */
   public ArrayList<Ellipsoid> getList() {
      return ellipsoidList;
   }
   /**
   * Reads in file until there is nothing left to read in.
   * @param filename filename
   * @return elist until null.
   */
   public static EllipsoidList readFile(String filename) {
      try {
         Scanner infile = new Scanner(new File(filename));
         EllipsoidList elist = new EllipsoidList(infile.nextLine(), 
            new ArrayList<Ellipsoid>());
         while (infile.hasNext()) {
            elist.addEllipsoid(infile.next(), infile.nextDouble(), 
               infile.nextDouble(), infile.nextDouble());
         }
         infile.close();
         return elist;
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }
   /**
   * Adds an Ellipsoid object.
   * @param label label
   * @param a a
   * @param b b
   * @param c c
   */
   public void addEllipsoid(String label, double a, double b, double c) {
      ellipsoidList.add(new Ellipsoid(label, a, b, c));
   }
   /**
   * Finds an Ellipsoid of certain string.
   * @param label label
   * @return e until null.
   */
   public Ellipsoid findEllipsoid(String label) {
      for (Ellipsoid e : ellipsoidList) {
         if (e.getLabel().equalsIgnoreCase(label)) {
            return e;
         }
      }
      return null;
   }
   /**
   * Deletes a certain Ellipsoid object.
   * @param label label
   * @return e
   */
   public Ellipsoid deleteEllipsoid(String label) {
      Ellipsoid e = findEllipsoid(label);
      if (e != null) {
         ellipsoidList.remove(e);
      }
      return e;
   }
   /**
   * Edit a certain Ellipsoid object.
   * @param label label
   * @param a a
   * @param b b
   * @param c c
   * @return e
   */
   public Ellipsoid editEllipsoid(String label, double a, double b, double c) {
      Ellipsoid e = findEllipsoid(label);
      if (e != null) {
         e.setA(a);
         e.setB(b);
         e.setC(c);
      
      }
      return e;
   }
}