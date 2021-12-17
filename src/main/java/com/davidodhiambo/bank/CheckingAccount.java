package bank;

/* File:    CheckingAccount.java
 * Class:   CheckingAccount
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: This class implements the checking account
 *          behavior, which is a special type of account
 *          which processes checks.
 */
import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable {
    public CheckingAccount(Name owner, float balance)
        throws AccountAccessException {
        super(owner, balance);
    }

    public CheckingAccount(Name owner)
        throws AccountAccessException {
        super(owner);
    }

    public void processCheck(float amount)
        throws InsufficentFundsException {
        float overdraftPenalty = 0.0F;

        if ((_balance - amount) < 0) {
            try {
                _accountOwner.processOverdraft(_balance - amount);
            } catch (InsufficentFundsException ife) {
                // If the customer reports that the account is indeed
                // overdrawn, get the penalty from the exception, and
                // then continue to propogate the exception.
                _balance -= ife.getCharge();
                throw ife;
            }
        }

        _balance -= amount;
    }
}