package library;

/**
 * Write a description of class Book here.
 *
 * @author 670060628 & 670024613
 * @version 1.0 18/02/2018
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
