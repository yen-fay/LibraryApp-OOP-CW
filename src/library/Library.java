package library;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import static ch.lambdaj.Lambda.*;
/**
 * Write a description of class Library here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Library
{
    Scanner input = new Scanner(System.in);
    ArrayList<Book> bookList = new ArrayList<Book>();
    ArrayList<Member> memberList = new ArrayList<Member>();
    ArrayList<BookLoans> loansList = new ArrayList<BookLoans>();
    /**
     * Constructor for objects of class Library
     */
    public Library(String books, String members, String bookLoans)
    {
      FileImport(books, "b");
      FileImport(members, "m");
      FileImport(bookLoans, "l");
    }
    public void FileImport(String filepath, String type)
    {
     File newFile = new File(filepath);
     try{
     Scanner inputLine = new Scanner(newFile);
      while (inputLine.hasNextLine())
      {
      String line = inputLine.nextLine();
      String[] fields = line.split(",");
        if (type == "b")
        {
         Book object = new Book(Integer.parseInt(fields[0]), fields[1], fields[2].split(":"), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
         bookList.add(object);
        }
        else if (type == "m")
        {
         Member object = new Member(Integer.parseInt(fields[0]), fields[1], fields[2], LocalDate.parse(fields[3]));
         memberList.add(object);
        }
        else
        {
         BookLoans object = new BookLoans(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), LocalDate.parse(fields[3]));
         loansList.add(object);
        }
      }
    }
    catch(FileNotFoundException ex){
        System.out.println(ex);
    }
}
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void showAllBooks()
    {
     System.out.println("Books:");
     for (int i=0; i<bookList.size(); i++)
     {
      Book x = bookList.get(i);
      System.out.print(x.bookID + "," + x.title + ",");
      for (int a=0; a<x.author.length; a++)
      {
       System.out.print(x.author);
       if (a!=x.author.length)
       {
        System.out.print(": ");
        }
      }
      System.out.print("," + x.yearPublished + "," + x.quantity + "\n");
     }
    }
    public void showAllMembers()
    {
     System.out.println("Members:");
     for (int j=0; j<memberList.size(); j++)
     {
      Member y = memberList.get(j);
      System.out.println(y.memberID + "," + y.firstName + "," + y.lastName + "," + y.dateJoined);
     }
    }
    public void showAllBookLoans()
    {
     System.out.println("Book Loans:");
     for (int k=0; k<loansList.size(); k++)
     {
      BookLoans z = loansList.get(k);
      System.out.println(z.bookLoanID + "," + z.bookID + "," + z.memberID + "," + z.dateBorrowed);
     }

    }
    public void searchBook()
    {
     System.out.println("Please input a keyword");
     String bTitle = input.next();
     searchBook(bTitle);
    }
    public void searchBook(String bTitle)
    {
     for (Book b : 
    }
    public void searchMember()
    {
        // put your code here

    }
    public void borrowBook()
    {

    }
    public void returnBook()
    {
        // put your code here

    }
    public void addNewBook()
    {
        // put your code here

    }
    public void addNewMember()
    {
        // put your code here

    }
    public void changeQuantity()
    {
        // put your code here

    }
    public void saveChanges()
    {
        // put your code here

    }
}
