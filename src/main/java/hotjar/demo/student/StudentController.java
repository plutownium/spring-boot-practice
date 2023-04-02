
package hotjar.demo.student;
import hotjar.demo.student.Student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired // magic
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/")
	public List<Student> getStudents() {
        return this.studentService.getStudents();
		// return List.of(student, bart);
	}
}
