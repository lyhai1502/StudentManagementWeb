package Control.CourseControl;

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
import java.util.List;

@WebServlet("/find_course")
public class FindCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("search-name");
        List<Course> list = null;
        try {
            list = new CourseDAO().getCoursesByName(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("listC", list);
        req.setAttribute("last-search-name", name);
        req.getRequestDispatcher("CourseUI/course_list.jsp").forward(req, resp);
        resp.sendRedirect("course_list");
    }
}
