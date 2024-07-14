<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, banker.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>
    <style type="text/css">
        body {
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
            padding: 20px; /* Adding padding for better spacing */
            background-color: rgb(65, 81, 255); /* Fallback background color */

            background-size: cover;
            background-position: center;
        }
        h2 {
            color: rgb(23, 23, 24); /* Header color */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: rgb(65, 81, 255); /* Background color for the table */
            color: white; /* Text color for table headers and data */
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 3px solid black;
        }
        th {
            background-color: black; /* Background color for table headers */
        }
        tr:nth-child(even) {
            background-color: rgb(65, 81, 255); /* Alternate row background color */
        }
        tr:hover {
            background-color: rgb(65, 81, 255); /* Hover color for rows */
        }
        td.no-data {
            text-align: center;
            font-style: italic;
            color: black; /* Color for no data message */
        }
    </style>
</head>
<body>
    <h2>TRANSACTION HISTORY</h2>
    <table border="1">
        <tr>
            <th>Transaction ID</th>
            <th>Transaction Type</th>
            <th>Amount</th>
            <th>Transaction Date</th>
        </tr>
        <%
            List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
            if (transactions != null && !transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
        %>
                <tr>
                    <td><%= transaction.getTransactionId() %></td>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getDate()) %></td>
                </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="4" class="no-data">No transactions found.</td>
                </tr>
        <%
            }
        %>
    </table>
</body>
</html>
