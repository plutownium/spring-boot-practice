package hotjar.demo.student;

// import hotjar.demo.student.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.sql.results.spi.ResultsConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import hotjar.demo.db.PostgreSQLJDBC;
import hotjar.demo.db.Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;



// todo: make an account/user table
// todo: a profile table
// todo: make a website table
// todo: recordings table
// todo: make a customer table

public class StudentRepositoryImpl implements StudentRepository {
    
    private Database db;
    public StudentRepositoryImpl(String mode) {
        Database started = new Database(mode);
        started.connect();
        this.db = started;
    }

    public char foo() {
        return 'c';
    }

    public void createTableIfNotExists() {
        try {

            db.createTableIfNotExists("Students");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Autowired
    public void StudentRepository(DataSource dataSource) {
    }


    public boolean emailExists(String email) {
        //
        return true;
    }

    public List<Student> getAll() { 

        List<Student> currentStudents = new ArrayList<Student>();
        String sqlForStudent = StudentSQLMaker.makeGetAllStudentsSQL();
        ResultSet resultSet;

        try {            
            System.out.println(sqlForStudent);
            resultSet = db.operate(sqlForStudent);
            // boolean x = resultSet.next();
            // System.out.println(x);
            while (resultSet.next()) {
                Long id = Long.valueOf(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
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
   
        Student newStudent;
        String sqlForStudent = StudentSQLMaker.makeCreateStudentSQL(student);
        ResultSet resultSet;
        try {            
            System.out.println(sqlForStudent);
            resultSet = db.operate(sqlForStudent);
            boolean x = resultSet.next();
            System.out.println(x);
            while (x) {
                Long id = Long.valueOf(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double gpa = resultSet.getDouble("gpa");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
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

    public String delete(int id) {
     

        String sqlForStudent = StudentSQLMaker.makeDeleteStudentSQL(id);

        try {            
            System.out.println(sqlForStudent);
            db.operate(sqlForStudent);
            return "Success";
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Fail";
    }

    public void destroyAll() {
        // Database db = new Database();
        String sql = StudentSQLMaker.destroyAll();
        
        try {            
            System.out.println(sql);
            db.operate(sql);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
