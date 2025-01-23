/**
* Intro to Computer Science II - Activity 5
* @author Jonathan Elder
* @date   9/20/2022
* This class manages an array of InventoryItems.
*/
public class ItemsList {
   
    // Instance variables including the array type initialization.
    private InventoryItem[] inventory;
    private int count;

    /**
    * Constructs an array for InventoryItem types, with a maximum capacity of 20 items.
    * Item count is initialized with 0 items.
    */
    public ItemsList() {
        inventory = new InventoryItem[20];
        count = 0;
    }

    /**
    * Adds an item to the inventory array.
    * The item is placed at the current count position, and the count is incremented.
    * @param itemIn The InventoryItem to be added to the array.
    */
    public void addItem(InventoryItem itemIn) {
        if (count < inventory.length) {
            inventory[count] = itemIn;
            count++;
        } else {
            System.out.println("Inventory is full. Cannot add more items.");
        }
    }

    /**
    * Calculates the total cost of all items in the inventory.
    * Electronics items include an additional surcharge.
    * @param electronicsSurcharge The surcharge applied to electronics items.
    * @return The total cost of all items, including the electronics surcharge.
    */
    public double calculateTotal(double electronicsSurcharge) {
        double total = 0;
        for (int i = 0; i < count; i++) {
            if (inventory[i] instanceof ElectronicsItem) {
                total += inventory[i].calculateCost() + electronicsSurcharge;
            } else {
                total += inventory[i].calculateCost();
            }
        }
        return total;
    }

    /**
    * Provides a string representation of all items in the InventoryItem array.
    * @return A formatted string containing all inventory items.
    */
    @Override // To override the toString to iterate through inventory array and appends each item's string representation.
    public String toString() {
        StringBuilder output = new StringBuilder("All inventory:\n\n");
        for (int i = 0; i < count; i++) {
            output.append(inventory[i]).append("\n");
        }
        return output.toString();
    }
}