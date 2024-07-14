package banker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT accountnumber, fullname, idproof, dob, email, mobilenumber, gender, typeofaccount, initialdeposit, balance FROM customers";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("accountnumber"));
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
        }
        return customers;
    }
}
