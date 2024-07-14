package banker;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewCustomerServlet")
public class ViewCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String URL = "jdbc:mysql://localhost:3306/bankerDB";
    private static final String USER = "root";
    private static final String PASSWORD = "67896789Bhargav@";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establish connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare SQL query without Customer ID
            String query = "SELECT fullname, idproof, dob, email, mobilenumber, gender, typeofaccount, initialdeposit, balance FROM customers";
            ps = con.prepareStatement(query);

            // Execute query
            rs = ps.executeQuery();

            // Process results
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setFullName(rs.getString("fullname"));
                customer.setIdProof(rs.getString("idproof"));
                customer.setDateOfBirth(rs.getString("dob"));
                customer.setEmail(rs.getString("email"));
                customer.setMobileNumber(rs.getString("mobilenumber"));
                customer.setGender(rs.getString("gender"));
                customer.setTypeOfAccount(rs.getString("typeofaccount"));
                customer.setInitialDeposit(rs.getDouble("initialdeposit"));
                customer.setBalance(rs.getDouble("balance"));
                customers.add(customer);
            }

            // Set content type and get writer
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            // Generate HTML content with styling
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Customers</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #5044ec; color: white; }");
            out.println(".container { width: 80%; margin: 0 auto; padding: 20px; }");
            out.println("h2 { text-align: center; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th, td { padding: 10px; text-align: left; border: 1px solid white; }");
            out.println("th { background-color: white; color: #5044ec; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Customer Details</h2>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Full Name</th>");
            out.println("<th>ID Proof</th>");
            out.println("<th>Date of Birth</th>");
            out.println("<th>Email</th>");
            out.println("<th>Mobile Number</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Type of Account</th>");
            out.println("<th>Initial Deposit</th>");
            out.println("<th>Balance</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            for (Customer customer : customers) {
                out.println("<tr>");
                out.println("<td>" + customer.getFullName() + "</td>");
                out.println("<td>" + customer.getIdProof() + "</td>");
                out.println("<td>" + customer.getDateOfBirth() + "</td>");
                out.println("<td>" + customer.getEmail() + "</td>");
                out.println("<td>" + customer.getMobileNumber() + "</td>");
                out.println("<td>" + customer.getGender() + "</td>");
                out.println("<td>" + customer.getTypeOfAccount() + "</td>");
                out.println("<td>" + customer.getInitialDeposit() + "</td>");
                out.println("<td>" + customer.getBalance() + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            throw new ServletException("Error fetching customer details", e);
        } finally {
            // Close resources in finally block
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignored */ }
            try { if (ps != null) ps.close(); } catch (SQLException e) { /* ignored */ }
            try { if (con != null) con.close(); } catch (SQLException e) { /* ignored */ }
        }
    }
}
