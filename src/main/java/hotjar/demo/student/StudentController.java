
package hotjar.demo.student;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hotjar.demo.util.IdField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Student createStudent(@RequestBody Student student) {
        System.out.println("here! 37rm");
        System.out.println(student);
        System.out.println("here! 39rm");
        // return new Student(1, "temp", "temp", )
        return studentService.createStudent(student);
    }

    @DeleteMapping(value = "/")
    public String deleteStudent(@RequestBody IdField id) {
        System.out.println("here is id");
        System.out.println(id);
        System.out.println("================================");
        return studentService.deleteStudent(id.getId());
    }


    @GetMapping(value = "/health")
    public String Health() {
        return "Online";
    }
}
