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

@WebServlet(urlPatterns = "/add_student_to_course")
public class AddStudentToCourseServlet extends HttpServlet {
    private int idS = -1;
    private int idC = -1;
    String textAlert = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idC = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("idC", idC);
        req.setAttribute("idS", idS);
        req.setAttribute("alert", textAlert);
        req.getRequestDispatcher("CourseUI/add_student_to_course.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        idS = Integer.parseInt(request.getParameter("idS"));
        if(!new StudentDAO().isIDExist(idS)){
            textAlert = "Sinh vien nay khong ton tai, hay them moi!";
            request.setAttribute("alert", textAlert);
            response.sendRedirect("add_student_to_course?id=" + idC);
        } else if (new CourseDAO().isStudentInCourse(idC, idS)) {
            textAlert = "Sinh vien nay dang hoc mon nay, hay them sinh vien khac!";
            request.setAttribute("alert", textAlert);
            response.sendRedirect("add_student_to_course?id=" + idC);
        } else {
            float score = Float.parseFloat(request.getParameter("score"));
            try {
                new CourseDAO().addStudentToCourse(idS, idC, score);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("show_students?id=" + idC);
        }
    }
}