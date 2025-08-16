package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The AddCustomerWindow class provides a GUI for adding a new customer to the Flight Booking System.
 * It allows users to input customer details including name, phone, email, and password.
 */
public class AddCustomerWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField nameField = new JTextField(20);
    private JTextField phoneField = new JTextField(15);
    private JTextField emailField = new JTextField(30);
    private JPasswordField passwordField = new JPasswordField(20);

    /**
     * Constructs an AddCustomerWindow and initializes the GUI components.
     * 
     * @param mw The main window of the Flight Booking System application.
     */
    public AddCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the GUI components and layout of the AddCustomerWindow.
     */
    private void initialize() {
        setTitle("Add New Customer");
        setSize(400, 250);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        panel.add(new JLabel("")); // Empty label for layout alignment
        panel.add(addBtn);
        
        getContentPane().add(panel);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Handles the action event when the "Add" button is clicked.
     * Captures the input data and attempts to add a new customer.
     * 
     * @param e The ActionEvent triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            // Create and execute the AddCustomer command
            AddCustomer addCmd = new AddCustomer(name, phone, email, password);
            addCmd.execute(mw.getFlightBookingSystem());
            
            // Refresh the customer list and close this window
            mw.displayAllCustomers();
            this.dispose();
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
