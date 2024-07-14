<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modify Customer Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #5044ec; /* Violet background */
            color: #fff; /* White text */
            text-align: center;
            padding: 50px;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.9); /* White with slight transparency */
            color: #5044ec; /* Violet text */
            padding: 20px;
            border-radius: 10px;
            max-width: 600px;
            margin: 0 auto;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
        }
        h1 {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #5044ec; /* Violet button background */
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            border: none;
        }
        button:hover {
            background-color: #4036c7; /* Darker violet on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Modify Customer Account</h1>
        <form action="FetchCustomerDetailsServlet" method="post">
            <label for="accountnumber">Enter Account Number:</label>
            <input type="text" id="accountnumber" name="accountnumber" required>
            <button type="submit">Fetch Details</button>
        </form>
    </div>
</body>
</html>
