package computersecurityprogramingproject;

import java.util.Scanner;

/**
 *
 * @author Ryan Richards, Chris Ashmore
 */
public class PasswordStrength_Part3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Get password for password strength test
        System.out.println("Enter a password to evaluate password strength: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        
        //Read dictionary.txt file
        
        //Check for weak password (if matches dictionary word exactly)
            
            //If match, stop check and output it is a weak password
        
            //If no match, check for moderate password (if contains dictionary word as substring with numerical characters (0-9) or special characters (@,#,$,%,&))
        
                //If match, stop check and output it is a moderate password
                
                //If no match, output as strong password (if does not contain any dictionary word as a substring)
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
