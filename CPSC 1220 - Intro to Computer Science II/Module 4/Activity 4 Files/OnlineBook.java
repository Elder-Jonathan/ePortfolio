/**
* Jonathan Elder.
* Intro to Computer Science 1220 - Activity 4
* 9/12/2022
*/
public class OnlineBook extends OnlineTextItem {
   
   // Instance variable.
   protected String author;

   /**
   * Constructor for setting up an "Online Book" object in inventory using inheritence.
   * @param name The name for the "Online Book" object.
   * @param price The price for the "Online Book" object.
   */
   public OnlineBook(String name, double price) {
       super(name, price);
       author = "Author Not Listed";
   }

   /**
   * Setter for the author variable of the "Online Book" object.
   * @param authorIn The author's name that was entered.
   */
   public void setAuthor(String authorIn) {
       author = authorIn;
   }

   /**
   * Overrides the toString method to give an "Online Book" object description.
   * @return A formatted string containing the "Online Book" object's name, author, and price.
   */
   @Override
   public String toString() {
       return name + " - " + author + ": $" + price;
   }
}