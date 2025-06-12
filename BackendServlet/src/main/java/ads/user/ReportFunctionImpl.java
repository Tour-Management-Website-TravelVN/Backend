package ads.user;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Booking;
import ads.objects.Customer;
import ads.objects.Tour;
import ads.objects.TourOperator;
import ads.objects.TourUnit;

public class ReportFunctionImpl implements ReportFunction {
private static ConnectionPool cp = ConnectionPoolImpl.getInstance();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	//Đối tượng kết nối
	private Connection con;
	
	private static volatile ReportFunctionImpl instance;
	
	//Singleton Pattern
	public static ReportFunctionImpl getInstance() {
        if (instance == null) {
            Class var0 = ReportFunctionImpl.class;
            synchronized (ReportFunctionImpl.class) {
                if (instance == null) {
                    instance = new ReportFunctionImpl();
                }
            }
        }

        return instance;
    }
	
	public ReportFunctionImpl() {
		
	}
	
	public Connection getConnection(ConnectionPool cp) {
        try {
            //Xin ket noi de lam viec	
            this.con = ReportFunctionImpl.cp.getConnection("Report");

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
	public int getDailyTourBookings() {
	    int count = 0;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COUNT(*) AS count FROM booking " + 
	    		     "WHERE DATE(booking_date) = CURDATE()";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                count = rs.getInt("count");
	            }

	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return count;
	}

	@Override
	public int getMonthlyTourBookings() {
	    int count = 0;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COUNT(*) AS count FROM booking " +
	                 "WHERE MONTH(booking_date) = MONTH(CURDATE()) AND YEAR(booking_date) = YEAR(CURDATE())";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                count = rs.getInt("count");
	            }

	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return count;
	}


