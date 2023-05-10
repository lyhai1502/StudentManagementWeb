package Control.CourseControl;

import DAO.CourseDAO;
import Entity.Course;
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

@WebServlet(urlPatterns = {"/course_list"})
public class CoursetListServlet extends HttpServlet {
    private CourseDAO courseDAO;

    public void init() {
        try {
            courseDAO = new CourseDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("last-search-name", "");
        List<Course> list = null;
        try {
            list = courseDAO.getAllCourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String actionSort = request.getParameter("sort");
        if(Objects.equals(actionSort, "name_asc")){
            sortNameCourseASC(list);
        } else if(Objects.equals(actionSort, "name_desc")){
            sortNameCourseDESC(list);
        }else{
            sortIDCourse(list);
        }

        request.setAttribute("listC", list);
        request.getRequestDispatcher("CourseUI/course_list.jsp").forward(request, response);}
    public void sortNameCourseASC(List<Course> list){
        Collections.sort(list, new Comparator<Course>() {
            @Override
            public int compare(Course s1, Course s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
    }
    public void sortNameCourseDESC(List<Course> list){
        Collections.sort(list, new Comparator<Course>() {
            @Override
            public int compare(Course s1, Course s2) {
                return s2.getName().compareTo(s1.getName());
            }
        });
    }
    public void sortIDCourse(List<Course> list){
        Collections.sort(list, new Comparator<Course>() {
            @Override
            public int compare(Course s1, Course s2) {
                return Integer.compare(s1.getId(), s2.getId());
            }
        });
    }
}
