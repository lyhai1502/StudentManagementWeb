package Control.CourseControl;

import DAO.CourseDAO;
import DAO.StudentDAO;
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
import java.util.HashMap;

@WebServlet(urlPatterns = "/edit_score")
public class EditScoreServlet extends HttpServlet {
    private int idS = -1;
    private int idC = -1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idC = Integer.parseInt(req.getParameter("id"));
        idS = Integer.parseInt(req.getParameter("ids"));
        float score = -1;
        try {
            score = new CourseDAO().getScoreInStudent(idS, idC);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("score", score);
        req.setAttribute("idC", idC);
        req.setAttribute("idS", idS);
        req.getRequestDispatcher("CourseUI/edit_score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        float score = Float.parseFloat(request.getParameter("score"));
        new CourseDAO().updateScore(idS, idC, score);
        response.sendRedirect("show_students?id=" + idC);
    }
}
