import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set a few UI defaults for readability
        UIManager.put("Label.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        UIManager.put("Button.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        UIManager.put("TextField.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow();
                window.setVisible(true);
            }
        });
    }
}
