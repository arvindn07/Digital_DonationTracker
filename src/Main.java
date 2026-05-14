import gui.HomeScreen;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Use system look & feel for more native, polished UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Launch the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.setVisible(true);  // Show the home screen
        });
    }
}
