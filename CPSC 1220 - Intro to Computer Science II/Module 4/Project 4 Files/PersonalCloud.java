/**
* Derived from SharedCloud and creates a personal cloud storage object.
* Jonathan Elder
* 9/16/2022
* Intro to Computer Science 1220 - Project 4
*/
public class PersonalCloud extends SharedCloud {

/**
* Constant for cost factor.
*/
   public static final double COST_FACTOR = 3.0;
   
/**
* Constructor that takes in name, base cost, data stored, and data limits.
* @param nameIn for name of item
* @param baseStorageCostIn for base cost of personal cloud storage object
* @param dataStoredIn for data stored of personal cloud storage object
* @param dataLimitIn for data limit of personal cloud storage object
*/
   public PersonalCloud(String nameIn, double baseStorageCostIn,
      double dataStoredIn, double dataLimitIn) {
   
      super(nameIn, baseStorageCostIn, dataStoredIn, dataLimitIn);
   }

/**
* Retrives the cost factor of personal cloud storage object.
* @return PublicCloud.COST_FACTOR cost factor of cloud storage object
*/
   public double getCostFactor() {
      return PersonalCloud.COST_FACTOR;
   }

/**
* Calculates the monthly cost of the personal cloud object.
* @return cost monthly cost of the personal cloud object
*/
   public double monthlyCost() {
      return baseStorageCost + dataOverage() * PersonalCloud.COST_FACTOR;
   }
}