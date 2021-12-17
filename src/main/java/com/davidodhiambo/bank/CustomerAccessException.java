package bank;


/* File:    CustomerAccessException.java
 * Class:   CustomerAccessException
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: Implements the Exception to be thrown when a
 *          request to access an customer does not work.
 */
import java.io.Serializable;

public class CustomerAccessException extends BankException implements Serializable {
    public CustomerAccessException() {
    }

    public CustomerAccessException(String message) {
        super(message);
    }
}