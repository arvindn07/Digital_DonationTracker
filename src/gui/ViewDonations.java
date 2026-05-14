package gui;

import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.DataHandler;
import models.Donation;

public class ViewDonations extends JFrame {
    public ViewDonations() {
        setTitle("View Donations");
        setSize(750, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Donor ID", "NGO Name", "Type", "Quantity", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        List<Donation> donations = DataHandler.getAllDonations();
        for (Donation donation : donations) {
            Object[] row = {
                    donation.getId(),
                    donation.getDonorId(),
                    donation.getNgoName(),
                    donation.getType(),
                    donation.getQuantity(),
                    donation.getDate()
            };
            model.addRow(row);
        }

        // Search / filter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JTextField searchField = new JTextField(20);
        searchField.setToolTipText("Type to search donations by donor, NGO, type, etc.");

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            private void filter() {
                String text = searchField.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) { filter(); }

            @Override
            public void removeUpdate(DocumentEvent e) { filter(); }

            @Override
            public void changedUpdate(DocumentEvent e) { filter(); }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
