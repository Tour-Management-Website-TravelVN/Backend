package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Administrator;
import ads.objects.UserAccount;

import java.sql.ResultSet;

public class AdministratorFunctionImpl implements AdministratorFunction {
private static ConnectionPool cp = ConnectionPoolImpl.getInstance();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	//Đối tượng kết nối
	private Connection con;
	
	private static volatile AdministratorFunctionImpl instance;
	
	//Singleton Pattern
	public static AdministratorFunctionImpl getInstance() {
        if (instance == null) {
            Class var0 = AdministratorFunctionImpl.class;
            synchronized (AdministratorFunctionImpl.class) {
                if (instance == null) {
                    instance = new AdministratorFunctionImpl();
                }
            }
        }

        return instance;
    }
	
	public AdministratorFunctionImpl() {
		
	}
	
	public Connection getConnection(ConnectionPool cp) {
        try {
            //Xin ket noi de lam viec	
            this.con = AdministratorFunctionImpl.cp.getConnection("Administrator");

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
	public Administrator getAdministratorByUsername(String username) {
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "SELECT a.administrator_id, ua.username, ua.password, ua.email FROM administrator a " +
	                     "JOIN user_account ua ON a.administrator_id = ua.administrator_id " +
	                     "WHERE ua.username = ?";
	        
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, username);
	        
	        rs = (ResultSet) pre.executeQuery();
	        
	        if (rs.next()) {
	            Administrator administrator = new Administrator();
	            
	            UserAccount userAccount = new UserAccount();
	            userAccount.setUsername(rs.getString("username"));
	            userAccount.setPassword(rs.getString("password"));
	            userAccount.setEmail(rs.getString("email"));
	            
	            administrator.setId(rs.getInt("administrator_id"));
	            administrator.setUserAccount(userAccount);
	            return administrator;
	        }
	        
	        return null;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return null;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "Administrator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean changeEmail(String email, String username) {
	    PreparedStatement pre = null;
	    try {
	        this.con = getConnection(this.cp);

	        String sql = "UPDATE user_account SET email = ? WHERE username = ?";
	        
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, email);
	        pre.setString(2, username);

	        int result = pre.executeUpdate();
	        
	        if (result > 0) {
	            this.con.commit();  // Xác nhận cập nhật
	            return true;
	        } else {
	            this.con.rollback(); // Không có dòng nào bị ảnh hưởng
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    } finally {
	        try {
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "Administrator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	@Override
	public boolean changePassword(String username, String newPassword) {
		PreparedStatement pre = null;

	    try {
	        this.con = getConnection(this.cp);

//	        String hashedPassword = passwordEncoder.encode(newPassword);

	        String sql = "UPDATE user_account SET password = ? WHERE username = ?";
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, newPassword);
	        pre.setString(2, username);

	        int result = pre.executeUpdate();

	        if (result > 0) {
	            this.con.commit(); // Cập nhật thành công
	            return true;
	        } else {
	            this.con.rollback(); // Không có bản ghi nào được cập nhật
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    } finally {
	        try {
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "Administrator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}

}
