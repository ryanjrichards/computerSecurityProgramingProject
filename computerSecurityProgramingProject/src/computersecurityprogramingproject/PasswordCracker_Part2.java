package computersecurityprogramingproject;

/**
 *
 * @author Ryan Richards, Chris Ashmore
 */
public class PasswordCracker_Part2 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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