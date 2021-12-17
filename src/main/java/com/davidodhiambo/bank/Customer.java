package bank;

/* File:    Customer.java
 * Class:   Customer
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: Implements the base Customer behavior.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Calendar;
import java.io.Serializable;

public abstract class Customer implements Serializable {
    private Name _name;
    private Calendar _dateOfBirth;

    ArrayList<Account> _accounts = new ArrayList<Account>();

    public Customer(Name name, Calendar dateOfBirth) {
        _name = name;
        _dateOfBirth = dateOfBirth;
    }

    public Customer(Customer customer) {
        _name = customer._name;
        _dateOfBirth = customer._dateOfBirth;
        _accounts = customer._accounts;
    }

    /* Method to retrieve name. */
    public Name getName() {
        return _name;
    }

    public String getDateOfBirth() {
        return _dateOfBirth.get(Calendar.MONTH) + "/" +
               + _dateOfBirth.get(Calendar.DAY_OF_MONTH) + "/" +
               + _dateOfBirth.get(Calendar.YEAR) + "\n";

    }

    public Collection<Account> getAccounts() {
        return _accounts;
    }

    public void addAccount(Account account) {
        _accounts.add(account);
    }

    @Override
    public String toString() {
        return _name + " was born on " +
            + _dateOfBirth.get(Calendar.MONTH) + "/" +
            + _dateOfBirth.get(Calendar.DAY_OF_MONTH) + "/" +
            + _dateOfBirth.get(Calendar.YEAR) + "\n";
    }

    public abstract void processOverdraft(float amount) throws
        InsufficentFundsException;
}