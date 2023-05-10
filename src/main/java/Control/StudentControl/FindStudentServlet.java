package Control.StudentControl;

import DAO.StudentDAO;
import Entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/find_student")
public class FindStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("search-name");
        List<Student> list = new StudentDAO().getStudentsByName(name);

        req.setAttribute("listS", list);
        req.setAttribute("last-search-name", name);
        req.getRequestDispatcher("StudentUI/student_list.jsp").forward(req, resp);
        resp.sendRedirect("student_list");
    }
}
