/**
* Activity 2 Part 1 CPSC 1220 Intro to Computer Science II
* Jonathan Elder
* Date 8/24/2022
*/
public class Customer implements Comparable<Customer> {
    
    // Instance variables.
    private String name;
    private String location;
    private double balance;

    /**
    * Constructor for Initializing a Customer object using an inputed name variable.
    * Location is initialized as an empty string, and balance is set to 0.
    * @param nameIn Inputed name variable for the customer object.
    */
    public Customer(String nameIn) {
        this.name = nameIn;
        this.location = "";
        this.balance = 0;
    }

    /**
    * Setter for the location variable of a customer object.
    * @param locationIn Taken input for location variable of a customer object.
    */
    public void setLocation(String locationIn) {
        location = locationIn;
    }

    /**
    * Overloaded method to set the location using city and state by
    * Combining city and state into a single location string.
    * @param city Input city of the customer.
    * @param state Input state of the customer.
    */
    public void setLocation(String city, String state) {
        location = city + ", " + state;
    }

    /**
    * Adjusts the current balance variable by adding the given amount. Amount can be negative.
    * Positive amounts increase the balance, and negative amounts decrease it.
    * @param amount Amount to be added(or subtracted) from the balance.
    */
    public void changeBalance(double amount) {
        balance += amount;
    }

    /**
    * Getter for the location variable of a Customer object.
    * @return The current location variable of the customer object.
    */
    public String getLocation() {
        return location;
    }

    /**
    * Getter for the current balance variable of a Customer object.
    * @return The current balance for the Customer object.
    */
    public double getBalance() {
        return balance;
    }

    /**
    * Provides a string representation of the Customer object.
    * Includes the customer's name, location, and balance, in a formatted structure.
    * @return A descriptive string representation of the customer object.
    */
    @Override
    public String toString() {
        return name + "\n" + location + "\n$" + balance;
    }

    /**
    * Compares this customer's balance with another customer's balance.
    * @param obj Another Customer object to compare with.
    * @return 0 if balances are equal, -1 if this customer's balance is less,
    *         or 1 if this customer's balance is greater.
    */
    @Override
    public int compareTo(Customer obj) {
        if (Math.abs(this.balance - obj.getBalance()) < 0.000001) {
            return 0;
        } else if (this.balance < obj.getBalance()) {
            return -1;
        } else {
            return 1;
        }
    }
}
