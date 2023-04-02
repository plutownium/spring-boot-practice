package hotjar.demo.student;

// import hotjar.demo.student.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentRepositoryImpl implements StudentRepository{
    
     private final DataSource dataSource;

    @Autowired
    public void StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteStudent(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Student> getAll() {
        Student student = new Student(1L, "Fat Toney", "tony@gmail.com", LocalDate.of(2000, Month.DECEMBER, 5), 21);
        Student bart = new Student(2L, "Bart Simpson", "bart@gmail.com", LocalDate.of(2000, Month.JANUARY, 4), 25);
        List<Student> students = List.of(student, bart);
        return students;
    }

    public Student create(Student student) {
        System.out.print("new dtudent");
        // TODO: create a student
        // TODO: delete a student
        // TODO: Update a student
        // TODO: unit test creating a student
        // TODO: unit test gettingg 3 student
        // TODO: unit test deleting a student

        System.out.print(student);
        return new Student(3L, "hat", "cat", LocalDate.of(2000, Month.MARCH, 3), 5);
        // return new Student()
    }

    public String delete() {
        return "Success";
    }
}
