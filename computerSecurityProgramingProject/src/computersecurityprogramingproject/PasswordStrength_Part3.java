package computersecurityprogramingproject;

import static computersecurityprogramingproject.JavaMD5Hash.md5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Ryan Richards, Chris Ashmore
 */
public class PasswordStrength_Part3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        //Get password for password strength test
        System.out.println("Enter a password to evaluate password strength: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        //Declare variables
        String status = null;

        Scanner readDictionary = new Scanner(new File("dictionary.txt"));
        readDictionary.useDelimiter("\r\n");
        while (readDictionary.hasNext()) {
            //Import content
            String dictionaryPassword = readDictionary.next();
            //Strip line terminator, referenced https://stackoverflow.com/questions/2163045/how-to-remove-line-breaks-from-a-file-in-java/2163204 to learn how to remove line terminator
            dictionaryPassword = dictionaryPassword.replace("\n", "").replace("\r", "");
            //Check for weak password
            if (dictionaryPassword.equals(password)) {
                status = "PasswordStrengthWeak";
                System.out.println("Password strength is weak, matches dictionary word exactly");
                break;
            }
        }

        //If not weak password, check if moderate password
        if (status == null) {
            Scanner readDictionary2 = new Scanner(new File("dictionary.txt"));
            readDictionary2.useDelimiter("\r\n");
            while (readDictionary2.hasNext()) {
                //Import content
                String dictionaryPassword = readDictionary2.next();
                //Strip line terminator, referenced https://stackoverflow.com/questions/2163045/how-to-remove-line-breaks-from-a-file-in-java/2163204 to learn how to remove line terminator
                dictionaryPassword = dictionaryPassword.replace("\n", "").replace("\r", "");

                //Referenced https://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case for how to check for substring
                if (password.toLowerCase().contains(dictionaryPassword.toLowerCase())) {
                    status = "PasswordStrengthModerate";
                    System.out.println("Password strength is moderate, contains dictionary word as a substring");
                    break;
                }
            }

        }
        //If not weak or moderate password, then it is a strong password
        if (status == null) {
            status = "PasswordStrengthStrong";
            System.out.println("Password strength is strong, does not contain any dictionary words");
        }

    }
}

/*
Part 3: Evaluating Password Strength

The third and final component of the project evaluates the strength of a password and classifies a given
password string as a strong, moderate or weak password based on their vulnerability to dictionary attack.

Weak password: Any password string that exactly matches a dictionary word is classified as a weak
password. For example, “authentic” is a weak password.

Moderate password: A moderate password string does not exactly match a dictionary word but contains a
dictionary word as a substring of the password string. For example, $authentic%4 is a moderate
password.

Strong password: A strong password does not contain any dictionary words as part of its substring. For
example, “gubdk$sj&nf@ds#kj%df$hs” is a strong password.

Your password evaluation program needs to take a password string as an input and try to check if it
matches a dictionary word. In that case the password string is classified as a weak password. If no
dictionary word directly matches the string, the program should check if the dictionary word is a substring
of the password string. In that case, the password is classified as a moderate password. If no dictionary
word matches even a substring of the password string, then the password is a strong password.
 */
