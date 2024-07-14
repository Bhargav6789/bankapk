package banker;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dummy customer details (replace with actual logic to retrieve from database)
        String fullName = "John Doe";
        String dob = "1990-01-01";
        String idProof = "Passport";
        String typeOfAccount = "Savings";
        String email = "john.doe@example.com";
        String mobileNumber = "1234567890";
        double balance = 5000.0;

        // Set customer details as request attributes
        request.setAttribute("fullName", fullName);
        request.setAttribute("dob", dob);
        request.setAttribute("idProof", idProof);
        request.setAttribute("typeOfAccount", typeOfAccount);
        request.setAttribute("email", email);
        request.setAttribute("mobileNumber", mobileNumber);
        request.setAttribute("balance", balance);

        // Forward to dashboard JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
