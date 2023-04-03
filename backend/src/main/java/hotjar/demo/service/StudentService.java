package hotjar.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotjar.demo.classes.student.Student;
import hotjar.demo.classes.student.StudentRepositoryImpl;

@Service
public class StudentService {

    private final StudentRepositoryImpl studentRepository;

    @Autowired
    public StudentService(StudentRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.getAll();
	}

    public Student createStudent(Student student) {
        return studentRepository.create(student);
    }

    public String deleteStudent(int id) {
        return studentRepository.delete(id);
    }
}
