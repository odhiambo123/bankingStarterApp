package bank;

/* File:    Bank.java
 * Class:   Bank
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: Implements a Bank Singleton object.
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Bank {
    private static Bank _bank;
    private Map<Name, Customer> customers =
        new HashMap<Name,Customer>();
    private Map<Integer, Account> accounts   =
        new HashMap<Integer, Account>();

    private Bank() {
    }

    private Bank(String fileName) throws IOException,
        ClassNotFoundException {
        this.restoreBank(fileName);
    }

    public synchronized static Bank getBank() {
        if (_bank == null)
            _bank = new Bank();
        return _bank;
    }

    public synchronized static Bank getBank(String fileName)
        throws BankException, IOException,
               ClassNotFoundException {
        if (_bank == null) {
            _bank = new Bank(fileName);
            return _bank;
        }

        throw new BankException(
            "You should call restoreBank to read a file for an existing bank");
    }

    public void addCustomer(Customer customer)
        throws CustomerAccessException {
        if (getCustomer(customer.getName()) != null) {
            throw new CustomerAccessException(
                "Invalid attempt to add an existing customer");
        }

        if (customer != null) {
            customers.put(customer.getName(), customer);
        }
    }

    public Customer getCustomer(Name name) {
       return customers.get(name);
    }

    public void changeCustomerType(Customer newCustomerType)
        throws CustomerAccessException {

        Name name = newCustomerType.getName();
        Customer customer = customers.get(name);
        if (customer != null)
                customers.put(name, newCustomerType);
        else
           throw new CustomerAccessException(
              "Attempt to change a non-existant customer");
    }

    public void addAccount(Account account) throws AccountAccessException {
        Integer accountNumber = account.getAccountNumber();
        if (accounts.get(accountNumber) != null)
            throw new AccountAccessException(
                "Attempt to add an existing account");
        if (account != null) {
            accounts.put(accountNumber, account);
            account.getAccountOwner().addAccount(account);
        }
    }

    public Account getAccount(int accountNumber) {
       return accounts.get(accountNumber);
    }

    public void saveBank(String fileName) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(fileName));
        oos.writeObject(customers);
        oos.writeObject(accounts);
        // Need to save the next account number to get the bank
        // going again.
        oos.writeObject(Account.getNextAccountNumber());
    }

    public void restoreBank(String fileName) throws IOException,
        ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(fileName));
        customers = (Map) ois.readObject();
        accounts = (Map) ois.readObject();
        // Need to save the next account number to get the bank
        // going again.
        Account.setNextAccountNumber((Integer)ois.readObject());
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    public Collection<Customer> getCustomers() {
        return customers.values();
    }

}