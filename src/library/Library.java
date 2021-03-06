package library;

import java.io.*;
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

/**
 * Library.java
 * Purpose: To emulate a library management application
 *
 * @author 670060628
 * @author 670024613
 * @version 1.0 13/03/2018
 */

/**
 * Scans for input to then be sorted into different lists
 */
public class Library
{
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<BookLoans> loansList = new ArrayList<>();

    /**
     * Imports the CSV files
     * @param books
     * @param members
     * @param bookLoans
     */
    public Library(String books, String members, String bookLoans)
    {
      FileImport(books, "b");
      FileImport(members, "m");
      FileImport(bookLoans, "l");
    }

    /**
     * Creates objects and adds them to their respective lists
     * @param filepath
     * @param type
     */
    private void FileImport(String filepath, String type)
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
     * Prints out the list of all the books
     */
    public void showAllBooks()
    {
     System.out.println("Books:");
     //Looping through the book list
        for (Book x : bookList) {
            System.out.print(x.bookID + "," + x.title + ",");
            //Creates another list for books with multiple authors
            for (int a = 0; a < x.author.length; a++) {
                System.out.print(x.author[a]);
                if (a != x.author.length - 1) {
                    System.out.print(":");
                }
            }
            System.out.print("," + x.yearPublished + "," + x.quantity + "\n");
        }
    }

    /**
     * Prints out the list of all the members
     */
    public void showAllMembers()
    {
     System.out.println("Members:");
        for (Member y : memberList) {
            System.out.println(y.memberID + "," + y.firstName + "," + y.lastName + "," + y.dateJoined);
        }
    }

    /**
     * Prints out the list of all the book loans
     */
    public void showAllBookLoans()
    {
     System.out.println("Book Loans:");
        for (BookLoans z : loansList) {
            System.out.println(z.bookLoanID + "," + z.bookID + "," + z.memberID + "," + z.dateBorrowed);
        }
    }

    /**
     * Asks for input then passes the information to the next method to search for books
     */
    public void searchBook()
    {

        Scanner input = new Scanner(System.in);
     System.out.println("Please input a keyword(s):");
     String bTitle = input.nextLine();
     searchBook(bTitle);

    }

    /**
     * Uses partial matching of the input to search through the book list and outputs matching book information
     * @param bTitle A String representing a book title
     */
    public void searchBook(String bTitle)
    {
        int counter = 0;

        for (Book aBookList : bookList) {
            if (aBookList.title.toLowerCase().contains(bTitle.toLowerCase())) {
                Book a = aBookList;
                System.out.println("****************************************");
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

                int number_on_loan = 0;
                for (BookLoans aLoansList : loansList) {
                    if (a.bookID == aLoansList.bookID) {
                        number_on_loan += 1;
                    }
                }
                int available_copy = a.quantity - number_on_loan;
                System.out.println("Number of Books Available: " + available_copy);
                System.out.println("****************************************");
                counter += 1;
            }
        }
        if (counter == 0)
        {
            System.out.println("****************************************");
            System.out.println("Invalid keyword. Please try again.");
            System.out.println("****************************************");
        }
    }

