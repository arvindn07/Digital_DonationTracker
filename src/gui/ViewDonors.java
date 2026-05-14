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
import models.Donor;

public class ViewDonors extends JFrame {
    public ViewDonors() {
        setTitle("View Donors");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Name", "Contact", "Email", "Address"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        List<Donor> donors = DataHandler.getAllDonors();  // Get donors from DataHandler
        for (Donor donor : donors) {
            Object[] row = {donor.getId(), donor.getName(), donor.getContact(), donor.getEmail(), donor.getAddress()};
            model.addRow(row);
        }

        // Search / filter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JTextField searchField = new JTextField(20);
        searchField.setToolTipText("Type to search donors by any field");

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
