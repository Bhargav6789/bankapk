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

                // Set customer details in the request attributes (optional)
                request.setAttribute("fullName", fullName);
                request.setAttribute("dob", dob);
                request.setAttribute("idProof", idProof);
                request.setAttribute("typeOfAccount", typeOfAccount);
                request.setAttribute("email", email);
                request.setAttribute("mobileNumber", mobileNumber);
                request.setAttribute("balance", balance);

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
                out.println("    * {");
                out.println("        text-decoration: none;");
                out.println("    }");
                out.println("    body {");
                out.println("        font-family: Arial, Helvetica, sans-serif;");
                out.println("        margin: 0;");
                out.println("        padding: 0;");
                out.println("        background-color: #f0f0f0; /* Fallback color */");
                out.println("        background-size: cover;");
                out.println("        background-position: center;");
                out.println("    }");
                out.println("    .navbar {");
                out.println("        background: rgb(65, 81, 255);");
                out.println("        font-family: calibri;");
                out.println("        padding-right: 15px;");
                out.println("        padding-left: 15px;");
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
                out.println("    }");
                out.println("    ul {");
                out.println("        list-style-type: none;");
                out.println("        margin: 0;");
                out.println("        padding: 0;");
                out.println("    }");
                out.println("    li {");
                out.println("        display: inline;");
                out.println("        margin-right: 15px;");
                out.println("    }");
                out.println("    li a {");
                out.println("        color: white;");
                out.println("        font-size: 18px;");
                out.println("        font-weight: bold;");
                out.println("    }");
                out.println("    button {");
                out.println("        background-color: black;");
                out.println("        margin-left: 10px;");
                out.println("        border-radius: 10px;");
                out.println("        padding: 10px;");
                out.println("        width: 90px;");
                out.println("    }");
                out.println("    button a {");
                out.println("        color: white;");
                out.println("        font-weight: bold;");
                out.println("        font-size: 15px;");
                out.println("    }");
                out.println("    .content {");
                out.println("        padding: 20px;");
                out.println("    }");
                out.println("    table {");
                out.println("        width: 100%;");
                out.println("        border-collapse: collapse;");
                out.println("        background-color: rgb(65, 81, 255); /* Background color for the table */");
                out.println("        color: white; /* Text color for table headers and data */");
                out.println("    }");
                out.println("    th, td {");
                out.println("        padding: 15px;");
                out.println("        text-align: left;");
                out.println("        border: 1px solid black;");
                out.println("    }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");

                // Navbar
                out.println("<nav class=\"navbar\">");
                out.println("<div class=\"navdiv\">");
                out.println("<div class=\"logo\"><a href=\"HomeServlet\">PRIME BANK</a></div>");
                out.println("<ul>");
                out.println("<li><a href=\"deposit.jsp\">DEPOSIT</a></li>");
                out.println("<li><a href=\"withdraw.jsp\">WITHDRAW</a></li>");
                out.println("<li><a href=\"transactionServlet\">TRANSACTION HISTORY</a></li>");
                out.println("<button><a href=\"input.jsp\">LOGOUT</a></button>");
                out.println("</ul>");
                out.println("</div>");
                out.println("</nav>");

                // Content
                out.println("<div class=\"content\">");
                out.println("<h2>WELCOME, " + fullName + "</h2>");
                out.println("<table border=\"1\">");
                out.println("<tr>");
                out.println("<th>Full Name</th>");
                out.println("<th>Date of Birth</th>");
                out.println("<th>ID Proof</th>");
                out.println("<th>Type of Account</th>");
                out.println("<th>Email</th>");
                out.println("<th>Mobile Number</th>");
                out.println("<th>Balance</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + fullName + "</td>");
                out.println("<td>" + dob + "</td>");
                out.println("<td>" + idProof + "</td>");
                out.println("<td>" + typeOfAccount + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + mobileNumber + "</td>");
                out.println("<td>" + balance + "</td>");
                out.println("</tr>");
                out.println("</table>");
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