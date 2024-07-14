<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Customer Account</title>
    <style>
        body {
            background-color: #5044ec; /* Background color */
            color: #fff; /* Text color */
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        .container {
            background-color: #fff; /* Container background */
            color: #5044ec; /* Text color inside container */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 0 auto;
        }
        h1 {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        button[type="submit"] {
            background-color: #5044ec; /* Button background */
            color: #fff; /* Button text */
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button[type="submit"]:hover {
            background-color: #4036c7; /* Button hover background */
        }
        p {
            color: red; /* Error message color */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Delete Customer Account</h1>
        <form action="DeleteCustomerServlet" method="post">
            <label for="accountNumber">Enter Account Number:</label>
            <input type="number" id="accountNumber" name="accountNumber" required>
            <button type="submit">Delete Account</button>
        </form>
        <%
            if (request.getParameter("message") != null) {
                out.println("<p>" + request.getParameter("message") + "</p>");
            }
        %>
    </div>
</body>
</html>
