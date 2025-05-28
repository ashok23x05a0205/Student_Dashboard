package com.example.demo.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses(Long studentId) {
        return courseRepository.findByStudentId(studentId);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);  // Handling optional correctly
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);  // Save or update course
    }

    @Override
    public void deleteCourse(Long id) {
        // Check if the course exists before deleting (optional, but a good practice)
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);  // Deletes the course by its ID
        }
    }
}