package hotjar.demo.student;

public class SQLMaker {
    
    public static String makeCreateStudentSQL(Student student) {
        System.out.println("Here is student @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(student);
        return String.format("INSERT INTO Students (NAME, EMAIL, DOB, AGE) " + 
        "VALUES ('%s', '%s', '%s', %s) " +
        "RETURNING *", student.getName(), student.getEmail(), student.getDob(), student.getAge());
    }

    public static String makeGetAllStudentsSQL() {
        return "SELECT * FROM Students";
    }
}
