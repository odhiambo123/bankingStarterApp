package bank;

/* File:    BankException.java
 * Class:   BankException
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: This is the parent of all exceptions in the
 *          bank package.
 */
import java.io.Serializable;

public class BankException extends Exception  implements Serializable {
    public BankException() {
    }

    public BankException(String message) {
        super(message);
    }
}