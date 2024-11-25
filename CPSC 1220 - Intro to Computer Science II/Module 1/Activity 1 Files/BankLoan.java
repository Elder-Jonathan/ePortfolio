/**
* Jonathan Elder
* CPSC 1220 Intro to Computer Science II
* Activity 1 BankLoan.java
* @date 8/18/2022
*/

public class BankLoan {

	// Constant field for the max valid loan amount.
   private static final int MAX_LOAN_AMOUNT = 100000;

   // instance variables (can be used within the class)
   private String customerName;
   private double balance, interestRate;
   private static int loansCreated = 0; // number of BankLoan objects instantiated.
   
 /**
 * Contructor that creates a BankLoan object with values for name and interest rate.
 * @param customerNameIn the customer name that is entered.
 * @param interestRateIn the interest rate assigned.
 */
   public BankLoan(String customerNameIn, double interestRateIn) { 
      customerName = customerNameIn;
      interestRate = interestRateIn;
      balance = 0;
      loansCreated++;
   }
   
 /**
 * Customer is able to borrow money if newly created balance does not exceed max loan constant.
 * @param amount the total money to borrow.
 * @return true if the BankLoan was approved.
 */
   public boolean borrowFromBank(double amount) {
      
      boolean wasLoanMade = false;
      
      if (balance + amount < MAX_LOAN_AMOUNT) {
         wasLoanMade = true;
         balance += amount;
      }
   
      return wasLoanMade;
   }

 /**
 * Customer makes a payment towards their current BankLoan.
 * @param amountPaid the amount to deduct from current BankLoan.
 * @return the update balance, overpayment, or 0 if paid off fully.
 */
   public double payBank(double amountPaid) {
      double newBalance = balance - amountPaid;
      if (newBalance < 0) {
         balance = 0;
         return Math.abs(newBalance); // if payment was more than current balance.
      } else {
         balance = newBalance;
         return 0;
      }
   }
   
 /**
 * Getter for retrieving the current balance of the BankLoan.
 * @return the current BankLoan balance.
 */
   public double getBalance() {
      return balance;
   }
   
 /**
 * Setter for an updated valid interest value between 0 and 1.
 * @param interestRateIn the entered validated and updated interest rate.
 * @return true if the interest rate was updated, otherwise false.
 */
   public boolean setInterestRate(double interestRateIn) {
   
      if (interestRateIn >= 0 && interestRateIn <= 1) {
         interestRate = interestRateIn;
         return true;
      }
      else {
         return false;
      }
   }
   
 /**
 * Getter for the current interest rate of a given BankLoan object.
 * @return the current interest rate.
 */
   public double getInterestRate() {
      return interestRate;
   }
   
 /**
 * Applies the current interest rate to the current balance, and
 * updates the balance with the interest charged.
 */
   public void chargeInterest() {
      balance = balance * (1 + interestRate);
   }
   
 /**
 * Checks if the given amount is greater than or equal to 0.
 * @param amount the amount to check
 * @return true if the amount is valid, otherwise false.
 */
   public static boolean isAmountValid(double amount) {
      return amount >= 0;
   }
   
  /**
  * Checks if the given loan has an outstanding balance.
  * @param loan the BankLoan to check.
  * @return true if the BankLoan has a balance greater than 0, otherwise false.
  */
   public static boolean isInDebt(BankLoan loan) {
      if (loan.getBalance() > 0) {
         return true;
      }
      return false;
   }
   
 /**
 * Getter for returning the current number of loans created.
 * @return the current number of loans created.
 */
   public static int getLoansCreated() {
      return loansCreated;
   }
   
 /**
 * Resets the counter variable for the amount of loans created.
 */
   public static void resetLoansCreated() {
      loansCreated = 0;
   }
   
 /**
 * This is the toString method including the customer's name, 
 * interest rate, and current balance for the BankLoan class.
 * @return a formatted string with details for the given BankLoan object.
 */
   public String toString() {
      String output = "Name: " + customerName + "\r\n" 
         + "Interest rate: " + interestRate + "%\r\n" 
         + "Balance: $" + balance + "\r\n";
      return output;
   }

}
