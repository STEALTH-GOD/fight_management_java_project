package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The CancelBookingWindow class provides a GUI for canceling a booking in the Flight Booking System.
 * It allows users to input a booking ID and confirms the cancellation with a fee.
 */
public class CancelBookingWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private MainWindow mw;
    private JTextField bookingIdField = new JTextField(10);
    private JButton cancelBtn = new JButton("Cancel Booking");

    /**
     * Constructs a CancelBookingWindow and initializes the GUI components.
     * 
     * @param mw The main window of the Flight Booking System application.
     * @param loggedInCustomerId The ID of the logged-in customer (currently not used in this class).
     */
    public CancelBookingWindow(MainWindow mw, Integer loggedInCustomerId) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the GUI components and layout of the CancelBookingWindow.
     * Sets up the input field for the booking ID and the action button.
     */
    private void initialize() {
        setTitle("Cancel Booking");
        setSize(350, 150);

        // Create the panel with labels and input fields
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Booking ID:"));
        panel.add(bookingIdField);
        panel.add(new JLabel("")); // Empty label for alignment
        panel.add(cancelBtn);

        // Register the action listener for the cancel button
        cancelBtn.addActionListener(this);

        getContentPane().add(panel);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Handles action events triggered by the "Cancel Booking" button.
     * 
     * @param e The ActionEvent triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int bookingId = Integer.parseInt(bookingIdField.getText());
            Booking booking = mw.getFlightBookingSystem().getBookingByID(bookingId);

            // Calculate the cancellation fee as 15% of the booking fee
            double cancellationFee = 0.15 * booking.getBookingFee();

            // Display a confirmation dialog with the cancellation fee
            int confirm = JOptionPane.showConfirmDialog(this,
                "A cancellation fee of $" + String.format("%.2f", cancellationFee) + " will be applied. Confirm cancellation?",
                "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

            // If confirmed, execute the cancellation command
            if (confirm == JOptionPane.YES_OPTION) {
                CancelBooking cancelCmd = new CancelBooking(bookingId, cancellationFee);
                cancelCmd.execute(mw.getFlightBookingSystem());

                // Refresh the booking list and close this window
                mw.displayBookings();
                this.dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for booking ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
