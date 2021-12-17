import bank.*;
import java.util.GregorianCalendar;
import java.util.Collection;

public class TestBank {
    public static void main(String[] args) {
        Bank bank = null;
        try {
            bank = Bank.getBank();
        //No customer, so this should throw an exception
        Account acct = new CheckingAccount(
            new Name("John", "Doe"), 2800.00F);
        } catch (Exception  e) {
            e.printStackTrace();
        }

        try {
            //First create the customer, and then add the account;
            bank.addCustomer(new BlueCustomer(new Name("John", "Doe"),
                new GregorianCalendar(1985, 3, 13)));
            Account acct = new CheckingAccount(
                new Name("John", "Doe"), 2800.00F);
            bank.addAccount(acct);

            System.out.println("Customer = " + bank.getCustomer(
                new Name("John", "Doe")));
            System.out.println("Account = " + bank.getAccount(
                acct.getAccountNumber()));

            // See if John Doe has the account.

            //Now try to process a check.  The first should clear, the
            //second should throw an exception.
            if (acct instanceof CheckingAccount) {
                CheckingAccount cacct = (CheckingAccount)acct;
                cacct.processCheck(1500.00F);
                System.out.println("Account = " + bank.getAccount(
                    cacct.getAccountNumber()));
                cacct.processCheck(1500.00F);
            }

        } catch (Exception  e) {
            e.printStackTrace();
            // Note account now has $25.00 fee taken out.
            System.out.println("Account = " + bank.getAccount(0));
        }

        try {
            // Now do Silver and Gold customers.  These should not throw
            // Exceptions
            bank.addCustomer(new SilverCustomer(new Name("Jane", "Doe"),
                new GregorianCalendar(1986, 5, 2), 500.00F));
            CheckingAccount acct1 = new CheckingAccount(
                new Name("Jane", "Doe"), 1500.00F);
            bank.addAccount(acct1);
            acct1.processCheck(1600.00F);
            System.out.println("Account = " + bank.getAccount(
                acct1.getAccountNumber()));


            bank.addCustomer(new GoldCustomer(new Name("Joe", "Doe"),
                new GregorianCalendar(1986, 5, 2), 500.00F));
            CheckingAccount acct2 = new CheckingAccount(
                new Name("Joe", "Doe"), 1500.00F);
            bank.addAccount(acct2);
            acct2.processCheck(1600.00F);
            System.out.println("Account = " + bank.getAccount(
                acct2.getAccountNumber()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Now test the Savings Accounts
            SavingsAccount acct3 = new SavingsAccount(
                new Name("John", "Doe"), 1500.00F);
            bank.addAccount(acct3);
            acct3.processWithdrawal(500.00F);
            System.out.println("Account = " + bank.getAccount(
                acct3.getAccountNumber()));
            acct3.processWithdrawal(1250.00F);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Account = " + bank.getAccount(3));
        }
        // Note account still has 1000.00 in it.
        try {
            bank.saveBank("tmp.bnk");
            bank.restoreBank("tmp.bnk");
            System.out.println("Accounts");
            Collection<Account> accounts = bank.getAccounts();
            for (Account a : accounts) {
                System.out.println(a);
            }

            System.out.println("Customers");
            Collection<Customer> customers = bank.getCustomers();
            for (Customer c : customers)
                System.out.println(c);

            System.out.println(accounts);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}