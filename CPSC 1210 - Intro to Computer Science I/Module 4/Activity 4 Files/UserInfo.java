/**
* Activity 4 Part 1 CPSC 1210 Intro to Computer Science.
* Jonathan Elder
* Version 6/16/2022
*/


/*
* Sets a user class with details about each user.
*/
public class UserInfo {

   // Constant variables that can never be given a different value .
   private static final int OFFLINE = 0, ONLINE = 1;
   
   // Initialize all variables for the UserInfo class.
   private String firstName;
   private String lastName;
   private String location;
   private int age;
   private int status;

   /*
   * Setting up the constructor with a pattern of the user's first name then last.
   */
   public UserInfo(String firstNameIn, String lastNameIn)
   {
      firstName = firstNameIn;
      lastName = lastNameIn;
      location = "Not specified";
      age = 0;
      status = OFFLINE;
   }
   
   /*
   * Methods for the User Info class.
   */
   public String toString() {
      String output = "Name: " + firstName + " "
         + lastName + "\n";
      output += "Location: " + location + "\n";
      output += "Age: " + age + "\n";
      if (status == OFFLINE)
      {
         output += "Offline";
      }
      else
      {
         output += "Online";
      }
      output += "Status: ";
      return output;
   }
   
   /*
   * Setter for location in UserInfo class.
   */
   public void setLocation(String locationIn)
   {
      location = locationIn;
   }
   
   /*
   * Setter for ageIn variable with a check for the number entered to be above 0.
   */
   public boolean setAge(int ageIn)
   {

      boolean isSet = false;
      if(ageIn > 0)
      {
         age = ageIn;
         isSet = true;
      }
      return isSet;
   }
   
   /*
   * Getter for the variable age in the UserInfo class.
   */ 
   public int getAge()
   {
      return age;
   }
   
   /*
   * Getter for the variable location in the UserInfo class.
   */
   public String getLocation()
   {   
      return location;
   }
   
   /*
   * Method for logging off.
   */
   public void logOff() {
      status = OFFLINE;
   }
   
   /*
   * Method for logging on.
   */
   public void logOn() {
      status = ONLINE;
   }
}