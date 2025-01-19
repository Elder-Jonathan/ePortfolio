/**
* Jonathan Elder.
* Intro to Computer Science 1220 - Activity 4
* 9/12/2022
*/
public class ElectronicsItem extends InventoryItem {
    
    // Instance variable.
    protected double weight;

    
    // Constant representing the shipping cost per unit of weight.
    public static final double SHIPPING_COST = 1.5;

    /**
    * Constructor to initialize an Electronics Item object.
    * This is also an Inventory Item since it extends from its class.
    * @param name Name taken for the electronics item object.
    * @param price Price taken for the electronics item object.
    * @param weightIn Weight taken for the electronics item object.
    */
    public ElectronicsItem(String name, double price, double weightIn) {
        super(name, price);
        weight = weightIn;
    }

    /**
    * Calculates the total cost of the electronics item, including tax and shipping.
    * Overrides the calculateCost method from InventoryItem to include shipping costs based on weight.
    * @return The total cost including tax and shipping.
    */
    @Override
    public double calculateCost() {
        return super.calculateCost() + (SHIPPING_COST * weight);
    }
}