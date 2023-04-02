package hotjar.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String deleteStudent() {
        return studentRepository.delete();
    }
}
