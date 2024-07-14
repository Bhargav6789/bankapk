<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
</head>
<body>
    <h1>Welcome to Your Dashboard</h1>
    <p>Account Number: <%= session.getAttribute("accountNumber") %></p>
    
    <!-- Other dashboard content here -->
    
    <form action="WithdrawServlet" method="post">
        <label>Enter Withdraw Amount:</label>
        <input type="text" name="amount" required>
        <button type="submit">Withdraw</button>
    </form>
</body>
</html>
