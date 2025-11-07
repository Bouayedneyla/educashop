import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * SidebarMenu.java
 * A left navigation panel that calls a provided Navigator when the user selects an item.
 *
 * Uses a small Navigator interface to avoid depending on java.util.function.Consumer,
 * so it is compatible with older Java versions and avoids lambda issues.
 */
public class SidebarMenu extends JPanel {
    public interface Navigator {
        void navigate(String sectionName);
    }

    private final JList<String> navList;
    private final DefaultListModel<String> model;

    /**
     * Constructor
     * @param navigator callback that receives the name of the section to show (must match card names)
     */
    public SidebarMenu(final Navigator navigator) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(250, 251, 253));

        JLabel title = new JLabel("Navigation");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        model = new DefaultListModel<String>();
        model.addElement("Dashboard");
        model.addElement("Courses");
        model.addElement("Cart");
        model.addElement("Profile");
        model.addElement("Settings");

        navList = new JList<String>(model);
        navList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        navList.setVisibleRowCount(8);
        navList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        navList.setBackground(Color.WHITE);
        navList.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        navList.setCellRenderer(new NavListRenderer());

        // When selection changes, call the navigation callback (only when user finishes adjusting)
        navList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String sel = navList.getSelectedValue();
                    if (sel != null && navigator != null) {
                        navigator.navigate(sel);
                    }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(navList);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    /**
     * Programmatically select a nav item (and ensure it is visible)
     */
    public void selectItem(String name) {
        if (name == null) return;
        navList.setSelectedValue(name, true);
        int idx = navList.getSelectedIndex();
        if (idx >= 0) navList.ensureIndexIsVisible(idx);
    }

    // Custom renderer for nicer visuals
    static class NavListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            lbl.setBorder(new EmptyBorder(6, 10, 6, 10));
            if (isSelected) {
                lbl.setBackground(new Color(99, 102, 241));
                lbl.setForeground(Color.WHITE);
            } else {
                lbl.setBackground(Color.WHITE);
                lbl.setForeground(new Color(30, 41, 59));
            }
            return lbl;
        }
    }
}
