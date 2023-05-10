
import DAO.CourseDAO;
import DAO.StudentDAO;
import Entity.Student;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(new StudentDAO().getCoursesInStudent(1));  }
}
