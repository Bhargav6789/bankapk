<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="registration.css">
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<title>Registration Form</title>
</head>
<body>
    <div class="container">
        <header>Registration</header>
        <form action="RegistrationServlet" method="post">
            <div class="form first">
                <div class="details personal">
                    <span class="title">Personal Details</span>
                    <div class="fields">
                        <div class="input-field">
                            <label>Full Name</label>
                            <input type="text" name="fullname" placeholder="Enter your name" required>
                        </div>
                        <div class="input-field">
                            <label>Id Proof</label>
                            <input type="text" name="idproof" placeholder="Enter your id proof" required>
                        </div>
                        <div class="input-field">
                            <label>Date of Birth</label>
                            <input type="date" name="dob" placeholder="Enter birth date" required>
                        </div>
                        <div class="input-field">
                            <label>Email</label>
                            <input type="text" name="email" placeholder="Enter your email" required>
                        </div>
                        <div class="input-field">
                            <label>Mobile Number</label>
                            <input type="number" name="mobilenumber" placeholder="Enter mobile number" required>
                        </div>
                        <div class="input-field">
                            <label>Initial Deposit</label>
                            <input type="number" name="initialDeposit" placeholder="Enter initial deposit" required>
                        </div>
                        <div class="input-field">
                            <label>Gender</label>
                            <select name="gender" required>
                                <option disabled selected>Select gender</option>
                                <option>Male</option>
                                <option>Female</option>
                                <option>Others</option>
                            </select>
                        </div>
                        <div class="input-field">
                            <label>Type of Account</label>
                            <select name="typeofaccount" required>
                                <option disabled selected>Select Account Type</option>
                                <option>Savings Account</option>
                                <option>Current Account</option>
                            </select>
                        </div>
                    </div>
                </div>
                 <div class="buttons">
        <button type="submit" class="submit">
            <span class="btnText">Submit</span>
            <i class="uil uil-navigator"></i>
        </button>
    </div>
            </div>
        </form>
    </div>
    <script src="script.js"></script>
</body>
</html>
