import org.junit.Assert;
import org.junit.Test;

/**
*
* Activity 2 Part 2 CPSC 1220 Intro to Computer Science II
* Jonathan Elder
* Version 8/24/2022
*/
public class CustomerTest {

    /** Tests the setLocation and getLocation methods (single input). */
    @Test public void setLocationTest1() {
        Customer cstmr = new Customer("Lane, Jane");
        cstmr.setLocation("Boston, MA");
        Assert.assertEquals("Location should match the set value.", "Boston, MA", cstmr.getLocation());
    }

    /** Tests the setLocation and getLocation methods (city and state input). */
    @Test public void setLocationTest2() {
        Customer cstmr = new Customer("Lane, Jane");
        cstmr.setLocation("Atlanta", "GA");
        Assert.assertEquals("Location should match the combined city and state.", "Atlanta, GA", cstmr.getLocation());
    }

    /** Tests the changeBalance and getBalance methods. */
    @Test public void changeBalanceTest() {
        Customer cstmr = new Customer("Lane, Jane");
        cstmr.changeBalance(100);
        Assert.assertEquals("Balance should reflect the updated value.", 100, cstmr.getBalance(), 0.000001);
    }

    /** Tests the toString method. */
    @Test public void toStringTest() {
        Customer cstmr = new Customer("Lane, Jane");
        cstmr.setLocation("Auburn, AL");
        cstmr.changeBalance(999);
        Assert.assertEquals("String representation should match the expected format.",
                "Lane, Jane\nAuburn, AL\n$999.0", cstmr.toString());
    }

    /** Tests the compareTo method when balances are equal. */
    @Test public void compareToTest1() {
        Customer cstmr1 = new Customer("Lane, Jane");
        cstmr1.changeBalance(500);

        Customer cstmr2 = new Customer("Lane, Jane");
        cstmr2.changeBalance(500);

        Assert.assertTrue("compareTo should return 0 when balances are equal.", cstmr1.compareTo(cstmr2) == 0);
    }

    /** Tests the compareTo method when the first balance is less than the second. */
    @Test public void compareToTest2() {
        Customer cstmr1 = new Customer("Lane, Jane");
        cstmr1.changeBalance(100);

        Customer cstmr2 = new Customer("Lane, Jane");
        cstmr2.changeBalance(500);

        Assert.assertTrue("compareTo should return a negative value when the first balance is less.", cstmr1.compareTo(cstmr2) < 0);
    }

    /** Tests the compareTo method when the first balance is greater than the second. */
    @Test public void compareToTest3() {
        Customer cstmr1 = new Customer("Lane, Jane");
        cstmr1.changeBalance(1000);

        Customer cstmr2 = new Customer("Lane, Jane");
        cstmr2.changeBalance(500);

        Assert.assertTrue("compareTo should return a positive value when the first balance is greater.", cstmr1.compareTo(cstmr2) > 0);
    }
}