/**
* Project 5 Part 3 CPSC 1210 Intro to Computer Science.
* @author Jonathan Elder 
* @date 6/25/2022
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for creating and managing an EllipsoidList from a file.
 * Prompts the user for a file name and generates an EllipsoidList based on the file's contents.
 */
public class EllipsoidListApp {
   /**
    * Main method serving as the entry point for the application.
    * Reads an input file to create and display an EllipsoidList.
    * @param args Command line arguments (not used).
    * @throws FileNotFoundException if the file cannot be found.
    */
   public static void main(String[] args) throws FileNotFoundException {
      // Create a scanner object to read the filename
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter file name: ");
      String filename = keyboard.nextLine();
      keyboard.close();
      System.out.println();
      
      // Create an ArrayList to store Ellipsoid objects
      ArrayList<Ellipsoid> elist = new ArrayList<>();
      
      // Use scanner to read the file
      Scanner infile = new Scanner(new File(filename));
      
      // Store the name in a local variable
      String name = infile.nextLine();
      
      // Loop through the file to read each Ellipsoid's details
      while (infile.hasNext()) {
         String ename = infile.nextLine();
         double a = infile.nextDouble();
         double b = infile.nextDouble();
         double c = infile.nextDouble();
         if (infile.hasNext()) {
            infile.nextLine();
         }
         // Add the new Ellipsoid object to the list
         elist.add(new Ellipsoid(ename, a, b, c));
      }
      infile.close();
      
      // Create an EllipsoidList object and display its contents
      EllipsoidList app = new EllipsoidList(name, elist);
      System.out.println(app.toString());
      System.out.println();
      System.out.println(app.summaryInfo());
   }
}