/**
* Project 4 Part 2 - CPSC 1210 Intro to Computer Science.
* Author: Jonathan Elder
* Version: 6/18/2022
*/

import java.util.Scanner;

public class EllipsoidApp {

   /**
   * Driver program that includes main method to run the EllipsoidApp program.
   * Prompts the user to enter a label and valid values for axes a, b, and c.
   * Then calculates the volume and surface area of the Ellipsoid.
   * and prints the toString representation of the Ellipsoid object.
   */
   public static void main(String[] args) {
   
      // Create an Ellipsoid object with default values.
      Ellipsoid ellipsoidObject = new Ellipsoid("", 0, 0, 0);
      Scanner input = new Scanner(System.in);

      // Prompt the user to enter the label and axes values for the Ellipsoid.
      System.out.println("Enter label and axes a, b, c for an ellipsoid.");
      
      // Prompt and read the label.
      System.out.print("\tlabel: ");
      String label = input.nextLine();
      
      // Prompt and read the value for axis a.
      System.out.print("\ta: ");
      double a = Double.parseDouble(input.next());
      if (!ellipsoidObject.setA(a)) {
         System.out.println("Error: axis value must be positive.");
      }
      
      // Prompt and read the value for axis b.
      System.out.print("\tb: ");
      double b = Double.parseDouble(input.next());
      if (!ellipsoidObject.setB(b)) {
         System.out.println("Error: axis value must be positive.");
      }
      
      // Prompt and read the value for axis c.
      System.out.print("\tc: ");
      double c = Double.parseDouble(input.next());
      if (!ellipsoidObject.setC(c)) {
         System.out.println("Error: axis value must be positive.");
      }
      
      // Update the Ellipsoid object with the entered values.
      ellipsoidObject = new Ellipsoid(label, a, b, c);

      // Display the Ellipsoid object, which shows its dimensions, volume, and surface area.
      System.out.println("\n" + myObj);
   }
}