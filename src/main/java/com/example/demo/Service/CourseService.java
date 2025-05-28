package com.example.demo.Service;
import com.example.demo.Entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Long studentId);
    Course getCourseById(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
}