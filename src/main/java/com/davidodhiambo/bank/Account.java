package bank;

/* File:    Account.java
 * Class:   Account
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: Implements the base Account behavior.
 */
import java.io.Serializable;

public abstract class Account implements Serializable {
    public static float STANDARD_CHARGE = 25.00F;

    private int _accountNumber;
    protected float _balance;
    protected Customer _accountOwner;

    private static int _nextAccountNumber = 0;

    public static int getNextAccountNumber() {
        return _nextAccountNumber;
    }

    public static void setNextAccountNumber(int nextAccountNumber) {
        _nextAccountNumber = nextAccountNumber;
    }

    public Account(Name accountOwner, float balance)
        throws AccountAccessException {
        Customer customer = Bank.getBank().getCustomer(accountOwner);
        if (customer == null) {
            throw new AccountAccessException(
                "Owner of the account must be a valid customer");
        }
        _accountNumber = _nextAccountNumber++;
        _accountOwner = customer;
        _balance = balance;
    }

    public Account(Name accountOwner)
        throws AccountAccessException {
        this(accountOwner, 0.00F);
    }

    public int getAccountNumber() {
        return _accountNumber;
    }

    public float getBalance() {
        return _balance;
    }

    public Customer getAccountOwner() {
        return _accountOwner;
    }

    public void deposit(float amount) {
        _balance += amount;
    }

    @Override
    public String toString() {
        return "Account: " + _accountNumber +
               " is owned by " + _accountOwner +
               " and has a balance of " + _balance + "\n";
    }

}