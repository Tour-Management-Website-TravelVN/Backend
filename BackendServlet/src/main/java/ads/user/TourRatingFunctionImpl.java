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
import ads.objects.Administrator;
import ads.objects.Customer;
import ads.objects.TourOperator;
import ads.objects.TourRating;
import ads.objects.TourUnit;
import ads.user.TourOperatorFunctionImpl;
import ads.user.TourRatingFunction;

public class TourRatingFunctionImpl implements TourRatingFunction {
private static ConnectionPool cp = ConnectionPoolImpl.getInstance();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	//Đối tượng kết nối
	private Connection con;
	
	private static volatile TourRatingFunctionImpl instance;
	
	//Singleton Pattern
	public static TourRatingFunctionImpl getInstance() {
        if (instance == null) {
            Class var0 = TourRatingFunctionImpl.class;
            synchronized (TourRatingFunctionImpl.class) {
                if (instance == null) {
                    instance = new TourRatingFunctionImpl();
                }
            }
        }

        return instance;
    }
	
	public TourRatingFunctionImpl() {
		
	}
	
	public Connection getConnection(ConnectionPool cp) {
        try {
            //Xin ket noi de lam viec	
            this.con = TourRatingFunctionImpl.cp.getConnection("TourRating");

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
	public ArrayList<TourRating> getFirst100TourRatings() {
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<TourRating> tourRatings = new ArrayList<>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 100 bản ghi
	        String sql = "SELECT tour_rating_id, administrator_id, c_id, tour_unit_id, rating_value, comment, status " +
	                     "FROM tour_rating LIMIT 500";
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	        	TourRating tourRating = new TourRating();
	        	
	        	Administrator administrator = new Administrator();
	            administrator.setId(rs.getInt("administrator_id"));
	            Customer customer = new Customer();
	            customer.setId(rs.getInt("c_id"));
	            TourUnit tourUnit = new TourUnit();
	            tourUnit.setTourUnitId(rs.getString("tour_unit_id"));
	            
	            tourRating.setId(rs.getInt("tour_rating_id"));
	            tourRating.setAdministrator(administrator);
	            tourRating.setC(customer);
	            tourRating.setTourUnit(tourUnit);
	            tourRating.setRatingValue(rs.getByte("rating_value"));
	            tourRating.setComment(rs.getString("comment"));
	            tourRating.setStatus(rs.getString("status"));
	            
	            tourRatings.add(tourRating);
	        }
	        
	        return tourRatings;
	        
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
	                this.cp.releaseConnection(this.con, "TourRating");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean setStatus(int tourRatingId, int administratorId, String status) {
		PreparedStatement pre = null;
	    Connection con = null;
	    
	    try {
	        con = getConnection(this.cp);
	        
	        String sql = "UPDATE tour_rating SET administrator_id = ?, status = ? WHERE tour_rating_id = ?";
	        pre = con.prepareStatement(sql);
	        
	        pre.setInt(1, administratorId);
	        pre.setString(2, status);
	        pre.setInt(3, tourRatingId);
	        
	        int rowsAffected = pre.executeUpdate();
	        con.commit();
	        
	        return rowsAffected > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if(con != null) {
	                con.rollback();
	            }
	        } catch(SQLException e1) {
	            e1.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if(pre != null) pre.close();
	            if(con != null) {
	                this.cp.releaseConnection(con, "TourRating");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public ArrayList<TourRating> getTourRatingsByStatus(String status) {
	    ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<TourRating> tourRatings = new ArrayList<>();
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "SELECT tour_rating_id, administrator_id, c_id, tour_unit_id, rating_value, comment, status " +
	                     "FROM tour_rating " +
	                     "WHERE status = ? " +
	                     "LIMIT 100";
	        pre = this.con.prepareStatement(sql);
	        pre.setString(1, status);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	            TourRating tourRating = new TourRating();
	            
	            Administrator administrator = new Administrator();
	            administrator.setId(rs.getInt("administrator_id"));
	            Customer customer = new Customer();
	            customer.setId(rs.getInt("c_id"));
	            TourUnit tourUnit = new TourUnit();
	            tourUnit.setTourUnitId(rs.getString("tour_unit_id"));
	            
	            tourRating.setId(rs.getInt("tour_rating_id"));
	            tourRating.setAdministrator(administrator);
	            tourRating.setC(customer);
	            tourRating.setTourUnit(tourUnit);
	            tourRating.setRatingValue(rs.getByte("rating_value"));
	            tourRating.setComment(rs.getString("comment"));
	            tourRating.setStatus(rs.getString("status"));
	            
	            tourRatings.add(tourRating);
	        }
	        
	        return tourRatings;
	        
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
	                this.con.commit();
	                this.cp.releaseConnection(this.con, "TourRating");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public byte getTourRatingValueByTourUnitId(String id) {
		    PreparedStatement pre = null;
		    ResultSet rs  =null;
		    Byte result = null;

try {
		        this.con = getConnection(this.cp);
		        
		        String sql = "SELECT rating_value " +
		                     "FROM tour_rating " +
		                     "WHERE tour_unit_id = ? ";
		        pre = this.con.prepareStatement(sql);
		        pre.setString(1, id);
		        
		        rs = pre.executeQuery();
		        
		        if(rs.next()) {
		            result = rs.getByte(1);
		        }
		        
		        return result;
		        
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
		    } finally {
		        try {
		            if(rs != null) rs.close();
		            if(pre != null) pre.close();
		            if(this.con != null) {
		                this.con.commit();
		                this.cp.releaseConnection(this.con, "TourRating");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
			return 5;

	}

}
