<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="banker.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Customer Details</title>
    <style type="text/css">
        /* Add your styles here */
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="navdiv">
            <div class="logo"><a href="DCustomerDetails.jsp">PRIME BANK</a></div>
            <ul>
                <li><a href="deposit.jsp">DEPOSIT</a></li>
                <li><a href="withdraw.jsp">WITHDRAW</a></li>
                <li><a href="transactionHistory">TRANSACTION HISTORY</a></li>
                <button><a href="Dlogout.jsp">LOGOUT</a></button>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h2>Customer Details</h2>
        <%
            Customer customer = (Customer) request.getAttribute("customer");
            if (customer != null) {
        %>
        <table>
            <tr>
                <td>Full Name:</td>
                <td><%= customer.getFullName() %></td>
            </tr>
            <tr>
                <td>ID Proof:</td>
                <td><%= customer.getIdProof() %></td>
            </tr>
            <tr>
                <td>Date of Birth:</td>
                <td><%= customer.getDateOfBirth() %></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><%= customer.getEmail() %></td>
            </tr>
            <tr>
                <td>Mobile Number:</td>
                <td><%= customer.getMobileNumber() %></td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td><%= customer.getGender() %></td>
            </tr>
            <tr>
                <td>Type of Account:</td>
                <td><%= customer.getTypeOfAccount() %></td>
            </tr>
            <tr>
                <td>Initial Deposit:</td>
                <td><%= customer.getInitialDeposit() %></td>
            </tr>
            <tr>
                <td>Balance:</td>
                <td><%= customer.getBalance() %></td>
            </tr>
        </table>
        <%
            } else {
        %>
        <p>No customer details found.</p>
        <%
            }
        %>
    </div>
</body>
</html>
