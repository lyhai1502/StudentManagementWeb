package Control.StudentControl;

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

@WebServlet(urlPatterns = "/edit_student")
public class EditStudentServlet extends HttpServlet {
    private int id = -1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Integer.parseInt(req.getParameter("id"));
        StudentDAO dao = new StudentDAO();
        Student student = dao.getStudentByID(id);
        req.setAttribute("st", student);
        req.getRequestDispatcher("StudentUI/edit_student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int grade = Integer.parseInt(request.getParameter("grade"));
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String address = request.getParameter("address");
        String notes = request.getParameter("notes");

        // Create a new Student object with the form data
        Student newStudent = new Student(id, name, grade, birthday, address, notes);

        // Add the new student to the database
        new StudentDAO().editStudent(newStudent);

        // Set the student list as a request attribute and forward to the studentList.jsp page
        response.sendRedirect("student_list");
    }
}
