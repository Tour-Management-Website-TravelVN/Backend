package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Customer;
import ads.objects.TourOperator;

public class CustomerFunctionImpl implements CustomerFunction {
private static ConnectionPool cp = ConnectionPoolImpl.getInstance();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	//Đối tượng kết nối
	private Connection con;
	
	private static volatile CustomerFunctionImpl instance;
	
	//Singleton Pattern
	public static CustomerFunctionImpl getInstance() {
        if (instance == null) {
            Class var0 = CustomerFunctionImpl.class;
            synchronized (CustomerFunctionImpl.class) {
                if (instance == null) {
                    instance = new CustomerFunctionImpl();
                }
            }
        }

        return instance;
    }
	
	public CustomerFunctionImpl() {
		
	}
	
	public Connection getConnection(ConnectionPool cp) {
        try {
            //Xin ket noi de lam viec	
            this.con = CustomerFunctionImpl.cp.getConnection("Customer");

            //Kiem tra che do thuc thi tu dong
            if (this.con.getAutoCommit()) {
                //Cham dut che do thuc thi tu dong

                this.con.setAutoCommit(false);
            }
            
            return con;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }

	@Override
	public ArrayList<Customer> getFirst100Customers() {
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<Customer> customers = new ArrayList<>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 100 bản ghi
	        String sql = "SELECT c_id, firstname, lastname, date_of_birth, gender, address, phone_number, citizen_id, nationality, passport, note " +
	                     "FROM customer LIMIT 500";
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	            Customer customer = new Customer();
	            
	            customer.setId(rs.getInt("c_id"));
	            customer.setFirstname(rs.getString("firstname"));
	            customer.setLastname(rs.getString("lastname"));
	            
	            java.sql.Date sqlDate = rs.getDate("date_of_birth");
	            LocalDate dateOfBirth = (sqlDate != null) ? sqlDate.toLocalDate() : null;
	            customer.setDateOfBirth(dateOfBirth);
	            
	            customer.setGender(rs.getBoolean("gender"));
	            customer.setAddress(rs.getString("address"));
	            customer.setPhoneNumber(rs.getString("phone_number"));
	            customer.setCitizenId(rs.getString("citizen_id"));
	            customer.setNationality(rs.getString("nationality"));
	            customer.setPassport(rs.getString("passport"));
	            customer.setNote(rs.getString("note"));
	            
	            customers.add(customer);
	        }
	        
	        return customers;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if(this.con != null) {
	                this.con.rollback();
	            }
	        }
	        catch(SQLException e1) {
	            e1.printStackTrace();
	        }
	        return new ArrayList<>();
	    } finally {
	        try {
	            if(rs != null) rs.close();
	            if(pre != null) pre.close();
	            if(this.con != null) {
	                this.cp.releaseConnection(this.con, "Customer");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
