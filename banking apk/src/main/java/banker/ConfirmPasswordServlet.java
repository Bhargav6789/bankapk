package banker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ConfirmPasswordServlet")
public class ConfirmPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jdbcURL = "jdbc:mysql://localhost:3306/bankerDB";
    private String dbUser = "root";
    private String dbPassword = "67896789Bhargav@";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountnumber");
        String newPassword = request.getParameter("newpassword");
        String confirmPassword = request.getParameter("confirmpassword");

        if (accountNumber == null || accountNumber.isEmpty()) {
            response.getWriter().println("Session expired or invalid account number.");
            return;
        }

        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            response.getWriter().println("Passwords do not match. Please try again.");
            return;
        }

        String sql = "UPDATE customers SET password = ?, temppassword = 0 WHERE accountnumber = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, accountNumber);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("sec.jsp");  // Redirect to success page
            } else {
                response.getWriter().println("Failed to update password. Please try again.");
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
