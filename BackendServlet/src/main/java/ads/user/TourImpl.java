package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.PoolImpl;
import ads.objects.TourObject;
import ads.objects.TourOperatorObject;
import ads.objects.TourUnitObject;

public class TourImpl implements Tour{

	
	public final static TourImpl Instance = new TourImpl();

	public static TourImpl getInstance()
	{
		return Instance;
	}
	private Connection con;

	public TourImpl() {
		try {
			this.con = PoolImpl.getInstance().getConnection("Tour");
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
	public ArrayList<TourObject> getAllTour() {
		ArrayList<TourObject> tours = new ArrayList<>();
	    String sql = "SELECT * FROM tour";

	    try (
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            TourObject tour = new TourObject();
	            tour.setTourId(rs.getString("tour_id"));
	            tour.setTourOperator(TourOperatorImpl.getInstance().getById(""+rs.getInt("tour_operator_id")));
	            tour.setLastUpdatedOperator(TourOperatorImpl.getInstance().getById(""+rs.getInt("last_updated_operator")));
	            tour.setTourName(rs.getString("tour_name"));
	            tour.setDuration(rs.getString("duration"));
	            tour.setVehicle(rs.getString("vehicle"));
	            tour.setTargetAudience(rs.getString("target_audience"));
	            tour.setDeparturePlace(rs.getString("departure_place"));
	            tour.setPlacesToVisit(rs.getString("places_to_visit"));
	            tour.setCuisine(rs.getString("cuisine"));
	            tour.setIdealTime(rs.getString("ideal_time"));
	            tour.setDescription(rs.getString("description"));
	            tour.setCreatedTime(rs.getTimestamp("created_time").toInstant());
	            tour.setLastUpdatedTime(rs.getTimestamp("last_updated_time").toInstant());
	            tour.setInclusions(rs.getString("inclusions"));
	            tour.setExclusions(rs.getString("exclusions"));

	            // Nếu muốn load các Set<TourProgram>, Set<Image>, Set<TourUnit> thì cần JOIN hoặc truy vấn riêng

	            tours.add(tour);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tours;
	}

	@Override
	public ArrayList<TourObject> getByName(String search) {
		ArrayList<TourObject> all  = new ArrayList<TourObject>();

		try {
			Connection cnt =  this.con;
			

			PreparedStatement p = cnt.prepareStatement("Select * from tour where tour_name like ? ");
			
			p.setString(1,"%"+ search +"%");
			
			ResultSet result = p.executeQuery();
			
//			while(result.next())
//			{
//				Tour temp = new Tour(result.getString(1),result.getInt(2),result.getInt(3),result.getInt(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12), result.getString(13), result.getDate(14), result.getDate(15), result.getString(16), result.getString(17), null, null, null);
//				all.add(temp);
//			}
			
			
			cnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return all;
	}

	@Override
	public int countByName(String search) {
		int rs= 0;
		try {
			Connection cnt = this.con;
			
			String query = "Select count(*) from tour where tour_name like ? ";
			
			PreparedStatement p = cnt.prepareStatement(query);
			p.setString(1,"%"+ search +"%");

			ResultSet result = p.executeQuery();
			if(result.next())
				rs =  result.getInt(1);
			cnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ArrayList<TourUnitObject> getObjects(TourObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TourObject getById(String id) {
		TourObject tmp  = new TourObject();
		String query = "Select * from tour where tour_id like ?";
		try(
		         PreparedStatement p = con.prepareStatement(query)
				) {
			
			p.setString(1, id);
			ResultSet rs = p.executeQuery();
			if(rs.next())
			{
			tmp = TourObject
					.builder()
					 .tourId(rs.getString("tour_id"))
                     .tourName(rs.getString("tour_name"))
                     .duration(rs.getString("duration"))
                     .vehicle(rs.getString("vehicle"))
                     .targetAudience(rs.getString("target_audience"))
                     .departurePlace(rs.getString("departure_place"))
                     .placesToVisit(rs.getString("places_to_visit"))
                     .cuisine(rs.getString("cuisine"))
                     .idealTime(rs.getString("ideal_time"))
                     .description(rs.getString("description"))
                     .inclusions(rs.getString("inclusions"))
                     .exclusions(rs.getString("exclusions"))
                     .createdTime(rs.getTimestamp("created_time").toInstant())          // Timestamp → Instant
                     .lastUpdatedTime(rs.getTimestamp("last_updated_time").toInstant()) // Timestamp → Instant
					.build();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return tmp;
	}

	@Override
	public boolean updateTourUnit(TourObject t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTourUnit(TourObject t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
