/**
* Project 5 Part 2 CPSC 1210 Intro to Computer Science.
* @author Jonathan Elder 
* @date 6/25/2022
*/

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents a collection of Ellipsoid objects.
 * Provides methods to calculate cumulative statistics like total volume and surface area.
 */
public class EllipsoidList {
   
   private String list; // Name of the list of ellipsoids
   private ArrayList<Ellipsoid> ellipsoidList;

   /**
    * Constructor that creates an EllipsoidList.
    * @param listIn The name of the list.
    * @param ellipsoidListIn ArrayList of Ellipsoid objects.
    */
   public EllipsoidList(String listIn, ArrayList<Ellipsoid> ellipsoidListIn) {
      list = listIn;
      ellipsoidList = new ArrayList<>(ellipsoidListIn); // Initialize with the provided list
   }

   /**
    * Gets the name of the list.
    * @return The name of the list.
    */
   public String getName() {
      return list;
   }

   /**
    * Gets the number of Ellipsoid objects in the list.
    * @return The size of the ellipsoid list.
    */
   public int numberOfEllipsoids() {               
      return ellipsoidList.size();
   }

   /**
    * Calculates the total volume of all Ellipsoids in the list.
    * @return The cumulative volume of all ellipsoids.
    */
   public double totalVolume() {
      double total = 0;
      for (Ellipsoid e : ellipsoidList) {
         if (e != null) {
            total += e.volume();
         }
      }
      return total;
   }

   /**
    * Calculates the total surface area of all Ellipsoids in the list.
    * @return The cumulative surface area of all ellipsoids.
    */
   public double totalSurfaceArea() {
      double total = 0;
      for (Ellipsoid e : ellipsoidList) {
         if (e != null) {
            total += e.surfaceArea();
         }
      }
      return total;
   }

   /**
    * Calculates the average volume of the Ellipsoids in the list.
    * @return The average volume, or 0 if the list is empty.
    */
   public double averageVolume() {
      return (numberOfEllipsoids() == 0) ? 0 : totalVolume() / numberOfEllipsoids();
   }

   /**
    * Calculates the average surface area of the Ellipsoids in the list.
    * @return The average surface area, or 0 if the list is empty.
    */
   public double averageSurfaceArea() {
      return (numberOfEllipsoids() == 0) ? 0 : totalSurfaceArea() / numberOfEllipsoids();
   }

   /**
    * Provides a detailed representation of each Ellipsoid in the list.
    * @return A formatted string containing information about each Ellipsoid.
    */
   @Override
   public String toString() {
      StringBuilder result = new StringBuilder(getName() + "\n");
      for (Ellipsoid e : ellipsoidList) {
         result.append("\n").append(e).append("\n"); // Utilizes the Ellipsoid class's toString method
      }
      return result.toString();
   }

   /**
    * Provides a summary of the Ellipsoid list.
    * @return A string summarizing the list, including number of ellipsoids, total volume, total surface area, and averages.
    */
   public String summaryInfo() {
      DecimalFormat decFt = new DecimalFormat("#,##0.###");
      return "---- Summary for " + getName() + " ----" +
             "\nNumber of Ellipsoid Objects: " + numberOfEllipsoids() +
             "\nTotal Volume: " + decFt.format(totalVolume()) + " cubic units" +
             "\nTotal Surface Area: " + decFt.format(totalSurfaceArea()) + " square units" +
             "\nAverage Volume: " + decFt.format(averageVolume()) + " cubic units" +
             "\nAverage Surface Area: " + decFt.format(averageSurfaceArea()) + " square units";
   }

   /**
    * Gets the ArrayList of Ellipsoid objects.
    * @return The ArrayList of Ellipsoids.
    */
   public ArrayList<Ellipsoid> getList() {
      return ellipsoidList;
   }
}