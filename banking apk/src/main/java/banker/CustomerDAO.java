package banker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    
    public Customer getCustomerByAccountNumber(String accountNumber) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE accountnumber = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setFullName(rs.getString("fullName"));
                    customer.setDob(rs.getString("dob"));
                    customer.setIdProof(rs.getString("idProof"));
                    customer.setTypeOfAccount(rs.getString("typeOfAccount"));
                    customer.setEmail(rs.getString("email"));
                    customer.setMobileNumber(rs.getString("mobileNumber"));
                    customer.setBalance(rs.getDouble("balance"));
                }
            }
        }
        return customer;
    }
}
