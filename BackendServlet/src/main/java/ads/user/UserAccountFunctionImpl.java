package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		this.cp = ConnectionPoolImpl.getInstance();
        this.con = getConnection(null);
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
	
	@Override
	public ArrayList<UserAccount> getFirst100UserAccounts() {
	    ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<UserAccount> userAccounts = new ArrayList<>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 100 bản ghi
	        String sql = "SELECT username, password, c_id, administrator_id, tour_guide_id, tour_operator_id, email, status " +
	                     "FROM user_account " +
	                     "WHERE status <> 'DEL' " +
	                     "LIMIT 500";
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	            UserAccount userAccount = new UserAccount();
	            
	            userAccount.setUsername(rs.getString("username"));
	            userAccount.setPassword(rs.getString("password"));
	            
	            int cid = rs.getInt("c_id");
	            if (cid != 0) {
	                Customer customer = new Customer();
	                customer.setId(cid);
	                userAccount.setC(customer);
	            }
	            
	            int adminId = rs.getInt("administrator_id");
	            if (adminId != 0) {
	                Administrator administrator = new Administrator();
	                administrator.setId(adminId);
	                userAccount.setAdministrator(administrator);
	            }
	            
	            int guideId = rs.getInt("tour_guide_id");
	            if (guideId != 0) {
	                TourGuide tourGuide = new TourGuide();
	                tourGuide.setId(guideId);
	                userAccount.setTourGuide(tourGuide);
	            }
	            
	            int operatorId = rs.getInt("tour_operator_id");
	            if (operatorId != 0) {
	                TourOperator tourOperator = new TourOperator();
	                tourOperator.setId(operatorId);
	                userAccount.setTourOperator(tourOperator);
	            }
	            
	            userAccount.setEmail(rs.getString("email"));
	            userAccount.setStatus(rs.getString("status"));
	            
	            userAccounts.add(userAccount);
	        }
	        
	        return userAccounts;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return new ArrayList<>();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean addUserAccount(UserAccount userAccount) {
	    PreparedStatement pre = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        if (isUsernameExist(userAccount.getUsername())) {
	            return false;
	        }
	        
	        if (userAccount.getEmail() != null && !userAccount.getEmail().isEmpty() && isEmailExist(userAccount.getEmail())) {
	            return false;
	        }
	        
//	        String encodedPassword = passwordEncoder.encode(userAccount.getPassword());
	        
	        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO user_account (username, password, email");
	        List<Object> params = new ArrayList<>();
	        params.add(userAccount.getUsername());
	        params.add(userAccount.getPassword());
	        params.add(userAccount.getEmail());
	        
	        if (userAccount.getC() != null) {
	            sqlBuilder.append(", c_id");
	            params.add(userAccount.getC().getId());
	        }
	        else if (userAccount.getAdministrator() != null) {
	            sqlBuilder.append(", administrator_id");
	            params.add(userAccount.getAdministrator().getId());
	        }
	        else if (userAccount.getTourGuide() != null) {
	            sqlBuilder.append(", tour_guide_id");
	            params.add(userAccount.getTourGuide().getId());
	        }
	        else if (userAccount.getTourOperator() != null) {
	            sqlBuilder.append(", tour_operator_id");
	            params.add(userAccount.getTourOperator().getId());
	        }
	        else if (userAccount.getEmail() != null) {
	            sqlBuilder.append(", email");
	            params.add(userAccount.getEmail());
	        }
	        
	        sqlBuilder.append(", status) VALUES (?, ?");
	        for (int i = 0; i < params.size() - 2; i++) {
	            sqlBuilder.append(", ?");
	        }
	        sqlBuilder.append(", ?)");
	        
	        params.add(userAccount.getStatus() != null ? userAccount.getStatus() : "OFF");
	        
	        pre = this.con.prepareStatement(sqlBuilder.toString());
	        
	        for (int i = 0; i < params.size(); i++) {
	            Object param = params.get(i);
	            if (param instanceof String) {
	                pre.setString(i + 1, (String) param);
	            } else if (param instanceof Integer) {
	                pre.setInt(i + 1, (Integer) param);
	            }
	        }
	        
	        return exe(pre);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	// Kiểm tra username đã tồn tại
	@Override
	public boolean isUsernameExist(String username) {
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT 1 FROM user_account WHERE username = ?";
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, username);
	        rs = pre.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        if(pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
		return false;
	}

	// Kiểm tra email đã tồn tại
	@Override
	public boolean isEmailExist(String email) {
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT 1 FROM user_account WHERE email = ?";
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, email);
	        rs = pre.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
		return false;
	}
	
	@Override
	public boolean isAdministratorIdExist(int id) {
		PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT 1 FROM user_account WHERE administrator_id = ? ";
	        pre = this.con.prepareStatement(sql);
	        pre.setInt(1, id);
	        rs = pre.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    }
		return false;
	}
	
	@Override
	public boolean isCustomerIdExist(int id) {
		PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT 1 FROM user_account WHERE c_id = ? ";
	        pre = this.con.prepareStatement(sql);
	        pre.setInt(1, id);
	        rs = pre.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    }
		return false;
	}
	
	@Override
	public boolean isTourOperatorIdExist(int id) {
		PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT 1 FROM user_account WHERE tour_operator_id = ? ";
	        pre = this.con.prepareStatement(sql);
	        pre.setInt(1, id);
	        rs = pre.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    }
		return false;
	}
	
	@Override
	public boolean isTourGuideIdExist(int id) {
		PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT 1 FROM user_account WHERE tour_guide_id = ? ";
	        pre = this.con.prepareStatement(sql);
	        pre.setInt(1, id);
	        rs = pre.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    }
		return false;
	}

	@Override
	public boolean toggleAccountStatus(String username) {
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String checkSql = "SELECT status FROM user_account WHERE username = ?";
	        pre = this.con.prepareStatement(checkSql);
	        pre.setString(1, username);
	        rs = pre.executeQuery();
	        
	        if (!rs.next()) {
	            return false;
	        }
	        
	        String currentStatus = rs.getString("status");
	        String newStatus = "OFF".equals(currentStatus) ? "LOCK" : "OFF";
	        
	        rs.close();
	        pre.close();
	        
	        String updateSql = "UPDATE user_account SET status = ? WHERE username = ?";
	        pre = this.con.prepareStatement(updateSql);
	        pre.setString(1, newStatus);
	        pre.setString(2, username);
	        
	        return exe(pre);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean updateUserAccount(String username, String password, String email) {
	    PreparedStatement pre = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "UPDATE user_account SET password = ?, email = ? WHERE username = ?";
	        
	        pre = this.con.prepareStatement(sql);
	        
//	        String encodedPassword = passwordEncoder.encode(password);
	        
	        pre.setString(1, password);
	        pre.setString(2, email);
	        pre.setString(3, username);
	        
	        return exe(pre);
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean deleteUserAccount(String username) {
	    PreparedStatement pre = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        // Kiểm tra xem tài khoản có tồn tại không
	        String checkSql = "SELECT 1 FROM user_account WHERE username = ?";
	        pre = this.con.prepareStatement(checkSql);
	        pre.setString(1, username);
	        ResultSet rs = pre.executeQuery();
	        
	        if (!rs.next()) {
	            return false; // Tài khoản không tồn tại
	        }
	        rs.close();
	        pre.close();
	        
	        // Xóa tài khoản
	        String deleteSql = "DELETE FROM user_account WHERE username = ?";
	        pre = this.con.prepareStatement(deleteSql);
	        pre.setString(1, username);
	        
	        return exe(pre);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean softDeleteAccount(String username) {
		PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String newStatus = "DEL";
	        
	        String updateSql = "UPDATE user_account SET status = ? WHERE username = ?";
	        pre = this.con.prepareStatement(updateSql);
	        pre.setString(1, newStatus);
	        pre.setString(2, username);
	        
	        return exe(pre);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public ArrayList<UserAccount> getRecentDelelteAccounts() {
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<UserAccount> userAccounts = new ArrayList<>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 100 bản ghi
	        String sql = "SELECT username, password, c_id, administrator_id, tour_guide_id, tour_operator_id, email, status " +
	                     "FROM user_account " +
	                     "WHERE status = 'DEL' " +
	                     "LIMIT 100";
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	            UserAccount userAccount = new UserAccount();
	            
	            userAccount.setUsername(rs.getString("username"));
	            userAccount.setPassword(rs.getString("password"));
	            
	            int cid = rs.getInt("c_id");
	            if (cid != 0) {
	                Customer customer = new Customer();
	                customer.setId(cid);
	                userAccount.setC(customer);
	            }
	            
	            int adminId = rs.getInt("administrator_id");
	            if (adminId != 0) {
	                Administrator administrator = new Administrator();
	                administrator.setId(adminId);
	                userAccount.setAdministrator(administrator);
	            }
	            
	            int guideId = rs.getInt("tour_guide_id");
	            if (guideId != 0) {
	                TourGuide tourGuide = new TourGuide();
	                tourGuide.setId(guideId);
	                userAccount.setTourGuide(tourGuide);
	            }
	            
	            int operatorId = rs.getInt("tour_operator_id");
	            if (operatorId != 0) {
	                TourOperator tourOperator = new TourOperator();
	                tourOperator.setId(operatorId);
	                userAccount.setTourOperator(tourOperator);
	            }
	            
	            userAccount.setEmail(rs.getString("email"));
	            userAccount.setStatus(rs.getString("status"));
	            
	            userAccounts.add(userAccount);
	        }
	        
	        return userAccounts;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return new ArrayList<>();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean restoreAccount(String username) {
		PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String newStatus = "OFF";
	        
	        String updateSql = "UPDATE user_account SET status = ? WHERE username = ?";
	        pre = this.con.prepareStatement(updateSql);
	        pre.setString(1, newStatus);
	        pre.setString(2, username);
	        
	        return exe(pre);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean restoreAllAccounts() {
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String checkSql = "SELECT COUNT(*) AS count FROM user_account WHERE status = 'DEL'";
	        pre = this.con.prepareStatement(checkSql);
	        rs = pre.executeQuery();
	        
	        if (rs.next()) {
	            int deletedCount = rs.getInt("count");
	            if (deletedCount == 0) {
	                return false;
	            }
	        }
	        
	        rs.close();
	        pre.close();
	        
	        String updateSql = "UPDATE user_account SET status = 'OFF' WHERE status = 'DEL'";
	        pre = this.con.prepareStatement(updateSql);
	        
	        int rowsAffected = pre.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            this.con.commit();
	            return true;
	        }
	        
	        return false;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean deleteAllSoftDeleteAccounts() {
	    PreparedStatement pre = null;
	    try {
	        this.con = getConnection(this.cp);
	        this.con.setAutoCommit(false);
	        
	        String deleteSql = "DELETE FROM user_account WHERE status = 'DEL'";
	        pre = this.con.prepareStatement(deleteSql);
	        int rowsDeleted = pre.executeUpdate();
	        
	        this.con.commit();
	        return rowsDeleted > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (this.con != null) {
	                this.con.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.con.setAutoCommit(true);
	                this.cp.releaseConnection(this.con, "UserAccount");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
