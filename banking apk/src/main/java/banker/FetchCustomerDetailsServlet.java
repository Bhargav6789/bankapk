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

@WebServlet("/FetchCustomerDetailsServlet")
public class FetchCustomerDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jdbcURL = "jdbc:mysql://localhost:3306/bankerDB";
    private String dbUser = "root";
    private String dbPassword = "67896789Bhargav@";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountnumber");

        String fetchDetailsSQL = "SELECT * FROM customers WHERE accountnumber = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement statement = connection.prepareStatement(fetchDetailsSQL);
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                boolean isActive = resultSet.getBoolean("active");
                if (!isActive) {
                    response.getWriter().println("Account is inactive.");
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().println("<!DOCTYPE html>");
                    response.getWriter().println("<html>");
                    response.getWriter().println("<head>");
                    response.getWriter().println("<meta charset='UTF-8'>");
                    response.getWriter().println("<title>Modify Customer Details</title>");
                    response.getWriter().println("<style>");
                    response.getWriter().println("body { background-color: #5044ec; color: #fff; font-family: Arial, sans-serif; text-align: center; padding: 50px; }");
                    response.getWriter().println("h1 { margin-bottom: 20px; }");
                    response.getWriter().println("form { max-width: 400px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); color: #5044ec; }");
                    response.getWriter().println("label { display: block; margin-bottom: 10px; }");
                    response.getWriter().println("input[type='text'], input[type='date'], input[type='email'], input[type='number'] { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; }");
                    response.getWriter().println("input[type='submit'] { background-color: #5044ec; color: #fff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; }");
                    response.getWriter().println("input[type='submit']:hover { background-color: #4036c7; }");
                    response.getWriter().println("</style>");
                    response.getWriter().println("</head>");
                    response.getWriter().println("<body>");
                    response.getWriter().println("<h1>Modify Customer Details</h1>");
                    response.getWriter().println("<form action='UpdateCustomerDetailsServlet' method='post'>");
                    response.getWriter().println("<input type='hidden' name='accountnumber' value='" + resultSet.getString("accountnumber") + "'>");
                    response.getWriter().println("<label for='fullname'>Full Name:</label>");
                    response.getWriter().println("<input type='text' id='fullname' name='fullname' value='" + resultSet.getString("fullname") + "' required>");
                    response.getWriter().println("<label for='idproof'>ID Proof:</label>");
                    response.getWriter().println("<input type='text' id='idproof' name='idproof' value='" + resultSet.getString("idproof") + "' required>");
                    response.getWriter().println("<label for='dob'>Date of Birth:</label>");
                    response.getWriter().println("<input type='date' id='dob' name='dob' value='" + resultSet.getString("dob") + "' required>");
                    response.getWriter().println("<label for='email'>Email:</label>");
                    response.getWriter().println("<input type='email' id='email' name='email' value='" + resultSet.getString("email") + "' required>");
                    response.getWriter().println("<label for='mobilenumber'>Mobile Number:</label>");
                    response.getWriter().println("<input type='text' id='mobilenumber' name='mobilenumber' value='" + resultSet.getString("mobilenumber") + "' required>");
                    response.getWriter().println("<label for='gender'>Gender:</label>");
                    response.getWriter().println("<input type='text' id='gender' name='gender' value='" + resultSet.getString("gender") + "' required>");
                    response.getWriter().println("<label for='typeofaccount'>Type of Account:</label>");
                    response.getWriter().println("<input type='text' id='typeofaccount' name='typeofaccount' value='" + resultSet.getString("typeofaccount") + "' required>");
                    response.getWriter().println("<label for='active'>Account Active:</label>");
                    response.getWriter().println("<input type='checkbox' id='active' name='active' " + (resultSet.getBoolean("active") ? "checked" : "") + ">");
                    response.getWriter().println("<input type='submit' value='Update Details'>");
                    response.getWriter().println("</form>");
                    response.getWriter().println("</body>");
                    response.getWriter().println("</html>");
                }
            } else {
                response.getWriter().println("Account number not found.");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}
