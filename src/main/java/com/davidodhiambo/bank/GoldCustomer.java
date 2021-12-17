package bank;

/* File:    GoldCustomer.java
 * Class:   GoldCustomer
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: This is the Gold Customer.  This customer has
 *          a loan to draw against for overdrafts, and gets
 *          3 overdrafts for free.
 */

import java.util.Calendar;
import java.io.Serializable;

public class GoldCustomer extends Customer implements Serializable {
    private static int MAX_OVERDRAFTS = 3;

    private float _maxLoanAmount;
    private float _loanAmount;
    private int _numberOfOverdrafts;

    public GoldCustomer (Name name, Calendar dateOfBirth,
        float maxLoanAmount) {
        super(name, dateOfBirth);
        _maxLoanAmount = maxLoanAmount;
        _loanAmount = 0.0F;
    }

    public GoldCustomer(Customer customer, float maxLoanAmount) {
        super(customer);
        _maxLoanAmount = maxLoanAmount;
        _loanAmount = 0.0F;
    }

    public void processOverdraft(float amount)
        throws InsufficentFundsException {
        if ((_loanAmount + amount) > _maxLoanAmount) {
            if (++_numberOfOverdrafts > MAX_OVERDRAFTS)
                throw new InsufficentFundsException(Account.STANDARD_CHARGE);
            else
                throw new InsufficentFundsException(0.00F);
        }

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