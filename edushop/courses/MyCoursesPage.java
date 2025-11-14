package courses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyCoursesPage extends JPanel {

    public MyCoursesPage() {
        super(new BorderLayout(12,12));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(12,12,12,12));

        JLabel title = new JLabel("My Courses");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        add(title, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 3, 12, 12));
        grid.setBackground(Color.WHITE);

        // Example courses with image names
        Course[] courses = new Course[] {
            new Course("Complete Web Dev", "HTML, CSS, JS, Backend", "4,500 DA", 4.8, "webdev.png"),
            new Course("Python for Data Science", "Pandas, NumPy, ML intro", "5,200 DA", 4.6, "python.png"),
            new Course("Java Fundamentals", "OOP, Threads, GUI", "3,800 DA", 4.4, "java.png"),
            new Course("React Advanced", "Hooks, Performance", "4,900 DA", 4.7, "react.png"),
            new Course("Node.js & Express", "APIs, Auth, DB", "3,600 DA", 4.3, "node.png"),
            new Course("UI/UX Basics", "Design & Prototyping", "2,900 DA", 4.1, "uiux.png")
        };

        for (Course c : courses) {
            grid.add(createCourseCard(c));
        }

        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(Color.WHITE);
        wrap.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrap,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(8,0,0,0));

        JLabel summary = new JLabel(courses.length + " courses found");
        summary.setFont(summary.getFont().deriveFont(Font.PLAIN, 13f));
        footer.add(summary, BorderLayout.WEST);

        JComboBox<String> sort = new JComboBox<>(
            new String[] {"Sort: Popular", "Price: Low → High", "Price: High → Low", "Newest"});
        footer.add(sort, BorderLayout.EAST);

        add(footer, BorderLayout.SOUTH);
    }


    private JComponent createCourseCard(final Course c) {
        JPanel card = new JPanel(new BorderLayout(8,8));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,225)),
                new EmptyBorder(10,10,10,10)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(240, 240));

        // === IMAGE SECTION ===
        JLabel img = new JLabel();
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setPreferredSize(new Dimension(200, 120));

        try {
            ImageIcon raw = new ImageIcon(
                getClass().getResource("/courses/images/" + c.imageName)
            );
            Image scaled = raw.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
            img.setIcon(new ImageIcon(scaled));
        } catch (Exception ex) {
            img.setText("[No Image]");
        }

        card.add(img, BorderLayout.NORTH);

        // === TITLE ===
        JLabel name = new JLabel("<html><b>" + c.title + "</b></html>");
        name.setFont(name.getFont().deriveFont(14f));

        // === CENTER CONTENT ===
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.WHITE);

        JLabel desc = new JLabel("<html><small>" + c.shortDesc + "</small></html>");
        desc.setFont(desc.getFont().deriveFont(12f));
        desc.setBorder(new EmptyBorder(4,0,6,0));

        JPanel rp = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        rp.setBackground(Color.WHITE);

        JLabel rating = new JLabel(String.format("★ %.1f", c.rating));
        rating.setFont(rating.getFont().deriveFont(Font.BOLD, 12f));

        JLabel price = new JLabel(c.price);
        price.setFont(price.getFont().deriveFont(Font.BOLD, 13f));
        price.setBorder(new EmptyBorder(0,12,0,0));

        rp.add(rating);
        rp.add(price);

        center.add(name, BorderLayout.NORTH);
        center.add(desc, BorderLayout.CENTER);
        center.add(rp, BorderLayout.SOUTH);

        card.add(center, BorderLayout.CENTER);

        // === ACTION BUTTONS ===
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        actions.setBackground(Color.WHITE);

        JButton details = new JButton("Details");
        details.addActionListener(e ->
            JOptionPane.showMessageDialog(card,
                c.title + "\n\n" + c.shortDesc + "\nPrice: " + c.price,
                "Course details",
                JOptionPane.INFORMATION_MESSAGE));

        JButton add = new JButton("Add to cart");
        add.setBackground(new Color(99,102,241));
        add.setForeground(Color.WHITE);
        add.setOpaque(true);
        add.setBorder(BorderFactory.createLineBorder(new Color(80,80,160)));

        add.addActionListener(e ->
            JOptionPane.showMessageDialog(card,
                c.title + " added to cart.",
                "Added",
                JOptionPane.INFORMATION_MESSAGE));

        actions.add(details);
        actions.add(add);

        card.add(actions, BorderLayout.SOUTH);

        // === Hover effect ===
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(245,245,255));
            }
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
            public void mouseClicked(MouseEvent e) {
                details.doClick();
            }
        });

        return card;
    }


    // === COURSE MODEL ===
    static class Course {
        String title, shortDesc, price, imageName;
        double rating;

        Course(String t, String s, String p, double r, String img) {
            title = t;
            shortDesc = s;
            price = p;
            rating = r;
            imageName = img;
        }
    }
}
