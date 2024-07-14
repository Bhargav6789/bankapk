<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Customers</title>
</head>
<body>
    <h2>Customer Details</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Customer ID</th>
                <th>Full Name</th>
                <th>ID Proof</th>
                <th>Date of Birth</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Gender</th>
                <th>Type of Account</th>
                <th>Initial Deposit</th>
                <th>Balance</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.customerId}</td>
                    <td>${customer.fullName}</td>
                    <td>${customer.idProof}</td>
                    <td>${customer.dateOfBirth}</td>
                    <td>${customer.email}</td>
                    <td>${customer.mobileNumber}</td>
                    <td>${customer.gender}</td>
                    <td>${customer.typeOfAccount}</td>
                    <td>${customer.initialDeposit}</td>
                    <td>${customer.balance}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
