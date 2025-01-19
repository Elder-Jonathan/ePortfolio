/**
  * Jonathan Elder.
  * Intro to Computer Science 1220 II - Activity 4
  * Date 9/12/2022
  */
public class InventoryApp {

    /**
    * Driver App. Contains main method for the Inventory App.
    * Shows different objects being produced using the base "InventoryItem" class.
    * The tax rate is set, and four different items are added to the inventory.
    * @param args Tax rate is set, and four different items are added as InventoryItems.
    */
    public static void main(String[] args) {

        // Sets the tax rate of all Inventory Items to 5 percent.
        InventoryItem.setTaxRate(0.05);

        // Creates a InventoryItem object.
        InventoryItem item1 = new InventoryItem("Oil Change Kit", 39.00);

        // Creates a ElectronicsItem object with inheritence from InventoryItem
        InventoryItem item2 = new ElectronicsItem("Cordless Phone", 80.00, 1.8);

        // Creates a OnlineArticle object with inheritence from OnlineTextItem > InventoryItem.
        OnlineArticle item3 = new OnlineArticle("Java News", 8.50);
        item3.setWordCount(700);

        // Creates a OnlineBook object with inheritence from OnlineTextItem > InventoryItem.
        OnlineBook item4 = new OnlineBook("Java for Noobs", 13.37);
        item4.setAuthor("L. G. Jones");

        // Simply using the toString of each object type, print items 1-4.
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println(item4);
    }
}
