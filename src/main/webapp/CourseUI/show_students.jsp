<%@ page import="Entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="DAO.CourseDAO" %>
<%@ page import="Entity.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=UTF-8">
    <title>Student List</title>
    <style>
        body{
            background-color: #f2f2f2;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        h1, h4 {
            text-align: center;
        }

        th, td {
            padding: 4px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        .btn-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .create-student-btn, .delete-link-btn, .edit-score-btn, .back-btn {
            display: inline-block;
            margin: 0 0 10px 0;
            background-color: #4CAF50 ;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 14px;
            border-radius: 5px;
        }
        .create-student-btn{
            padding: 10px 20px;
        }
        .edit-score-btn {
            background-color: #e08611;
            padding: 5px 10px;
            margin: 0px 20px 0px 0px;
        }
        .delete-link-btn {
            background-color: #ff0000;
            padding: 5px 10px;
            margin: 0px 20px 0px 0px;
        }
        .back-btn{
            background-color: #9d9696;
            padding: 10px 20px;
        }
        .score-label{
            background-color: #4ef0ff;
        }

    </style>
</head>

<body>
<div>
    <a class="back-btn" href="course_list"> <-- Back</a>
</div>
<h1>Show list students in course</h1>
<% int idC = (int) request.getAttribute("cid");
    Course c = null;
    try {
        c = new CourseDAO().getCourseByID(idC);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>
<h4><%=c.getName()%></h4>

<div class="btn-group">
    <a href="add_student_to_course?id=<%=idC%>" class="create-student-btn" >Add Student</a>
</div>

<table border="1px solid black">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Grade</th>
        <th class="score-label">Score</th>
        <th>Action</th>
    </tr>
    </thead>
    <% HashMap<Student, Float> map = (HashMap<Student, Float>) request.getAttribute("mapSt-score");
        Set<Student> set =  map.keySet();
        for(Student s : set) {%>
    <tr>
        <td><%=s.getId()%></td>
        <td><%=s.getName()%></td>
        <td><%=s.getGrade()%></td>
        <td><%=map.get(s)%></td>
        <td>
            <div>
                <a class="edit-score-btn" href="edit_score?id=<%=idC%>&ids=<%=s.getId()%>">Edit Score</a>
                <a class="delete-link-btn" href="#" onclick="showMess(<%=s.getId()%>)">Delete</a>
            </div>
        </td>
    </tr>
    <%}%>
</table>
</body>
<script>
    function showMess (idS) {
        const option = confirm("Are you sure to delete this student?");
        if(option === true) {
            window.location.href = "delete_student_in_course?id=<%=idC%>&ids=" +idS;
        }
    }
</script>
</html>
