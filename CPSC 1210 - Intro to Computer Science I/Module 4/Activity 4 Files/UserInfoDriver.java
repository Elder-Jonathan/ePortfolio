/*
* Activity 4 Part 2 CPSC 1210 Intro to Computer Science.
* Jonathan Elder
* Version 6/16/2022
*/
public class UserInfoDriver
/*
* Driver program that uses the defined UserInfo class and sets default data.
*/
{
   public static void main(String[] args) 
   {
      // Creates the first user object using the UserInfo class and sets default data.
      UserInfo user1 = new UserInfo("Pat", "Doe");
      user1.setLocation("Auburn");
      user1.setAge(19);
      user1.logOn();
      System.out.println("\n" + user1);
      
      // Creates the second user object using the UserInfo class and sets default data.
      UserInfo user2 = new UserInfo("Sam", "Jones");
      user2.setLocation("Atlanta");
      user2.setAge(21);
      user2.logOn();
      System.out.println("\n" + user2);
   }
}