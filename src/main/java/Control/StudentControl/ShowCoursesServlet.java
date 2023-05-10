package Control.StudentControl;

import DAO.CourseDAO;
import DAO.StudentDAO;
import Entity.Course;
import Entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/show_courses")
public class ShowCoursesServlet extends HttpServlet {
    private int idS = -1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idS = Integer.parseInt(req.getParameter("id"));
        HashMap<Course, Float> map;
        try {
            map = new StudentDAO().getCoursesInStudent(idS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("sid", idS);
        req.setAttribute("mapC-score", map);
        req.getRequestDispatcher("StudentUI/show_courses.jsp").forward(req, resp);
    }
}
