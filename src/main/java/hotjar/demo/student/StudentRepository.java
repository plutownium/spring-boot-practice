// package hotjar.demo.student;

// import org.springframework.stereotype.Repository;

// @Repository
// public interface StudentRepository {
    
//     public Student getAllStudents() {
//         //
//     }
// }

// ***
// gpt wrote this
// ***
// package hotjar.demo.student;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// @Repository
// public class StudentRepositoryImpl implements StudentRepository {

//     private JdbcTemplate jdbcTemplate;

//     @Autowired
//     public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     @Override
//     public List<Student> getAllStudents() {
//         String sql = "SELECT * FROM student";
//         List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
//         return students;
//     }

// }