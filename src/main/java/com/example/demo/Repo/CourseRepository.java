package com.example.demo.Repo;

import com.example.demo.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStudentId(Long studentId);
}