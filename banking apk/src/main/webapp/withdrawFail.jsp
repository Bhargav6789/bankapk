<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Withdrawal Failed</title>
</head>
<body>
    <h1>Withdrawal Failed</h1>
    <p><%= request.getSession().getAttribute("withdrawFail") %></p>
    <a href="withdraw.jsp">Try Again</a>
</body>
</html>
