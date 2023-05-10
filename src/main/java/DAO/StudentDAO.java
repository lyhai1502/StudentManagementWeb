package DAO;

import Context.DBConnection;
import Entity.Course;
import Entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, grade, birthday, address, notes) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getGrade());
            ps.setDate(3, new java.sql.Date(student.getBirthday().getTime()));
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getNotes());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editStudent(Student student) {
        String query = "UPDATE students SET name=?, grade=?, birthday=?, address=?, notes=? WHERE id=?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getGrade());
            ps.setDate(3, new java.sql.Date(student.getBirthday().getTime()));
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getNotes());
            ps.setInt(6, student.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(int id) throws Exception {
        String query = "DELETE FROM students WHERE id=?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ignored) {
        }

    }

    public List<Student> getAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        conn = new DBConnection().getConnection();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            students.add(new Student(rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getDate(4),
                    rs.getString(5),
                    rs.getString(6)));
        }
        return students;
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = new ArrayList<Student>();
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement("SELECT * FROM students WHERE name LIKE ?");
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getInt("grade"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddress(rs.getString("address"));
                student.setNotes(rs.getString("notes"));
                students.add(student);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public Student getStudentByID(int id) {
        String query = "select * from students where id=?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Student(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getString(6));

            }
        } catch (Exception ignored) {
        }
        return null;
    }


    public List<Student> getAllStudentsSortedByName() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students ORDER BY name")) {
            while (rs.next()) {
                Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("grade"),
                        rs.getDate("birthday"), rs.getString("address"), rs.getString("notes"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public boolean isIDExist(int id) {
        List<Integer> listIds = new ArrayList<>();
        String query = "SELECT students.id FROM students";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listIds.add(rs.getInt(1));
            }
            return listIds.contains(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Course, Float> getCoursesInStudent(int sid){
        HashMap<Course, Float> map = new HashMap<>();
        String query = "select * from courses_students_score where student_id=? order by course_id asc ";
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt(1);
                float score = rs.getFloat(3);
                Course c = new CourseDAO().getCourseByID(cid);
                map.put(c, score);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}



