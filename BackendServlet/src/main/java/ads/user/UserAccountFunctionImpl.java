package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Administrator;
import ads.objects.Customer;
import ads.objects.TourGuide;
import ads.objects.TourOperator;
import ads.objects.UserAccount;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAccountFunctionImpl implements UserAccountFunction {

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	//Đối tượng kết nối
	private Connection con;
	
	private static volatile UserAccountFunctionImpl instance;
	
	//Singleton Pattern
	public static UserAccountFunctionImpl getInstance() {
        if (instance == null) {
            Class var0 = UserAccountFunctionImpl.class;
            synchronized (UserAccountFunctionImpl.class) {
                if (instance == null) {
                    instance = new UserAccountFunctionImpl();
                }
            }
        }

        return instance;
    }
	
	public UserAccountFunctionImpl() {
		
	}
	
	//DEPRECATED
//	public UserAccountFunctionImpl(ConnectionPool cp) {
//        // TODO Auto-generated constructor stub
//        //Xac dinh bo quan ly ket noi de lam viec
//        if (cp == null) {
//            UserAccountFunctionImpl.cp = ConnectionPoolImpl.getInstance();
//        } else {
//        	UserAccountFunctionImpl.cp = cp;
//        }
//
//        try {
//            //Xin ket noi de lam viec	
//            this.con = cp.getConnection("UserAccount");
//
//            //Kiem tra che do thuc thi tu dong
//            if (this.con.getAutoCommit()) {
//                //Cham dut che do thuc thi tu dong
//
//                this.con.setAutoCommit(false);
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
	
	/**
	 * Hàm lấy kết nối (do dùng SingletonPattern)
	 * 
	 * @param cp pool
	 * @return 1 kết nối
	 */
	public Connection getConnection(ConnectionPool cp) {
        // TODO Auto-generated constructor stub
        //Xac dinh bo quan ly ket noi de lam viec
//        if (cp == null) {
//            cp = ConnectionPoolImpl.getInstance();
//        } else {
//        	UserAccountFunctionImpl.cp = cp;
//        }
        try {
            //Xin ket noi de lam viec	
            this.con = UserAccountFunctionImpl.cp.getConnection("UserAccount");

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
	
	/**
	 * Thực hiện thực thi 1 câu lệnh
	 * 
	 * @param pre Câu truy vấn (thêm, sửa, xóa)
	 * @return Kết quả (thêm, sửa, xóa)
	 */
	private boolean exe(PreparedStatement pre) {
        //pre đã được buiên dịch và truyền giá trị đầy đủ cho các tham số
        if (pre != null) {
            try {
                int results = pre.executeUpdate();
                if (results == 0) {
                    this.con.rollback();
                    return false;
                }

                //Xac nhan thuc thi sau cung
                this.con.commit();

                return true;

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                try {
                    this.con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } finally {
                pre = null;
            }
        }

        return false;
    }
	
	/**
	 * Thực hiện thực thi nhiều câu lệnh
	 * 
	 * @param statements Các câu truy vấn (thêm, sửa, xóa)
	 * @return Kết quả (thêm, sửa, xóa)
	 */
	public boolean exeBatch(List<PreparedStatement> statements) {
	    if (statements == null || statements.isEmpty()) return false;

	    try {
	        this.con.setAutoCommit(false);

	        for (PreparedStatement pre : statements) {
	            int result = pre.executeUpdate();
	            if (result == 0) {
	                this.con.rollback();
	                return false;
	            }
	        }

	        this.con.commit();
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            con.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return false;
	}

	
	@Override
	public UserAccount getUserAccount(String username, String password) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			this.con = getConnection(this.cp);
			
			String sql = "SELECT * FROM user_account where username = ?";
			pre = this.con.prepareStatement(sql);
			
			pre.setString(1, username);
			
			rs = pre.executeQuery();
			
			String pwd = "";
			
			UserAccount userAccount = new UserAccount();
			
			//Kiểm tra xem có kết quả không
			if(rs.next()) {
				userAccount.setUsername(rs.getString("username"));
				pwd = rs.getString("password");
				
				userAccount.setPassword(pwd);
				
				int aid = rs.getInt("administrator_id");
				if(aid == 0) {
					userAccount.setAdministrator(null);
				} else {
					userAccount.setAdministrator(new Administrator().builder()
							.id(aid)
							.build());
				}
				
				int cid = rs.getInt("c_id");
				if(cid == 0) {
					userAccount.setC(null);
				} else {
					userAccount.setC(new Customer().builder()
							.id(cid)
							.build());
				}
				
				int tgid = rs.getInt("tour_guide_id");
				if(tgid == 0) {
					userAccount.setTourGuide(null);
				} else {
					userAccount.setTourGuide(new TourGuide().builder()
							.id(tgid)
							.build());
				}
				
				int toid = rs.getInt("tour_operator_id");
				if(toid == 0) {
					userAccount.setTourOperator(null);
				} else {
					userAccount.setTourOperator(new TourOperator().builder()
							.id(rs.getInt("tour_operator_id"))
							.build());
				}
				
			} else {
				return null;
			}
			
			//Check pwd băm
			if(!passwordEncoder.matches(password, pwd)) {
				return null;
			} else {
				return userAccount;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		} finally {
			try {
				rs.close();
				pre.close();
				this.cp.releaseConnection(this.con, "UserAccount");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
