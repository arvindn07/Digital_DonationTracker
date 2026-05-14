package gui;

import java.awt.*;
import javax.swing.*;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        setTitle("Digital Donation Tracker");
        setSize(750, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        setLayout(new BorderLayout());

        // ===== LEFT SIDEBAR (Navigation) =====
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(new Color(245, 245, 245));

        JButton addDonorButton = createSidebarButton("👤  Add Donor");
        JButton addDonationButton = createSidebarButton("💰  Add Donation");
        JButton viewDonorsButton = createSidebarButton("📋  View Donors");
        JButton viewDonationsButton = createSidebarButton("📦  View Donations");
        JButton reportsButton = createSidebarButton("📊  Reports");
        JButton exitButton = createSidebarButton("⏻  Exit");

        sidebar.add(addDonorButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(addDonationButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(viewDonorsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(viewDonationsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(reportsButton);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(exitButton);
        sidebar.add(Box.createVerticalGlue());

        add(sidebar, BorderLayout.WEST);

        // ===== MAIN PANEL (Info / Welcome) =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Digital Donation Tracker");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        JLabel subtitleLabel = new JLabel("Track donors, donations, and reports in one place.");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.DARK_GRAY);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitleLabel);

        JTextArea infoArea = new JTextArea(
                "Quick tips:\n" +
                "• Use the left menu to add donors and donations.\n" +
                "• Use \"View Donors\" / \"View Donations\" to see and search records.\n" +
                "• Use \"Reports\" to see a clear breakdown of total donations.\n\n" +
                "This interface is designed to grow with your project as you add more features."
        );
        infoArea.setEditable(false);
        infoArea.setOpaque(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoArea.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(infoArea, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // ===== Action Listeners =====
        addDonorButton.addActionListener(e -> {
            AddDonorForm form = new AddDonorForm();
            form.setVisible(true);
        });

        addDonationButton.addActionListener(e -> {
            AddDonationForm form = new AddDonationForm();
            form.setVisible(true);
        });

        viewDonorsButton.addActionListener(e -> {
            ViewDonors view = new ViewDonors();
            view.setVisible(true);
        });

        viewDonationsButton.addActionListener(e -> {
            ViewDonations view = new ViewDonations();
            view.setVisible(true);
        });

        reportsButton.addActionListener(e -> {
            Reports reports = new Reports();
            reports.setVisible(true);
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }
}
