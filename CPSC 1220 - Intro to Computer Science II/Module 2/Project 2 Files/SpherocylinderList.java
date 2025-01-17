/**
* Intro to Computer Science 1220 Project 3
* Jonathan Elder
* Date 9/10/2022
*/
public class SpherocylinderList {

   private static final int MAX = 100; // Constant.

   // Instance variables.
   private String name;
   private int numberOfSpherocylinders;
   private Spherocylinder[] cylinders;

   /**
   * Creates an array with a blank name and initializes it to 0.
   */
   public SpherocylinderList() {
      this.name = "";
      this.numberOfSpherocylinders = 0;
      this.cylinders = new Spherocylinder[MAX];
   }

   /**
   * Creates an array and initializes it with a given name.
   * @param name Name of the array to be created.
   */
   public SpherocylinderList(String name) {
      this.name = name;
      this.numberOfSpherocylinders = 0;
      this.cylinders = new Spherocylinder[MAX];
   }

   /**
   * Retrieves the name of the array.
   * @return The name of the array.
   */
   public String getName() {
      return this.name;
   }

   /**
   * Retrieves the total number of Spherocylinder objects in the array.
   * @return Total number of objects in the array.
   */
   public int getNumberOfSpherocylinders() {
      return numberOfSpherocylinders;
   }

   /**
   * Calculates the total surface area of all Spherocylinders in the array.
   * @return The total surface area.
   */
   public double totalSurfaceArea() {
      double total = 0;
      for (int i = 0; i < this.numberOfSpherocylinders; i++) {
         total += this.cylinders[i].surfaceArea();
      }
      return total;
   }

   /**
   * Calculates the total volume of all Spherocylinders in the array.
   * @return The total volume.
   */
   public double totalVolume() {
      double total = 0;
      for (int i = 0; i < this.numberOfSpherocylinders; i++) {
         total += this.cylinders[i].volume();
      }
      return total;
   }

   /**
   * Calculates the average surface area of all Spherocylinders in the array.
   * @return The average surface area.
   */
   public double averageSurfaceArea() {
      return (totalSurfaceArea() / this.numberOfSpherocylinders);
   }

   /**
   * Calculates the average volume of all Spherocylinders in the array.
   * @return The average volume.
   */
   public double averageVolume() {
      return (totalVolume() / this.numberOfSpherocylinders);
   }

   /**
   * Provides a summary of the SpherocylinderList.
   * @return A formatted string summary.
   */
   @Override
   public String toString() {
      String output = "----- Summary for " + this.name + " -----\n"
         + "Number of Spherocylinders: " + getNumberOfSpherocylinders() + "\n"
         + "Total Surface Area: " + String.format("%,.3f", totalSurfaceArea()) + "\n"
         + "Total Volume: " + String.format("%,.3f", totalVolume()) + "\n"
         + "Average Surface Area: " + String.format("%,.3f", averageSurfaceArea()) + "\n"
         + "Average Volume: " + String.format("%,.3f", averageVolume());
      return output;
   }

   /**
   * Retrieves the array of Spherocylinder objects.
   * @return The array of Spherocylinder objects.
   */
   public Spherocylinder[] getList() {
      return this.cylinders;
   }

   /**
   * Adds a Spherocylinder to the array.
   * @param label Name of the Spherocylinder.
   * @param radius Radius of the Spherocylinder.
   * @param height Height of the Spherocylinder.
   */
   public void addSpherocylinder(String label, double radius, double height) {
      if (this.numberOfSpherocylinders >= MAX) {
         System.out.println("Cannot add more than " + MAX + " Spherocylinders, maximum number reached!");
         return;
      }
      this.cylinders[this.numberOfSpherocylinders++] = new Spherocylinder(label, radius, height);
   }

   /**
   * Finds a Spherocylinder in the array by its label.
   * @param label Name of the Spherocylinder to find.
   * @return The matching Spherocylinder or null if not found.
   */
   public Spherocylinder findSpherocylinder(String label) {
      for (int i = 0; i < this.numberOfSpherocylinders; i++) {
         if (this.cylinders[i].getLabel().equalsIgnoreCase(label)) {
            return this.cylinders[i];
         }
      }
      return null;
   }

   /**
   * Deletes a Spherocylinder from the array by its label.
   * @param label Name of the Spherocylinder to delete.
   * @return The deleted Spherocylinder or null if not found.
   */
   public Spherocylinder deleteSpherocylinder(String label) {
      for (int i = 0; i < this.numberOfSpherocylinders; i++) {
         if (this.cylinders[i].getLabel().equalsIgnoreCase(label)) {
            Spherocylinder res = this.cylinders[i];
            for (int j = i; j < this.numberOfSpherocylinders - 1; j++) {
               this.cylinders[j] = this.cylinders[j + 1];
            }
            this.cylinders[this.numberOfSpherocylinders - 1] = null;
            this.numberOfSpherocylinders--;
            return res;
         }
      }
      return null;
   }

   /**
   * Edits an existing Spherocylinder in the array.
   * @param label Name of the Spherocylinder to edit.
   * @param radius New radius of the Spherocylinder.
   * @param height New height of the Spherocylinder.
   * @return True if the Spherocylinder was edited, false otherwise.
   */
   public boolean editSpherocylinder(String label, double radius, double height) {
      for (int i = 0; i < this.numberOfSpherocylinders; i++) {
         if (this.cylinders[i].getLabel().equalsIgnoreCase(label)) {
            this.cylinders[i].setRadius(radius);
            this.cylinders[i].setCylinderHeight(height);
            return true;
         }
      }
      return false;
   }

   /**
   * Finds the Spherocylinder with the largest volume.
   * @return The Spherocylinder with the largest volume or null if the list is empty.
   */
   public Spherocylinder findSpherocylinderWithLargestVolume() {
      if (this.numberOfSpherocylinders == 0) {
         return null;
      }
      Spherocylinder res = this.cylinders[0];
      double max = res.volume();
      for (int i = 1; i < this.numberOfSpherocylinders; i++) {
         if (this.cylinders[i].volume() > max) {
            max = this.cylinders[i].volume();
            res = this.cylinders[i];
         }
      }
      return res;
   }
}