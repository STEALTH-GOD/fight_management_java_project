package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * The LoginWindow class provides the GUI for users to log into the Everest Airlines system.
 * It supports both admin and customer logins, verifying credentials and redirecting users accordingly.
 */
public class LoginWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private FlightBookingSystem fbs;
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");
    private JLabel messageLabel = new JLabel("", JLabel.CENTER);

    /**
     * Constructs a LoginWindow and initializes the GUI components.
     * 
     * @param fbs The FlightBookingSystem containing customer data and system logic.
     */
    public LoginWindow(FlightBookingSystem fbs) {
        this.fbs = fbs;
        initialize();
    }

    /**
     * Initializes the GUI components and layout of the LoginWindow.
     * Sets up input fields, labels, buttons, and background styling.
     */
    private void initialize() {
        setTitle("Everest Airlines - Login");
        setSize(800, 400);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#fffffe"));

        // Create and style the navigation bar
        JPanel navBar = new JPanel();
        navBar.setBackground(Color.decode("#94a1b2"));
        navBar.setPreferredSize(new Dimension(getWidth(), 50));
        JLabel navTitle = new JLabel("Welcome to EVEREST Airlines");
        navTitle.setForeground(Color.WHITE);
        navTitle.setFont(new Font("Poppins", Font.BOLD, 28));
        navBar.add(navTitle);
        add(navBar, BorderLayout.NORTH);

        // Create the login form panel
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(Color.decode("#fffffe"));

        JLabel headingLabel = new JLabel("Enter your credentials", JLabel.CENTER);
        headingLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        headingLabel.setForeground(Color.decode("#232323"));
        loginPanel.add(headingLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.decode("#fffffe"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Set up Username Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.decode("#5f6c7b"));
        inputPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));
        usernameField.setPreferredSize(new Dimension(250, 30));
        usernameField.setBorder(new LineBorder(Color.GRAY, 1, true));
        inputPanel.add(usernameField, gbc);

        // Set up Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.decode("#5f6c7b"));
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(250, 30));
        passwordField.setBorder(new LineBorder(Color.GRAY, 1, true));
        inputPanel.add(passwordField, gbc);

        // Set up Login Button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.setFont(new Font("Poppins", Font.BOLD, 18));
        loginButton.setPreferredSize(new Dimension(160, 45));
        loginButton.setBackground(Color.decode("#7f5af0"));
        loginButton.setForeground(Color.WHITE);
        inputPanel.add(loginButton, gbc);
        loginButton.addActionListener(this);

        loginPanel.add(inputPanel, BorderLayout.CENTER);
        loginPanel.add(messageLabel, BorderLayout.SOUTH);
        loginPanel.setPreferredSize(new Dimension(500, 0));

        // Create and add the image panel for the right side
        JPanel imagePanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(0x1E3A5F);
                Color color2 = new Color(0x3A607F);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };

        File imageFile = new File("resources/icons/login_bg.png");
        if (imageFile.exists()) {
            ImageIcon originalIcon = new ImageIcon(imageFile.getAbsolutePath());
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 320, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } else {
            JLabel errorLabel = new JLabel("Image not found", SwingConstants.CENTER);
            imagePanel.add(errorLabel, BorderLayout.CENTER);
        }

        add(loginPanel, BorderLayout.WEST);
        add(imagePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Handles action events triggered by the "Login" button.
     * It validates the user's credentials and redirects to the appropriate main window.
     * 
     * @param e The ActionEvent triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Admin login check (hard-coded credentials)
        if (username.equals("admin") && password.equals("admin")) {
            this.setVisible(false);
            SwingUtilities.invokeLater(() -> {
                MainWindow mainWindow = new MainWindow(fbs);
                mainWindow.setAdminMode(true);
            });
        } else {
            Customer customer = authenticateCustomer(username, password);
            if (customer != null) {
                this.setVisible(false);
                SwingUtilities.invokeLater(() -> {
                    MainWindow mainWindow = new MainWindow(fbs);
                    mainWindow.setAdminMode(false);
                    mainWindow.setLoggedInCustomerId(customer.getId());
                    mainWindow.displayCustomerDetails(customer.getId());
                });
            } else {
                messageLabel.setText("Invalid credentials. Try again.");
            }
        }
    }

    /**
     * Authenticates a customer by checking the username and password.
     * 
     * @param username The entered username.
     * @param password The entered password.
     * @return The authenticated Customer object if valid, otherwise null.
     */
    private Customer authenticateCustomer(String username, String password) {
        for (Customer customer : fbs.getCustomers()) {
            if (customer.getName().equalsIgnoreCase(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }
}
