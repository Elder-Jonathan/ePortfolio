import java.text.DecimalFormat;

/**
* Intro to Computer Science 1220 II - Project 4
* @author Jonathan Elder
* @date   9/14/2022
* Defines an abstract cloud storage class to be extended by subclasses.
*/
public abstract class CloudStorage {
    
    // Instance variables.
    protected String name;
    protected double baseStorageCost;
    protected static int count = 0;

    /**
    * Constructor that initializes a CloudStorage object with name and base storage cost.
    * @param nameIn User defined name of the cloud storage service.
    * @param baseStorageCostIn User defined cost of the cloud storage service.
    */
    public CloudStorage(String nameIn, double baseStorageCostIn) {
        name = nameIn;
        baseStorageCost = baseStorageCostIn;
        count++;
    }

    // Methods

    /**
    * Getter for the name of the cloud storage service.
    * @return The name of the cloud storage object.
    */
    public String getName() {
        return name;
    }

    /**
    * Setter for the name of a given cloud storage service.
    * @param nameIn The name to be set.
    */
    public void setName(String nameIn) {
        name = nameIn;
    }

    /**
    * Getter for the "base storage cost" for a given cloud storage service.
    * @return The base storage cost for a given cloud storage service.
    */
    public double getBaseStorageCost() {
        return baseStorageCost;
    }

    /**
    * Setter for the "base storage cost" of the cloud storage service.
    * @param baseStorageCostIn The cost to be set.
    */
    public void setBaseStorageCost(double baseStorageCostIn) {
        baseStorageCost = baseStorageCostIn;
    }

    /**
    * Getter for the total created cloud storage objects.
    * @return The total count of cloud storage objects.
    */
    public static int getCount() {
        return count;
    }

    /**
    * Resets the count variable for number of cloud storage objects to zero.
    */
    public static void resetCount() {
        count = 0;
    }

    /**
    * Provides a string representation for a given cloud storage service.
    * Includes the name, class type, monthly cost, and base storage cost.
    * @return A formatted string containing the cloud storage details.
    */
    @Override
    public String toString() {
        DecimalFormat costFormat = new DecimalFormat("$#,##0.00");
        return name + " (" + this.getClass().getSimpleName() + ") "
            + "Monthly Cost: " + costFormat.format(monthlyCost()) + "\n"
            + "Base Storage Cost: " + costFormat.format(getBaseStorageCost());
    }

    /**
    * Abstract method to calculate the monthly cost of the cloud storage service.
    * Subclasses are to provide their own implementation.
    * @return A subclasses' calculated monthly cost of a certain service.
    */
    public abstract double monthlyCost();

    /**
    * Compares cloud storage objects lexicographically.
    * @param o The cloud storage object to compare with.
    * @return A negative if object's name is less than other, zero if names are equal, and a positive value if its greater.
    */
    public int compareTo(CloudStorage o) {
        return name.compareToIgnoreCase(o.name);
    }
}