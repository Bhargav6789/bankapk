package banker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private String jdbcURL = "jdbc:mysql://localhost:3306/bankerDB";
    private String dbUser = "root";
    private String dbPassword = "67896789Bhargav@";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        if (accountNumber == null || accountNumber.isEmpty()) {
            response.sendRedirect("deleteCustomer.jsp?message=Account number is required.");
            return;
        }

        String checkStatusSQL = "SELECT status FROM customers WHERE accountnumber = ?";
        String updateSQL = "UPDATE customers SET status = 'inactive' WHERE accountnumber = ?";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Check the current status of the account
            PreparedStatement checkStatement = connection.prepareStatement(checkStatusSQL);
            checkStatement.setString(1, accountNumber);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String status = resultSet.getString("status");
                if ("inactive".equals(status)) {
                    response.sendRedirect("deleteCustomer.jsp?message=Account not exist.");
                } else {
                    // Account is active, proceed to mark it as inactive
                    PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
                    updateStatement.setString(1, accountNumber);
                    int rowsUpdated = updateStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        response.sendRedirect("deleteCustomer.jsp?message=Account successfully deleted.");
                    } else {
                        response.sendRedirect("deleteCustomer.jsp?message=Account number not found.");
                    }

                    updateStatement.close();
                }
            } else {
                response.sendRedirect("deleteCustomer.jsp?message=Account number not found.");
            }

            // Close the connection
            resultSet.close();
            checkStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("deleteCustomer.jsp?message=An error occurred: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
