import java.text.DecimalFormat;

/**
* Jonathan Elder
* 9/14/2022
* Intro to Computer Science 1220 - Project 4
* Creates an abstract cloud storage object to be used for other sub classes.
*/
public abstract class CloudStorage {

   protected String name;
   protected double baseStorageCost;
   protected static int count = 0;

/**
* CloudStorage constructor that takes in values for name and base storage cost.
* @param nameIn for name of cloud storage.
* @param baseStorageCostIn for price of service.
*/
   public CloudStorage(String nameIn, double baseStorageCostIn) {
      name = nameIn;
      baseStorageCost = baseStorageCostIn;
      count++;
   }

//methods
/**
* Gets cloud storage name.
* @return name of cloud storage
*/
   public String getName() {
      return name;
   }

/**
* Sets cloud storage name.
* @param nameIn for name of cloud storage
*/
   public void setName(String nameIn) {
      name = nameIn;
   }

/**
* Returns the cloud storage base cost.
* @return baseStorageCost cost of base cloud storage
*/
   public double getBaseStorageCost() {
      return baseStorageCost;
   }

/**
* Sets the cloud storage base cost.
* @param baseStorageCostIn cost of cloud storage
*/

   public void setBaseStorageCost(double baseStorageCostIn) {
      baseStorageCost = baseStorageCostIn;
   }

/**
* Retrieves number of total cloud storage objects.
* @return count total number of cloud storage objects
*/
   public static int getCount() {
      return count;
   }

/**
* Resets the number of cloud storage objects to zero.
*/
   public static void resetCount() {
      count = 0;
   }

/**
* Outputs a string containing the cloud storage data.
* @return output of cloud storage data
*/
   public String toString() {
      DecimalFormat costFormat = new DecimalFormat("$#,##0.00");
   
      String output = name + " (" + this.getClass() + ") "
         + "Monthly Cost: "
         + costFormat.format(monthlyCost()) + "\n"
         + "Base Storage Cost: "
         + costFormat.format(getBaseStorageCost());
   
      return output;
   }
/**
* Abstract method to calculate the monthly cost of service.
* @return monthlyCost monthly cost of service
*/
   public abstract double monthlyCost();
   
/**
* Compares names of cloud storage objects.
* @param o compares object arrays
* @return o boolean value if it equals name or not
*/
   public int compareTo(CloudStorage o) {
   	
      return name.compareToIgnoreCase(o.name);
   }
}