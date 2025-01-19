import java.text.DecimalFormat;
/**
* Jonathan Elder
* 9/15/2022
* Intro to Computer Science 1220 - Project 4
* Derived from CloudStorage and creates another object type for cloud storage.
*/
public class DedicatedCloud extends CloudStorage {

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
* Gets the server cost of the dedicated server.
* @return serverCost server cost of the dedicated cloud object
*/
   public double getServerCost() {
      return serverCost;
   }
   
/**
* Sets the server cost.
* @param serverCostIn for serverCost
*/
   public void setServerCost(double serverCostIn) {
      serverCost = serverCostIn;
   }
   
/**
* Calculates monthly cost of the dedicated cloud server object.
* @return total cost of monthly dedicated server upkeep
*/
   public double monthlyCost() {
      double total = baseStorageCost + serverCost;
      return total;
   }
   
/**
* Outputs a string containing the cloud storage data of cloud object.
* @return output of cloud storage data.
*/
   public String toString() {
      DecimalFormat costFormat = new DecimalFormat("$#,##0.00");
      String output = super.toString();
      output += "\nServer Cost: " + costFormat.format(serverCost);
      return output;
   }
}