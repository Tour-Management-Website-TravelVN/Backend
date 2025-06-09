package ads.user;

import java.sql.Connection;

import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.ArrayList;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.TourUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TourUnitFunctionImpl implements TourUnitFunction{
	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	// Đối tượng kết nối
	private Connection con;

	private final int MAX_ITEM_OF_PAGE = 500;

	private static volatile TourUnitFunctionImpl instance;

	// Singleton Pattern
	public static TourUnitFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = TourUnitFunctionImpl.class;
			synchronized (TourUnitFunctionImpl.class) {
				if (instance == null) {
					instance = new TourUnitFunctionImpl();
				}
			}
		}

		return instance;
	}

	public TourUnitFunctionImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Hàm lấy kết nối (do dùng SingletonPattern)
	 * 
	 * @param cp pool
	 * @return 1 kết nối
	 */
	public Connection getConnection(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		try {
			// Xin ket noi de lam viec
			this.con = TourUnitFunctionImpl.cp.getConnection("Tour_Unit");

			// Kiem tra che do thuc thi tu dong
			if (this.con.getAutoCommit()) {
				// Cham dut che do thuc thi tu dong

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
	public ArrayList<TourUnit> getAllTourUnit(String tour_id, int page) {
		this.con = getConnection(this.cp);
		ArrayList<TourUnit> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tour_unit where tour_id = ? LIMIT ")
		.append((page-1)*MAX_ITEM_OF_PAGE).append(",").append(page*MAX_ITEM_OF_PAGE);
		
	    PreparedStatement pre = null;
        ResultSet rs = null;
	    try  {
	    	
	    	pre = this.con.prepareStatement(sql.toString());
	    	System.out.println(sql.toString());
	    	
	    	pre.setString(1, tour_id);
	    	rs = pre.executeQuery();
	        while (rs.next()) {
	            TourUnit tu = new TourUnit();
	            tu.setTourUnitId(rs.getString("tour_unit_id"));
	            tu.setAdultTourCost(rs.getBigDecimal("adult_tour_cost"));
	            tu.setAdultTourPrice(rs.getBigDecimal("adult_tour_price"));
	            tu.setAvailableCapacity(rs.getShort("available_capacity"));
	            tu.setBabyTourCost(rs.getBigDecimal("baby_tour_cost"));
	            tu.setBabyTourPrice(rs.getBigDecimal("baby_tour_price"));
	            tu.setChildTourCost(rs.getBigDecimal("child_tour_cost"));
	            tu.setChildTourPrice(rs.getBigDecimal("child_tour_price"));
	            tu.setCreatedTime( rs.getTimestamp("created_time").toInstant());
	            tu.setDepartureDate(rs.getDate("departure_date").toLocalDate());
	            tu.setLastUpdatedTime(rs.getTimestamp("last_updated_time").toInstant());
	            tu.setMaximumCapacity(rs.getShort("maximum_capacity"));
	            tu.setPrivateRoomPrice(rs.getBigDecimal("private_room_price"));
	            tu.setReturnDate(rs.getDate("return_date").toLocalDate());
	            tu.setToddlerTourCost(rs.getBigDecimal("toddler_tour_cost"));
	            tu.setToddlerTourPrice(rs.getBigDecimal("toddler_tour_price"));
	            tu.setTotalAdditionalCost(rs.getBigDecimal("total_additional_cost"));
	            tu.setDiscount(DiscountFunctionImpl.getInstance().getById(rs.getInt("discount_id")));
	            // Nếu cần load thêm các entity liên quan
	           //  tu.setDiscount(discountDAO.getById(rs.getInt("discount_id")));
	             tu.setFestival(FestivalFunctionImpl.getInstance().getById(rs.getInt("festival_id")));
	             tu.setTour(TourFunctionImpl.getInstance().getTourByTourId(rs.getString("tour_id")));
	             tu.setTourOperator(TourOperatorFunctionImpl.getInstance().getById(""+rs.getInt("tour_operator_id")));
	             tu.setLastUpdatedOperator(TourOperatorFunctionImpl.getInstance().getById(""+rs.getInt("last_updated_operator")));
	            list.add(tu);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	       
	    }finally {
	    	try {
	    		ConnectionPoolImpl.getInstance().releaseConnection(con, "Tour_Unit");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    return list;
	}

	@Override
	public ArrayList<TourUnit> getByName(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByName(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TourUnit> getObjects(TourUnit similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public TourUnit getById(String id) {
		TourUnit tu  = new TourUnit();
		String query = "Select * from tour_unit where tour_unit_id like ?";
		try {
			this.con = getConnection(this.cp);
	         PreparedStatement p = con.prepareStatement(query);
			
			p.setString(1, id);
			ResultSet rs = p.executeQuery();
			if(rs.next())
			{
				 tu.setTourUnitId(rs.getString("tour_unit_id"));
		            tu.setAdultTourCost(rs.getBigDecimal("adult_tour_cost"));
		            tu.setAdultTourPrice(rs.getBigDecimal("adult_tour_price"));
		            tu.setAvailableCapacity(rs.getShort("available_capacity"));
		            tu.setBabyTourCost(rs.getBigDecimal("baby_tour_cost"));
		            tu.setBabyTourPrice(rs.getBigDecimal("baby_tour_price"));
		            tu.setChildTourCost(rs.getBigDecimal("child_tour_cost"));
		            tu.setChildTourPrice(rs.getBigDecimal("child_tour_price"));
		            tu.setCreatedTime(rs.getTimestamp("created_time").toInstant());
		            tu.setDepartureDate(rs.getDate("departure_date").toLocalDate());
		            tu.setLastUpdatedTime(rs.getTimestamp("last_updated_time").toInstant());
		            tu.setMaximumCapacity(rs.getShort("maximum_capacity"));
		            tu.setPrivateRoomPrice(rs.getBigDecimal("private_room_price"));
		            tu.setReturnDate(rs.getDate("return_date").toLocalDate());
		            tu.setToddlerTourCost(rs.getBigDecimal("toddler_tour_cost"));
		            tu.setToddlerTourPrice(rs.getBigDecimal("toddler_tour_price"));
		            tu.setTotalAdditionalCost(rs.getBigDecimal("total_additional_cost"));

		            // Nếu cần load thêm các entity liên quan
		           //  tu.setDiscount(discountDAO.getById(rs.getInt("discount_id")));
		             tu.setFestival(FestivalFunctionImpl.getInstance().getById(rs.getInt("festival_id")));
		             tu.setTour(TourFunctionImpl.getInstance().getTourByTourId(rs.getString("tour_id")));
		             tu.setTourOperator(TourOperatorFunctionImpl.getInstance().getById(""+rs.getInt("tour_operator_id")));
		             tu.setLastUpdatedOperator(TourOperatorFunctionImpl.getInstance().getById(""+rs.getInt("last_updated_operator")));
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ConnectionPoolImpl.getInstance().releaseConnection(con, "Tour_Unit");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return tu;
	}

	//ERROR
	@Override
	public boolean updateTourUnit(TourUnit tourUnit) {
		 String sql = "UPDATE tour_unit SET " +
	                "adult_tour_cost = ?, adult_tour_price = ?, available_capacity = ?, " +
	                "baby_tour_cost = ?, baby_tour_price = ?, child_tour_cost = ?, child_tour_price = ?, " +
	                "departure_date = ?, return_date = ?, last_updated_time = ?, maximum_capacity = ?, " +
	                "private_room_price = ?, toddler_tour_cost = ?, toddler_tour_price = ?, " +
	                "total_additional_cost = ?, discount_id = ?, festival_id = ?, tour_id = ?, " +
	                "tour_operator_id = ?, last_updated_operator = ? " +
	                "WHERE tour_unit_id = ?";
		 StringBuilder sql_2 = new StringBuilder();
		 boolean result = false;
		 int rowUpdated = 0;
	        try  {
	        	this.con  = getConnection(this.cp);
	        	PreparedStatement stmt = con.prepareStatement(sql);
	        	
	        	Short avaiableCapacity = tourUnit.getAvailableCapacity();
	            stmt.setBigDecimal(1, tourUnit.getAdultTourCost());
	            stmt.setBigDecimal(2, tourUnit.getAdultTourPrice());
	            stmt.setShort(3, avaiableCapacity);
	            stmt.setBigDecimal(4, tourUnit.getBabyTourCost());
	            stmt.setBigDecimal(5, tourUnit.getBabyTourPrice());
	            stmt.setBigDecimal(6, tourUnit.getChildTourCost());
	            stmt.setBigDecimal(7, tourUnit.getChildTourPrice());

	            stmt.setDate(8, java.sql.Date.valueOf(tourUnit.getDepartureDate()));
	            stmt.setDate(9, java.sql.Date.valueOf(tourUnit.getReturnDate()));

	            stmt.setTimestamp(10,  new Timestamp(System.currentTimeMillis()));
	            stmt.setShort(11, tourUnit.getMaximumCapacity());
	            stmt.setBigDecimal(12, tourUnit.getPrivateRoomPrice());
	            stmt.setBigDecimal(13, tourUnit.getToddlerTourCost());
	            stmt.setBigDecimal(14, tourUnit.getToddlerTourPrice());
	            stmt.setBigDecimal(15, tourUnit.getTotalAdditionalCost());

	            // discount, festival, tour, operator: kiểm tra null để tránh lỗi
	            if (tourUnit.getDiscount() != null)
	                stmt.setInt(16, tourUnit.getDiscount().getId());
	            else
	                stmt.setNull(16, Types.INTEGER);

	            if (tourUnit.getFestival() != null)
	                stmt.setInt(17, tourUnit.getFestival().getId());
	            else
	                stmt.setNull(17, Types.INTEGER);

	            if (tourUnit.getTour() != null)
	                stmt.setString(18, tourUnit.getTour().getTourId());
	            else
	                stmt.setNull(18, Types.VARCHAR);

	            if (tourUnit.getTourOperator() != null)
	                stmt.setInt(19, 1);
	            else
	                stmt.setNull(19, Types.INTEGER);

	            if (tourUnit.getLastUpdatedOperator() != null)
	                stmt.setInt(20, tourUnit.getLastUpdatedOperator().getId());
	            else
	                stmt.setNull(20, Types.INTEGER);

	            stmt.setString(21, tourUnit.getTourUnitId());

	            rowUpdated  = stmt.executeUpdate();
	            System.out.println(rowUpdated);
	            System.out.println("Running : "+stmt);
	            
	            if(rowUpdated != 0)
	            {
	            	this.con.commit();
	            	result = true;
	            }
	            else {
	            	this.con.rollback();
	            	result = false;
	            }
	            
	        } catch (SQLException e) {
	        	try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            e.printStackTrace();
	        }finally{
				try {
					ConnectionPoolImpl.getInstance().releaseConnection(con, "Tour_Unit");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        return result;
	}

	@Override
	public boolean addTourUnit(TourUnit tourUnit) {
		String sql = "INSERT INTO tour_unit (" +
		        "adult_tour_cost, adult_tour_price, available_capacity, " +
		        "baby_tour_cost, baby_tour_price, child_tour_cost, child_tour_price, " +
		        "departure_date, return_date, last_updated_time, maximum_capacity, " +
		        "private_room_price, toddler_tour_cost, toddler_tour_price, " +
		        "total_additional_cost, discount_id, festival_id, tour_id, " +
		        "tour_operator_id, last_updated_operator, tour_unit_id,created_time) " +
		        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

	        try {
	        	this.con = getConnection(this.cp);
	        	PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setBigDecimal(1, tourUnit.getAdultTourCost());
	            stmt.setBigDecimal(2, tourUnit.getAdultTourPrice());	
	            stmt.setShort(3, tourUnit.getMaximumCapacity());
	            stmt.setBigDecimal(4, tourUnit.getBabyTourCost());
	            stmt.setBigDecimal(5, tourUnit.getBabyTourPrice());
	            stmt.setBigDecimal(6, tourUnit.getChildTourCost());
	            stmt.setBigDecimal(7, tourUnit.getChildTourPrice());

	            stmt.setDate(8, java.sql.Date.valueOf(tourUnit.getDepartureDate()));
	            stmt.setDate(9, java.sql.Date.valueOf(tourUnit.getReturnDate()));
	            
	            stmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()) );
	            stmt.setShort(11, tourUnit.getMaximumCapacity());
	            stmt.setBigDecimal(12, tourUnit.getPrivateRoomPrice());
	            stmt.setBigDecimal(13, tourUnit.getToddlerTourCost());
	            stmt.setBigDecimal(14, tourUnit.getToddlerTourPrice());
	            stmt.setBigDecimal(15, tourUnit.getTotalAdditionalCost());

	            // discount, festival, tour, operator: kiểm tra null để tránh lỗi
	            if (tourUnit.getDiscount() != null)
	                stmt.setInt(16, tourUnit.getDiscount().getId());
	            else
	                stmt.setNull(16, Types.INTEGER);

	            if (tourUnit.getFestival() != null)
	                stmt.setInt(17, tourUnit.getFestival().getId());
	            else
	                stmt.setNull(17, Types.INTEGER);

	            if (tourUnit.getTour() != null)
	                stmt.setString(18, tourUnit.getTour().getTourId());
	            else
	                stmt.setNull(18, Types.VARCHAR);

	            if (tourUnit.getTourOperator() != null)
	                stmt.setInt(19, 1);
	            else
	                stmt.setNull(19, Types.INTEGER);

	            if (tourUnit.getLastUpdatedOperator() != null)
	                stmt.setInt(20, tourUnit.getLastUpdatedOperator().getId());
	            else
	                stmt.setNull(20, Types.INTEGER);

	            stmt.setString(21, tourUnit.getTourUnitId());
	            stmt.setTimestamp(22, new Timestamp(System.currentTimeMillis()) );
	            
	            return exe(stmt);

	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	            return false;
	        }finally{
				try {
					ConnectionPoolImpl.getInstance().releaseConnection(con, "Tour_Unit");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
	}

	@Override
	public boolean deleteById(String id) {
		
		String checkDel = "Select * from booking where tour_unit_id like ? ";
		String query = "Delete from tour_unit where tour_unit_id like ?";
		boolean rs = false;
		try{
			this.con = getConnection(this.cp);
			PreparedStatement r = con.prepareStatement(checkDel);
			r.setString(1, id);
			ResultSet isAvaiable = r.executeQuery();
			if(isAvaiable.next())
			{
				return false;
			}

			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, id);
			
			rs = exe(p);
		
		} catch (SQLException e) {
			e.printStackTrace();
			 
		}
		finally{
			try {
				ConnectionPoolImpl.getInstance().releaseConnection(con, "Tour_Unit");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return rs;
	}
	@Override
	public int getMaxPage() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			rs = con.prepareStatement("SELECT COUNT(*) FROM tour_unit").executeQuery();
			if(rs.next()) {
				return (int)Math.ceil(rs.getInt(1)/MAX_ITEM_OF_PAGE);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if(con!=null) this.cp.releaseConnection(con, "Tour_Unit");
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return -1;
	}


	private boolean exe(PreparedStatement pre) {
		// pre đã được buiên dịch và truyền giá trị đầy đủ cho các tham số
		if (pre != null) {
			try {
				int results = pre.executeUpdate();
				System.out.println("Running: " + pre.toString());

				System.out.println("Updated row :"+results);
				if (results == 0) {
					this.con.rollback();
					return false;
				}

				// Xac nhan thuc thi sau cung
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
				try {
			        if (pre != null) pre.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
		}

		return false;
	}
	public boolean exeBatch(List<PreparedStatement> statements) {
		if (statements == null || statements.isEmpty())
			return false;

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


}
