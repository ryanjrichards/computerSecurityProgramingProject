package computersecurityprogramingproject;

import java.util.Scanner;

/**
 *
 * @author Ryan Richards, Chris Ashmore
 */
public class AuthenticationMechanism_Part1 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //PART 1, Register and add new user
        //Get username and password, referenced https://stackoverflow.com/questions/11871520/how-can-i-read-input-from-the-console-using-the-scanner-class-in-java for refreshed on how to use scanner
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        
        //Put in userData array
        String[] userData = {username,password};
        
        //Write to file
        //FileWriter writer = 
                
        //PART 2, Ask for username and password and verify
    }

}

/*
Part 1: Implementation of password authentication mechanism
In the first part, you need to implement a password authentication mechanism. You will create two
programs. The first program registers and adds a new user into the system and stores the users password
information in a file. The second program is a user login program which asks for the username and
password from the user and verifies it based on the information stored in the password file.

The password file in the system can be a text file that has the list of all usernames and the corresponding
passwords. However, instead of storing the passwords in plain texts, the password list contains the
message digests (hash) of the passwords to prevent attacks. You may use MD5 message digest scheme to
create the message digest (hash) of the password.

For example, if Tom and Harry are two existing users and if their passwords are authentic and
prevention, then the password file will contain:
where 973d98ac221d7e433fd7c417aa41027 is the MD5 message digest of the password string
authentic. Similarly, 7fd9a35dfa69c58a7ef0ecb4d53a1651 is the MD5 message digest of the
password string prevention. A java code snippet for obtaining MD5 message digest is provided at the
end of this project description in Appendix 1 for your reference.

Your first program should be able to register a new user by accepting a new username and password
string and add the corresponding entry to the password file shown above.

Your second program should accept a username and password as input and check if the username is
already a registered user. If so, it computes the MD5 message digest of the entered password and checks
if it matches the MD5 digest of the corresponding user password stored in the password file. The program
accepts the user only if the message digests match.
*/
