package Control.StudentControl;
import DAO.StudentDAO;
import Entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "Main", urlPatterns = {"/student_list"})
public class StudentListServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("last-search-name", "");
        String actionSort = request.getParameter("sort");
        List<Student> list = null;
        try {
            list = studentDAO.getAllStudents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(Objects.equals(actionSort, "name_asc")){
            sortNameStudentASC(list);
        } else if(Objects.equals(actionSort, "name_desc")){
            sortNameStudentDESC(list);
        }
        else if(Objects.equals(actionSort, "grade_asc")) {
            sortGradeStudentASC(list);
        } else if(Objects.equals(actionSort, "grade_desc")) {
            sortGradeStudentDESC(list);
        }else{
            sortIDStudent(list);
        }

        request.setAttribute("listS", list);
        request.getRequestDispatcher("StudentUI/student_list.jsp").forward(request, response);
    }
    public void sortNameStudentASC(List<Student> list){
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
    }
    public void sortNameStudentDESC(List<Student> list){
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s2.getName().compareTo(s1.getName());
            }
        });
    }
    public void sortGradeStudentASC(List<Student> list){
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getGrade(), s2.getGrade());
            }
        });
    }
    public void sortGradeStudentDESC(List<Student> list){
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s2.getGrade(), s1.getGrade());
            }
        });
    }
    public void sortIDStudent(List<Student> list){
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getId(), s2.getId());
            }
        });
    }
}

