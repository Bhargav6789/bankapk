<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modify Customer Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #5044ec;
            color: white;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            color: #5044ec;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type="text"],
        input[type="email"],
        input[type="date"],
        input[type="password"],
        input[type="checkbox"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #5044ec;
            border-radius: 4px;
        }
        input[type="checkbox"] {
            width: auto;
        }
        button {
            background-color: #5044ec;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        button:hover {
            background-color: #4038d3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Modify Customer Details</h1>
        <form action="UpdateCustomerDetailsServlet" method="post">
            <input type="hidden" name="accountnumber" value="${accountnumber}">
            <label for="fullname">Full Name:</label>
            <input type="text" id="fullname" name="fullname" value="${fullname}" required>
            <br>
            <label for="idproof">ID Proof:</label>
            <input type="text" id="idproof" name="idproof" value="${idproof}" required>
            <br>
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${dob}" required>
            <br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${email}" required>
            <br>
            <label for="mobilenumber">Mobile Number:</label>
            <input type="text" id="mobilenumber" name="mobilenumber" value="${mobilenumber}" required>
            <br>
            <label for="gender">Gender:</label>
            <input type="text" id="gender" name="gender" value="${gender}" required>
            <br>
            <label for="typeofaccount">Type of Account:</label>
            <input type="text" id="typeofaccount" name="typeofaccount" value="${typeofaccount}" required>
            <br>
            <label for="active">Account Active:</label>
            <input type="checkbox" id="active" name="active" <c:if test="${active}">checked</c:if>>
            <br>
            <button type="submit">Update Details</button>
        </form>
    </div>
</body>
</html>
