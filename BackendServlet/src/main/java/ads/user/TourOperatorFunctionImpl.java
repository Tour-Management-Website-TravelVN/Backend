package ads.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Administrator;
import ads.objects.Customer;
import ads.objects.TourGuide;
import ads.objects.TourOperator;
import ads.objects.UserAccount;

public class TourOperatorFunctionImpl implements TourOperatorFunction {
	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	//Đối tượng kết nối
	private Connection con;
	
	private static volatile TourOperatorFunctionImpl instance;
	
	//Singleton Pattern
	public static TourOperatorFunctionImpl getInstance() {
        if (instance == null) {
            Class var0 = TourOperatorFunctionImpl.class;
            synchronized (TourOperatorFunctionImpl.class) {
                if (instance == null) {
                    instance = new TourOperatorFunctionImpl();
                }
            }
        }

        return instance;
    }
	
	public TourOperatorFunctionImpl() {
		
	}
	
	public Connection getConnection(ConnectionPool cp) {
        try {
            //Xin ket noi de lam viec	
            this.con = TourOperatorFunctionImpl.cp.getConnection("TourOperator");

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
	public ArrayList<TourOperator> getFirst100TourOperators() {
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<TourOperator> tourOperators = new ArrayList<>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 100 bản ghi
	        String sql = "SELECT tour_operator_id, firstname, lastname, date_of_birth, gender, address, phone_number, citizen_id, hometown, salary, start_date, end_date " +
	                     "FROM tour_operator LIMIT 500";
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	            TourOperator tourOperator = new TourOperator();
	            
	            tourOperator.setId(rs.getInt("tour_operator_id"));
	            tourOperator.setFirstname(rs.getString("firstname"));
	            tourOperator.setLastname(rs.getString("lastname"));
	            
	            java.sql.Date sqlDate = rs.getDate("date_of_birth");
	            LocalDate dateOfBirth = (sqlDate != null) ? sqlDate.toLocalDate() : null;
	            tourOperator.setDateOfBirth(dateOfBirth);
	            
	            tourOperator.setGender(rs.getBoolean("gender"));
	            tourOperator.setAddress(rs.getString("address"));
	            tourOperator.setPhoneNumber(rs.getString("phone_number"));
	            tourOperator.setCitizenId(rs.getString("citizen_id"));
	            tourOperator.setHometown(rs.getString("hometown"));
	            tourOperator.setSalary(rs.getBigDecimal("salary"));
	            
	            java.sql.Date startSqlDate = rs.getDate("start_date");
	            LocalDate startDate = (startSqlDate != null) ? startSqlDate.toLocalDate() : null;
	            tourOperator.setStartDate(startDate);
	            
	            java.sql.Date endSqlDate = rs.getDate("end_date");
	            LocalDate endDate = (endSqlDate != null) ? endSqlDate.toLocalDate() : null;
	            tourOperator.setEndDate(endDate);
	            
	            tourOperators.add(tourOperator);
	        }
	        
	        return tourOperators;
	        
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
	                this.cp.releaseConnection(this.con, "TourOperator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean addTourOperator(TourOperator tourOperator) {
	    PreparedStatement pre = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "INSERT INTO tour_operator(firstname, lastname, date_of_birth, gender, address, phone_number, citizen_id, hometown, salary, start_date, end_date) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        pre = this.con.prepareStatement(sql);
	        
	        pre.setString(1, tourOperator.getFirstname());
	        pre.setString(2, tourOperator.getLastname());
	        
	        if (tourOperator.getDateOfBirth() != null) {
	            pre.setDate(3, java.sql.Date.valueOf(tourOperator.getDateOfBirth()));
	        } else {
	            pre.setNull(3, java.sql.Types.DATE);
	        }
	        
	        pre.setBoolean(4, tourOperator.getGender());
	        pre.setString(5, tourOperator.getAddress());
	        pre.setString(6, tourOperator.getPhoneNumber());
	        pre.setString(7, tourOperator.getCitizenId());
	        pre.setString(8, tourOperator.getHometown());
	        pre.setBigDecimal(9, tourOperator.getSalary());
	        
	        if (tourOperator.getStartDate() != null) {
	            pre.setDate(10, java.sql.Date.valueOf(tourOperator.getStartDate()));
	        } else {
	            pre.setNull(10, java.sql.Types.DATE);
	        }
	        
	        if (tourOperator.getEndDate() != null) {
	            pre.setDate(11, java.sql.Date.valueOf(tourOperator.getEndDate()));
	        } else {
	            pre.setNull(11, java.sql.Types.DATE);
	        }
	        
	        int rowsAffected = pre.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            this.con.commit();
	            return true;
	        } else {
	            this.con.rollback();
	            return false;
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
	        return false;
	    } finally {
	        try {
	            if (pre != null) pre.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "TourOperator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	@Override
	public boolean isPhoneNumberExist(String phoneNumber) {
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "SELECT COUNT(*) FROM tour_operator WHERE phone_number = ?";
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, phoneNumber);
	        
	        rs = pre.executeQuery();
	        
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; // Trả về true nếu tìm thấy số điện thoại
	        }
	        
	        return false;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if(this.con != null) {
	                this.con.rollback();
	            }
	        } catch(SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if(rs != null) rs.close();
	            if(pre != null) pre.close();
	            if(this.con != null) {
	                this.cp.releaseConnection(this.con, "TourOperator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	@Override
	public boolean isCitizenIdExist(String citizenId) {
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "SELECT 1 FROM tour_operator WHERE citizen_id = ? LIMIT 1";
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, citizenId);
	        
	        rs = pre.executeQuery();
	        return rs.next(); // Trả về true nếu tìm thấy
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if(rs != null) rs.close();
	            if(pre != null) pre.close();
	            if(this.con != null) {
	                this.cp.releaseConnection(this.con, "TourOperator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean updateTourOperator(TourOperator tourOperator) {
		PreparedStatement pre = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "UPDATE tour_operator SET firstname = ?, "
	        								   + "lastname = ?, "
	        								   + "date_of_birth = ?, "
	        								   + "gender = ?, "
	        								   + "address = ?, "
	        								   + "phone_number = ?, "
	        								   + "citizen_id = ?, "
	        								   + "hometown = ?, "
	        								   + "salary = ?, "
	        								   + "start_date = ?, "
	        								   + "end_date = ? "
	        		   + "WHERE tour_operator_id = ?";
	        
	        pre = this.con.prepareStatement(sql);
	        
	        pre.setString(1, tourOperator.getFirstname());
            pre.setString(2, tourOperator.getLastname());
            pre.setDate(3, Date.valueOf(tourOperator.getDateOfBirth()));
            pre.setBoolean(4, tourOperator.getGender());
            pre.setString(5, tourOperator.getAddress());
            pre.setString(6, tourOperator.getPhoneNumber());
            pre.setString(7, tourOperator.getCitizenId());
            pre.setString(8, tourOperator.getHometown());
            pre.setBigDecimal(9, tourOperator.getSalary());
            pre.setDate(10, Date.valueOf(tourOperator.getStartDate()));
            pre.setDate(11, Date.valueOf(tourOperator.getEndDate()));
            pre.setInt(12, tourOperator.getId());
	        
            int rowsAffected  = pre.executeUpdate();
            
            if(rowsAffected  > 0) {
                this.con.commit();
                return true;
            }
            
            this.con.rollback();
            return false;
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
	                this.cp.releaseConnection(this.con, "TourOperator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	private void setLocalDate(PreparedStatement pre, int parameterIndex, Date date) throws SQLException {
	    if (date != null) {
	        pre.setDate(parameterIndex, new java.sql.Date(date.getTime()));
	    } else {
	        pre.setNull(parameterIndex, Types.DATE);
	    }
	}

	@Override
	public boolean deleteTourOperatorAndAccount(int tourOperatorId) {
	    PreparedStatement preTourOperator = null;
	    PreparedStatement preAccount = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        // Xóa tài khoản tương ứng liên quan
	        String sqlAccount = "DELETE FROM user_account WHERE tour_operator_id = ?";
	        preAccount = this.con.prepareStatement(sqlAccount);
	        preAccount.setInt(1, tourOperatorId);
	        int accountDeleted = preAccount.executeUpdate();
	        
	        // Xóa tour operator
	        String sqlTourOperator = "DELETE FROM tour_operator WHERE tour_operator_id = ?";
	        preTourOperator = this.con.prepareStatement(sqlTourOperator);
	        preTourOperator.setInt(1, tourOperatorId);
	        int tourOperatorDeleted = preTourOperator.executeUpdate();
	        
	        if (tourOperatorDeleted > 0) {
	            this.con.commit();
	            return true;
	        } else {
	            this.con.rollback();
	            return false;
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
	        return false;
	    } finally {
	        try {
	            if (preTourOperator != null) preTourOperator.close();
	            if (preAccount != null) preAccount.close();
	            if (this.con != null) {
	                this.cp.releaseConnection(this.con, "TourOperator");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
