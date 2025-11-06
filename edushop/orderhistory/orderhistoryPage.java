package orderhistory;
public class orderhistoryPage{
private JLabel titleLabel; // "Order History"
private JLabel subtitleLabel; // "View and manage your past purchases"

// --- List Panel ---
// A panel to hold multiple "OrderSummary" components
private JPanel pastOrdersPanel; 

// --- Data ---
private List<Order> pastOrders; 
}
