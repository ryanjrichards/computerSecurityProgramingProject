package computersecurityprogramingproject;

import static computersecurityprogramingproject.JavaMD5Hash.md5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ryan Richards, Chris Ashmore
 */
public class PasswordCracker_Part2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        //Get registered username
        System.out.println("Enter the username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        if (username != null && !username.isEmpty()) {
            //Check if registered user
            //Read userData text file, referenced https://stackoverflow.com/questions/14242984/using-delimiter-when-reading-a-file for how to read file
            Scanner readUserData = new Scanner(new File("userData.txt"));
            readUserData.useDelimiter(",");

            //Declare variables
            String importUsername, importPassword;
            String status = null;
            String[] specialCharacters = {"@", "#", "$", "%", "&", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

            //Read through userDataFile
            while (readUserData.hasNext()) {
                //Read variables from userData text file
                importUsername = readUserData.next();
                importPassword = readUserData.next();
                //Check if registered user
                if (username.equals(importUsername)) {
                    status = "userNameSuccess";
                    //Read dictionary file
                    Scanner readDictionary = new Scanner(new File("dictionary.txt"));
                    readDictionary.useDelimiter("\r\n");
                    while (readDictionary.hasNext()) {
                        //Import content
                        String dictionaryPassword = readDictionary.next();
                        //Strip line terminator, referenced https://stackoverflow.com/questions/2163045/how-to-remove-line-breaks-from-a-file-in-java/2163204 to learn how to remove line terminator
                        dictionaryPassword = dictionaryPassword.replace("\n", "").replace("\r", "");
                        //Run encyrption
                        String dictionaryMD5PasswordType1 = md5(dictionaryPassword);
                        //Check if password matches, Type 1 Passwrod
                        if (dictionaryMD5PasswordType1.equals(importPassword)) {
                            status = "Password successfully cracked";
                            System.out.println("Password succesfully cracked. Type 1 Password");
                            System.out.println("User: " + importUsername + ", Password: " + dictionaryPassword);
                            break;
                        }
                        if (status.equals("userNameSuccess")) {
                            //Generate type 2
                            //Referenced https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line for how to create ArrayList 
                            ArrayList<String> dictionaryPasswordType2List = new ArrayList<String>();
                            for (String specialCharacter : specialCharacters) {
                                //Create type2 combination: [word] (1 special character)
                                dictionaryPasswordType2List.add(dictionaryPassword + specialCharacter);
                                for (String specialCharacter1 : specialCharacters) {
                                    //Create type2 combination: [word] (2 special characters)
                                    dictionaryPasswordType2List.add(dictionaryPassword + specialCharacter + specialCharacter1);
                                    //Create type2 combination: (2 special characters) [word]
                                    dictionaryPasswordType2List.add(specialCharacter + specialCharacter1 + dictionaryPassword);
                                    for (String specialCharacter2 : specialCharacters) {
                                        //Create type2 combination: (1 special character) [word] (2 special characters)
                                        dictionaryPasswordType2List.add(specialCharacter + dictionaryPassword + specialCharacter1 + specialCharacter2);
                                    }
                                }
                            }
                            for (int r = 0; r < dictionaryPasswordType2List.size(); r++) {
                                //Run encryption
                                String dictionaryMD5PasswordType2 = md5(dictionaryPasswordType2List.get(r));
                                //Check if match
                                if (dictionaryMD5PasswordType2.equals(importPassword)) {
                                    status = "Password successfully cracked";
                                    System.out.println("Password succesfully cracked. Type 2 Password");
                                    System.out.println("User: " + importUsername + ", Password: " + dictionaryPasswordType2List.get(r));
                                    break;
                                }
                            }
                        }
                    }
                    readDictionary.close();
                }
            }
            readUserData.close();

            //If there was no match, output login failed
            if (status == null) {
                System.out.println("Password crack failed");
            } else if (status.equals("userNameSuccess")) {
                System.out.println("Username exists but password crack failed");
            }
        }
        //STILL TO DO
        //Also must display time taken to crack the given password
    }
}

/*
Part 2: password cracker
The second part of your project should implement a password cracker that cracks a given user’s password
from the message digest information present in the password file. The password cracker uses a dictionary
of English words to aid the dictionary attack.

For example, to crack the password of user “Tom”, the attacker knows that
“973d98ac221d7e433fd7c417aa41027a” is the message digest of user Tom’s password. To facilitate the
dictionary attack, the attacker uses a dictionary that contains a list of common English words.
For your convenience, a small subset of English words is provided as a file called dictionary.txt and it is
attached with this project description. Your password cracker program can use this dictionary to aid the
dictionary attack.

In general, you can assume that there are two kinds of passwords used by the users.

Type 1: the password string is just exactly one of the words present in the dictionary

Type 2: the password string is a combination of a dictionary word, numerical characters 0-9 and special
characters, {@, #, $, %, &}. You can assume that there are no other special characters and no upper case
letters in the password.

For Type 1 passwords, your program should compute the MD5 message digest of each dictionary word in
the dictionary and check if it matches the MD5 message digest of the user in the password file. When a
match is found, the corresponding English word is displayed as the cracked password.

For Type 2 passwords, your program should first generate a set of possible type 2 passwords for a given
English word. For example, for the word, “authentic”, you can create a number of type 2 passwords such
as
$authentic%4
authentic#@
5&authentic
authentic8

Your program should create all possible type 2 passwords for each given English word and for each
possible type 2 password, your program computes the MD5 digest and checks if it matches the MD5
digest present in the password file and thereby knows if the guessed password is correct.

Your password cracker program should also display the time taken to crack the given password.

In addition to the above mentioned components, you are encouraged (but not required) to implement
additional optimizations in the password cracking logic to minimize the password cracking time. Any
optimizations you introduce needs to be clearly documented in your report. Depending on the
effectiveness and complexity of the optimization, you may receive some extra credit for it.
 */
