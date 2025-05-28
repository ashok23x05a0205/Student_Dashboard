package com.example.demo.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentrepo;

    public StudentServiceImpl(StudentRepo studentrepo) {
        this.studentrepo = studentrepo;
    }

    @Override
    public Optional<Student> findByUsername(String username) {
        return studentrepo.findByUsername(username);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentrepo.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentrepo.deleteById(id);
    }

	@Override
	public Student saveStudent1(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

}