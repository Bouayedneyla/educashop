package courses;
public class MyCoursesPage{
private JLabel myCoursesTitle; // "My courses"
private JLabel coursesStatusLabel; // "2 courses purchased - 1 incompleted"
private JTextField searchField;

// A panel to hold the list of course cards
private JPanel courseListPanel; 

// --- Data ---
// A list of Course objects that the user owns
private List<Course> userCourses; 
}
