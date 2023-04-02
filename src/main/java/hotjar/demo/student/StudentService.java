package hotjar.demo.student;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
		// Student student = new Student(1L, "Fat Toney", "tony@gmail.com", LocalDate.of(2000, Month.DECEMBER, 5), 21);
		// Student bart = new Student(2L, "Bart Simpson", "bart@gmail.com", LocalDate.of(2000, Month.JANUARY, 4), 25);
		// return List.of(student, bart);
        return studentRepository.getAllStudents();
	}

}
