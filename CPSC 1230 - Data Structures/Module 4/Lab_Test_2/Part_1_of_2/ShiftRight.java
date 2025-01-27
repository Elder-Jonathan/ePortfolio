/**
 * Implements shift-right behavior in an array.
 * @auther Jonathan Elder
 */

public class ShiftRight {


    /**
     * Shifts the elements at a[index] through a[a.length - 2] one
     * position to the right. 
     */
    public static void shiftRight(Object[] array, int index) {

         // FILL IN THE BODY OF THIS METHOD

         for (int i = array.length - 2; i >= index; i--) {
           
		   // shift each element towards right one by one
		   array[i + 1] = array[i];
	      }
        
	      // assign null to given index    
	      array[index] = null;
}