	@Override
	public int getYearlyTourBookings() {
	    int count = 0;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COUNT(*) AS count FROM booking " +
	                 "WHERE YEAR(booking_date) = YEAR(CURDATE())";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                count = rs.getInt("count");
	            }

	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return count;
	}

	@Override
	public BigDecimal getDailyRevenue() {
	    BigDecimal revenue = BigDecimal.ZERO;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COALESCE(SUM(total_amount), 0) AS revenue FROM booking " +
	                 "WHERE DATE(booking_date) = CURDATE() AND status <> 'C'";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                revenue = rs.getBigDecimal("revenue");
	            }

	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return revenue;
	}

	@Override
	public BigDecimal getMonthlyRevenue() {
	    BigDecimal revenue = BigDecimal.ZERO;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COALESCE(SUM(total_amount), 0) AS revenue FROM booking " +
	                 "WHERE MONTH(booking_date) = MONTH(CURDATE()) AND YEAR(booking_date) = YEAR(CURDATE()) AND status <> 'C'";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                revenue = rs.getBigDecimal("revenue");
	            }

	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return revenue;
	}

	@Override
	public BigDecimal getYearlyTourRevenue() {
	    BigDecimal revenue = BigDecimal.ZERO;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COALESCE(SUM(total_amount), 0) AS revenue FROM booking " +
	                 "WHERE YEAR(booking_date) = YEAR(CURDATE()) AND status <> 'C'";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                revenue = rs.getBigDecimal("revenue");
	            }

	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return revenue;
	}

	@Override
	public int getDailyCustomers() {
	    int count = 0;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COUNT(DISTINCT c_id) AS count FROM ( " +
	                 "    SELECT c_id FROM booking WHERE DATE(booking_date) = CURDATE() " +
	                 "    UNION " +
	                 "    SELECT c_id FROM companion_customer WHERE booking_id IN ( " +
	                 "        SELECT booking_id FROM booking WHERE DATE(booking_date) = CURDATE() " +
	                 "    ) " +
	                 ") AS combined";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                count = rs.getInt("count");
	            }
	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return count;
	}


	@Override
	public int getMonthlyCustomers() {
	    int count = 0;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COUNT(DISTINCT c_id) AS count FROM ( " +
	                 "    SELECT c_id FROM booking WHERE MONTH(booking_date) = MONTH(CURDATE()) AND YEAR(booking_date) = YEAR(CURDATE()) " +
	                 "    UNION " +
	                 "    SELECT c_id FROM companion_customer WHERE booking_id IN ( " +
	                 "        SELECT booking_id FROM booking WHERE MONTH(booking_date) = MONTH(CURDATE()) AND YEAR(booking_date) = YEAR(CURDATE()) " +
	                 "    ) " +
	                 ") AS combined";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                count = rs.getInt("count");
	            }
	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return count;
	}


	@Override
	public int getYearlyCustomers() {
	    int count = 0;
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    java.sql.ResultSet rs = null;

	    String sql = "SELECT COUNT(DISTINCT c_id) AS count FROM ( " +
	                 "    SELECT c_id FROM booking WHERE YEAR(booking_date) = YEAR(CURDATE()) " +
	                 "    UNION " +
	                 "    SELECT c_id FROM companion_customer WHERE booking_id IN ( " +
	                 "        SELECT booking_id FROM booking WHERE YEAR(booking_date) = YEAR(CURDATE()) " +
	                 "    ) " +
	                 ") AS combined";

	    try {
	        conn = getConnection(cp);
	        if (conn != null) {
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                count = rs.getInt("count");
	            }
	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return count;
	}

	@Override
	public ArrayList<Object> getOverViewReportOfYear() {
		ArrayList<Object> overViewReportOfYear = new ArrayList<Object>();
		
		ArrayList<String> time =  new ArrayList<String>();
		
		ArrayList<BigDecimal> revenue = new ArrayList<BigDecimal>();
		
		ArrayList<BigDecimal> cost = new ArrayList<BigDecimal>();
		
		ArrayList<BigDecimal> profit = new ArrayList<BigDecimal>();
		
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "SELECT " +
	    	             "    DATE_FORMAT(b.booking_date, '%Y-%m') AS month, " +
	    	             "    SUM(b.total_amount) AS revenue, " +
	    	             "    SUM( " +
	    	             "        (b.adult_number * tu.adult_tour_cost) + " +
	    	             "        (b.child_number * tu.child_tour_cost) + " +
	    	             "        (b.toddler_number * tu.toddler_tour_cost) + " +
	    	             "        (b.baby_number * tu.baby_tour_cost) " +
	    	             "    ) AS cost, " +
	    	             "    SUM(b.total_amount - " +
	    	             "        ( " +
	    	             "            (b.adult_number * tu.adult_tour_cost) + " +
	    	             "            (b.child_number * tu.child_tour_cost) + " +
	    	             "            (b.toddler_number * tu.toddler_tour_cost) + " +
	    	             "            (b.baby_number * tu.baby_tour_cost) " +
	    	             "        ) " +
	    	             "    ) AS profit " +
	    	             "FROM booking b " +
	    	             "JOIN tour_unit tu ON b.tour_unit_id = tu.tour_unit_id " +
	    	             "WHERE YEAR(b.booking_date) = YEAR(CURDATE()) AND status <> 'C'" +
	    	             "GROUP BY month " +
	    	             "ORDER BY month; ";
	        
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	        	time.add(rs.getString("month"));
	        	
	        	revenue.add(rs.getBigDecimal("revenue"));
	        	
	        	cost.add(rs.getBigDecimal("cost"));
	        	
	        	profit.add(rs.getBigDecimal("profit"));
	        }
	        
	        overViewReportOfYear.add(time);
	        overViewReportOfYear.add(revenue);
	        overViewReportOfYear.add(cost);
	        overViewReportOfYear.add(profit);
	        
	        return overViewReportOfYear;
	        
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
	                this.cp.releaseConnection(this.con, "Report");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public ArrayList<Object> getOverViewReportOfMonth() {
		ArrayList<Object> overViewReportOfMonth = new ArrayList<Object>();
		
		ArrayList<String> time =  new ArrayList<String>();
		
		ArrayList<BigDecimal> revenue = new ArrayList<BigDecimal>();
		
		ArrayList<BigDecimal> cost = new ArrayList<BigDecimal>();
		
		ArrayList<BigDecimal> profit = new ArrayList<BigDecimal>();
		
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        String sql = "SELECT " +
	    	         	 "    DATE_FORMAT(b.booking_date, '%Y-%m-%d') AS day, " +
	    	             "    SUM(b.total_amount) AS revenue, " +
	    	             "    SUM( " +
	    	             "        (b.adult_number * tu.adult_tour_cost) + " +
	    	             "        (b.child_number * tu.child_tour_cost) + " +
	    	             "        (b.toddler_number * tu.toddler_tour_cost) + " +
	    	             "        (b.baby_number * tu.baby_tour_cost) " +
	    	             "    ) AS cost, " +
	    	             "    SUM(b.total_amount -" +
	    	             "        ( " +
	    	             "            (b.adult_number * tu.adult_tour_cost) + " +
	    	             "            (b.child_number * tu.child_tour_cost) + " +
	    	             "            (b.toddler_number * tu.toddler_tour_cost) + " +
	    	             "            (b.baby_number * tu.baby_tour_cost) " +
	    	             "        ) " +
	    	             "    ) AS profit " +
	    	             "FROM booking b " +
	    	             "JOIN tour_unit tu ON b.tour_unit_id = tu.tour_unit_id " +
//	    	             "WHERE YEAR(b.booking_date) = YEAR(CURDATE()) AND MONTH(b.booking_date) = MONTH(CURDATE()) " +
						 "WHERE YEAR(b.booking_date) = YEAR(CURDATE()) AND MONTH(b.booking_date) = 2 AND status <> 'C'" +
	    	             "GROUP BY day " +
	    	             "ORDER BY day; ";
	        
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	        	time.add(rs.getString("day"));
	        	
	        	revenue.add(rs.getBigDecimal("revenue"));
	        	
	        	cost.add(rs.getBigDecimal("cost"));
	        	
	        	profit.add(rs.getBigDecimal("profit"));
	        }
	        
	        overViewReportOfMonth.add(time);
	        overViewReportOfMonth.add(revenue);
	        overViewReportOfMonth.add(cost);
	        overViewReportOfMonth.add(profit);
	        
	        return overViewReportOfMonth;
	        
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
	                this.cp.releaseConnection(this.con, "Report");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public ArrayList<Booking> get50RecentBooking() {
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<Booking> bookings = new ArrayList<Booking>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 10 bản ghi
	        String sql = "SELECT b.tour_unit_id, t.tour_name, c.lastname, c.firstname, b.booking_date, b.status " +
	    	        	 "FROM " +
	    	             "    booking b " +
	    	             "JOIN " +
	    	             "    customer c ON b.c_id = c.c_id " +
	    	             "JOIN " +
	    	             "    tour_unit tu ON b.tour_unit_id = tu.tour_unit_id " +
	    	             "JOIN " +
	    	             "    tour t ON tu.tour_id = t.tour_id " +
	    	             "ORDER BY " +
	    	             "    b.booking_date DESC " +
	    	             "LIMIT 50;";
	        
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	        	Booking booking = new Booking();
	        	
	        	TourUnit tourUnit = new TourUnit();
	        	tourUnit.setTourUnitId(rs.getString("tour_unit_id"));
	        	
	        	Tour tour = new Tour();
	        	tour.setTourName(rs.getString("tour_name"));
	        	
	        	tourUnit.setTour(tour);
	        	
	        	Customer customer = new Customer();
	        	customer.setLastname(rs.getString("lastname"));
	        	customer.setFirstname(rs.getString("firstname"));
	        	
	        	
	        	booking.setTourUnit(tourUnit); // tourUnitId, tourName
	        	booking.setC(customer); // firtName, lastName
	        	
	        	Timestamp timestamp = rs.getTimestamp("booking_date"); // lấy Timestamp từ DB
	        	Instant instant = timestamp.toInstant();               // chuyển thành Instant
	        	booking.setBookingDate(instant); // bookingDate
	        	
	        	booking.setStatus(rs.getString("status")); // status
	        	
	        	bookings.add(booking);
	        }
	        
	        return bookings;
	        
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
	public ArrayList<Object> get50TopBookingTour() {
		ResultSet rs = null;
	    PreparedStatement pre = null;
	    ArrayList<Object> topBookingTours = new ArrayList<Object>();
	    
	    ArrayList<String> tourNames = new ArrayList<String>();
	    ArrayList<Integer> totalBookings = new ArrayList<Integer>();
	    ArrayList<BigDecimal> totalValues = new ArrayList<BigDecimal>();
	    
	    try {
	        this.con = getConnection(this.cp);
	        
	        //Lấy 50 bản ghi
	        String sql = "SELECT " +
	    	             "    t.tour_id, " +
	    	             "    t.tour_name, " +
	        		     "    COUNT(b.booking_id) AS total_bookings, " +
	    	             "    SUM(b.total_amount) AS total_revenue " +
	    	             "FROM " +
	    	             "    booking b " +
	    	             "JOIN " +
	    	             "    tour_unit tu ON b.tour_unit_id = tu.tour_unit_id " +
	    	             "JOIN " +
	    	             "    tour t ON tu.tour_id = t.tour_id " +
	    	             "WHERE status <> 'C'" +
	    	             "GROUP BY " +
	    	             "    t.tour_id, t.tour_name " +
	    	             "ORDER BY " +
	    	             "    total_bookings DESC " +
	    	             "LIMIT 50;";
	        		
	        pre = this.con.prepareStatement(sql);
	        
	        rs = pre.executeQuery();
	        
	        while(rs.next()) {
	        	ArrayList<String> topBookingTour = new ArrayList<String>();
	        	
	        	tourNames.add(rs.getString("tour_name"));
	        	totalBookings.add(rs.getInt("total_bookings"));
	        	totalValues.add(rs.getBigDecimal("total_revenue"));
	        }
	        topBookingTours.add(tourNames);
	        topBookingTours.add(totalBookings);
	        topBookingTours.add(totalValues);
	        
	        return topBookingTours;
	        
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
	                this.cp.releaseConnection(this.con, "TopBookingTour");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}
