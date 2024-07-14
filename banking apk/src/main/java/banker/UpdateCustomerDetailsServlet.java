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

@WebServlet("/UpdateCustomerDetailsServlet")
public class UpdateCustomerDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jdbcURL = "jdbc:mysql://localhost:3306/bankerDB";
    private String dbUser = "root";
    private String dbPassword = "67896789Bhargav@";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountnumber");
        String fullName = request.getParameter("fullname");
        String idProof = request.getParameter("idproof");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String mobileNumber = request.getParameter("mobilenumber");
        String gender = request.getParameter("gender");
        String typeOfAccount = request.getParameter("typeofaccount");
        boolean isActive = request.getParameter("active") != null;

        String checkAccountStatusSQL = "SELECT active FROM customers WHERE accountnumber = ?";
        String updateDetailsSQL = "UPDATE customers SET FullName = ?, IdProof = ?, dob = ?, email = ?, mobilenumber = ?, gender = ?, typeofaccount = ?, active = ? WHERE accountnumber = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Check the account status
            PreparedStatement checkStatement = connection.prepareStatement(checkAccountStatusSQL);
            checkStatement.setString(1, accountNumber);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                boolean currentStatus = resultSet.getBoolean("active");
                if (!currentStatus) {
                    response.getWriter().println("Your account is already closed and cannot be modified.");
                } else {
                    PreparedStatement statement = connection.prepareStatement(updateDetailsSQL);
                    statement.setString(1, fullName);
                    statement.setString(2, idProof);
                    statement.setString(3, dob);
                    statement.setString(4, email);
                    statement.setString(5, mobileNumber);
                    statement.setString(6, gender);
                    statement.setString(7, typeOfAccount);
                    statement.setBoolean(8, isActive);
                    statement.setString(9, accountNumber);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        response.getWriter().println("Customer details updated successfully!");
                    } else {
                        response.getWriter().println("Failed to update customer details. Please try again.");
                    }

                    statement.close();
                }
            } else {
                response.getWriter().println("Account number not found.");
            }

            resultSet.close();
            checkStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}
