package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code UpdateBookingWindow} class provides a GUI for updating an existing flight booking.
 * Users can input a booking ID and a new flight ID to update their booking.
 */
public class UpdateBookingWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private MainWindow mw;
    private JTextField bookingIdField = new JTextField(10);
    private JTextField newFlightIdField = new JTextField(10);

    /**
     * Constructs an {@code UpdateBookingWindow} associated with the given {@code MainWindow}.
     *
     * @param mw the main application window
     */
    public UpdateBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initializes the GUI components and layout.
     */
    private void initialize() {
        setTitle("Update Booking");
        setSize(300, 150);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Booking ID:"));
        panel.add(bookingIdField);
        panel.add(new JLabel("New Flight ID:"));
        panel.add(newFlightIdField);

        JButton updateBtn = new JButton("Update Booking");
        updateBtn.addActionListener(this);

        panel.add(new JLabel("")); // Placeholder for alignment
        panel.add(updateBtn);

        getContentPane().add(panel);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Handles the button click event to update a booking.
     *
     * @param e the action event triggered by the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int bookingId = Integer.parseInt(bookingIdField.getText());
            int newFlightId = Integer.parseInt(newFlightIdField.getText());

            // Create and execute the update booking command
            bcu.cmp5332.bookingsystem.commands.UpdateBooking updateCmd = 
                new bcu.cmp5332.bookingsystem.commands.UpdateBooking(bookingId, newFlightId);
            updateCmd.execute(mw.getFlightBookingSystem());

            JOptionPane.showMessageDialog(this, "Booking updated successfully.");
            mw.displayBookings();
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check IDs.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
