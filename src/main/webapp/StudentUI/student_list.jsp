<%@ page import="Entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

        .btn-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .create-student-btn, .search-btn, .delete-link-btn, .edit-link-btn, .course-list-btn, .show-score-board-btn, .show-courses-btn {
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
        .search-btn {
            padding: 5px 5px;
            background-color: #18c7cb;
        }
        .edit-link-btn {
            background-color: #e08611;
            padding: 5px 10px;
            margin: 0px 20px 0px 0px;
        }
        .delete-link-btn {
            background-color: #ff0000;
            padding: 5px 10px;
            margin: 0px 20px 0px 0px;
        }
        .show-courses-btn{
            background-color: #6653ff;
            padding: 5px 10px;
            margin: 0px 20px 0px 0px;
        }
        .show-score-board-btn {
            background-color: #de5dcb;
            padding: 5px 10px;
            margin: 0px 20px 0px 0px;
        }
        .course-list-btn{
            background-color: #9d9696;
            padding: 10px 20px;
        }
        form {
            margin-left: auto;
        }
        .sort-dropdown {
            border-radius: 10px;
            margin-left: 90px;
        }
        .sort-dropdown label{
            margin-right: 10px;
        }
    </style>
</head>

<body>
<div>
    <a class="course-list-btn" href="course_list">Course list</a>
</div>
<h1>STUDENT LIST</h1>

<div class="btn-group">
    <a href="add_student" class="create-student-btn" >Create Student</a>
    <div class="sort-dropdown">
        <form method="get">
            <label >
                <select name="sort" >
                    <option value="">None</option>
                    <option value="name_asc" formaction="/sort-by-name-asc">Ascending Name</option>
                    <option value="name_desc" formaction="/sort-by-name-desc">Descending Name</option>
                    <option value="grade_asc" formaction="/sort-by-grade-asc">Ascending Grade</option>
                    <option value="grade_desc" formaction="/sort-by-grade-desc">Descending Grade</option>
                </select>
            </label>
            <button type="submit">Sort</button>
        </form>

    </div>
    <form class="search-bar" action="find_student" method="get" >
        <input type="text" name="search-name" value = "<%=request.getAttribute("last-search-name")%>" placeholder="Find by name...">
        <button class="search-btn" type="submit">Search</button>
    </form>
</div>

<table border="1px solid black">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Grade</th>
        <th>Birthday</th>
        <th>Address</th>
        <th>Notes</th>
        <th>Action</th>
    </tr>
    </thead>
    <% for(Student s : (ArrayList<Student>) request.getAttribute("listS")) {%>
            <tr>
                <td><%=s.getId()%></td>
                <td><%=s.getName()%></td>
                <td><%=s.getGrade()%></td>
                <td><%=new SimpleDateFormat("dd-MM-yyyy").format(s.getBirthday().getTime())%></td>
                <td><%=s.getAddress()%></td>
                <td><%=s.getNotes()%></td>
                <td>
                    <div>
                        <a class="edit-link-btn" href="edit_student?id=<%=s.getId()%>">Edit</a>
                        <a class="delete-link-btn" href="#" onclick="showMess(<%=s.getId()%>)">Delete</a>
                        <a class="show-courses-btn" href="show_courses?id=<%=s.getId()%>" >Show courses</a>
                    </div>
                </td>
            </tr>
<%}%>
</table>
</body>
    <script>
        function showMess (id) {
            const option = confirm("Are you sure to delete this student?");
            if(option === true) {
                window.location.href = "delete_student?id="+id;
            }
        }
    </script>
</html>
