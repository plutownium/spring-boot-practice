package hotjar.demo.student;


public class StudentSQLMaker {
    
    public static String makeCreateStudentSQL(Student student) {
        System.out.println(student);
        return String.format("INSERT INTO Students (NAME, EMAIL, DOB, AGE) " + 
        "VALUES ('%s', '%s', '%s', %s) " +
        "RETURNING *", student.getName(), student.getEmail(), student.getDob(), student.getAge());
    }

    public static String makeGetAllStudentsSQL() {
        return "SELECT * FROM Students";
    }

    public static String makeDeleteStudentSQL(int id) {
        return String.format("DELETE FROM Students WHERE id = %s RETURNING *", id);
    }

    public static String destroyAll() {
        return "DELETE FROM Students;";
    }
}
