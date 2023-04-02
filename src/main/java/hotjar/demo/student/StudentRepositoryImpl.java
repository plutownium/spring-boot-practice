package hotjar.demo.student;

// import hotjar.demo.student.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.sql.results.spi.ResultsConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotjar.demo.db.PostgreSQLJDBC;
import hotjar.demo.db.Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
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
    private Database db;

    // @Autowired
    public void StudentRepository(DataSource dataSource) {
        // this.connection = 
        // this.db = new Database();
        // db.connect();

    }

    // public void deleteStudent(Long id) {
    //     String sql = "DELETE FROM students WHERE id = ?";
        
    // }

    public List<Student> getAll() { 
        // Student student = new Student(1L, "Fat Toney", "tony@gmail.com", LocalDate.of(2000, Month.DECEMBER, 5), 21);
        // Student bart = new Student(2L, "Bart Simpson", "bart@gmail.com", LocalDate.of(2000, Month.JANUARY, 4), 25);
        // List<Student> students = List.of(student, bart);

        Database db = new Database();
        try {
            
            System.out.println("try connect --- in the getAll");
            db.connect();
        } catch(Exception e) {
            System.out.println("error!!!!!! with connecting");
            throw e;
        }

        List<Student> currentStudents = new ArrayList<Student>();
        String sqlForStudent = SQLMaker.makeGetAllStudentsSQL();
        ResultSet resultSet;

        try {            
            System.out.println(sqlForStudent);
            resultSet = db.operate(sqlForStudent);
            // boolean x = resultSet.next();
            // System.out.println(x);
            while (resultSet.next()) {
                System.out.println("FOO FOO FOO FOO");
                Long id = Long.valueOf(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                System.out.println("HAT HAT HAT HAT");
                Student retrieved = new Student(id, name, email, dob, age);
                currentStudents.add(retrieved);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return currentStudents;
    }

    public Student create(Student student) {
        System.out.println("in the create. Here is student");
        System.out.println(student);
        Database db = new Database();
        try {
            
            System.out.println("try connect");
            db.connect();
        } catch(Exception e) {
            System.out.println("error!!!!!! with connecting");
            throw e;
        }

        Student newStudent;
        String sqlForStudent = SQLMaker.makeCreateStudentSQL(student);
        ResultSet resultSet;
        try {            
            System.out.println(sqlForStudent);
            resultSet = db.operate(sqlForStudent);
            boolean x = resultSet.next();
            System.out.println(x);
            while (x) {
                System.out.println("FOO FOO FOO FOO");
                Long id = Long.valueOf(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
               System.out.println("HAT HAT HAT HAT");
                newStudent = new Student(id, name, email, dob, age);
                // Student newStudent = new Student(3L, "hatttt", "cattttt", LocalDate.of(1111, Month.MARCH, 3), 5);
                return newStudent;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        // return if failed
        Student studentAgain = new Student(3L, "hat", "cat", LocalDate.of(2000, Month.MARCH, 3), 5);
        return studentAgain;
        // return new Student()
    }

    public String delete() {
        return "Success";
    }
}
