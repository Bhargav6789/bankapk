<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Status</title>
<link rel="stylesheet" href="registration.css">
</head>
<body>
    <div class="container">
        <header>Registration Status</header>
        <div class="message">
            <p>${param.message}</p>
        </div>
        <div class="buttons">
            <button onclick="window.location.href='input.jsp'">LOG OUT</button>
        </div>
    </div>
</body>
</html>
