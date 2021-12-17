package bank;

/* File:    BlueCustomer.java
 * Class:   BlueCustomer
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: This is the Blue Customer.  This customer is
 *          always assessed a fee on overdraft.
 */

import java.util.Calendar;
import java.io.Serializable;

public class BlueCustomer extends Customer implements Serializable {
    static final long serialVersionUID = 1;

    public BlueCustomer(Name name, Calendar dateOfBirth) {
        super(name, dateOfBirth);
    }

    public BlueCustomer(Customer customer) {
        super(customer);
    }

    public void processOverdraft(float amount)
        throws InsufficentFundsException {
        throw new InsufficentFundsException(Account.STANDARD_CHARGE);
    }
}