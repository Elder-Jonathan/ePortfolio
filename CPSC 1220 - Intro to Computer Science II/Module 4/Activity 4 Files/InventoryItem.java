/**
* Jonathan Elder
* Intro to Computer Science II 1220 - Activity 4
* Date 9/12/2022
*/
public class InventoryItem {

   // Instance variables.
   protected String name;
   protected double price;
   private static double taxRate = 0;

 /**
 * Constructor to create an Iventory Item object.
 * @param nameIn Title given to the inventory item.
 * @param priceIn Price set to the inventory item.
 */
   public InventoryItem(String nameIn, double priceIn) {
      name = nameIn;
      price = priceIn;
   }

   /**
   * Getter for the name variable of an inventory item object.
   * @return The name of the item.
   */
   public String getName() {
      return name;
   }

   /**
   * Setter for the tax rate to be applied on an inventory item.
   * @param taxRateIn The tax rate to be set.
   */
   public static void setTaxRate(double taxRateIn) {
      InventoryItem.taxRate = taxRateIn;
   }

   /**
   * Calculates the total cost of the item, including tax.
   * The cost is determined by multiplying the price by (1 + tax rate).
   * @return The total cost of the item including tax.
   */
   public double calculateCost() {
      return price * (1 + taxRate);
   }

   /**
   * Provides a string representation of the inventory item.
   * @return The item name along and its total cost with tax.
   */
   @Override
   public String toString() {
      return name + ": $" + calculateCost();
   }
}