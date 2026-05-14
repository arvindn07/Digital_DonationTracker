package gui;

import java.awt.*;
import javax.swing.*;
import models.DataHandler;
import models.Donation;

public class AddDonationForm extends JFrame {
    private JTextField donorIdField, ngoNameField, typeField, quantityField, dateField;

    public AddDonationForm() {
        setTitle("Add Donation");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel donorIdLabel = new JLabel("Donor ID (e.g., D1):");
        donorIdField = new JTextField(20);
        donorIdField.setToolTipText("Enter an existing donor ID like D1, D2, etc.");

        JLabel ngoNameLabel = new JLabel("NGO Name:");
        ngoNameField = new JTextField(20);
        ngoNameField.setToolTipText("Name of the NGO receiving the donation");

        JLabel typeLabel = new JLabel("Type (Money/Clothes/Food/Others):");
        typeField = new JTextField(20);
        typeField.setToolTipText("Type of donation, e.g., Money, Clothes, Food");

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(20);
        quantityField.setToolTipText("Total units / amount (must be a number)");

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateField = new JTextField(20);
        dateField.setToolTipText("Donation date, for example 2025-12-01");

        JButton submitButton = new JButton("Save Donation");

        formPanel.add(donorIdLabel);
        formPanel.add(donorIdField);
        formPanel.add(ngoNameLabel);
        formPanel.add(ngoNameField);
        formPanel.add(typeLabel);
        formPanel.add(typeField);
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);
        formPanel.add(dateLabel);
        formPanel.add(dateField);
        formPanel.add(new JLabel());  // Empty for spacing
        formPanel.add(submitButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String donorId = donorIdField.getText().trim();
            String ngoName = ngoNameField.getText().trim();
            String type = typeField.getText().trim();
            String quantityText = quantityField.getText().trim();
            String date = dateField.getText().trim();

            if (donorId.isEmpty() || ngoName.isEmpty() || type.isEmpty() || quantityText.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(
                        AddDonationForm.this,
                        "All fields are required!",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        AddDonationForm.this,
                        "Quantity must be a positive number.",
                        "Invalid Quantity",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Basic date format validation: YYYY-MM-DD
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(
                        AddDonationForm.this,
                        "Date must be in the format YYYY-MM-DD.",
                        "Invalid Date",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Donation donation = new Donation(donorId, ngoName, type, quantity, date);
            DataHandler.saveDonation(donation);
            JOptionPane.showMessageDialog(AddDonationForm.this, "Donation added successfully!");
            dispose();
        });
    }
}
