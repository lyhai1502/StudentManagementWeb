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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/edit_course")
public class EditCourseServlet extends HttpServlet {
    private int id = -1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Integer.parseInt(req.getParameter("id"));
        CourseDAO dao = null;
        try {
            dao = new CourseDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Course course = dao.getCourseByID(id);
        req.setAttribute("ec", course);
        req.getRequestDispatcher("CourseUI/edit_course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String lecture = request.getParameter("lecture");
        int year = Integer.parseInt(request.getParameter("year"));
        String notes = request.getParameter("notes");

        Course course = new Course(id, name, lecture, year, notes);

        try {
            new CourseDAO().editCourse(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("course_list");
    }
}
