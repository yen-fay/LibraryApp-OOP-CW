package library;

import java.time.LocalDate;
/**
 * Write a description of class BookLoans here.
 *
 * @author 670060628 & 670024613
 * @version 1.0 18/02/2018
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