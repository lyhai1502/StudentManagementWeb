package Control.StudentControl;

import DAO.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/delete_student"})
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        StudentDAO dao = new StudentDAO();
        try {
            dao.deleteStudent(Integer.parseInt(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("student_list");
    }
}
