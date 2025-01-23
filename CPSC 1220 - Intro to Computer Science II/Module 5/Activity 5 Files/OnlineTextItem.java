/**
* Jonathan Elder.
* Intro to Computer Science 1220 - Activity 4
* 9/12/2022
*/
public abstract class OnlineTextItem extends InventoryItem {

   /**
   * Constructor to initialize an OnlineTextItem object.
   * Since this class extends InventoryItem, it sets up an inventory item.
   * @param name The name of the "OnlineTextItem" object in inventory.
   * @param price The price of the "OnlineTextItem" object in inventory.
   */
   public OnlineTextItem(String name, double price) {
      super(name, price);
   }

    /**
    * Overrides the calculateCost method from InventoryItem 
    * to simply return the price without any tax calculations.
    * @return The base price of the Inventory object.
    */
   @Override
    public double calculateCost() {
      return price;
   }
}