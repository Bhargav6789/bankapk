<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Money</title>
    <style>
        body {
             background-color: #3a5ee2;  /* Violet background */
            color: #e4d9d9; /* White text */
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        .header {
            background-color: #3a5ee2; /* Violet background */
            color: #212020; /* White text */
            padding: 20px;
            margin-bottom: 20px;
        }

        .header h1 {
            margin: 0;
        }

        .content {
            background-color: #3a5ee2; /* White background */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        form {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type=number] {
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #161515;
            width: 200px;
        }

        button {
            background-color: #1d1e1fe5; /* Violet button */
            color: #dbd6d6; /* White text */
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        button:hover {
            background-color: #3b3b3d; /* Darker violet on hover */
        }

        .footer {
            margin-top: 20px;
            text-align: center;
            font-size: 14px;
            color: #1f1e1e;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Withdraw Money</h1>
        </div>
        
        <div class="content">
            <form action="WithdrawServlet" method="post">
                <label for="amount">Enter Amount to Withdraw:</label>
                <input type="number" id="amount" name="amount" required>
                <br><br>
                <button type="submit">Withdraw</button>
            </form>
        </div>
        
        <div class="footer">
            
        </div>
    </div>
</body>
</html>
