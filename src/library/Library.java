package library;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.temporal.ChronoUnit;

/**
 * Library.java
 * Purpose: To emulate a library management application
 *
 * @author 670060628 & 670024613
 * @version 1.0 13/03/2018
 */

/**
 * Scans for input to then be sorted into different lists
 */
public class Library
{
    Scanner input = new Scanner(System.in);
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<BookLoans> loansList = new ArrayList<>();

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
          switch (type) {
              case "b": {
                  Book object = new Book(Integer.parseInt(fields[0]), fields[1], fields[2].split(":"), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
                  bookList.add(object);
                  break;
              }
              case "m": {
                  Member object = new Member(Integer.parseInt(fields[0]), fields[1], fields[2], LocalDate.parse(fields[3]));

                  memberList.add(object);
                  break;
              }
              case "l": {
                  BookLoans object = new BookLoans(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), LocalDate.parse(fields[3]));
                  loansList.add(object);
                  break;
              }
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
        for (Book x : bookList) {
            System.out.print(x.bookID + "," + x.title + ",");
            for (int a = 0; a < x.author.length; a++) {
                System.out.print(x.author[a]);
                if (a != x.author.length - 1) {
                    System.out.print(":");
                }
            }
            System.out.print("," + x.yearPublished + "," + x.quantity + "\n");
        }
    }
    public void showAllMembers()
    {
     System.out.println("Members:");
        for (Member y : memberList) {
            System.out.println(y.memberID + "," + y.firstName + "," + y.lastName + "," + y.dateJoined);
        }
    }
    public void showAllBookLoans()
    {
     System.out.println("Book Loans:");
        for (BookLoans z : loansList) {
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
        for (Book aBookList : bookList) {
            if (aBookList.title.toLowerCase().contains(bTitle.toLowerCase())) {
                Book a = aBookList;
                System.out.println("Book ID: " + a.bookID);
                System.out.println("Title: " + a.title);
                System.out.print("Author: ");
                for (int b = 0; b < a.author.length; b++) {
                    System.out.print(a.author[b]);
                    if (b != a.author.length - 1) {
                        System.out.print(":");
                    }
                }
                System.out.println("\n" + "Year Published: " + a.yearPublished);
                System.out.println("Total Quantity: " + a.quantity);
                // gotta include number available
                System.out.println("********************");
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
        System.out.println("Please input a first name and last name:");
        String first_name = input.next();
        String last_name = input.next();
        searchMember(first_name, last_name);
    }
    public void searchMember(String first_name, String last_name)
    {
        int memberCount = 0;
        int bookCount = 0;
        for (Member aMemberList : memberList) {
            if (aMemberList.firstName.toLowerCase().equals(first_name.toLowerCase()) && aMemberList.lastName.toLowerCase().equals(last_name.toLowerCase())) {
                Member c = aMemberList;
                System.out.println("**************************");
                System.out.println("ID: " + c.memberID);
                System.out.println("Name: " + c.firstName + " " + c.lastName);
                System.out.println("Date Joined: " + c.dateJoined);
                System.out.println("**************************");
                for (BookLoans aLoansList : loansList) {
                    if (c.memberID == aLoansList.memberID) {
                        bookCount += 1;
                        BookLoans d = aLoansList;
                        System.out.println("Book Loan ID: " + d.bookLoanID);
                        for (Book aBook : bookList) {
                            if (aBook.bookID == d.bookID) {
                                System.out.println("Title: " + aBook.title);
                            }
                        }
                        System.out.println("Date Borrowed: " + d.dateBorrowed);
                        System.out.println("Due Date: " + d.dateBorrowed.plusDays(30));
                        System.out.println("**************************");
                    }
                }
                System.out.println("Number of Books on Loan: " + bookCount);
                memberCount += 1;
            }
        }
        if (memberCount == 0)
        {
            System.out.println("Member not found. Please try again.");
        }
    }
    /**
     * This method checks if the member is valid .
     *
     * @return    the sum of x and y
     */
    public void borrowBook()
    {
        String lastName = "" ;
        String firstName = "";
        String bookTitle ;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Enter your first and last name: ");
            firstName = input.next();
            lastName = input.next();
            for (int j = 0; j < memberList.size(); j++) {
                if (firstName.equalsIgnoreCase(memberList.get(j).firstName) && (lastName.equalsIgnoreCase((memberList.get(j).lastName)))) {
                    isValidInput = true;
                    break;
                } else if (j == memberList.size() - 1 && !firstName.equalsIgnoreCase(memberList.get(j).firstName) && (!lastName.equalsIgnoreCase((memberList.get(j).lastName)))) {
                    System.out.println("Invalid Input");
                }
            }
        }
            System.out.println("Enter a keyword: ");
            bookTitle = input.nextLine();

        borrowBook(bookTitle, firstName, lastName);
    }
    public void borrowBook(String bTitle, String first_name, String last_name) {
        boolean isacValidInput = false;
        boolean isValidInput = false;
        boolean foundBook = false;
        int mID = 0;
        int x = 0;
        int bID = 0;
        int bQuantity = 0;
        int count = 0;
        System.out.println("****************************************");
        System.out.println("Book(s) found: ");
        System.out.println("****************************************");
        for (Book aBookList : bookList) {
            if (aBookList.title.toLowerCase().contains(bTitle.toLowerCase())) {
                System.out.println("****************************************");
                System.out.println("ID: " + aBookList.bookID);
                System.out.println("Title: " + aBookList.title);
                System.out.print("Author(s): ");
                for (int a = 0; a < aBookList.author.length; a++) {
                    System.out.print(aBookList.author[a] + " ");
                }
                System.out.println();
                System.out.println("Year: " + aBookList.yearPublished);
                System.out.println("Number of copies: " + aBookList.quantity);
                System.out.println("****************************************");
                count = count + 1;
            }
        }
        if (count == 0)
        {
            System.out.println("*********************************************");
            System.out.println("No books containing the keyword exists. ");
            System.out.println("*********************************************");
        } else {

            System.out.println("****************************************");
            System.out.println("Please enter the book title: ");
            String bookTitle = System.console().readLine();

            for (int i = 0; i < bookList.size(); i++) {
                if (bookTitle.equalsIgnoreCase(bookList.get(i).title)) {
                    bID = bookList.get(i).bookID;
                    bQuantity = bookList.get(i).quantity;
                    foundBook = true;
                    break;
                } else if (i == bookList.size() - 1 && !bookTitle.equalsIgnoreCase(bookList.get(i).title)) {
                    System.out.println("****************************************");
                    System.out.println("The book you've entered doesn't exist");
                    System.out.println("****************************************");

                }
            }
            for (Member aMemberList : memberList) {
                if (first_name.equalsIgnoreCase(aMemberList.firstName) && last_name.equalsIgnoreCase(aMemberList.lastName)) {
                    mID = aMemberList.memberID;
                }
            }
            int number_on_loan = 0;
            for (BookLoans aLoansList : loansList) {
                if (mID == aLoansList.memberID) {
                    x = x + 1;
                }
                if (bID == aLoansList.bookID) {
                    number_on_loan = number_on_loan + 1;
                }
            }
            int available_copy = bQuantity - number_on_loan;
            if (foundBook) {
                if (available_copy == 0) {
                    System.out.println("****************************************");
                    System.out.println("No copies available to take on loan");
                    System.out.println("****************************************");
                } else {
                    isacValidInput = true;
                }

                if (x < 5) {
                    isValidInput = true;
                } else {
                    System.out.println("********************************************************");
                    System.out.println("You already have 5 books out, can't take out more");
                    System.out.println("********************************************************");
                }
            }


            if (isValidInput && isacValidInput) {
                int a = loansList.size() - 1;
                BookLoans b = loansList.get(a);
                int c = b.bookLoanID + 1;
                BookLoans object = new BookLoans(c, bID, mID, LocalDate.now());
                loansList.add(object);
                System.out.println("****************************************");
                System.out.println("Your book has been loaned");
                System.out.println("****************************************");
            }
        }
    }


    public void returnBook()
    {
        System.out.print("Enter your book loan ID: ");
        int bookLoanID = input.nextInt();
        returnBook(bookLoanID);
    }
    public void returnBook(int bID)
    {
        for (int i = 0; i < loansList.size(); i++) {
            if (bID == loansList.get(i).bookLoanID) {
                LocalDate dueDate = loansList.get(i).dateBorrowed.plusDays(30);
                if ((dueDate == LocalDate.now()) || (dueDate.isAfter(LocalDate.now()))) {
                    loansList.remove(i);
                    System.out.println("****************************************");
                    System.out.println("Your book is returned");
                    System.out.println("****************************************");
                    break;
                    //Add quantity to book list?
                } else {
                    long daysBetween = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
                    //What data type should fine be?
                    double fine = (int) daysBetween * 0.1;
                    String answer;
                    String pound = ("\u00A3");
                    do {
                        System.out.println("Your book is overdue. You must pay the fine of " + pound  + fine + ". Would you like to pay and return the book? (y/n)");
                        answer = input.next();
                    } while (!answer.equalsIgnoreCase("N") && !answer.equalsIgnoreCase( "Y"));
                    if (answer.equalsIgnoreCase("Y")){
                        loansList.remove(i);
                        System.out.println("****************************************");
                        System.out.println("Your book is returned");
                        System.out.println("****************************************");
                        break;
                    }else if(answer.equalsIgnoreCase(("N")));
                    {
                        break;
                    }
                }
            }else if (i == loansList.size() - 1 && bID != loansList.get(i).bookLoanID){
                System.out.println("****************************************");
                System.out.println("The book loan ID doesn't exist");
                System.out.println("****************************************");
                    break;
            }
        }
    }
    public void addNewBook()
    {
        String answer;
        boolean isFound = false;

        System.out.println("Enter the book title:");
        String bTitle = input.nextLine();
        for (Book aBookList : bookList) {
            if (bTitle.equalsIgnoreCase(aBookList.title)) {
                isFound = true;
                System.out.println("*****************************************************************");
                System.out.println("This book already exists. Would you like to continue? [Y/N]");
                System.out.println("*****************************************************************");
                answer = input.next();
                input.nextLine();

                if (answer.equalsIgnoreCase("Y")) {
                    isFound = false;
                    break;
                }
            }
        }
        if (!isFound){
            System.out.println("Enter the book author: (For multiple authors, separate using a colon ':')");
            String bAuthor = input.nextLine();
            String[] bAuthorList = bAuthor.split(":");
            System.out.println("Enter the year of publishing:");
            int year = input.nextInt();
            input.nextLine();
            System.out.println("Enter the quantity of books added:");
            int bQuantity = input.nextInt();
            input.nextLine();
            addNewBook(bTitle, bAuthorList, year, bQuantity);
        }
    }
    public void addNewBook(String bTitle, String[] bAuthorList, int year, int bQuantity)
    {


            int x = bookList.size() - 1;
            Book y = bookList.get(x);
            int z = y.bookID + 1;

            Book object = new Book(z, bTitle, bAuthorList, year, bQuantity);
            bookList.add(object);
            System.out.println("****************************************");
            System.out.println("The book has been added.");
            System.out.println("****************************************");
            showAllBooks();

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
        System.out.println("****************************************");
        System.out.println("The member has been added.");
        System.out.println("****************************************");
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
            if (bookList.get(i).title.toLowerCase().contains(bTitle.toLowerCase()))
            {
                int number_of_copies = bookList.get(i).quantity;
                int ID = bookList.get(i).bookID;
                int number_on_loan = 0;

                for (BookLoans aLoansList : loansList) {
                    if (ID == aLoansList.bookID) {
                        number_on_loan += 1;
                    }
                }
                available_copy = number_of_copies - number_on_loan;
                //int newStock = available_copy + quantity;
                if (bQuantity < 0 )
                {
                    if (available_copy >= Math.abs(bQuantity))
                    {
                        bookList.get(i).quantity = number_of_copies + bQuantity;
                        System.out.println("****************************************");
                        System.out.println("The quantity has been updated");
                        System.out.println("****************************************");
                        break;

                    } else{
                        System.out.println("********************************************************************************************************");
                        System.out.println("Invalid. The quantity you want to decrease the stock by is more than the available number of copies");
                        System.out.println("********************************************************************************************************");
                        break;

                    }
                } else {
                    bookList.get(i).quantity += bQuantity;
                    System.out.println("****************************************");
                    System.out.println("The quantity has been updated");
                    System.out.println("****************************************");
                    break;
                }
            }else if ((!bookList.get(i).title.toLowerCase().contains(bTitle.toLowerCase())) && (i == bookList.size() -1))
            {
                System.out.println("****************************************");
                System.out.println("No such book exists");
                System.out.println("****************************************");
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

            for (Book x : bookList) {
                w.write(x.bookID + "," + x.title + ",");
                for (int a = 0; a < x.author.length; a++) {
                    w.write(x.author[a]);
                    if (a != x.author.length - 1) {
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

            for (Member y : memberList) {
                w.write(y.memberID + "," + y.firstName + "," + y.lastName + "," + y.dateJoined);
                w.write("\n");
            }
            w.close();

            //Book Loans
            info = new File(bookLoans);
            os = new FileOutputStream(info);
            osw = new OutputStreamWriter(os);

            w = new BufferedWriter(osw);

            for (BookLoans z : loansList) {
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
