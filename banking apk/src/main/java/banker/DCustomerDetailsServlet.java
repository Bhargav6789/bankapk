package banker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DCustomerDetailsServlet")
public class DCustomerDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch customer details, possibly from a database or another source
        Customer customer = getCustomerDetails();

        // Set the customer attribute in the request scope
        request.setAttribute("customer", customer);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/CustomerDetails.jsp").forward(request, response);
    }

    private Customer getCustomerDetails() {
        // Simulating fetching customer details from a database or other source
        Customer customer = new Customer();
        customer.setFullName("John Doe");
        customer.setIdProof("ID123456");
        customer.setMobileNumber("1234567890");
        customer.setGender("Male");
        customer.setTypeOfAccount("Savings");
        customer.setInitialDeposit(1000.0);
        customer.setBalance(1500.0);
        return customer;
    }
}
