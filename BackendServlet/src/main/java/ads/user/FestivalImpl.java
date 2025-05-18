package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.PoolImpl;
import ads.objects.FestivalObject;

public class FestivalImpl implements Festival {

	public final static FestivalImpl Instance = new FestivalImpl();

	public static FestivalImpl getInstance()
	{
		return Instance;
	}
	private Connection con;

	public FestivalImpl() {
		try {
			this.con = PoolImpl.getInstance().getConnection("Festival");
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
	public ArrayList<FestivalObject> getAllFestival() {
		 ArrayList<FestivalObject> festivals = new ArrayList<>();
	        String sql = "SELECT festival_id, festival_name, description, display_status FROM festival";

	        try (
	        		PreparedStatement stmt = con.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                FestivalObject festival = new FestivalObject();
	                festival.setId(rs.getInt("festival_id"));
	                festival.setFestivalName(rs.getString("festival_name"));
	                festival.setDescription(rs.getString("description"));
	                festival.setDisplayStatus(rs.getBoolean("display_status"));

	                // Không lấy tourUnitSet ở đây để tránh N+1 query, unless cần thiết
	                festivals.add(festival);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); // hoặc log bằng Logger
	        }finally {
		    	try {
					PoolImpl.getInstance().releaseConnection(con, "Festival");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }

	        return festivals;
	}

	@Override
	public ArrayList<FestivalObject> getByName(String search) {
		// TODO Auto-generated method stub
		return null;
	}
	public FestivalObject getFestivalByName(String name) {
		FestivalObject festival = null;
	    String sql = "SELECT festival_id, festival_name, description, display_status FROM festival WHERE festival_name like ?";
	    
	    try (
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setString(1, name);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            festival = new FestivalObject();
	            festival.setId(rs.getInt("festival_id"));
	            festival.setFestivalName(rs.getString("festival_name"));
	            festival.setDescription(rs.getString("description"));
	            festival.setDisplayStatus(rs.getBoolean("display_status"));

	            // Nếu display_status có thể null, nên xử lý:
	            if (rs.wasNull()) {
	                festival.setDisplayStatus(null);
	            }

	            // Bạn có thể load tourUnitSet sau, nếu cần
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	try {
				PoolImpl.getInstance().releaseConnection(con, "Festival");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return festival;
	}

	@Override
	public int countByName(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<FestivalObject> getObjects(FestivalObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FestivalObject getById(String id) {
		FestivalObject festival = null;
	    String sql = "SELECT festival_id, festival_name, description, display_status FROM festival WHERE festival_id = ?";
	    
	    try (
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            festival = new FestivalObject();
	            festival.setId(rs.getInt("festival_id"));
	            festival.setFestivalName(rs.getString("festival_name"));
	            festival.setDescription(rs.getString("description"));
	            festival.setDisplayStatus(rs.getBoolean("display_status"));

	            // Nếu display_status có thể null, nên xử lý:
	            if (rs.wasNull()) {
	                festival.setDisplayStatus(null);
	            }

	            // Bạn có thể load tourUnitSet sau, nếu cần
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	    	try {
				PoolImpl.getInstance().releaseConnection(con, "Festival");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return festival;
	}

	@Override
	public boolean updateFestival(FestivalObject t) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
