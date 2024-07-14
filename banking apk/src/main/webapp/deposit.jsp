<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit Money</title>
    <style>
        body {
            background-color: #5044ec; /* Violet background */
            color: #fff; /* White text */
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        h1 {
            margin-bottom: 20px;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff; /* White form background */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #5044ec; /* Violet text for labels */
        }
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #5044ec; /* Violet border */
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #5044ec; /* Violet button */
            color: #fff; /* White text */
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #fff; /* White on hover */
            color: #5044ec; /* Violet text on hover */
        }
    </style>
</head>
<body>
    <h1>Deposit Money</h1>
    <form action="DepositServlet" method="post">
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required><br><br>

        <input type="submit" value="Deposit">
    </form>
</body>
</html>
