import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Tests the methods of the PublicCloud class.
* Jonathan Elder
* 9/17/2022
*/
public class PublicCloudTest {

/** Fixture initialization (common initialization
* for all tests). **/
   @Before public void setUp() {
   }

/** Test the get cost factor methods for public cloud. **/
   @Test public void getCostFactorTest() {
      PublicCloud c4 = new PublicCloud("Cloud Four", 9.00, 25.0, 20.0);
      Assert.assertEquals("get cost factor failed", 2.0,
         c4.getCostFactor(), .0001);
   }

/** Test the monthly cost method for public cloud. **/
   @Test public void monthlyCosttTest() {
      PublicCloud c4 = new PublicCloud("Cloud Four", 9.00, 25.0, 20.0);
      Assert.assertEquals("monthly cost failed", 19.00,
         c4.monthlyCost(), .0001);
   }

/** Test the toString method for public cloud. **/
   @Test public void toStringTest() {
      PublicCloud c4 = new PublicCloud("Cloud Four", 9.00, 25.0, 20.0);
      Assert.assertTrue(c4.toString().contains("Cloud Four"));
      Assert.assertTrue(c4.toString().contains("(class PublicCloud)"));
      Assert.assertTrue(c4.toString().contains("Monthly Cost: $19.00"));
      Assert.assertTrue(c4.toString().contains("Base Storage Cost: $9.00"));
      Assert.assertTrue(c4.toString().contains("Data Stored: 25.000 GB"));
      Assert.assertTrue(c4.toString().contains("Data Limit: 20.000 GB"));
      Assert.assertTrue(c4.toString().contains("Overage: 5.000 GB"));
      Assert.assertTrue(c4.toString().contains("Cost Factor: 2.0"));
   }
}