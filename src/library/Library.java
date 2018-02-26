package library;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
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
    { /*
        System.out.println("Enter your book loan ID: ");
        // does this have to be an integer?
        int bookloanID = input.nextInt();
        for (int i = 0; i < loansList.size(); i++)
        {
            if (loansList.get(i).bookLoanID == bookloanID)
            {
                if ((loansList.get(i).dateBorrowed == LocalDate.now()) || (loansList.get(i).dateBorrowed < LocalDate.now()) )
                {

               }
               else{
                   long daysBetween = DAYS.between(loansList.get(i).dateBorrowed, LocalDate.now());

                }
           }

       }
       */
    }
    public void addNewBook()
    {
        /*
        System.out.println("Enter the book title: ");
        String bTitle = input.next();
        //precheck
        for (int i = 0; i < bookList.size(); i++)
        {
            if (bookList.get(i).title == bTitle)
            {
                System.out.println("This book already exists. Would you like to continue? (Y/N)");
                String answer = input.next();

                if ((answer == "Y")|| (answer == "y")){
                    break;
            }
                else if ((answer == "N") || (answer == "n")){
                    LibraryApp.getUserInput();
                }
                else {
                    //Needs to go back to the Y/N bit
            }
        }

    }
        System.out.println("Enter the book author(s): ");
        String bAuthor = input.next();
        String[] bAuthorList = bAuthor.split(":");
        System.out.println("Enter the year of publishing: ");
        int year = input.nextInt();
        System.out.println("Enter the quantity of books added: ");
        int bQuantity = input.nextInt();

        addNewBook(bTitle, bAuthorList, year, bQuantity);
    */
    }
    public void addNewBook(String bTitle, String[] bAuthor, int year, int bQuantity)
    {
        /*
        int x = bookList.size() - 1;
        Book y = bookList.get(x);
        int z = y.bookID + 1;

        Book object = new Book (z, bTitle, bAuthor, year, bQuantity);
        bookList.add(object);
        System.out.println("The book has been added.");
        //Do we need to print out the book list?
        */
    }
    public void addNewMember()
    {
        // put your code here
        /*
        System.out.println("Enter your first name: ");
        String firstName = input.next();
        System.out.println("Enter your last name: ");
        String lastName = input.next();

        addNewMember(firstName, lastName, LocalDate.now());
        */
    }
    public void addNewMember(String firstName, String lastName, LocalDate date)
    {  /*
        int x = memberList.size() - 1;
        Member y = memberList.get(x);
        int z = y.memberID + 1;
        Member object = new Member (z,firstName, lastName, date);
        memberList.add(object);
        System.out.println("The member has been added. ");
        //Precheck? to see if the member is already there?
        */
    }
    public void changeQuantity()
    {
        System.out.println("Enter the book title: ");
        String btitle = input.next();
        System.out.println("Enter the quantity you want to change ('-' decreasing) ");
        int stockQuan = input.nextInt();
        changeQuantity(btitle, stockQuan);

    }
    public void changeQuantity(String bookTitle, int bquantity)
    {
        int available_copy;
        for (int i = 0; i < bookList.size(); i++)
        {
            if (bookTitle == bookList.get(i).title)
            {
                int number_of_copys = bookList.get(i).quantity;
                int ID = bookList.get(i).bookID;
                int number_on_loan = 0;
                for (int j = 0; j < bookList.size(); j++)
                { if (ID == loansList.get(i).bookID)
                {
                    number_on_loan = number_on_loan + 1;
                }
                }
                available_copy = number_of_copys - number_on_loan;
                //int newStock = available_copy + quantity;
                if (bquantity < 0 )
                {
                    if (available_copy > Math.abs(bquantity))
                    {
                        int newStock = number_of_copys + bquantity;
                        bookList.get(i).quantity = newStock;

                    } else{
                        System.out.println("The quantity you want decrease the stock by is more than the available number of copys");
                        changeQuantity();
                    }
                } else {
                    int newStock = bookList.get(i).quantity + bquantity;
                    bookList.get(i).quantity = newStock;
                }
            }
        }

    }
    public void saveChanges()
    {
        // put your code here

    }
}
