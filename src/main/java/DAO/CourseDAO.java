package DAO;

import Context.DBConnection;
import Entity.Course;
import Entity.Student;

import java.sql.*;
import java.util.*;

public class CourseDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public void addCourse(Course course) {
        try {
            conn = DBConnection.getConnection();
            String query = "INSERT INTO courses (name, lecture, year, notes) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, course.getName());
            ps.setString(2, course.getLecture());
            ps.setInt(3, course.getYear());
            ps.setString(4, course.getNotes());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void editCourse(Course course) {
        String query = "UPDATE courses SET name=?, lecture=?, year=?, notes=? WHERE id=?";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, course.getName());
            ps.setString(2, course.getLecture());
            ps.setInt(3, course.getYear());
            ps.setString(4, course.getNotes());
            ps.setInt(5, course.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCourse(int id) {
        try {
            conn = DBConnection.getConnection();
            ps  = conn.prepareStatement("DELETE FROM courses WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public List<Course> getAllCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        conn = DBConnection.getConnection();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            courses.add(new Course(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5)));
        }
        return courses;
    }

    public Course getCourseByID(int id) {
        String query = "select * from courses where id=?";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Course> getCoursesByName(String name) {
        List<Course> courses = new ArrayList<Course>();
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM courses WHERE name LIKE ?");
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setLecture(rs.getString("lecture"));
                course.setYear(rs.getInt("year"));
                course.setNotes(rs.getString("notes"));
                courses.add(course);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    public HashMap<Student, Float> getStudentsInCourse(int id){
        HashMap<Student, Float> map = new HashMap<>();
        String query = "select * from courses_students_score where course_id=? order by student_id asc ";
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt(2);
                float score = rs.getFloat(3);
                Student s = new StudentDAO().getStudentByID(sid);
                map.put(s, score);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public float getScoreInStudent(int idS, int idC){
        HashMap<Student, Float> map = this.getStudentsInCourse(idC);
        Set<Student> set =  map.keySet();
        float score = 0;
        for(Student s : set) {
            if (s.getId() == idS) {
                score = map.get(s);
                break;
            }
        }
        return score;
    }

    public void updateScore(int idS, int idC, float score){
        String query = "UPDATE courses_students_score SET score=? WHERE course_id=? and student_id=?";
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setFloat(1, score);
            ps.setInt(2, idC);
            ps.setInt(3, idS);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudentToCourse(int idS, int idC, float score) throws Exception {
        String query = "INSERT INTO courses_students_score (course_id,student_id, score) VALUES (?, ?, ?)";
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idC);
            ps.setInt(2,idS);
            ps.setFloat(3, score);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isStudentInCourse(int idC, int idS){
        String query = "SELECT * FROM courses_students_score";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getInt(1) == idC && rs.getInt(2) == idS){
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void deleteStudentInCourse(int idC, int idS){
        try {
            conn = DBConnection.getConnection();
            ps  = conn.prepareStatement("DELETE FROM courses_students_score WHERE student_id=? and course_id=?");
            ps.setInt(1, idS);
            ps.setInt(2, idC);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
