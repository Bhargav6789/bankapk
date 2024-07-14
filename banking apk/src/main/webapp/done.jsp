<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
package banker;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("accountnumber") == null) {
            response.sendRedirect("input.jsp"); // Redirect to login page if not logged in
            return;
        }

        String accountNumber = (String) session.getAttribute("accountnumber");

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT fullName, dob, idProof, typeOfAccount, email, mobileNumber, balance FROM customers WHERE accountNumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve customer details
                String fullName = resultSet.getString("fullName");
                String dob = resultSet.getString("dob");
                String idProof = resultSet.getString("idProof");
                String typeOfAccount = resultSet.getString("typeOfAccount");
                String email = resultSet.getString("email");
                String mobileNumber = resultSet.getString("mobileNumber");
                double balance = resultSet.getDouble("balance");

                // Generate HTML content dynamically
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
                out.println("<title>Customer Dashboard</title>");
                out.println("<style type=\"text/css\">");
                out.println("    body {");
                out.println("        font-family: Arial, sans-serif;");
                out.println("        background-color: #ffffff; /* White background */");
                out.println("        color: #000000; /* Black text */");
                out.println("        margin: 0;");
                out.println("        padding: 0;");
                out.println("    }");
                out.println("    .navbar {");
                out.println("        background: rgb(65, 81, 255); /* Navbar background color */");
                out.println("        font-family: calibri;");
                out.println("        padding-right: 15px;");
                out.println("        padding-left: 15px;");
                out.println("        overflow: hidden;");
                out.println("    }");
                out.println("    .navdiv {");
                out.println("        display: flex;");
                out.println("        align-items: center;");
                out.println("        justify-content: space-between;");
                out.println("    }");
                out.println("    .logo a {");
                out.println("        font-size: 35px;");
                out.println("        font-weight: 600;");
                out.println("        color: white;");
                out.println("        text-decoration: none;");
                out.println("    }");
                out.println("    ul {");
                out.println("        list-style-type: none;");
                out.println("        margin: 0;");
                out.println("        padding: 0;");
                out.println("        overflow: hidden;");
                out.println("    }");
                out.println("    li {");
                out.println("        float: left;");
                out.println("    }");
                out.println("    li a {");
                out.println("        display: block;");
                out.println("        color: white;");
                out.println("        text-align: center;");
                out.println("        padding: 14px 16px;");
                out.println("        text-decoration: none;");
                out.println("        font-weight: bold;");
                out.println("    }");
                out.println("    li a:hover {");
                out.println("        background-color: #4036c7; /* Darker violet on hover */");
                out.println("    }");
                out.println("    .content {");
                out.println("        padding: 20px;");
                out.println("        margin: 20px;");
                out.println("    }");
                out.println("    table {");
                out.println("        width: 100%;");
                out.println("        border-collapse: collapse;");
                out.println("        margin-top: 20px;");
                out.println("    }");
                out.println("    th, td {");
                out.println("        border: 1px solid black;");
                out.println("        padding: 8px;");
                out.println("        text-align: left;");
                out.println("    }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");

                // Navbar
                out.println("<nav class=\"navbar\">");
                out.println("    <div class=\"navdiv\">");
                out.println("        <div class=\"logo\"><a href=\"HomeServlet\">PRIME BANK</a></div>");
                out.println("        <ul>");
                out.println("            <li><a href=\"deposit.jsp\">DEPOSIT</a></li>");
                out.println("            <li><a href=\"withdraw.jsp\">WITHDRAW</a></li>");
                out.println("            <li><a href=\"transactionServlet\">TRANSACTION HISTORY</a></li>");
                out.println("            <li><a href=\"input.jsp\">LOGOUT</a></li>");
                out.println("        </ul>");
                out.println("    </div>");
                out.println("</nav>");

                // Content
                out.println("<div class=\"content\">");
                out.println("    <h2>WELCOME, " + fullName + "</h2>");
                out.println("    <table>");
                out.println("        <tr>");
                out.println("            <th>Full Name</th>");
                out.println("            <th>Date of Birth</th>");
                out.println("            <th>ID Proof</th>");
                out.println("            <th>Type of Account</th>");
                out.println("            <th>Email</th>");
                out.println("            <th>Mobile Number</th>");
                out.println("            <th>Balance</th>");
                out.println("        </tr>");
                out.println("        <tr>");
                out.println("            <td>" + fullName + "</td>");
                out.println("            <td>" + dob + "</td>");
                out.println("            <td>" + idProof + "</td>");
                out.println("            <td>" + typeOfAccount + "</td>");
                out.println("            <td>" + email + "</td>");
                out.println("            <td>" + mobileNumber + "</td>");
                out.println("            <td>" + balance + "</td>");
                out.println("        </tr>");
                out.println("    </table>");
                out.println("</div>");

                out.println("</body>");
                out.println("</html>");

            } else {
                response.getWriter().println("Customer not found for account number: " + accountNumber);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching customer details", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
