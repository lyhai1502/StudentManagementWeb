<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=UTF-8">
    <title>Course List</title>
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
        .create-course-btn, .search-btn, .delete-link-btn, .edit-link-btn, .student-list-btn, .show-score-board-btn {
            display: inline-block;
            margin: 0 0 10px 0;
            background-color: #4CAF50 ;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 14px;
            border-radius: 5px;
        }
        .create-course-btn{
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
        .show-score-board-btn {
            background-color: #de5dcb;
            padding: 5px 10px;
            margin: 0px 20px 0px 0px;
        }
        .student-list-btn{
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
    <a class="student-list-btn" href="student_list">Student list</a>
</div>
<h1>COURSE LIST</h1>

<div class="btn-group">
    <a href="add_course" class="create-course-btn" >Create Course</a>
    <div class="sort-dropdown">
        <label>Sort:</label>
        <form method="get">
            <select name="sort">
            <option value="">None</option>
            <option value="name_asc" formaction="/sort-by-name-asc">Ascending Name</option>
            <option value="name_desc" formaction="/sort-by-name-desc">Descending Name</option>
            </select>
            <button type="submit">Sort</button>
        </form>
    </div>
    <form class="search-bar" action="find_course" method="get" >
        <input type="text" name="search-name" value = "<%=request.getAttribute("last-search-name")%>" placeholder="Find by name...">
        <button class="search-btn" type="submit">Search</button>
    </form>
</div>

<table border="1px solid black">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Lecture</th>
        <th>Year</th>
        <th>Notes</th>
        <th>Action</th>
    </tr>
    </thead>
    <% for(Course c : (ArrayList<Course>) request.getAttribute("listC")) {%>
            <tr>
                <td><%=c.getId()%></td>
                <td><%=c.getName()%></td>
                <td><%=c.getLecture()%></td>
                <td><%=c.getYear()%></td>
                <td><%=c.getNotes()%></td>
                <td>
                    <div>
                        <a class="edit-link-btn" href="edit_course?id=<%=c.getId()%>">Edit</a>
                        <a class="delete-link-btn" href="#" onclick="showMess(<%=c.getId()%>)">Delete</a>
                        <a class="show-score-board-btn" href="show_students?id=<%=c.getId()%>">Show students</a>
                    </div>
                </td>
            </tr>
<%}%>
</table>
</body>
    <script>
        function showMess (id) {
            const option = confirm("Are you sure to delete this course?");
            if(option === true) {
                window.location.href = "delete_course?id="+id;
            }
        }
    </script>
</html>
