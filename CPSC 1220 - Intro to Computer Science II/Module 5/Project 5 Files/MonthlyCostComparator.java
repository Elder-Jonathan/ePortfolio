import java.util.Comparator;

/**
* Intro to Computer Science II - Project 5
* @author Jonathan Elder
* @date   9/21/2022
* Compares against other cloud storage types.
*/
public class MonthlyCostComparator implements Comparator<CloudStorage> {
   /**
   * Sets up compare methods with each cloud type name.
   * @param c1 The first object to compare.
   * @param c2 The second object to compare against the first.
   * @return a -1 if first object is greater, 1 if second object is greater, or 0 if neither implying they are equal.
   */
   public int compare(CloudStorage c1, CloudStorage c2) {
      if (c1.monthlyCost() > c2.monthlyCost()) {
         return -1;
      }
      else if (c1.monthlyCost() < c2.monthlyCost()) {
         return 1;
      }
      else {
         return 0;
      }
      
   }
}