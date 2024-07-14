<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit Success</title>
    <style>
        body {
            background-color: #5044ec;; 
            color: #fff; 
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        h1 {
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 20px;
        }
        a.button {
            display: inline-block;
            background-color: #fff; 
            color: #5044ec;;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        a.button:hover {
            background-color: #ecf0f1; 
        }
    </style>
</head>
<body>
    <h1>Deposit Successful</h1>
    <p>New Balance: <%= request.getAttribute("newBalance") %></p>
    <a href="HomeServlet" class="button">Back to Dashboard</a>
</body>
</html>
