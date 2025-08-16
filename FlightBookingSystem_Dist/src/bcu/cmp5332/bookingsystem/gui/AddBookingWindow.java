package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

/**
 * The AddBookingWindow class provides a GUI for creating a new booking in the Flight Booking System.
 * It allows users to input a Customer ID and Flight ID, compute the booking fee, and confirm the booking.
 */
public class AddBookingWindow extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private MainWindow mw;
    private JTextField custIdField = new JTextField(10);
    private JTextField flightIdField = new JTextField(10);
    private JLabel feeLabel = new JLabel("Booking Fee: $0.00");
    private JButton computeFeeButton = new JButton("Compute Fee");
    private JButton bookButton = new JButton("Book Flight");
    private double computedFee = 0.0;
    
    /**
     * Constructs an AddBookingWindow and initializes the GUI components.
     * 
     * @param mw The main window of the Flight Booking System application.
     */
    public AddBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    /**
     * Initializes the GUI components and layout of the AddBookingWindow.
     */
    private void initialize() {
        setTitle("Create New Booking");
        setSize(350, 250);
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("Customer ID:"));
        panel.add(custIdField);
        panel.add(new JLabel("Flight ID:"));
        panel.add(flightIdField);
        panel.add(new JLabel("")); 
        panel.add(computeFeeButton);
        panel.add(new JLabel("Booking Fee:"));
        panel.add(feeLabel);
        panel.add(new JLabel("")); 
        panel.add(bookButton);
        
        // If a customer is logged in, preselect their ID and disable editing.
        if (mw.getLoggedInCustomerId() != null) {
            custIdField.setText(String.valueOf(mw.getLoggedInCustomerId()));
            custIdField.setEditable(false);
        }
        
        computeFeeButton.addActionListener(this);
        bookButton.addActionListener(this);
        
        getContentPane().add(panel);
        setLocationRelativeTo(mw);
        setVisible(true);
    }
    
    /**
     * Handles button click events for computing the fee and creating a booking.
     * 
     * @param e The ActionEvent triggered by a button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == computeFeeButton) {
            computeFee();
        } else if (e.getSource() == bookButton) {
            createBooking();
        }
    }
    
    /**
     * Computes the booking fee based on the entered Flight ID and displays the fee.
     */
    private void computeFee() {
        try {
            int flightId = Integer.parseInt(flightIdField.getText());
            double fee = mw.getFlightBookingSystem().getFlightByID(flightId).calculatePrice(LocalDate.now());
            computedFee = fee;
            feeLabel.setText(String.format("Booking Fee: $%.2f", fee));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Flight ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Creates a new booking using the entered Customer ID and Flight ID.
     * Displays a success message and updates the main window if successful.
     */
    private void createBooking() {
        try {
            int custId = Integer.parseInt(custIdField.getText());
            int flightId = Integer.parseInt(flightIdField.getText());
            AddBooking addCmd = new AddBooking(custId, flightId, LocalDate.now());
            addCmd.execute(mw.getFlightBookingSystem());
            JOptionPane.showMessageDialog(this, "Booking created successfully.\nFee: $" + String.format("%.2f", computedFee));
            mw.displayBookings();
            this.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check IDs.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
