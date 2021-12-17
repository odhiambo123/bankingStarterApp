package bank;

/* File:    SilverCustomer.java
 * Class:   SilverCustomer
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: This is the Silver Customer.  This customer has
 *          a loan to draw against for overdrafts.
 */

import java.util.Calendar;
import java.io.Serializable;

public class SilverCustomer extends Customer implements Serializable {
    private float _maxLoanAmount;
    private float _loanAmount;

    public SilverCustomer (Name name, Calendar dateOfBirth,
        float maxLoanAmount) {
        super(name, dateOfBirth);
        _maxLoanAmount = maxLoanAmount;
        _loanAmount = 0.0F;
    }

    public SilverCustomer (Customer customer,float maxLoanAmount) {
        super(customer);
        _maxLoanAmount = maxLoanAmount;
        _loanAmount = 0.0F;
    }

    public void processOverdraft(float amount)
        throws InsufficentFundsException {
        if ((_loanAmount + amount) > _maxLoanAmount) {
            throw new InsufficentFundsException(Account.STANDARD_CHARGE);
        }
        else
            _loanAmount += amount;
    }

    public void setMaxLoanAmount(float amount) {
        _maxLoanAmount = amount;
    }

    public float getLoanAmount() {
        return _loanAmount;
    }

    public void payLoan(float amount) {
        _loanAmount -= amount;
    }
}