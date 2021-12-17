package bank;

/* File:    AccountAccessException.java
 * Class:   AccountAccessException
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: Implements the Exception to be thrown when a
 *          request to access an account does not work.
 */
import java.io.Serializable;

public class AccountAccessException extends BankException  implements Serializable {
    public AccountAccessException() {
    }

    public AccountAccessException(String message) {
        super(message);
    }
}