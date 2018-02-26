package library;

/**
 * Write a description of class Book here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Book
{
  int bookID, yearPublished, quantity;
  String title;
  String[] author;
  
    /**
     * Constructor for objects of class Book
     */
    public Book(int book_ID, String book_title, String[] book_author, int year, int book_quantity)
    {
        bookID = book_ID;
        title = book_title;
        author = book_author;
        yearPublished = year;
        quantity = book_quantity;
    }

}
