import java.text.DecimalFormat;

/**
* Derived from CloudStorage class and creates a cloud storage object.
* Jonathan Elder
* 9/15/2022
* Intro to Computer Science 1220 - Project 4
*/
public class SharedCloud extends CloudStorage {

   protected double dataStored;
   protected double dataLimit;

/**
* Constant for cost factor of SharedCloud class.
*/
   public static final double COST_FACTOR = 1.0;

/**
* Constructor that takes in values for name, cost, data stored, and limit.
* @param nameIn for name of item
* @param baseStorageCostIn for base cost
* @param dataStoredIn for data stored
* @param dataLimitIn for data limit
*/
   public SharedCloud(String nameIn, double baseStorageCostIn,
      double dataStoredIn, double dataLimitIn) {
      super(nameIn, baseStorageCostIn);
      dataStored = dataStoredIn;
      dataLimit = dataLimitIn;
   }

/**
* Retrieves the data stored in shared cloud object.
* @return dataLimit for the amount of data stored
*/
   public double getDataStored() {
      return dataStored;
   }
   
/**
* Sets the amount of data stored.
* @param dataStoredIn for amount of data to set in the shared cloud object
*/
   public void setDataStored(double dataStoredIn) {
      dataStored = dataStoredIn;
   }
   
/**
* Gets the data limit in the shared cloud object.
* @return dataLimit the limit of cloud storage.
*/
   public double getDataLimit() {
      return dataLimit;
   }
   
/**
* Sets the data limit in the shared cloud object.
* @param dataLimitIn the limit of cloud storage
*/
   public void setDataLimit(double dataLimitIn) {
      dataLimit = dataLimitIn;
   }
   
/**
* Retrieves the cost factor in the shared cloud object.
* @return sharedCloud.COST_FACTOR the cost factor of cloud storage
*/
   public double getCostFactor() {
      return SharedCloud.COST_FACTOR; }

/**
* Calculates the data overage of the shared cloud object.
* @return over if the overage is greater than 0.
*/
   public double dataOverage() {
      double over = dataStored - dataLimit;
      if (over < 0) {
         return 0.0;
      }
      else {
         return over;
      }
   }

/**
* Calculates the monthly cost to maintain the shared cloud object.
* @return cost monthly cost of cloud storage object
*/
   public double monthlyCost() {
      double cost = baseStorageCost + dataOverage() * SharedCloud.COST_FACTOR;
      return cost;
   }
   
/**
* Outputs a string containing the cloud storage data of the shared cloud object.
* @return output of cloud storage data.
*/
   public String toString() {
      DecimalFormat dataFormat = new DecimalFormat("0.000");
   
      String output = super.toString();
   
      output += "\nData Stored: " + dataFormat.format(getDataStored()) + " GB\n"
         + "Data Limit: " + dataFormat.format(getDataLimit()) + " GB\n"
         + "Overage: " + dataFormat.format(dataOverage()) + " GB\n"
         + "Cost Factor: " + getCostFactor();
      return output;
   }
}