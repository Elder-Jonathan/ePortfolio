import java.text.DecimalFormat;

/**
* Intro to Computer Science 1220 II - Project 4
* @author Jonathan Elder
* @date   9/15/2022
* Derived from CloudStorage class. Creates another object type for CloudStorage >> SharedCloud.
*/
public class SharedCloud extends CloudStorage {
   
   // Constant.
   public static final double COST_FACTOR = 1.0;
   
   // Other instance variables.
   protected double dataStored;
   protected double dataLimit;

/**
* Constructor for a SharedCloud object. Takes in values for: name, cost, data stored, and limit.
* @param nameIn The user defined name of SharedCloud object.
* @param baseStorageCostIn The user defined base storage cost of the SharedCloud object.
* @param dataStoredIn The user defined already needed amount of data stored.
* @param dataLimitIn The user defined SharedCloud object's data limit.
*/
   public SharedCloud(String nameIn, double baseStorageCostIn,
      double dataStoredIn, double dataLimitIn) {
      super(nameIn, baseStorageCostIn);
      dataStored = dataStoredIn;
      dataLimit = dataLimitIn;
   }

/**
* Getter for the data stored in the SharedCloud object.
* @return The amount of data already stored in the SharedCloud object.
*/
   public double getDataStored() {
      return dataStored;
   }
   
/**
* Setter for the amount of data stored in the SharedCloud object.
* @param dataStoredIn The current amount of data to set in the SharedCloud object.
*/
   public void setDataStored(double dataStoredIn) {
      dataStored = dataStoredIn;
   }
   
/**
* Getter for the data limit variable of a SharedCloud object.
* @return The data limit of the SharedCloud object.
*/
   public double getDataLimit() {
      return dataLimit;
   }
   
/**
* Setter for the data limit of a SharedCloud object.
* @param dataLimitIn The data limit set for the SharedCloud storage object.
*/
   public void setDataLimit(double dataLimitIn) {
      dataLimit = dataLimitIn;
   }
   
/**
* Getter for the cost factor constant for a given SharedCloud object.
* @return The cost factor constant variable defined for the SharedCloud object.
*/
   public double getCostFactor() {
      return SharedCloud.COST_FACTOR; }

/**
* Method to calculate the data overage of a SharedCloud object.
* @return over, remaining data available, if the overage is greater than 0.
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
* Method to calculate the monthly cost of a SharedCloud object.
* @return The monthly cost of the SharedCloud object.
*/
   public double monthlyCost() {
      double cost = baseStorageCost + dataOverage() * SharedCloud.COST_FACTOR;
      return cost;
   }
   
/**
* String representation of a SharedCloud object.
* @return The formatted version of the SharedCloud storage object.
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