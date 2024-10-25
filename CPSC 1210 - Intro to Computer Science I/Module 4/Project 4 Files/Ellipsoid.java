/**
 * Project 4 Part 1 - CPSC 1210 Intro to Computer Science.
 * Author: Jonathan Elder
 * Date: 6/18/2022
 */

import java.text.DecimalFormat;

/**
* This is a representation of an Ellipsoid object with 3 axes (a, b, c).
* Provides methods to calculate its volume and surface area.
*/
public class Ellipsoid {
   
   // Initialized variables representing the name and three axes of the Ellipsoid class.
   private String label = "";
   private double a = 0;
   private double b = 0;
   private double c = 0;
   
   /**
   * Contructor to create an instance of the Ellipsoid class.
   * @param labelIn Name of the Ellipsoid to create.
   * @param a axis a of the Ellipsoid being created.
   * @param b axis b of the Ellipsoid being created.
   * @param c axis c of the Ellipsoid being created.
   */
   public Ellipsoid(String labelIn, double a, double b, double c) {
      setLabel(labelIn);
      setA(a);
      setB(b);
      setC(c);
   }
   
    /**
    * Retrieves the label variable of the Ellipsoid object.
    * @return The label of the Ellipsoid object. 
    */
   public String getLabel() {
      return label;
   }

   /**
    * Sets the label for the Ellipsoid. The label variable is trimmed.
    * @param label The name entered for the Ellipsoid.
    * @return true if the label is not null, false otherwise.
    */
   public boolean setLabel(String label)
   {
      if (label != null)
      {
         this.label = label.trim();
         return true;
      }
      else {
         return false;
      }
   }
   
   /**
   * Getter for the value of the axis "a".
   * @return the value of stored for axis a.
   */
   public double getA() {
      return a;
   }
   
   /**
   * Setter for the value of the axis "a", as long as it's greater than 0.
   * @param a The value to set for axis "a".
   * @return true if the value is greater than 0, otherwise return false.
   */
   public boolean setA(double a)
   {
      if (a > 0)
      {
         this.a = a;
         return true;
      }
      else {
         return false;
      }
   }
   
   /**
   * Getter for the value of the axis "b".
   * @return the value of stored for axis b.
   */
   public double getB() {
      return b;
   }
   
   /**
   * Setter for the value of the axis "b", as long as it's greater than 0.
   * @param b The value to set for axis "b".
   * @return true if the value is greater than 0, otherwise return false.
   */
   public boolean setB(double b)
   {
      if (b > 0)
      {
         this.b = b;
         return true;
      }
      else {
         return false;
      }
   }
   
   /**
    * Getter for the value of the axis "c".
    * @return the value of stored for axis c.
    */
   public double getC() {
      return c;
   }

   /**
   * Setter for the value of the axis "c", as long as it's greater than 0.
   * @param c The value to set for axis "c".
   * @return true if the value is greater than 0, otherwise return false.
   */
   public boolean setC(double c)
   {
      if (c > 0)
      {
         this.c = c;
         return true;
      }
      else {
         return false;
      }
   }
   
   /**
   * Calculates the volume of the Ellipsoid.
   * @return The calculated volume.
   */
   public double volume()
   {
      return (4 * Math.PI * a * b * c) / 3;
   }
   
   /**
   * Calculates the surface area of the Ellipsoid.
   * @return The calculated surface area.
   */
   public double surfaceArea()
   {
      double sa = (Math.pow((a * b), 1.6) + Math.pow((a * c), 1.6) 
         + Math.pow((b * c), 1.6)) / 3;
      sa = 4 * Math.PI * Math.pow(sa, (1.0 / 1.6));
      return sa;
   }
   
   /**
   * Returns a string representation of the Ellipsoid.
   * @return A formatted string with the Ellipsoid's dimensions, volume, and surface area.
   */
   @Override
   public String toString() {
      DecimalFormat decimalFormat = new DecimalFormat("#,##0.0###");
      return "Ellipsoid \"" + label + "\" with axes a = " + getA() + ", b = " 
         + getB() + ", c = " + getC() + " units has:\n   volume = " 
         + decimalFormat.format(volume()) + " cubic units" 
         + "\n   surface area = " + decimalFormat.format(surfaceArea()) 
         + " square units";
   }
}