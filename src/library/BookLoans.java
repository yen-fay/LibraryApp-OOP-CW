package library;

import java.time.LocalDate;
/**
 * Write a description of class BookLoans here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BookLoans
{
    int bookLoanID, bookID, memberID;
    LocalDate dateBorrowed;

    /**
     * Constructor for objects of class BookLoans
     */
    public BookLoans(int bookLoan_ID, int book_ID, int member_ID, LocalDate date_Borrowed)
    {
       bookLoanID = bookLoan_ID;
       bookID = book_ID;
       memberID = member_ID;
       dateBorrowed = date_Borrowed;
    }
}