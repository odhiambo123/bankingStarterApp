import javax.swing.*;
import java.awt.event.*;
import bank.*;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class BankApplication {
    public static void main(String[] args) {
        final Bank bank = Bank.getBank();

        final JFrame jf = new JFrame();
        Container con = jf.getContentPane();
        con.setLayout(new GridLayout(5,1));

        final JDialog customerDialog = createCustomerDialog(jf);
        JButton button = new JButton("Customer Options");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                customerDialog.pack();
                customerDialog.setVisible(true);
            }
        });
        con.add(button);

        final JDialog accountDialog = createAccountDialog(jf);
        button = new JButton("Account Options");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                accountDialog.pack();
                accountDialog.setVisible(true);
            }
        });
        con.add(button);

        button = new JButton("Save Bank");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                   JFileChooser chooser = new JFileChooser();
                   //In response to a button click:
                   int returnVal = chooser.showSaveDialog(jf);
                   if(returnVal == JFileChooser.APPROVE_OPTION) {
                      bank.saveBank(chooser.getSelectedFile().getName());
                    }

                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
        });
        con.add(button);

        button = new JButton("Restore Bank");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    //In response to a button click:
                    int returnVal = chooser.showOpenDialog(jf);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                       bank.restoreBank(chooser.getSelectedFile().getName());
                    }
               } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
        });
        con.add(button);

        button = new JButton("Exit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        con.add(button);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }


    /**
        This method creates the dialog for the customer
    */
    public static JDialog createCustomerDialog(JFrame parent) {
        final JDialog dialog = new JDialog(parent, "Customer", true);
        Container con = dialog.getContentPane();
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(4,2));
        dataPanel.add(new JLabel("First Name"));
        final JTextField firstName = new JTextField();
        dataPanel.add(firstName);
        dataPanel.add(new JLabel("Last Name"));
        final JTextField lastName = new JTextField();
        dataPanel.add(lastName);
        dataPanel.add(new JLabel("Date of Birth"));
        final JTextField dob = new JTextField();
        dataPanel.add(dob);

        dataPanel.add(new JLabel("Customer Type"));
        String[] custStrings = {"Blue Customer", "Silver Customer",
            "Gold Customer"};
        final JComboBox custType = new JComboBox(custStrings);
        custType.setEditable(false);
        dataPanel.add(custType);

        con.add(dataPanel, BorderLayout.NORTH);

        JPanel accountPanel = new JPanel();
        accountPanel.add(new JLabel("Accounts"));
        final JList accounts = new JList(new DefaultListModel());
        JScrollPane jsp = new JScrollPane(accounts);
        accountPanel.add(jsp);
        con.add(accountPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();

        JButton button = new JButton("Add");
        buttonPanel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Name name = new Name(firstName.getText(),
                    lastName.getText());
                Customer cust = Bank.getBank().getCustomer(name);

                if (cust != null) {
                    JOptionPane.showMessageDialog(null, "Customer already exists");
                    return;
                }

                // Note:  it is too much trouble to format the name,
                // and we already showed how to sort with it, so just
                // set it to a constant.

                try {
                    if (custType.getSelectedIndex() == 2)
                        Bank.getBank().addCustomer(new GoldCustomer(
                            name, new GregorianCalendar(1985, 1, 1), 1000.00f));
                    else if (custType.getSelectedIndex() == 1)
                        Bank.getBank().addCustomer(new SilverCustomer(
                            name, new GregorianCalendar(1985, 1, 1), 1000.00f));
                    else
                        Bank.getBank().addCustomer(new BlueCustomer(
                            name, new GregorianCalendar(1985, 1, 1)));
               } catch (CustomerAccessException cae) {
                    JOptionPane.showMessageDialog(null, "Customer already exists");
                    return;
               }
            }
        });

        button = new JButton("Find");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Name name = new Name(firstName.getText(),
                    lastName.getText());
                Customer cust = Bank.getBank().getCustomer(name);

                if (cust == null) {
                    JOptionPane.showMessageDialog(null, "Customer not found");
                    return;
                }

                firstName.setText(cust.getName().getFirstName());
                lastName.setText(cust.getName().getLastName());
                dob.setText(cust.getDateOfBirth());
                if (cust instanceof GoldCustomer)
                    custType.setSelectedIndex(2);
                else if (cust instanceof SilverCustomer)
                    custType.setSelectedIndex(1);
                else
                    custType.setSelectedIndex(0);

                DefaultListModel lm = (DefaultListModel)(accounts.getModel());
                lm.clear();
                for (Account a : cust.getAccounts())
                    lm.addElement(a);

            }
        });
        buttonPanel.add(button );

        button = new JButton("Change");
        buttonPanel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Name name = new Name(firstName.getText(),
                    lastName.getText());
                Customer cust = Bank.getBank().getCustomer(name);

                if (cust == null) {
                    JOptionPane.showMessageDialog(null, "Customer must exist to change them");
                    return;
                }

                // Note:  it is too much trouble to format the name,
                // and we already showed how to sort with it, so just
                // set it to a constant.

                try {
                    if (custType.getSelectedIndex() == 2)
                        Bank.getBank().changeCustomerType(
                            new GoldCustomer(
                            name, new GregorianCalendar(1985, 1, 1), 1000.00f));
                    else if (custType.getSelectedIndex() == 1)
                        Bank.getBank().changeCustomerType(
                            new SilverCustomer(
                            name, new GregorianCalendar(1985, 1, 1), 1000.00f));
                    else
                        Bank.getBank().changeCustomerType(
                            new BlueCustomer(
                            name, new GregorianCalendar(1985, 1, 1)));
               } catch (CustomerAccessException cae) {
                    JOptionPane.showMessageDialog(null, "Customer must exist to change them");
                    return;
               }
            }
        });
        buttonPanel.add(button );

        button = new JButton("Clear");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                firstName.setText("");
                lastName.setText("");
                dob.setText("");
                ((DefaultListModel)(accounts.getModel())).clear();
            }
        });
        buttonPanel.add(button );

        button = new JButton("Done");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dialog.setVisible(false);
            }
        });
        buttonPanel.add(button );

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setLocation(125,0);
        return dialog;
    }

    /**
     * This method creates the dialog for the account.
    */
    public static JDialog createAccountDialog(JFrame parent) {
               final JDialog dialog = new JDialog(parent, "Customer", true);
       Container con = dialog.getContentPane();
       JPanel dataPanel = new JPanel();
       dataPanel.setLayout(new GridLayout(4,2));
       dataPanel.add(new JLabel("Account Number"));
       final JTextField accountNumber = new JTextField();
       dataPanel.add(accountNumber);
       dataPanel.add(new JLabel("Balance"));
       final JTextField balance = new JTextField();
       dataPanel.add(balance);

       dataPanel.add(new JLabel("Customer Name"));
       final JTextField name = new JTextField();
       dataPanel.add(name);

       dataPanel.add(new JLabel("Customer Type"));
       String[] custStrings = {"Blue Customer", "Silver Customer",
           "Gold Customer"};
       final JComboBox custType = new JComboBox(custStrings);
       custType.setEditable(false);
       dataPanel.add(custType);

       con.add(dataPanel, BorderLayout.NORTH);

       JPanel buttonPanel = new JPanel();
       JButton button;

       button = new JButton("Find");
       buttonPanel.add(button);
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
               int acctNo = Integer.parseInt(accountNumber.getText());
               Account acct = Bank.getBank().getAccount(acctNo);

               if (acct == null) {
                   JOptionPane.showMessageDialog(null, "Account not found");
                   return;
               }

               accountNumber.setText("" + acct.getAccountNumber());
               balance.setText("" + acct.getBalance());
               Customer cust = acct.getAccountOwner();
               name.setText(cust.getName().toString());
               if (cust instanceof GoldCustomer)
                   custType.setSelectedIndex(2);
               else if (cust instanceof SilverCustomer)
                   custType.setSelectedIndex(1);
               else
                   custType.setSelectedIndex(0);

           }
       });
       buttonPanel.add(button );

       button = new JButton("Add");
       buttonPanel.add(button);
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
              JOptionPane.showMessageDialog(null, "Option not implemented");
              return;
           }
       });

       button = new JButton("Done");
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
                dialog.setVisible(false);
           }
       });
       buttonPanel.add(button );

       dialog.add(buttonPanel, BorderLayout.SOUTH);

       dialog.setLocation(125,0);
       return dialog;
    }

}