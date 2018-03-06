package library;

import java.time.LocalDate;

/**
 * Write a description of class Member here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Member
{
    int memberID;
    String firstName, lastName;
    LocalDate dateJoined;

    /**
     * Constructor for objects of class Member
     */
    public Member(int member_ID, String first_name, String last_name, LocalDate date)
    {
        memberID = member_ID;
        firstName = first_name;
        lastName = last_name;
        dateJoined = date;
    }
}
