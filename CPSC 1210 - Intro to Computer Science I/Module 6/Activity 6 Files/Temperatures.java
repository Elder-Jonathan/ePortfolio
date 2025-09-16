import java.util.ArrayList;

/**
* Activity 6 Part 1 CPSC 1210 Intro to Computer Science.
* @author Jonathan Elder
* @version 6/30/2022
*/

public class Temperatures {
   private ArrayList<Integer> temperatures;

   /**
    * Constructor.
    * @param temperaturesIn temperaturesIn
    */
   public Temperatures(ArrayList<Integer> temperaturesIn) {
      temperatures = temperaturesIn;
   }

   /**
    * If temp is not empty then getLowTemp.
    * @return the lowest temperature
    */
   public int getLowTemp() {
      if (temperatures.isEmpty()) {
         return 0;
      }
   
      int low = temperatures.get(0);
      for (int i = 0; i < temperatures.size(); i++) {
         if (temperatures.get(i) < low) {
            low = temperatures.get(i);
         }
      }
   
      return low;
   }

   /**
    * If temp is not empty then getHighTemp.
    * @return the highest temperature
    */
   public int getHighTemp() {
      if (temperatures.isEmpty()) {
         return 0;
      }
   
      int high = temperatures.get(0);
      for (Integer temp : temperatures) {
         if (temp > high) {
            high = temp;
         }
      }
   
      return high;
   }

   /**
    * Boolean expression that calculates if one temp is lower than another.
    * @param lowIn lowIn
    * @return finds the lower of the 2 temperatures
    */
   public int lowerMinimum(int lowIn) {
      return lowIn < getLowTemp() ? lowIn : getLowTemp();
   }

   /**
    * Boolean expression that calculates if one temp is higher than another.
    * @param highIn highIn
    * @return find the higher of the 2 temperature
    */
   public int higherMaximum(int highIn) {
      return highIn > getHighTemp() ? highIn : getHighTemp();
   }

   /**
    * Gives a toString method that returns the values of low and high temps.
    * @return temperatures in string format
    */
   public String toString() {
      return     "\tTemperatures: " + temperatures
             +  "\n\tLow: " + getLowTemp()
             +  "\n\tHigh: " + getHighTemp();
   }


}