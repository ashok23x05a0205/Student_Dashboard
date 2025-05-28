package com.example.demo.Controller;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Display signup form
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("student", new Student());
        return "signup";
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute Student student, Model model) {
        if (studentService.findByUsername(student.getUsername()).isPresent()) {
            model.addAttribute("error", "User already exists");
            return "signup";
        }
        studentService.saveStudent(student);
        return "redirect:/login";
    }

    // Display login form
    @GetMapping("/login")
    public String loginPage(Model model) {
        // Add a possible error attribute for login failure
        model.addAttribute("error", null); // Clear any previous error message
        return "login";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String loginSubmit(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        var studentOpt = studentService.findByUsername(username);
        if (studentOpt.isPresent() && studentOpt.get().getPassword().equals(password)) {
            session.setAttribute("student", studentOpt.get());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    // Handle logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidates the current session
        return "redirect:/login";  // Redirect to login page after logout
    }

    // Display dashboard page
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";  // Redirect to login if no student is found in session
        }
        model.addAttribute("student", student);
        return "dashboard"; // Ensure dashboard.html exists in templates
    }

    // Display student profile page
    @GetMapping("/studentPages/studentprofile")
    public String profile(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";  // Redirect to login if no student in session
        }
        model.addAttribute("student", student);
        return "profile"; // Ensure profile.html exists in templates
    }

    // Display courses page
    @GetMapping("/studentPages/courses1")
    public String courses(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }
        return "courses";  // Ensure courses.html exists in templates
    }

    // Display grades page
    @GetMapping("/studentPages/grades")
    public String grades(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }
        return "grades";  // Ensure grades.html exists in templates
    }

    // Display notifications page
    @GetMapping("/studentPages/notifications")
    public String notifications(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }
        return "notifications";  // Ensure notifications.html exists in templates
    }
}