import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Tests the DedicatedCloud class methods.
* Jonathan Elder
* 9/17/2022
*/
public class DedicatedCloudTest {

/** Fixture initialization (common initialization
* for all tests). **/
   @Before public void setUp() {
   }

/** Test the get and set name methods for CloudStorage. **/
   @Test public void getAndSetNameTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      Assert.assertEquals("get name failed", "Cloud One",
         c1.getName());
      c1.setName("Test");
      Assert.assertEquals("set name failed", "Test",
         c1.getName());
   }

/** Test the get and set baseStorageCost methods for CloudStorage. **/
   @Test public void getAndSetBaseStorageCostTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      Assert.assertEquals("get base storage cost failed", 40.00,
         c1.getBaseStorageCost(), .001);
      c1.setBaseStorageCost(98.00);
      Assert.assertEquals("set base storage cost failed", 98.00,
         c1.getBaseStorageCost(), .001);
   }

/** Test the get and set server cost methods for dedicated cloud. **/
   @Test public void getAndSetServerCostTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      Assert.assertEquals("get server cost failed", 10.00,
         c1.getServerCost(), .0001);
      c1.setServerCost(42.00);
      Assert.assertEquals("set server cost failed", 42.00,
         c1.getServerCost(), .0001);
   }

/** Test the monthly cost method for dedicated cloud. **/
   @Test public void monthlyCosttTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      Assert.assertEquals("monthly cost failed", 50.00,
         c1.monthlyCost(), .0001);
   }

/** Test the toString method for dedicated cloud. **/
   @Test public void toStringTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      Assert.assertTrue(c1.toString().contains("Cloud One"));
      Assert.assertTrue(c1.toString().contains("(class DedicatedCloud)"));
      Assert.assertTrue(c1.toString().contains("Monthly Cost: $50.00"));
      Assert.assertTrue(c1.toString().contains("Base Storage Cost: $40.00"));
      Assert.assertTrue(c1.toString().contains("Server Cost: $10.00"));
   }
}