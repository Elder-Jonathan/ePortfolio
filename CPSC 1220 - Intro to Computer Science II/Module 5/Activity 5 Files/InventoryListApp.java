/**
* Intro to Computer Science II - Activity 5
* @author Jonathan Elder
* @date   9/20/2022
* This driver class initializes an inventory list, adds items, and prints the inventory details.
*/
public class InventoryListApp {

    /**
    * The main method serves as the driver program.
    * @param args Driver class that initializes an inventory list, adds items, and prints the inventory details.
    */
    public static void main(String[] args) {
    
        // Create an ItemsList object to store inventory items.
        ItemsList myItems = new ItemsList();
        
        // Tax rate is set to 5 percent for all InventoryItems.
        InventoryItem.setTaxRate(0.05);

        // Adds InventoryItem objects of different types to the inventory array.
        myItems.addItem(new ElectronicsItem("Laptop", 1234.56, 10));
        myItems.addItem(new InventoryItem("Motor oil", 9.8));
        myItems.addItem(new OnlineBook("All Things Java", 12.3));
        myItems.addItem(new OnlineArticle("Useful Acronyms", 3.4));

        // Print the entire inventory array using the overridden toString method.
        System.out.println(myItems);

        // Adds an electronics surcharge of 2.0 to each item and displays the surcharge total.
        System.out.println("Total: " + myItems.calculateTotal(2.0));
    }
}