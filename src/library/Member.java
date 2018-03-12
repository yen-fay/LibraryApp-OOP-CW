package library;

import java.time.LocalDate;

/**
 * Write a description of class Member here.
 *
 * @author 670060628 & 670024613
 * @version 1.0 18/02/2018
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
