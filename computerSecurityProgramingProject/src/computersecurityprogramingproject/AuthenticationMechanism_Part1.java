package computersecurityprogramingproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static computersecurityprogramingproject.JavaMD5Hash.md5;
import java.io.FileNotFoundException;

/**
 *
 * @author Ryan Richards, Chris Ashmore
 */
public class AuthenticationMechanism_Part1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        System.out.println("Enter 1 to register user, enter 2 to login: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equals("1")){
            //PART 1, Register and add new user
            registerUser();
        }
        if(choice.equals("2")){
            //PART 2, Ask for username and password and verify
            verifyUser();  
        }

        
    }

    public static void registerUser() throws IOException {
        //PART 1, Register and add new user
        //Get username and password, referenced https://stackoverflow.com/questions/11871520/how-can-i-read-input-from-the-console-using-the-scanner-class-in-java for refresher on how to use scanner
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        //Check that username and passsword are both provided
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {

            //Convert to md5Password
            String md5Password = md5(password);

            //Write to new file, referenced http://www.baeldung.com/java-write-to-file for file writing
            //Referenced https://howtodoinjava.com/core-java/io/how-to-create-a-new-file-in-java/ to check if file already exists
            File file = new File("userData.txt");
            //Check if file exits
            if (file.createNewFile()) {
                //If file does not yet exist, create and write
                BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt"));
                writer.write(username);
                writer.write(",");
                writer.write(md5Password);

                writer.close();
            } else {
                //If file does exist, append
                BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt", true));
                writer.append(',');
                writer.append(username);
                writer.append(",");
                writer.append(md5Password);

                writer.close();
            }
        } else {
            if (username == null || username.isEmpty() && password == null || password.isEmpty()) {
                System.out.println("Username and password were both empty. Please try again ");
            } else if (username == null || username.isEmpty()) {
                System.out.println("Username was empty. Please try again ");
            } else if (password == null || password.isEmpty()) {
                System.out.println("Password was empty. Please try again ");
            }
        }
    }

    public static void verifyUser() throws FileNotFoundException {
        //PART 2, Ask for username and password and verify
        //Get username and password, referenced https://stackoverflow.com/questions/11871520/how-can-i-read-input-from-the-console-using-the-scanner-class-in-java for refresher on how to use scanner
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            //Apply encryption to password
            String md5Password = md5(password);

            //Read userData text file, referenced https://stackoverflow.com/questions/14242984/using-delimiter-when-reading-a-file for how to read file
            Scanner read = new Scanner(new File("userData.txt"));
            read.useDelimiter(",");

            //Declare variables
            String importUsername, importPassword;
            String status = null;

            while (read.hasNext()) {
                //Read variables from userData text file
                importUsername = read.next();
                importPassword = read.next();
                //Check if registered user
                if (username.equals(importUsername)) {
                    //Check if password matches
                    status = "userNameSuccess";
                    if (md5Password.equals(importPassword)) {
                        status = "Successful";
                        System.out.println("Login successful");
                        break;
                    }
                }
            }
            read.close();
            
            //If there was no match, output login failed
            if(status.equals(null)){
                System.out.println("Login failed");
            }
            else if(status.equals("userNameSuccess")){
                System.out.println("Username exists but password was incorrect");
            }
                
        }
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
