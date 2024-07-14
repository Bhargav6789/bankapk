<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirm Password</title>
    <style type="text/css">
        body {
            background-color: #5044ec; /* Blue background */
            color: #211f1f; /* White text */
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h1 {
            text-align: center;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #ccccccd0; /* White background */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccccccd0;
            border-radius: 5px;
            box-sizing: border-box;
        }
        button[type="submit"] {
            background-color: #5044ec; /* Darker blue button */
            color: #252424;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button[type="submit"]:hover {
            background-color: #5044ec; /* Lighter blue on hover */
        }
    </style>
</head>
<body>
    <h1>Confirm Password</h1>
    <form action="ConfirmPasswordServlet" method="post">
        <label for="newpassword">New Password:</label>
        <input type="password" id="newpassword" name="newpassword" required><br><br>
        <label for="confirmpassword">Confirm Password:</label>
        <input type="password" id="confirmpassword" name="confirmpassword" required><br><br>
        <button type="submit">Update Password</button>
    </form>
</body>
</html>
