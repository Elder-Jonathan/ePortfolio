import java.text.DecimalFormat;
/**
* Intro to Computer Science 1220 II - Project 4
* @author Jonathan Elder
* @date   9/15/2022
* Derived from the CloudStorage class, creating another object type for CloudStorage >> DedicatedCloud.
*/
public class DedicatedCloud extends CloudStorage {
   
   // Instance variables.
   private double serverCost;

/**
* Constructor that takes in name, storage cost, and server cost.
* @param nameIn for name of the item
* @param baseStorageCostIn for base cost
* @param serverCostIn for server cost
*/
   public DedicatedCloud(String nameIn, double baseStorageCostIn,
      double serverCostIn) {
      super(nameIn, baseStorageCostIn);
      serverCost = serverCostIn;
   }

/**
* Getter for the server cost for a DedicatedCloud service.
* @return serverCost server cost of the dedicated cloud object
*/
   public double getServerCost() {
      return serverCost;
   }
   
/**
* Setter for the server cost for a DedicatedCloud service.
* @param serverCostIn for serverCost
*/
   public void setServerCost(double serverCostIn) {
      serverCost = serverCostIn;
   }
   
/**
* Calculates monthly cost of the DedicatedCloud object.
* @return The total cost monthly of the DedicatedCloud service.
*/
   public double monthlyCost() {
      double total = baseStorageCost + serverCost;
      return total;
   }
   
/**
* String representation of a DedicatedCloud object.
* @return The formatted tostring output of a DedicatedCloud object.
*/
   public String toString() {
      DecimalFormat costFormat = new DecimalFormat("$#,##0.00");
      String output = super.toString();
      output += "\nServer Cost: " + costFormat.format(serverCost);
      return output;
   }
}