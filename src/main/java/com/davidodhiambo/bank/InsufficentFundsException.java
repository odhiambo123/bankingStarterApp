package bank;

/* File:    InsufficentFundsException.java
 * Class:   InsufficentFundsException
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: Implements the Exception to be thrown when a
 *          check is process that exceeds the fund (and
 *          possible loan amount) for that account.  Note
 *          it contains an extra field for the amount to
 *          charge for this overdraft, so requires more
 *          constructors, and a get method to retrieve this
 *          field.  Note that this class is "immutable" in
 *          that once the exception is constructed, the
 *          charge cannot be changed.
 */
import java.io.Serializable;

public class InsufficentFundsException extends BankException {
    final float _charge;

    public InsufficentFundsException() {
        _charge = 0.0F;
    }

    public InsufficentFundsException(float charge) {
        _charge = charge;
    }

    public InsufficentFundsException(String message) {
        super(message);
        _charge = 0.0F;
    }

    public InsufficentFundsException(String message,
        float charge) {

        super(message);
        _charge = charge;
    }

    public float getCharge() {
        return _charge;
    }
}