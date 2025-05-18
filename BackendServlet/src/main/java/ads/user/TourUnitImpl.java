package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;



import ads.PoolImpl;
import ads.objects.TourUnitObject;

public class TourUnitImpl implements TourUnit{

	public final static TourUnitImpl Instance = new TourUnitImpl();

	public static TourUnitImpl getInstance()
	{
		return Instance;
	}
	
	private Connection con;

	public TourUnitImpl() {
		try {
			this.con = PoolImpl.getInstance().getConnection("Tour_unit");
			if (this.con == null) {
			    throw new IllegalStateException("Không lấy được Connection từ pool!");
			}
			if(this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	@Override
	public ArrayList<TourUnitObject> getAllTourUnit() {
		ArrayList<TourUnitObject> list = new ArrayList<>();
	    String sql = "SELECT * FROM tour_unit";

	    try (
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            TourUnitObject tu = new TourUnitObject();
	            tu.setTourUnitId(rs.getString("tour_unit_id"));
	            tu.setAdultTourCost(rs.getBigDecimal("adult_tour_cost"));
	            tu.setAdultTourPrice(rs.getBigDecimal("adult_tour_price"));
	            tu.setAvailableCapacity(rs.getShort("available_capacity"));
	            tu.setBabyTourCost(rs.getBigDecimal("baby_tour_cost"));
	            tu.setBabyTourPrice(rs.getBigDecimal("baby_tour_price"));
	            tu.setChildTourCost(rs.getBigDecimal("child_tour_cost"));
	            tu.setChildTourPrice(rs.getBigDecimal("child_tour_price"));
	            tu.setCreatedTime(rs.getTimestamp("created_time"));
	            tu.setDepartureDate(rs.getDate("departure_date").toLocalDate());
	            tu.setLastUpdatedTime(rs.getTimestamp("last_updated_time"));
	            tu.setMaximumCapacity(rs.getShort("maximum_capacity"));
	            tu.setPrivateRoomPrice(rs.getBigDecimal("private_room_price"));
	            tu.setReturnDate(rs.getDate("return_date").toLocalDate());
	            tu.setToddlerTourCost(rs.getBigDecimal("toddler_tour_cost"));
	            tu.setToddlerTourPrice(rs.getBigDecimal("toddler_tour_price"));
	            tu.setTotalAdditionalCost(rs.getBigDecimal("total_additional_cost"));
	            tu.setDiscount(DiscountImpl.getInstance().getById(""+rs.getInt("discount_id")));
	            // Nếu cần load thêm các entity liên quan
	           //  tu.setDiscount(discountDAO.getById(rs.getInt("discount_id")));
	             tu.setFestival(FestivalImpl.getInstance().getById(""+rs.getInt("festival_id")));
	             tu.setTour(TourImpl.getInstance().getById(rs.getString("tour_id")));
	             tu.setTourOperator(TourOperatorImpl.getInstance().getById(""+rs.getInt("tour_operator_id")));
	             tu.setLastUpdatedOperator(TourOperatorImpl.getInstance().getById(""+rs.getInt("last_updated_operator")));
	            list.add(tu);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	       
	    }finally {
	    	try {
				PoolImpl.getInstance().releaseConnection(con, "Tour_unit");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    return list;
	}

	@Override
	public ArrayList<TourUnitObject> getByName(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByName(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TourUnitObject> getObjects(TourUnitObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TourUnitObject getById(String id) {
		TourUnitObject tu  = new TourUnitObject();
		String query = "Select * from tour_unit where tour_unit_id like ?";
		try(
		         PreparedStatement p = con.prepareStatement(query)
				) {
			
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
		            tu.setCreatedTime(rs.getTimestamp("created_time"));
		            tu.setDepartureDate(rs.getDate("departure_date").toLocalDate());
		            tu.setLastUpdatedTime(rs.getTimestamp("last_updated_time"));
		            tu.setMaximumCapacity(rs.getShort("maximum_capacity"));
		            tu.setPrivateRoomPrice(rs.getBigDecimal("private_room_price"));
		            tu.setReturnDate(rs.getDate("return_date").toLocalDate());
		            tu.setToddlerTourCost(rs.getBigDecimal("toddler_tour_cost"));
		            tu.setToddlerTourPrice(rs.getBigDecimal("toddler_tour_price"));
		            tu.setTotalAdditionalCost(rs.getBigDecimal("total_additional_cost"));

		            // Nếu cần load thêm các entity liên quan
		           //  tu.setDiscount(discountDAO.getById(rs.getInt("discount_id")));
		             tu.setFestival(FestivalImpl.getInstance().getById(""+rs.getInt("festival_id")));
		             tu.setTour(TourImpl.getInstance().getById(rs.getString("tour_id")));
		             tu.setTourOperator(TourOperatorImpl.getInstance().getById(""+rs.getInt("tour_operator_id")));
		             tu.setLastUpdatedOperator(TourOperatorImpl.getInstance().getById(""+rs.getInt("last_updated_operator")));
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				PoolImpl.getInstance().releaseConnection(con, "Tour_unit");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return tu;
	}

	@Override
	public boolean updateTourUnit(TourUnitObject tourUnit) {
		 String sql = "UPDATE tour_unit SET " +
	                "adult_tour_cost = ?, adult_tour_price = ?, available_capacity = ?, " +
	                "baby_tour_cost = ?, baby_tour_price = ?, child_tour_cost = ?, child_tour_price = ?, " +
	                "departure_date = ?, return_date = ?, last_updated_time = ?, maximum_capacity = ?, " +
	                "private_room_price = ?, toddler_tour_cost = ?, toddler_tour_price = ?, " +
	                "total_additional_cost = ?, discount_id = ?, festival_id = ?, tour_id = ?, " +
	                "tour_operator_id = ?, last_updated_operator = ? " +
	                "WHERE tour_unit_id = ?";
		 boolean result = false;
	        try (
	        		PreparedStatement stmt = con.prepareStatement(sql)) {
	        	Short avaiableCapacity = TourUnitImpl.getInstance().getById(tourUnit.getTourUnitId()).getAvailableCapacity();
	            stmt.setBigDecimal(1, tourUnit.getAdultTourCost());
	            stmt.setBigDecimal(2, tourUnit.getAdultTourPrice());
	            stmt.setShort(3, avaiableCapacity);
	            stmt.setBigDecimal(4, tourUnit.getBabyTourCost());
	            stmt.setBigDecimal(5, tourUnit.getBabyTourPrice());
	            stmt.setBigDecimal(6, tourUnit.getChildTourCost());
	            stmt.setBigDecimal(7, tourUnit.getChildTourPrice());

	            stmt.setDate(8, java.sql.Date.valueOf(tourUnit.getDepartureDate()));
	            stmt.setDate(9, java.sql.Date.valueOf(tourUnit.getReturnDate()));

	            stmt.setTimestamp(10, new Timestamp(tourUnit.getLastUpdatedTime().getTime()));
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

	            return exe(stmt);
	            

	        } catch (SQLException e) {
	        	
	            e.printStackTrace();
	        }finally{
	        	
				try {
					PoolImpl.getInstance().releaseConnection(con, "Tour_unit");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        return result;
	}

	@Override
	public boolean addTourUnit(TourUnitObject tourUnit) {
		String sql = "INSERT INTO tour_unit (" +
		        "adult_tour_cost, adult_tour_price, available_capacity, " +
		        "baby_tour_cost, baby_tour_price, child_tour_cost, child_tour_price, " +
		        "departure_date, return_date, last_updated_time, maximum_capacity, " +
		        "private_room_price, toddler_tour_cost, toddler_tour_price, " +
		        "total_additional_cost, discount_id, festival_id, tour_id, " +
		        "tour_operator_id, last_updated_operator, tour_unit_id,created_time) " +
		        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

	        try (
	        		PreparedStatement stmt = con.prepareStatement(sql)) {
	        	Short avaiableCapacity = 0;
	            stmt.setBigDecimal(1, tourUnit.getAdultTourCost());
	            stmt.setBigDecimal(2, tourUnit.getAdultTourPrice());
	            stmt.setShort(3, avaiableCapacity);
	            stmt.setBigDecimal(4, tourUnit.getBabyTourCost());
	            stmt.setBigDecimal(5, tourUnit.getBabyTourPrice());
	            stmt.setBigDecimal(6, tourUnit.getChildTourCost());
	            stmt.setBigDecimal(7, tourUnit.getChildTourPrice());

	            stmt.setDate(8, java.sql.Date.valueOf(tourUnit.getDepartureDate()));
	            stmt.setDate(9, java.sql.Date.valueOf(tourUnit.getReturnDate()));
	            
	            stmt.setTimestamp(10, new Timestamp(tourUnit.getLastUpdatedTime().getTime()));
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
	            stmt.setTimestamp(22, new Timestamp(tourUnit.getLastUpdatedTime().getTime()));

	            return exe(stmt);

	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	            return false;
	        }finally{
				try {
					PoolImpl.getInstance().releaseConnection(con, "Tour_unit");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
	}

	@Override
	public boolean deleteById(String id) {
		String query = "Delete from tour_unit where tour_unit_id like ?";
		int rs = 1;
		try(
		         PreparedStatement p = con.prepareStatement(query)
				) {
			
			p.setString(1, id);
			
			rs = p.executeUpdate(query);
		
		} catch (SQLException e) {
			e.printStackTrace();
			 
		}
		finally{
			try {
				PoolImpl.getInstance().releaseConnection(con, "Tour_unit");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return rs == 1;
	}
	private boolean exe(PreparedStatement pre)
	{
		if(pre != null)
		{
			
			try {
			int result = pre.executeUpdate();
			
			if(result == 0)
			{

			this.con.rollback();
			return false;
				
			}
			this.con.commit();
			return true;
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
		
	}

}
