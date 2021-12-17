package bank;

/* File:    SavingsAccount.java
 * Class:   SavingsAccount
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: This class implements the saving account
 *          behavior, which is a special type of account
 *          which allows withdrawals.
 */
import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable {
    public SavingsAccount(Name owner, float amount)
        throws AccountAccessException {
        super(owner, amount);
    }

    public SavingsAccount(Name owner)
        throws AccountAccessException {
        super(owner);
    }

    public void processWithdrawal(float amount)
        throws InsufficentFundsException{
        if ((_balance - amount) < 0.0) {
            throw new InsufficentFundsException(Account.STANDARD_CHARGE);
        }

        _balance -= amount;
    }
}