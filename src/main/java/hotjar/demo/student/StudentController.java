
package hotjar.demo.student;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        return studentService.getStudents();
		// return List.of(student, bart);
	}

    @PostMapping(value = "/")
    @ResponseBody
    public Student createStudent(Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping(value = "/")
    public String deleteStudent() {
        return studentService.deleteStudent();
    }


    @GetMapping(value = "/health")
    public String Health() {
        return "Online";
    }
}
