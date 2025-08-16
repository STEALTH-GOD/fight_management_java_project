package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.UpdateCustomer;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code UpdateCustomerWindow} class provides a GUI for updating customer details.
 * Users can enter a customer ID and update their name, phone, email, and password.
 */
public class UpdateCustomerWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField custIdField = new JTextField(10);
    private JTextField nameField = new JTextField(20);
    private JTextField phoneField = new JTextField(15);
    private JTextField emailField = new JTextField(30);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton updateButton = new JButton("Update Customer");

    /**
     * Constructs an {@code UpdateCustomerWindow} associated with the given {@code MainWindow}.
     *
     * @param mw the main application window
     */
    public UpdateCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the GUI components and layout.
     */
    private void initialize() {
        setTitle("Update Customer");
        setSize(400, 250);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("Customer ID:"));
        panel.add(custIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(new JLabel("")); // Empty label for spacing
        panel.add(updateButton);

        updateButton.addActionListener(this);
        getContentPane().add(panel);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Handles the action event triggered by clicking the "Update Customer" button.
     * Attempts to update the customer details based on user input.
     *
     * @param e the action event triggered by the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int custId = Integer.parseInt(custIdField.getText());
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Execute update command
            UpdateCustomer updateCmd = new UpdateCustomer(custId, name, phone, email, password);
            updateCmd.execute(mw.getFlightBookingSystem());

            JOptionPane.showMessageDialog(this, "Customer updated successfully.");
            mw.displayAllCustomers();
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Customer ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
