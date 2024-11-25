/**
* Jonathan Elder
* CPSC 1220 Intro to Computer Science II
* Activity 1 BankLoanTest.java
* @date 8/18/2022
*/

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


    /**
    * This is meant to test the BankLoan class using jUnit testing.
    */
public class BankLoanTest {

    // Set up any shared test data here if needed
   @Before public void setUp() {
   }

    /**
    * Test for interest being applied to the balance correctly.
    */
   @Test public void chargeInterestTest() {
      BankLoan loan1 = new BankLoan("Jane", 0.10);
      loan1.borrowFromBank(1000.00);
      loan1.chargeInterest();
      Assert.assertEquals("The balance should be $1100 after charging 10% interest.", 
         1100, loan1.getBalance(), 0.000001);
   }

    /**
    * Test for creating a loan with name and ensuring initial values are set correctly.
    */
   @Test public void loanCreationTest() {
      BankLoan loan2 = new BankLoan("Jonathan Elder", 0.05);
      Assert.assertEquals("Initial balance should be 0.", 0, loan2.getBalance(), 0.000001);
      Assert.assertEquals("Interest rate should be 0.05.", 0.05, loan2.getInterestRate(), 0.000001);
   }
 
    /**
    * Test for borrowing an amount and ensuring the balance is updated.
    */
   @Test public void borrowFromBankTest() {
      BankLoan loan3 = new BankLoan("Jane Doe", 0.05); 
      boolean wasLoanMade = loan3.borrowFromBank(500.00);
      Assert.assertTrue("Loan should be approved for $500.", wasLoanMade);
      Assert.assertEquals("Balance should be $500 after borrowing.", 500, loan3.getBalance(), 0.000001);
   }

    /**
    * Test for making sure paying the loan off works, including overpayment logic.
    */
   @Test public void payBankTest() {
      BankLoan loan4 = new BankLoan("Billy Bob", 0.10);
      loan4.borrowFromBank(200.00);
      double overpayment = loan4.payBank(250.00);
      Assert.assertEquals("Balance should be $0 after overpayment.", 0, loan4.getBalance(), 0.000001);
      Assert.assertEquals("Overpayment amount should be $50.", 50, overpayment, 0.000001);
   }

    /**
    * Test setting a valid and then invalid interest rate.
    */
   @Test public void setInterestRateTest() {
      BankLoan loan5 = new BankLoan("Jonathan Elder", 0.05);
      boolean validRateSet = loan5.setInterestRate(0.15);
      Assert.assertTrue("Interest rate of 0.15 should be valid.", validRateSet);
      Assert.assertEquals("Interest rate should now be 0.15.", 0.15, loan5.getInterestRate(), 0.000001);
      
      boolean invalidRateSet = loan5.setInterestRate(-0.01); // Not a valid interest rate.
      Assert.assertFalse("Negative interest rate should not be allowed.", invalidRateSet);
   }

   /**
    * Test the static method to check if an amount is valid.
    */
   @Test public void isAmountValidTest() {
      Assert.assertTrue("A balance of 0 should be a valid amount.", BankLoan.isAmountValid(0));
      Assert.assertTrue("A positive balance should be valid.", BankLoan.isAmountValid(100.00));
      Assert.assertFalse("A negative balance should not be valid.", BankLoan.isAmountValid(-10.00)); // Not a valid balance amount.
   }
}