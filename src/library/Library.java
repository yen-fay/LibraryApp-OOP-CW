package library;

import javax.swing.text.DateFormatter;
import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Write a description of class Library here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Library
{
    Scanner input = new Scanner(System.in);
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<BookLoans> loansList = new ArrayList<>();
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
        else if (type == "l")
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
       System.out.print(x.author[a]);
       if (a!=x.author.length -1)
       {
        System.out.print(":");
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
     System.out.println("Please input a keyword(s):");
     String bTitle = input.nextLine();
     searchBook(bTitle);
    }
    public void searchBook(String bTitle)
    {
        int counter = 0;
        for (int i = 0; i < bookList.size(); i++)
        {
            if (bookList.get(i).title.toLowerCase().contains(bTitle.toLowerCase()))
            {
                Book a = bookList.get(i);
                System.out.print(a.bookID + "," + a.title + ",");
                for (int b = 0; b < a.author.length; b++)
                {
                    System.out.print(a.author[b]);
                    if (b != a.author.length - 1)
                    {
                        System.out.print(":");
                    }
                }
                System.out.print("," + a.yearPublished + "," + a.quantity + "\n");
                counter += 1;
            }
        }
        if (counter == 0)
        {
            System.out.println("Invalid keyword. Please try again.");
        }
    }
    public void searchMember()
    {
        System.out.println("Please input a first name:");
        String first_name = input.next();
        System.out.println("Please input a last name:");
        String last_name = input.next();
        searchMember(first_name, last_name);
    }
    public void searchMember(String first_name, String last_name)
    {
        int counter = 0;
        for (int j=0; j<memberList.size(); j++)
            {
                if (memberList.get(j).firstName.toLowerCase().equals(first_name.toLowerCase()) && memberList.get(j).lastName.toLowerCase().equals(last_name.toLowerCase()))
                {
                    Member c = memberList.get(j);
                    System.out.println(c.memberID + "," + c.firstName + "," + c.lastName + "," + c.dateJoined);
                    counter += 1;
                }
            }
        if (counter == 0)
        {
            System.out.println("Member not found. Please try again.");
        }
    }
    public void borrowBook()
    {
        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.println("Enter the title of the book you want to borrow: ");
        String bookTitle = input.nextLine();
        for (int i = 0; i < bookList.size(); i++ ) {
            if (bookList.get(i).title.toLowerCase().contains(bookTitle)) {
                System.out.println(bookList.get(i).title);
            }
        }
        System.out.println("Please pick a book: ");
        String bTitle = input.nextLine();
        borrowBook(bTitle, firstName, lastName);
    }
    public void borrowBook(String bTitle, String first_name, String last_name) {
        boolean isbValidInput = false;
        boolean ismValidInput = false;
        boolean isblValidInput = false;
        boolean isValidInput = false;
        int mID = 0;
        int x = 0;
        int number_on_loan = 0;
        int bID = 0;
        int bQuantity = 0;

        for (int i = 0; i < bookList.size(); i++) {

            if (bTitle.equalsIgnoreCase(bookList.get(i).title)) {
                isbValidInput = true;
                bID = bookList.get(i).bookID;
                bQuantity = bookList.get(i).quantity;

            }
        }
        for (int j = 0; j < memberList.size() - 1; j++) {
            if (first_name.equalsIgnoreCase(memberList.get(j).firstName) && last_name.equalsIgnoreCase(memberList.get(j).lastName)) {
                ismValidInput = true;
                mID = memberList.get(j).memberID;
            }
        }
        for (int k = 0; k < loansList.size() - 1; k++) {
            if (mID == loansList.get(k).memberID) {
                x = x + 1;
            }
            if (bID == loansList.get(k).bookID) {
                number_on_loan = number_on_loan + 1;
            }
        }
        int available_copy = bQuantity - number_on_loan;
        if (available_copy >= 0) {
            isblValidInput = true;
        } else {
            System.out.println("No copies available to take on loan");
        }
        if (x < 5) {
            isValidInput = true;
        } else {
            System.out.println("You already have 5 books out, can't take out more");
        }


        if (isValidInput == true && isbValidInput == true && isblValidInput == true && ismValidInput == true) {
            int a = loansList.size() - 1;
            System.out.println();
            BookLoans b = loansList.get(a);
            int c = b.bookLoanID + 1;
            BookLoans object = new BookLoans(c, bID, mID, LocalDate.now());
            loansList.add(object);
            /*
            for (int i = 0; i < bookList.size() - 1; i++)
            {
                if (bTitle == bookList.get(i).title)
                {
                    bookList.get(i).quantity = bookList.get(i).quantity - 1;

                }
            }
            */
        }
    }

    public void returnBook()
    {
        System.out.print("Enter your book loan ID: ");
        int bookloanID = input.nextInt();
        returnBook(bookloanID);
    }
    public void returnBook(int bID)
    {
        for (int i = 0; i < loansList.size(); i++) {
            if (bID == loansList.get(i).bookLoanID) {
                LocalDate dueDate = loansList.get(i).dateBorrowed.plusDays(30);
                if ((dueDate == LocalDate.now()) || (dueDate.isAfter(LocalDate.now()))) {
                    loansList.remove(i);
                    System.out.println("Your book is returned");
                    break;
                    //Add quantity to book list?
                } else {
                    long daysBetween = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
                    //What data type should fine be?
                    double fine = (int) daysBetween * 0.1;
                    String answer;
                    do {
                        System.out.println("Your book is overdue. You must pay the fine of £" + fine + ". Would you like to pay and return the book? (y/n)");
                        answer = input.next();
                    } while (!answer.equalsIgnoreCase("N") && !answer.equalsIgnoreCase( "Y"));
                    if (answer.equalsIgnoreCase("Y")){
                        loansList.remove(i);
                        System.out.println("Your book is returned");
                        break;
                    }


                }
            }else if (i == loansList.size() - 1 && bID != loansList.get(i).bookLoanID){
                System.out.println("The book loan ID doesn't exist");
                    break;
            }



        }


    }
    public void addNewBook()
    {

        System.out.println("Enter the book title: ");
        String bTitle = input.nextLine();

        System.out.println("Enter the book author(s): ");
        String bAuthor = input.nextLine();
        String[] bAuthorList = bAuthor.split(":");
        System.out.println("Enter the year of publishing: ");
        int year = input.nextInt();
        input.nextLine();
        System.out.println("Enter the quantity of books added: ");
        int bQuantity = input.nextInt();
        input.nextLine();
        addNewBook(bTitle, bAuthorList, year, bQuantity);

    }
    public void addNewBook(String bTitle, String[] bAuthorList, int year, int bQuantity)
    {

        String answer;
        for (int i = 0; i < bookList.size(); i++)
        {
            if (bTitle.equalsIgnoreCase( bookList.get(i).title))
            {    do {
                    System.out.println("This book already exists. Would you like to continue? (Y/N)");
                    answer = input.next();
                    if ((answer.equalsIgnoreCase("Y"))){
                        int x = bookList.size() - 1;
                        Book y = bookList.get(x);
                        int z = y.bookID + 1;

                        Book object = new Book (z, bTitle, bAuthorList, year, bQuantity);
                        bookList.add(object);
                        System.out.println("The book has been added.");
                        showAllBooks();
                        break;
                    }
                }while ((!answer.equalsIgnoreCase("Y")) && (!answer.equalsIgnoreCase("N")));


            }else if (i == bookList.size()){
                int x = bookList.size() - 1;
                Book y = bookList.get(x);
                int z = y.bookID + 1;

                Book object = new Book (z, bTitle, bAuthorList, year, bQuantity);
                bookList.add(object);
                System.out.println("The book has been added.");
                showAllBooks();
                break;
            }
        }



        //Do we need to print out the book list?
    }
    public void addNewMember()
    {
        System.out.println("Enter your first name:");
        String firstName = input.next();
        System.out.println("Enter your last name:");
        String lastName = input.next();
        addNewMember(firstName, lastName, LocalDate.now());

    }
    public void addNewMember(String firstName, String lastName, LocalDate date)
    {
        int x = memberList.size() -1;
        Member y = memberList.get(x);
        int z = y.memberID + 1;
        Member object = new Member (z, firstName, lastName, date);
        memberList.add(object);
        System.out.println("The member has been added.");
        showAllMembers();
        //Precheck? to see if the member is already there?

    }
    public void changeQuantity()
    {
        input.nextLine();
        System.out.println("Enter the book title: ");
        String bookTitle = input.nextLine();
        System.out.println("Enter the quantity you want to change ('-' decreasing) ");
        int stockQuan = input.nextInt();
        input.nextLine();
        changeQuantity(bookTitle, stockQuan);

    }
    public void changeQuantity(String bTitle, int bQuantity)
    {
        int available_copy;
        for (int i = 0; i < bookList.size(); i++)
        {
            if (bTitle.equalsIgnoreCase(bookList.get(i).title))
            {
                int number_of_copies = bookList.get(i).quantity;
                int ID = bookList.get(i).bookID;
                int number_on_loan = 0;

                for (int j = 0; j < loansList.size(); j++)
                { if (ID == loansList.get(i).bookID)
                {
                    number_on_loan = number_on_loan + 1;
                }
                }
                available_copy = number_of_copies - number_on_loan;
                //int newStock = available_copy + quantity;
                if (bQuantity < 0 )
                {
                    if (available_copy > Math.abs(bQuantity))
                    {
                        int newStock = number_of_copies + bQuantity;
                        bookList.get(i).quantity = newStock;
                        System.out.println("The quantity has been updated");
                        showAllBooks();
                        break;

                    } else{
                        System.out.println("Invalid. The quantity you want to decrease the stock by is more than the available number of copies");
                        break;

                    }
                } else {
                    int newStock = bookList.get(i).quantity + bQuantity;
                    bookList.get(i).quantity = newStock;
                    System.out.println("The quantity has been updated");
                    showAllBooks();
                    break;
                }
            }else if ((!bTitle.equalsIgnoreCase(bookList.get(i).title)) && (i == bookList.size() -1))
            {
                System.out.print("No such book exists");
            }


        }



    }
    public void saveChanges(String books, String members, String bookLoans)
    {
        try
        {
            //Books

            File info = new File(books);
            FileOutputStream os = new FileOutputStream(info);
            OutputStreamWriter osw = new OutputStreamWriter(os);

            Writer w = new BufferedWriter(osw);

            for (int i=0; i<bookList.size(); i++)
            {
                Book x = bookList.get(i);
                w.write(x.bookID + "," + x.title + ",");
                for (int a=0; a<x.author.length; a++)
                {
                    w.write(x.author[a]);
                    if (a!=x.author.length -1)
                    {
                        w.write(":");
                    }
                }
                w.write("," + x.yearPublished + "," + x.quantity + "\n");
            }
            w.close();

            //Members
            info = new File(members);
            os = new FileOutputStream(info);
            osw = new OutputStreamWriter(os);

            w = new BufferedWriter(osw);

            for (int j=0; j<memberList.size(); j++)
            {
                Member y = memberList.get(j);
                w.write(y.memberID + "," + y.firstName + "," + y.lastName + "," + y.dateJoined);
                w.write("\n");
            }
            w.close();

            //Book Loans
            info = new File(bookLoans);
            os = new FileOutputStream(info);
            osw = new OutputStreamWriter(os);

            w = new BufferedWriter(osw);

            for (int k=0; k<loansList.size(); k++)
            {
                BookLoans z = loansList.get(k);
                w.write(z.bookLoanID + "," + z.bookID + "," + z.memberID + "," + z.dateBorrowed);
                w.write("\n");
            }
            w.close();
        }
        catch(IOException e)
        {
            System.err.println("Error: Unable to write to files.");
        }
    }
}
