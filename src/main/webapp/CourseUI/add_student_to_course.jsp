<%@ page import="DAO.StudentDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student To Course</title>
    <style>
        /* style.css */
        body {
            background-color: #f2f2f2;
            position: absolute;
            left: 50%;
            top: 40%;
            transform: translate(-50%, -50%);
        }

        label {
            font-weight: bold;
            text-align: center;
        }

        input[type=text], input[type=number], input[type=date] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        input[type=submit] {
            background-color: #45a049;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 10px;
        }

        input[type=submit]:hover, button:hover {
            background-color: #45a049;
        }
        a{
            margin-left: 10px;
            color: #45a049;
            text-decoration: none;
        }


    </style>
</head>
<body>
<h1>Add New Student</h1>
<%  int idC = (int) request.getAttribute("idC");
    String alert = request.getAttribute("alert").toString();%>
<form action="add_student_to_course" method="post">
    <label>ID:</label>
    <input type="number" name="idS" required><br><br>
    <label>Score</label>
    <input type="text" name="score" required><br><br>
    <h4><%=alert%></h4>
    <input type="submit" value="Save">
    <a href="show_students?id=<%=idC%>">Back</a>

</form>
<script>

</script>
</body>
</html>
