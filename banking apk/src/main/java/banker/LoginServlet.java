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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jdbcURL = "jdbc:mysql://localhost:3306/bankerDB";
    private String dbUser = "root";
    private String dbPassword = "67896789Bhargav@";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountnumber");
        String password = request.getParameter("password");

        if (accountNumber == null || accountNumber.isEmpty() || password == null || password.isEmpty()) {
            response.getWriter().println("Account number or password is null or empty. Please provide valid credentials.");
            return;
        }

        String sql = "SELECT temppassword FROM customers WHERE accountnumber = ? AND password = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tempPasswordFlag = resultSet.getString("temppassword");
                HttpSession session = request.getSession();
                session.setAttribute("accountnumber", accountNumber); // Set the account number in the session

                // Retrieve customer details immediately upon successful login
                String customerDetailsSQL = "SELECT fullName, dob, idProof, typeOfAccount, email, mobileNumber, balance FROM customers WHERE accountNumber = ?";
                PreparedStatement customerStatement = connection.prepareStatement(customerDetailsSQL);
                customerStatement.setString(1, accountNumber);
                ResultSet customerResultSet = customerStatement.executeQuery();

                if (customerResultSet.next()) {
                    // Retrieve customer details
                    String fullName = customerResultSet.getString("fullName");
                    String dob = customerResultSet.getString("dob");
                    String idProof = customerResultSet.getString("idProof");
                    String typeOfAccount = customerResultSet.getString("typeOfAccount");
                    String email = customerResultSet.getString("email");
                    String mobileNumber = customerResultSet.getString("mobileNumber");
                    double balance = customerResultSet.getDouble("balance");

                    // Create a Customer object to hold the details
                    Customer customer = new Customer();
                    customer.setFullName(fullName);
                    customer.setDob(dob);
                    customer.setIdProof(idProof);
                    customer.setTypeOfAccount(typeOfAccount);
                    customer.setAccountNumber(accountNumber);
                    customer.setEmail(email);
                    customer.setMobileNumber(mobileNumber);
                    customer.setBalance(balance);

                    // Set customer object as a request attribute
                    request.setAttribute("customer", customer);

                    // After setting customer attributes and determining tempPasswordFlag
                    customerResultSet.close();
                    customerStatement.close();
                    resultSet.close();
                    statement.close();
                    connection.close();

                    if ("1".equals(tempPasswordFlag)) {
                        response.sendRedirect("cnfmpass.jsp");
                    } else {
                        // Forward to done.jsp after setting customer attribute
                        request.getRequestDispatcher("HomeServlet").forward(request, response);
                    }
                } else {
                    response.getWriter().println("Customer not found for account number: " + accountNumber);
                }
            } else {
                response.getWriter().println("Invalid account number or password. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
