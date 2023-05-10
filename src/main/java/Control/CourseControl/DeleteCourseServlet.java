package Control.CourseControl;

import DAO.CourseDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/delete_course"})
public class DeleteCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        CourseDAO dao = null;
        try {
            dao = new CourseDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dao.deleteCourse(Integer.parseInt(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("course_list");
    }
}
