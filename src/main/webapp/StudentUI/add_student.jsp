<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Student</title>
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
        .reset-btn {
            display: inline-block;
            margin: 0px 0px 10px 0px;
            background-color: #45a049;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 14px;
            border-radius: 5px;
            padding: 10px 20px;
        }


    </style>
</head>
<body>
<h1>Add New Student</h1>
<form action="add_student" method="post">
    <label>Name:</label>
    <input type="text" name="name" required><br><br>

    <label>Grade:</label>
    <input type="number" name="grade" required><br><br>

    <label>Birthday:</label>
    <input type="date" name="birthday" required><br><br>

    <label>Address:</label>
    <input type="text" name="address" ><br><br>

    <label>Notes:</label>
    <input type="text" name="notes" ><br><br>
    <input type="submit" value="Save">
    <a class="reset-btn" href="add_student" > Reset</a>
    <a href="student_list">Back to Student List</a>

</form>
</body>
</html>
