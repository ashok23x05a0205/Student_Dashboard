package com.example.demo.Service;

import java.util.Optional;

import com.example.demo.Entity.Student;

public interface StudentService {
	
	Student saveStudent1(Student student);
	Optional<Student> findByUsername(String username);
	Student saveStudent(Student student);
	void deleteStudent(Long id);
	
}