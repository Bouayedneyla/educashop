package cart;
public class SecureCheckoutPage{
// --- Payment Method Selection ---
private JRadioButton creditCardRadio;
private JRadioButton payPalRadio;
private JRadioButton bankTransferRadio;
private ButtonGroup paymentMethodGroup;

// --- Card Details Form (Page 10) ---
private JTextField cardNumberField;
private JTextField expireDateField;
private JTextField cardholderNameField;
// You'll also need a JPasswordField for the CVV (not in mockup but standard)

private JButton proceedToPaymentButton;

// --- Order Summary Data (to display) ---
private double subtotal;
private double tax;
private double total; 
}
