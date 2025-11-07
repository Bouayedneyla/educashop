import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * MainWindow.java
 * Main frame that contains top bar, sidebar and CardLayout content.
 */
public class MainWindow extends JFrame {
    private final CardLayout contentCards = new CardLayout();
    private final JPanel contentPanel = new JPanel(contentCards);
    private SidebarMenu sidebar;

    public MainWindow() {
        super("EduShop — Learn Anywhere, Anytime");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 248));

        add(createTopBar(), BorderLayout.NORTH);

        // Create sidebar and pass a navigation callback (anonymous inner class)
        sidebar = new SidebarMenu(new SidebarMenu.Navigator() {
            @Override
            public void navigate(String sectionName) {
                // show the card named exactly as the section
                showCard(sectionName);
            }
        });

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, createContentPanel());
        split.setDividerLocation(260);
        split.setOneTouchExpandable(true);
        add(split, BorderLayout.CENTER);

        add(createFooter(), BorderLayout.SOUTH);

        // Default selection/show
        sidebar.selectItem("Dashboard");
    }

    private JComponent createTopBar() {
        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setBackground(new Color(30, 41, 59));
        top.setBorder(new EmptyBorder(10, 12, 10, 12));

        JLabel brand = new JLabel("EduShop");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 20));
        brand.setForeground(Color.WHITE);
        brand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        brand.setToolTipText("Return to Dashboard");
        brand.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("Dashboard");
                sidebar.selectItem("Dashboard");
            }
        });
        top.add(brand, BorderLayout.WEST);

        JTextField search = new JTextField();
        search.setToolTipText("Search courses (press Enter)");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = search.getText().trim();
                if (q.isEmpty()) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Please type a search query.", "Search", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainWindow.this, "Search: " + q, "Search", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        top.add(search, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actions.setOpaque(false);
        JButton cartBtn = new JButton("Cart");
        JButton profileBtn = new JButton("Profile");
        JButton settingsBtn = new JButton("Settings");

        cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("Cart");
                sidebar.selectItem("Cart");
            }
        });
        profileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("Profile");
                sidebar.selectItem("Profile");
            }
        });
        settingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("Settings");
                sidebar.selectItem("Settings");
            }
        });

        actions.add(cartBtn);
        actions.add(profileBtn);
        actions.add(settingsBtn);
        top.add(actions, BorderLayout.EAST);

        return top;
    }

    private JComponent createContentPanel() {
        contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        // Add cards — names must exactly match what SidebarMenu passes
        contentPanel.add(makeDashboardPanel(), "Dashboard");
        contentPanel.add(makeCoursesPanel(), "Courses");
        contentPanel.add(makeCartPanel(), "Cart");
        contentPanel.add(makeProfilePanel(), "Profile");
        contentPanel.add(makeSettingsPanel(), "Settings");

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(contentPanel, BorderLayout.CENTER);
        wrapper.setBackground(Color.WHITE);
        return wrapper;
    }

    private JComponent createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(245, 246, 248));
        footer.setBorder(new EmptyBorder(8, 12, 8, 12));
        footer.add(new JLabel("© 2025 EduShop — Empowering digital learning"), BorderLayout.WEST);
        footer.add(new JLabel("Need help? Use Alt+ shortcuts"), BorderLayout.EAST);
        return footer;
    }

    // ---- Content panels ----
    private JPanel basePanel(String title) {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(new EmptyBorder(12, 12, 12, 12));
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(title);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 18f));
        p.add(lbl, BorderLayout.NORTH);
        return p;
    }

    private JPanel makeDashboardPanel() {
        JPanel p = basePanel("Dashboard");
        p.add(new JLabel("<html><p>Welcome to EduShop! Browse courses and start learning.</p></html>"), BorderLayout.CENTER);
        return p;
    }

    private JPanel makeCoursesPanel() {
        JPanel p = basePanel("Courses");
        String[] items = {"Java Fundamentals", "Python for Data Science", "React Basics"};
        JList<String> list = new JList<String>(items);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        p.add(new JScrollPane(list), BorderLayout.CENTER);
        return p;
    }

    private JPanel makeCartPanel() {
        JPanel p = basePanel("Cart");
        p.add(new JLabel("Your shopping cart is empty."), BorderLayout.CENTER);
        return p;
    }

    private JPanel makeProfilePanel() {
        JPanel p = basePanel("Profile");
        p.add(new JLabel("Profile information goes here."), BorderLayout.CENTER);
        return p;
    }

    private JPanel makeSettingsPanel() {
        JPanel p = basePanel("Settings");
        p.add(new JLabel("User settings and preferences."), BorderLayout.CENTER);
        return p;
    }

    /**
     * Show the named card. Names are the keys used in contentPanel.add(..., name).
     */
    public void showCard(String name) {
        if (name == null || name.trim().isEmpty()) return;
        contentCards.show(contentPanel, name);
    }
}
