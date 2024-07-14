package banker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("accountnumber") == null) {
            response.sendRedirect("done.jsp");
            return;
        }

        int accountnumber = (int) session.getAttribute("accountnumber");

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT transactionId, transactionType, amount, transactionDate FROM transactions WHERE accountnumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accountnumber);

            ResultSet resultSet = statement.executeQuery();

            List<Transaction> transactions = new ArrayList<>();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transactionId"));
                transaction.setTransactionType(resultSet.getString("transactionType"));
                transaction.setAmount(resultSet.getDouble("amount"));

                // Convert string date to Date object
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parsedDate = dateFormat.parse(resultSet.getString("transactionDate"));
                transaction.setDate(new java.sql.Timestamp(parsedDate.getTime()));

                transactions.add(transaction);
            }

            // Set transactions list as request attribute
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
        } catch (SQLException | ParseException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
