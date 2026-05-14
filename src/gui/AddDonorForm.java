package gui;

import models.DataHandler;
import models.Donor;
import javax.swing.*;
import java.awt.*;

public class AddDonorForm extends JFrame {
    private JTextField nameField, contactField, emailField, addressField;

    public AddDonorForm() {
        setTitle("Add Donor");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setToolTipText("Full name of the donor");

        JLabel contactLabel = new JLabel("Contact:");
        contactField = new JTextField(20);
        contactField.setToolTipText("Phone number or mobile number");

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setToolTipText("Email address of the donor");

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setToolTipText("City / area / full address");

        JButton submitButton = new JButton("Save Donor");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(new JLabel());  // Empty for spacing
        formPanel.add(submitButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String contact = contactField.getText().trim();
            String email = emailField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || contact.isEmpty() || email.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(
                        AddDonorForm.this,
                        "All fields are required!",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Donor donor = new Donor(name, contact, email, address);  // Create Donor object
            DataHandler.saveDonor(donor);  // Save via DataHandler
            JOptionPane.showMessageDialog(AddDonorForm.this, "Donor added successfully!");
            dispose();  // Close the form
        });
    }
}
