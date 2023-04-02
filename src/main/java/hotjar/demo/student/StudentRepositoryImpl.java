package hotjar.demo.student;

// import hotjar.demo.student.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotjar.demo.db.PostgreSQLJDBC;
import hotjar.demo.db.Database;
import java.sql.Connection;
import java.sql.SQLException;



 // TODO: create a student
        // TODO: delete a student
        // TODO: Update a student
        // TODO: unit test creating a student
        // TODO: unit test gettingg 3 student
        // TODO: unit test deleting a student

@Service
public class StudentRepositoryImpl implements StudentRepository{
    
    public char foo() {
        return 'c';
    }
    //  private final DataSource dataSource;
    private Connection connection;

    // @Autowired
    public void StudentRepository(DataSource dataSource) {
        // this.connection = 
    }

    // public void deleteStudent(Long id) {
    //     String sql = "DELETE FROM students WHERE id = ?";
        
    // }

    public List<Student> getAll() { 
        Student student = new Student(1L, "Fat Toney", "tony@gmail.com", LocalDate.of(2000, Month.DECEMBER, 5), 21);
        Student bart = new Student(2L, "Bart Simpson", "bart@gmail.com", LocalDate.of(2000, Month.JANUARY, 4), 25);
        List<Student> students = List.of(student, bart);
        return students;
    }

    public Student create(Student student) {
        System.out.println("in the create");
        Database db = new Database();
        try {
            
            System.out.println("try connect");
            db.connect();
        } catch(Exception e) {
            System.out.println("error!!!!!! with connecting");
            throw e;
        }
        try {
            System.out.print("creating student table");
            db.createTableIfNotExists("STUDENT");
        } catch(SQLException e) {
            System.out.println("error! with creating student");
            System.out.println(e.getMessage());
        }
        String sqlForStudent = SQLMaker.makeStudentSQL(student);
        try {            
            System.out.println(sqlForStudent);
            db.operate(sqlForStudent);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        // PostgreSQLJDBC.connect();
        
        // PostgreSQLJDBC thing = new PostgreSQLJDBC(); // todo: test me
        // thing.connect();
        System.out.print("new dtudent");
       

        System.out.print(student);
        return new Student(3L, "hat", "cat", LocalDate.of(2000, Month.MARCH, 3), 5);
        // return new Student()
    }

    public String delete() {
        return "Success";
    }
}
