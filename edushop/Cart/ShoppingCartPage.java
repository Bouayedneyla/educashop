package cart;
public class ShoppingCartPage{
// This panel will hold all the items in the cart
private JPanel cartItemsPanel; 
// You will add/remove items from this panel

// --- Order Summary (Page 9) ---
private JLabel subtotalLabel;
private JLabel taxLabel;
private JLabel totalLabel;

private JTextField promoCodeField;
private JButton redeemButton;
private JButton proceedToPaymentButton;

// --- Data ---
// A list of items in the cart
private List<OrderItem> items; 
}