    /**
     * Asks for input then passes the information to the next method to search for members
     */
    public void searchMember()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a first name and last name:");
        String first_name = input.next();
        String last_name = input.next();
        searchMember(first_name, last_name);

    }

    /**
     * Takes the input given and searches through the member list to output member information
     * @param first_name A String representing the member's first name
     * @param last_name A String representing the member's last name
     */
    public void searchMember(String first_name, String last_name)
    {
        int memberCount = 0;
        int bookCount = 0;
        for (Member aMemberList : memberList) {
            if (aMemberList.firstName.toLowerCase().equals(first_name.toLowerCase()) && aMemberList.lastName.toLowerCase().equals(last_name.toLowerCase())) {
                Member c = aMemberList;
                System.out.println("**********************************************");
                System.out.println("Member information: ");
                System.out.println("**********************************************");
                System.out.println("ID: " + c.memberID);
                System.out.println("Name: " + c.firstName + " " + c.lastName);
                System.out.println("Date Joined: " + c.dateJoined);
                System.out.println("**********************************************");
                System.out.println("**********************************************");
                System.out.println("Book(s) on loan: ");
                System.out.println("**********************************************");
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
                        System.out.println("**********************************************");
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
     * This method asks for user input such as name and keyword.
     * It checks if the member exists and then passes the variables as arguments
     * into the next borrow book method.
     */
    public void borrowBook()
    {
        Scanner input = new Scanner(System.in);
        String lastName = "" ;
        String firstName = "";
        String bookTitle;
        boolean isValidInput = false;
        //This loop makes sure that the member exists
        while (!isValidInput) {
            System.out.println("Enter your first and last name: ");
            firstName = input.next();
            lastName = input.next();
            for (int j = 0; j < memberList.size(); j++) {
                if (firstName.equalsIgnoreCase(memberList.get(j).firstName) && (lastName.equalsIgnoreCase((memberList.get(j).lastName)))) {
                    isValidInput = true;
                    break;
                } else if (j == memberList.size() - 1 && !firstName.equalsIgnoreCase(memberList.get(j).firstName) && (!lastName.equalsIgnoreCase((memberList.get(j).lastName)))) {
                    System.out.println("The member doesn't exist. Please try again.");
                }
            }
        }
        System.out.println("Enter a keyword: ");
        bookTitle = input.next();
        borrowBook(bookTitle, firstName, lastName);
    }

    /**
     * This method takes in arguments from either the user input in borrow book method without parameters
     * or the fixed input values given in the LibraryApp. It checks if the book exists (uses partial searching),
     * if there are available copies and if the member already has 5 books on loan.
     * If all is successful, the book is loaned
     *
     * @param bTitle A String that is entered to allow partial search to take place
     * @param first_name A String representing the member's first name
     * @param last_name A String representing the member's last name
     */
    public void borrowBook(String bTitle, String first_name, String last_name) {
        boolean isacValidInput = false;
        boolean isValidInput = false;
        boolean foundBook = false;
        int mID = 0;
        int x = 0;
        int bID = 0;
        int bQuantity = 0;
        int count = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("****************************************");
        System.out.println("Book(s) found: ");
        System.out.println("****************************************");
        //Loops through the book list
        for (Book aBookList : bookList) {
            //Checks if the keyword is contained in any of the book titles
            if (aBookList.title.toLowerCase().contains(bTitle.toLowerCase())) {
                //Prints out all the books containing the keyword
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
            String bookTitle = input.nextLine();
            //Checks if the book entered exists
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
            //Gets the member ID so that we can check the number of books they have on loan
            for (Member aMemberList : memberList) {
                if (first_name.equalsIgnoreCase(aMemberList.firstName) && last_name.equalsIgnoreCase(aMemberList.lastName)) {
                    mID = aMemberList.memberID;
                }
            }
            int number_on_loan = 0;
            //Loops through the loan list and counts the number of books the member has on loan
            for (BookLoans aLoansList : loansList) {
                if (mID == aLoansList.memberID) {
                    x += 1;
                }
                //Counts the number of books on loan for the title given
                if (bID == aLoansList.bookID) {
                    number_on_loan += 1;
                }
            }
            int available_copy = bQuantity - number_on_loan;
            //If statements check to see if there are available copies
            if (foundBook) {
                if (available_copy == 0) {
                    System.out.println("****************************************");
                    System.out.println("No copies available to take on loan");
                    System.out.println("****************************************");
                } else {
                    isacValidInput = true;
                }
                // Checks to see if the member already has 5 books out
                if (x < 5) {
                    isValidInput = true;
                } else {
                    System.out.println("********************************************************");
                    System.out.println("You already have 5 books out, can't take out more");
                    System.out.println("********************************************************");
                }
            }
            //Book is loaned if all the conditions are okay
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

    /**
     * This method asks for the bookloan ID.
     */
    public void returnBook()
    {
        Scanner input = new Scanner(System.in);
        //Makes sure an integer is inputted
        try {
            System.out.print("Enter your book loan ID: ");
            int bookLoanID = input.nextInt();
            returnBook(bookLoanID);
        }catch(InputMismatchException e){
            System.out.println("***************************************");
            System.out.println("Input entered is not an integer");
            System.out.println("***************************************");
        }

    }

    /**
     * This method checks if the bookloan ID exists and also checks if the book is overdue.
     * If it is, it then calculates the fine due and gives the user a choice of whether to
     * pay the fine or not.
     * @param bID An integer representing the bookloan ID
     */
    public void returnBook(int bID)
    {
        Scanner input = new Scanner(System.in);
        //Loops through the loan list
        for (int i = 0; i < loansList.size(); i++) {
            //Checks to see if the bookloanID given by user matches any in the list
            if (bID == loansList.get(i).bookLoanID) {
                LocalDate dueDate = loansList.get(i).dateBorrowed.plusDays(30);
                //Returns it normally if the current date if before the due date
                if ((dueDate == LocalDate.now()) || (dueDate.isAfter(LocalDate.now()))) {
                    loansList.remove(i);
                    System.out.println("****************************************");
                    System.out.println("Your book is returned");
                    System.out.println("****************************************");
                    break;
                } else {
                    //Calculates fine if book is over due
                    long daysBetween = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
                    float fine = (float) daysBetween * 10/100;
                    String answer;
                    String pound = ("\u00A3");
                    do {
                        System.out.println("Your book is overdue. You must pay the fine of " + pound  + fine + "0, would you like to pay and return the book? (y/n)");
                        answer = input.next();
                    } while (!answer.equalsIgnoreCase("N") && !answer.equalsIgnoreCase( "Y"));
                    //If user wants to pay and return, then it returns the book
                    if (answer.equalsIgnoreCase("Y")) {
                        loansList.remove(i);
                        System.out.println("****************************************");
                        System.out.println("Your book is returned");
                        System.out.println("****************************************");
                        break;
                    }else if(answer.equalsIgnoreCase(("N"))) {
                        break;
                    }
                }
            } else if (i == loansList.size() - 1 && bID != loansList.get(i).bookLoanID) {
                System.out.println("****************************************");
                System.out.println("The book loan ID doesn't exist");
                System.out.println("****************************************");
                break;
            }

        }

    }

    /**
     * Take general book information and checks whether the book title already exists in the system
     * and gives the user the option to continue adding the book or not.
     */
    public void addNewBook()
    {
        Scanner input = new Scanner(System.in);
        String answer;
        boolean isFound = false;
        System.out.println("Enter the book title:");
        String bTitle = input.nextLine();
        //Loops through to check if the book title given already exists
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
        //If user wants to carry on, then it carries on asking other questions
        if (!isFound){
            System.out.println("Enter the book author: (For multiple authors, separate using a colon ':')");
            String bAuthor = input.nextLine();
            String[] bAuthorList = bAuthor.split(":");
            try {
                System.out.println("Enter the year of publishing:");
                int year = input.nextInt();
                input.nextLine();
                System.out.println("Enter the quantity of books added:");
                int bQuantity = input.nextInt();
                input.nextLine();
                addNewBook(bTitle, bAuthorList, year, bQuantity);
            }catch (InputMismatchException e){
                System.out.println("***************************************");
                System.out.println("Input entered is not an integer");
                System.out.println("***************************************");
            }
        }

    }

    /**
     * Adds the new book to the book list
     * @param bTitle A String representing the book title
     * @param bAuthorList A list representing the book authors
     * @param year An integer representing the book publish year
     * @param bQuantity An integer stating the total number of books
     */
    public void addNewBook(String bTitle, String[] bAuthorList, int year, int bQuantity)
    {
            //The book is added
            int x = bookList.size() - 1;
            Book y = bookList.get(x);
            int z = y.bookID + 1;

            Book object = new Book(z, bTitle, bAuthorList, year, bQuantity);
            bookList.add(object);
            System.out.println("****************************************");
            System.out.println("The book has been added.");
            System.out.println("****************************************");

    }

    /**
     * This method takes a user's name as input
     */
    public void addNewMember()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first name:");
        String firstName = input.next();
        System.out.println("Enter your last name:");
        String lastName = input.next();
        //The date is taken the the current date
        addNewMember(firstName, lastName, LocalDate.now());

    }

    /**
     * This method adds the new member to the member list
     * along with the date joined assuming that it's the current date
     * @param firstName A String representing the member's first name
     * @param lastName A String representing the member's last name
     * @param date The current date
     */
    public void addNewMember(String firstName, String lastName, LocalDate date)
    {
        //Calculates the member ID and adds the member
        int x = memberList.size() -1;
        Member y = memberList.get(x);
        int z = y.memberID + 1;
        Member object = new Member (z, firstName, lastName, date);
        memberList.add(object);
        System.out.println("****************************************");
        System.out.println("The member has been added.");
        System.out.println("****************************************");

    }

    /**
     * This method takes the book title and the number of which to increase or decrease the quantity by.
     */
    public void changeQuantity()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the book title: ");
        String bookTitle = input.nextLine();
        try {
            System.out.println("Enter the quantity you want to change ('-' decreasing) ");
            int stockQuan = input.nextInt();
            input.nextLine();
            changeQuantity(bookTitle, stockQuan);
        }catch(InputMismatchException e){
            System.out.println("***************************************");
            System.out.println("Input entered is not an integer");
            System.out.println("***************************************");
        }


    }

    /**
     * This method checks if the book exists and if it does, it checks the number of
     * available copies to make sure that the number the user is decreasing by
     * is not greater than the number of available copies.
     * @param bTitle A String representing the book title
     * @param bQuantity An integer stating the change of total quantity
     */
    public void changeQuantity(String bTitle, int bQuantity)
    {
        int available_copy;
        for (int i = 0; i < bookList.size(); i++)
        {   //Checks if the book title contains the user input
            if (bookList.get(i).title.toLowerCase().contains(bTitle.toLowerCase()))
            {
                int number_of_copies = bookList.get(i).quantity;
                int ID = bookList.get(i).bookID;
                int number_on_loan = 0;

                for (BookLoans aLoansList : loansList) {
                    //Calculates number of the book on loan
                    if (ID == aLoansList.bookID) {
                        number_on_loan += 1;
                    }
                }
                available_copy = number_of_copies - number_on_loan;
                //If the user wants to decrease the book quantity, it has to check available copies
                if (bQuantity < 0 )
                {   //Updates quantity only if there are enough available copies
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
                    //If user wants to increase it, it automatically updates it without any checks
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

    /** This method saves all of the changes made with the files
     *
     * @param books
     * @param members
     * @param bookLoans
     */
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
