/**
* Jonathan Elder.
* Intro to Computer Science II 1220 - Activity 4
* Date 9/12/2022
*/
public class OnlineArticle extends OnlineTextItem {

   private int wordCount = 0;

   /**
   * Constructor to initialize an OnlineArticle object derived from OnlineTextItem.
   * @param name The name given to the article object.
   * @param price The price given to the article object.
   */
   public OnlineArticle(String name, double price) {
      super(name, price);
      this.wordCount = 0;
   }

   /**
   * Setter for the word_count variable for the article object.
   * @param wordCountIn The entered number of words for the article object.
   */
   public void setWordCount(int wordCountIn) {
      this.wordCount = wordCountIn;
   }
}