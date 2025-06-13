package ads.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.TourGuide;
import ads.objects.TourOperator;

public class TourGuideFunctionImpl implements TourGuideFunction {
private static ConnectionPool cp = ConnectionPoolImpl.getInstance();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	//Đối tượng kết nối
	private Connection con;
	
	private static volatile TourGuideFunctionImpl instance;
	
	//Singleton Pattern
	public static TourGuideFunctionImpl getInstance() {
        if (instance == null) {
            Class var0 = TourGuideFunctionImpl.class;
            synchronized (TourGuideFunctionImpl.class) {
                if (instance == null) {
                    instance = new TourGuideFunctionImpl();
                }
            }
        }

        return instance;
    }
	
	public TourGuideFunctionImpl() {
		
	}
	
	public Connection getConnection(ConnectionPool cp) {
        try {
            //Xin ket noi de lam viec	
            this.con = TourGuideFunctionImpl.cp.getConnection("TourGuide");

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
	public ArrayList<TourGuide> getFirst100TourGuide() {
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<TourGuide> tourGuides = new ArrayList<>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 100 bản ghi
	        String sql = "SELECT tour_guide_id, firstname, lastname, date_of_birth, gender, address, phone_number, citizen_id, hometown, salary, start_date, end_date, card_id, language " +
	                     "FROM tour_guide LIMIT 500";
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	        	TourGuide tourGuide = new TourGuide();
	            
	        	tourGuide.setId(rs.getInt("tour_guide_id"));
	        	tourGuide.setFirstname(rs.getString("firstname"));
	        	tourGuide.setLastname(rs.getString("lastname"));
	            
	            java.sql.Date sqlDate = rs.getDate("date_of_birth");
	            LocalDate dateOfBirth = (sqlDate != null) ? sqlDate.toLocalDate() : null;
	            tourGuide.setDateOfBirth(dateOfBirth);
	            
	            tourGuide.setGender(rs.getBoolean("gender"));
	            tourGuide.setAddress(rs.getString("address"));
	            tourGuide.setPhoneNumber(rs.getString("phone_number"));
	            tourGuide.setCitizenId(rs.getString("citizen_id"));
	            tourGuide.setHometown(rs.getString("hometown"));
	            tourGuide.setSalary(rs.getBigDecimal("salary"));
	            tourGuide.setCardId(rs.getString("card_id"));
	            tourGuide.setLanguage(rs.getString("language"));
	            
	            java.sql.Date startSqlDate = rs.getDate("start_date");
	            LocalDate startDate = (startSqlDate != null) ? startSqlDate.toLocalDate() : null;
	            tourGuide.setStartDate(startDate);
	            
	            java.sql.Date endSqlDate = rs.getDate("end_date");
	            LocalDate endDate = (endSqlDate != null) ? endSqlDate.toLocalDate() : null;
	            tourGuide.setEndDate(endDate);
	            
	            tourGuides.add(tourGuide);
	        }
	        
	        return tourGuides;
	        
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
	                this.cp.releaseConnection(this.con, "TourGuide");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean addTourGuide(TourGuide tourGuide) {
		PreparedStatement pre = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "INSERT INTO tour_guide(firstname, lastname, date_of_birth, gender, address, phone_number, citizen_id, hometown, salary, start_date, end_date, card_id, language) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        pre = this.con.prepareStatement(sql);
	        
	        pre.setString(1, tourGuide.getFirstname());
	        pre.setString(2, tourGuide.getLastname());
	        
	        if (tourGuide.getDateOfBirth() != null) {
	            pre.setDate(3, java.sql.Date.valueOf(tourGuide.getDateOfBirth()));
	        } else {
	            pre.setNull(3, java.sql.Types.DATE);
	        }
	        
	        pre.setBoolean(4, tourGuide.getGender());
	        pre.setString(5, tourGuide.getAddress());
	        pre.setString(6, tourGuide.getPhoneNumber());
	        pre.setString(7, tourGuide.getCitizenId());
	        pre.setString(8, tourGuide.getHometown());
	        pre.setBigDecimal(9, tourGuide.getSalary());
	        
	        if (tourGuide.getStartDate() != null) {
	            pre.setDate(10, java.sql.Date.valueOf(tourGuide.getStartDate()));
	        } else {
	            pre.setNull(10, java.sql.Types.DATE);
	        }
	        
	        if (tourGuide.getEndDate() != null) {
	            pre.setDate(11, java.sql.Date.valueOf(tourGuide.getEndDate()));
	        } else {
	            pre.setNull(11, java.sql.Types.DATE);
	        }
	        
	        pre.setString(12, tourGuide.getCardId());
	        pre.setString(13, tourGuide.getLanguage());
	        
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
	                this.cp.releaseConnection(this.con, "TourGuide");
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
	        
	        String sql = "SELECT COUNT(*) FROM tour_guide WHERE phone_number = ?";
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
	                this.cp.releaseConnection(this.con, "TourGuide");
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
	        
	        String sql = "SELECT 1 FROM tour_guide WHERE citizen_id = ? LIMIT 1";
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
	                this.cp.releaseConnection(this.con, "TourGuide");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean isCardIdExist(String cardId) {
		PreparedStatement pre = null;
	    ResultSet rs = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "SELECT 1 FROM tour_guide WHERE card_id = ? LIMIT 1";
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, cardId);
	        
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
	                this.cp.releaseConnection(this.con, "TourGuide");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean updateTourGuide(TourGuide tourGuide) {
		PreparedStatement pre = null;
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "UPDATE tour_guide SET firstname = ?, "
	        								 + "lastname = ?, "
	        								 + "date_of_birth = ?, "
	        								 + "gender = ?, "
	        								 + "address = ?, "
	        								 + "phone_number = ?, "
	        								 + "citizen_id = ?, "
	        								 + "hometown = ?, "
	        								 + "salary = ?, "
	        								 + "start_date = ?, "
	        								 + "end_date = ?, "
	        								 + "card_id = ?, "
	        								 + "language = ? "
	        		   + "WHERE tour_guide_id = ?";
	        
	        pre = this.con.prepareStatement(sql);
	        
	        pre.setString(1, tourGuide.getFirstname());
            pre.setString(2, tourGuide.getLastname());
            pre.setDate(3, Date.valueOf(tourGuide.getDateOfBirth()));
            pre.setBoolean(4, tourGuide.getGender());
            pre.setString(5, tourGuide.getAddress());
            pre.setString(6, tourGuide.getPhoneNumber());
            pre.setString(7, tourGuide.getCitizenId());
            pre.setString(8, tourGuide.getHometown());
            pre.setBigDecimal(9, tourGuide.getSalary());
            pre.setDate(10, Date.valueOf(tourGuide.getStartDate()));
            pre.setDate(11, Date.valueOf(tourGuide.getEndDate()));
            pre.setString(12, tourGuide.getCardId());
            pre.setString(13, tourGuide.getLanguage());
            pre.setInt(14, tourGuide.getId());
	        
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
	                this.cp.releaseConnection(this.con, "TourGuide");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean deleteTourGuideAndAccount(int tourGuideId) {
		PreparedStatement preTourOperator = null;
	    PreparedStatement preAccount = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        // Xóa tài khoản tương ứng liên quan
	        String sqlAccount = "DELETE FROM user_account WHERE tour_guide_id = ?";
	        preAccount = this.con.prepareStatement(sqlAccount);
	        preAccount.setInt(1, tourGuideId);
	        int accountDeleted = preAccount.executeUpdate();
	        
	        // Xóa tour operator
	        String sqlTourOperator = "DELETE FROM tour_guide WHERE tour_guide_id = ?";
	        preTourOperator = this.con.prepareStatement(sqlTourOperator);
	        preTourOperator.setInt(1, tourGuideId);
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
	                this.cp.releaseConnection(this.con, "TourGuide");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
