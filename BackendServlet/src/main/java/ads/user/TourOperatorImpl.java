package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.PoolImpl;
import ads.objects.TourOperatorObject;
import ads.objects.TourUnitObject;

public class TourOperatorImpl implements TourOperator {


	public final static TourOperatorImpl Instance = new TourOperatorImpl();

	public static TourOperatorImpl getInstance()
	{
		return Instance;
	}
	private Connection con;

	public TourOperatorImpl() {
		try {
			this.con = PoolImpl.getInstance().getConnection("Tour_Operation");
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
	public ArrayList<TourOperatorObject> getAllTourUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TourOperatorObject> getByName(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByName(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TourOperatorObject> getObjects(TourOperatorObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TourOperatorObject getById(String id) {
		TourOperatorObject tmp  = new TourOperatorObject();
		
		try {
			
			
			String query = "Select * from tour_operator where tour_operator_id like ?";
			
			PreparedStatement p = con.prepareStatement(query);
			
			p.setString(1, id);
			ResultSet result = p.executeQuery();
			if(result.next())
			{
				tmp = new TourOperatorObject(result.getInt(1),result.getString(2),result.getString(3),(result.getDate(4) != null)?result.getDate(4).toLocalDate():null,result.getBoolean(5),result.getString(6),result.getString(7),result.getString(8),result.getString(9),result.getBigDecimal(10),(result.getDate(11) != null)?result.getDate(11).toLocalDate():null,(result.getDate(12) != null)?result.getDate(12).toLocalDate():null,null,null,null,null,null,null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				PoolImpl.getInstance().releaseConnection(con, "Tour_Operator");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			
		return tmp;
	}

	@Override
	public boolean updateTourOperator(TourOperatorObject t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTourOperator(TourOperatorObject t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
