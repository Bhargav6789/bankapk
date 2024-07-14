package banker;

import java.io.IOException;
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

@WebServlet("/CustomerDetailsServlet")
public class CustomerDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountnumber");

        if (accountNumber == null) {
            response.sendRedirect("input.jsp");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            con = DatabaseUtil.getConnection();

            String query = "SELECT fullname, idproof, mobilenumber, gender, typeofaccount, initialdeposit, balance FROM customers WHERE accountnumber = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, accountNumber);
            rs = ps.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setFullName(rs.getString("fullname"));
                customer.setIdProof(rs.getString("idproof"));
                customer.setMobileNumber(rs.getString("mobilenumber"));
                customer.setGender(rs.getString("gender"));
                customer.setTypeOfAccount(rs.getString("typeofaccount"));
                customer.setInitialDeposit(rs.getDouble("initialdeposit"));
                customer.setBalance(rs.getDouble("balance"));

                request.setAttribute("customer", customer);
                request.getRequestDispatcher("done.jsp").forward(request, response);
            } else {
                response.sendRedirect("done.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
