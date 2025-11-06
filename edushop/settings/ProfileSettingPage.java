package settings;
public class ProfileSettingPage {
// --- Profile Settings ---
private JLabel profilePhotoLabel; // To show the image
private JButton changePhotoButton;
private JTextField fullNameField;
private JTextField emailField;
private JComboBox<String> languageComboBox;

// --- Change Password ---
private JPasswordField currentPasswordField;
private JPasswordField newPasswordField;
private JPasswordField confirmPasswordField; // Not in mockup, but good practice

// --- Actions ---
private JButton saveChangesButton;
private JButton resetButton; 
}
