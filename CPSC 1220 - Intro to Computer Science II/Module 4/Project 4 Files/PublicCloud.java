/**
* Derived from the SharedCloud class and creates a public cloud storage object.
* Jonathan Elder
* 9/15/2022
* Intro to Computer Science 1220 - Project 4
*/
public class PublicCloud extends SharedCloud {

/**
* Constant for cost factor of PublicCloud class.
*/
   public static final double COST_FACTOR = 2.0;
   
/**
* Constructor that takes in name, base cost, data input, and limit of data.
* @param nameIn for name of item
* @param baseStorageCostIn for base storage cost
* @param dataStoredIn for data stored
* @param dataLimitIn for data limit that can be stored
*/
   public PublicCloud(String nameIn, double baseStorageCostIn,
      double dataStoredIn, double dataLimitIn) {
   
      super(nameIn, baseStorageCostIn, dataStoredIn, dataLimitIn);
   }

/**
* Retrieves the cost factor of the public cloud object.
* @return PublicCloud.COST_FACTOR the cost factor of cloud storage
*/
   public double getCostFactor() {
      return PublicCloud.COST_FACTOR; }

/**
* Calculates the monthly cost to maintain the public cloud object.
* @return cost monthly cost of cloud storage
*/
   public double monthlyCost() {
      return baseStorageCost + dataOverage() * PublicCloud.COST_FACTOR;
   }
}