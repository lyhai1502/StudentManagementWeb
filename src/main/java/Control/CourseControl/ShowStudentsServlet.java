package Control.CourseControl;

import DAO.CourseDAO;
import Entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/show_students")
public class ShowStudentsServlet extends HttpServlet {
    private int id = -1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Integer.parseInt(req.getParameter("id"));
        HashMap<Student, Float> map;
        try {
            map = new CourseDAO().getStudentsInCourse(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("cid", id);
        req.setAttribute("mapSt-score", map);
        req.getRequestDispatcher("CourseUI/show_students.jsp").forward(req, resp);
    }
}
