package com.example.demo.Controller;
import java.util.Objects; // add this at the top

//Line added for test
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.Service.CourseService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/studentPages/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listCourses(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";

        model.addAttribute("courses", courseService.getAllCourses(student.getId()));
        return "courses";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";

        course.setStudent(student);
        courseService.saveCourse(course);
        return "redirect:/studentPages/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";

        Course course = courseService.getCourseById(id);
        if (course == null || !Objects.equals(course.getStudent().getId(), student.getId()))
 {
            return "redirect:/studentPages/courses";
        }
        model.addAttribute("course", course);
        return "course_form";
    }

    @PostMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, @ModelAttribute Course course, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";

        course.setId(id);
        course.setStudent(student);
        courseService.saveCourse(course);
        return "redirect:/studentPages/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";

        Course course = courseService.getCourseById(id);
        if (course != null && Objects.equals(course.getStudent().getId(), student.getId()))
 {
            courseService.deleteCourse(id);
        }
        return "redirect:/studentPages/courses";
    }
}