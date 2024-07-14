package banker;

import java.io.IOException;
import java.sql.Connection;
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
import javax.servlet.http.HttpSession;

@WebServlet("/transactionServlet")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Retrieve existing session, do not create new
        if (session == null || session.getAttribute("accountnumber") == null) {
            response.sendRedirect("input.jsp"); // Redirect to login page if session doesn't exist or account number not set
            return;
        }

        String accountNumber = (String) session.getAttribute("accountnumber");
        String transactionType = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try (Connection connection = DatabaseUtil.getConnection()) {
            // Process transaction
            if ("deposit".equals(transactionType)) {
                updateBalance(connection, Integer.parseInt(accountNumber), amount, true);
            } else if ("withdraw".equals(transactionType)) {
                updateBalance(connection, Integer.parseInt(accountNumber), amount, false);
            } else {
                throw new ServletException("Invalid transaction type: " + transactionType);
            }

            // Add transaction record
            addTransaction(connection, Integer.parseInt(accountNumber), transactionType, amount);

            // Fetch transaction history
            List<Transaction> transactions = getTransactionHistory(connection, Integer.parseInt(accountNumber));

            // Set transactions as request attribute and forward to JSP
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
        } catch (NumberFormatException | SQLException e) {
            throw new ServletException("Error processing transaction", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Retrieve existing session, do not create new
        if (session == null || session.getAttribute("accountnumber") == null) {
            response.sendRedirect("input.jsp"); // Redirect to login page if session doesn't exist or account number not set
            return;
        }

        String accountNumber = (String) session.getAttribute("accountnumber");

        try (Connection connection = DatabaseUtil.getConnection()) {
            // Fetch transaction history
            List<Transaction> transactions = getTransactionHistory(connection, Integer.parseInt(accountNumber));

            // Set transactions as request attribute and forward to JSP
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error fetching transaction history", e);
        }
    }

    private void updateBalance(Connection connection, int accountNumber, double amount, boolean isDeposit) throws SQLException {
        String sql = isDeposit ? "UPDATE customers SET balance = balance + ? WHERE accountnumber = ?"
                               : "UPDATE customers SET balance = balance - ? WHERE accountnumber = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, accountNumber);
            ps.executeUpdate();
        }
    }

    private void addTransaction(Connection connection, int accountNumber, String transactionType, double amount) throws SQLException {
        String sql = "INSERT INTO transactions (accountnumber, transactionType, amount, transactionDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountNumber);
            ps.setString(2, transactionType);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        }
    }

    private List<Transaction> getTransactionHistory(Connection connection, int accountNumber) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactionId, transactionType, amount, transactionDate FROM transactions WHERE accountnumber = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transactionId"));
                    transaction.setTransactionType(rs.getString("transactionType"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setDate(rs.getTimestamp("transactionDate"));
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }
}
