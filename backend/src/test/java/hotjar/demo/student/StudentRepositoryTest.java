package hotjar.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest 
public class StudentRepositoryTest {
 
    private StudentRepositoryImpl underTest;
    public StudentRepositoryTest() {
        this.underTest = new StudentRepositoryImpl("testing");
    }

    @BeforeEach
    void reset() {
        underTest.createTableIfNotExists();
        underTest.destroyAll();
    }

    @Test
    void itCreatesThreeStudents() {
        // given
        Student s1 = new Student(Long.valueOf(101), "A", "a@gmail.com", LocalDate.of(2022, Month.DECEMBER, 22), 25);
        Student s2 = new Student(Long.valueOf(102), "B", "b@gmail.com", LocalDate.of(2022, Month.DECEMBER, 22), 25);
        Student s3 = new Student(Long.valueOf(103), "A", "a@gmail.com", LocalDate.of(2022, Month.DECEMBER, 22), 25);

        // when
        Student created1 = underTest.create(s1);
        Student created2 = underTest.create(s2);
        Student created3 = underTest.create(s3);

        // then
        List<Student> made = underTest.getAll();
        assertThat(made.size()).isEqualTo(3);
    }

    @Test
    void itShouldCheckIfStudentExistsByEmail() {
        // given
        String email = "jamila@gmail.com";
        Student student = new Student(Long.valueOf(100), "Jamila", email, LocalDate.of(2022, Month.DECEMBER, 22), 25);
        underTest.create(student);
        // when
        boolean exists = underTest.emailExists(student.getEmail());

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void itGetsAllStudnets() {
        // given
        Student s1 = new Student(Long.valueOf(101), "A", "a@gmail.com", LocalDate.of(2022, Month.DECEMBER, 22), 25);
        Student s2 = new Student(Long.valueOf(102), "B", "b@gmail.com", LocalDate.of(2022, Month.DECEMBER, 22), 25);
        Student s3 = new Student(Long.valueOf(103), "A", "a@gmail.com", LocalDate.of(2022, Month.DECEMBER, 22), 25);
        Student s4 = new Student(Long.valueOf(103), "D", "d@gmail.com", LocalDate.of(2022, Month.DECEMBER, 22), 25);
        underTest.create(s1);
        underTest.create(s2);
        underTest.create(s3);
        underTest.create(s4);

        // when
        List<Student> students = underTest.getAll();
        
        // then
        assertThat(students.size()).isGreaterThanOrEqualTo(4);
    }
}
