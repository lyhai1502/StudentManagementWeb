package Control.CourseControl;

import DAO.CourseDAO;
import DAO.StudentDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/delete_student_in_course"})
public class DeleteStudentInCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idC = Integer.parseInt(req.getParameter("id"));
        int idS = Integer.parseInt(req.getParameter("ids"));

        new CourseDAO().deleteStudentInCourse(idC, idS);
        resp.sendRedirect("show_students?id="+idC);
    }
}
