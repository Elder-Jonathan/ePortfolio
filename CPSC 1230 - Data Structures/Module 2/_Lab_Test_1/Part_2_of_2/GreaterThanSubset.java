import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.*;
 /**
 * Return the elements in a collection that are strictly greater than a specified
 * value.
 *
 */
public class GreaterThanSubset {
 // C O M P L E T E   T H I S   M E T H O D 

    /**
     * Returns the elements in collection strictly greater than value.
     */
   public static <T extends Comparable<T>> Collection<T> greaterThanSubset(Collection<T> collection, T value) {
      ArrayList<T> result = new ArrayList<>();
      for (Iterator<T> i = collection.iterator(); i.hasNext();) {
         T a = i.next();
         if (value.compareTo(a) < 0)
            result.add(a);
      }
      for (int i=0; i < result.size(); i++){
         System.out.print(result.get(i).toString() + " ");
      }
      return result;
   }

}
