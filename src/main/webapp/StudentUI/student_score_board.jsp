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
        h1 {
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

        .back-to-student-list-btn {
            display: inline-block;
            margin: 0px 0px 10px 0px;
            background-color: #4CAF50 ;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 14px;
            border-radius: 5px;
        }





        .back-to-student-list-btn{
            background-color: #9d9696;
            padding: 5px 10px;
        }
        form {
            margin-left: auto;
        }
        .sort-dropdown label{
            margin-right: 10px;
        }
    </style>
</head>

<body>
<div>
    <a class="back-to-student-list-btn" href="student_list.jsp"><-- Back to Student List</a>
</div>
<h1>SCORE BOARD</h1>


<table border="1px solid black">
    <thead>
    <tr>
        <th>Name Course</th>
        <th>Lecture</th>
        <th>Year</th>
        <th>Score</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${students}">
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
