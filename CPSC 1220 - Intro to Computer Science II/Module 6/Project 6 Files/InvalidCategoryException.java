/**
* Intro to Computer Science II - Project 5
* @author Jonathan Elder
* @date   9/21/2022
* Represents the custom exception class to be used for default.
*/
public class InvalidCategoryException extends Exception {

   private String message;
   private static final long serialVersionUID = 1L;

   // Constructor with category argument.
   public InvalidCategoryException(String category) {
      super("For category: " + "\"" + category + "\"");
      this.message = "For category: " + "\"" + category + "\"";
   }

   // No-argument constructor.
   public InvalidCategoryException() {
      super("Invalid category detected.");
      this.message = "Invalid category detected.";
   }

   @Override
   public String toString() {
      return message;
   }

   @Override
   public String getMessage() {
      return message;
   }
}