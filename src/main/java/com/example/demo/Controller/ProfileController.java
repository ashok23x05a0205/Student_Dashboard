package com.example.demo.Controller;

	import com.example.demo.Entity.Student;
	import com.example.demo.Service.StudentService;
	import jakarta.servlet.http.HttpSession;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;

	@Controller
	@RequestMapping("/studentPages")
	public class ProfileController {

	    @Autowired
	    private StudentService studentService;

	    @GetMapping("/profile")
	    public String profile(HttpSession session, Model model) {
	        Student student = (Student) session.getAttribute("student");
	        if (student == null) return "redirect:/login";
	        model.addAttribute("student", student);
	        return "profile";
	    }

	    // GET - Edit profile form
	    @GetMapping("/edit-profile")
	    public String editProfileForm(HttpSession session, Model model) {
	        Student student = (Student) session.getAttribute("student");
	        if (student == null) return "redirect:/login";
	        model.addAttribute("student", student);
	        return "edit-profile";
	    }

	    // POST - Save profile changes
	    @PostMapping("/edit-profile")
	    public String updateProfile(@ModelAttribute Student updatedStudent, HttpSession session) {
	        Student current = (Student) session.getAttribute("student");
	        if (current == null) return "redirect:/login";

	        current.setName(updatedStudent.getName());
	        current.setEmail(updatedStudent.getEmail());
	        // Update other fields if needed

	        studentService.saveStudent(current);
	        session.setAttribute("student", current); // update session
	        return "redirect:/studentPages/profile";
	    }
	    @GetMapping("/delete-profile")
	    public String deleteProfile(HttpSession session) {
	        Student student = (Student) session.getAttribute("student");
	        if (student == null) {
	            return "redirect:/login";
	        }

	        studentService.deleteStudent(student.getId());
	        session.invalidate();  // logout after delete
	        return "redirect:/signup"; // or wherever you want to send after delete
	    }
	}