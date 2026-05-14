package gui;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import javax.swing.*;
import models.DataHandler;

public class Reports extends JFrame {
    private JTextArea reportArea;

    public Reports() {
        setTitle("Reports");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel headerLabel = new JLabel("Donation Summary Report");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        headerPanel.add(headerLabel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Report area
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        reportArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        JButton exportButton = new JButton("Export as Text");
        JButton closeButton = new JButton("Close");
        bottomPanel.add(exportButton);
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Build report content
        buildAndShowReport();

        // Actions
        exportButton.addActionListener(e -> exportReport());
        closeButton.addActionListener(e -> dispose());
    }

    private void buildAndShowReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("DIGITAL DONATION TRACKER - SUMMARY REPORT\n");
        sb.append("=========================================\n\n");

        int totalDonors = DataHandler.getTotalDonors();
        int totalQuantity = DataHandler.getTotalDonationQuantity();
        Map<String, Integer> breakdown = DataHandler.getDonationBreakdownByType();

        sb.append(String.format("Total Donors           : %d%n", totalDonors));
        sb.append(String.format("Total Donation Quantity: %d%n%n", totalQuantity));

        sb.append("Donation Breakdown by Type:\n");

        if (breakdown.isEmpty()) {
            sb.append("    No donations yet.\n");
        } else {
            int maxKeyLen = 0;
            for (String key : breakdown.keySet()) {
                if (key != null && key.length() > maxKeyLen) {
                    maxKeyLen = key.length();
                }
            }

            for (Map.Entry<String, Integer> entry : breakdown.entrySet()) {
                String type = entry.getKey();
                int qty = entry.getValue();
                sb.append("    ")
                  .append(String.format("%-" + maxKeyLen + "s", type))
                  .append(" : ")
                  .append(qty)
                  .append("\n");
            }
        }

        sb.append("\n");
        sb.append("Tip: Use \"View Donations\" to see all individual donation records.\n");

        reportArea.setText(sb.toString());
        reportArea.setCaretPosition(0);
    }

    private void exportReport() {
        String reportText = reportArea.getText();

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Report");
        chooser.setSelectedFile(new File("donation_report.txt"));

        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(reportText);
                JOptionPane.showMessageDialog(
                        this,
                        "Report saved to:\n" + file.getAbsolutePath(),
                        "Export Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Failed to save report:\n" + ex.getMessage(),
                        "Export Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
