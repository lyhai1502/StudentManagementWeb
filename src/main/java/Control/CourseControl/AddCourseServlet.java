package Control.CourseControl;

import DAO.CourseDAO;
import Entity.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add_course")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("CourseUI/add_course.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String lecture = request.getParameter("lecture");
        int year = Integer.parseInt(request.getParameter("year"));
        String notes = request.getParameter("notes");

        Course course = new Course(-1, name, lecture, year, notes);

        try {
            new CourseDAO().addCourse(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("course_list");
    }
}