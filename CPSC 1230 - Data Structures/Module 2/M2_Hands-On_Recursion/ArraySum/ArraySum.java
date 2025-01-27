/**
 * Provides a sum function on arrays.
 *
 * @author Dean Hendrix (dh@auburn.edu) instructor
 * @version 2018-03-26
 */

public class ArraySum {  
   public static int sumArray(int[] a, int left, int right) {
      if(left<=right){
         return a[left]+sumArray(a,left+1,right);
      }
      else{
         return 0;
      }
   }
}