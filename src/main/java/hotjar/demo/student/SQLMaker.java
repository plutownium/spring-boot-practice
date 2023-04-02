package hotjar.demo.student;

public class SQLMaker {
    
    public static String makeStudentSQL(Student student) {
        return String.format("INSERT INTO Students(NAME, EMAIL, DOB, AGE)" + 
        "VALUES (%s, %s, %s, %s)" +
        "RETURNING *", student.getName(), student.getEmail(), student.getDob(), student.getAge());
    }
}
