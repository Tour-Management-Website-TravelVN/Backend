package ads.user;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.TourOperator;
import ads.objects.TourUnit;

public class TourOperatorFunctionImpl implements TourOperatorFunction {

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	// Đối tượng kết nối
	private Connection con;

	private final int MAX_ITEM_OF_PAGE = 20;

	private static volatile TourOperatorFunctionImpl instance;

	// Singleton Pattern
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
			this.con = TourOperatorFunctionImpl.cp.getConnection("Tour_Operator");

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
	public ArrayList<TourOperator> getAllTourUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TourOperator> getByName(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByName(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TourOperator> getObjects(TourOperator similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TourOperator getById(String id) {
		this.con = getConnection(this.cp);
		TourOperator tmp  = new TourOperator();
		PreparedStatement p = null;
		ResultSet result = null;
		try {
			String query = "Select * from tour_operator where tour_operator_id like ?";
			
			p = con.prepareStatement(query);
			
			p.setString(1, id);
			result = p.executeQuery();
			if(result.next())
			{
				tmp = new TourOperator(result.getInt(1),result.getString(2),result.getString(3),(result.getDate(4) != null)?result.getDate(4).toLocalDate():null,result.getBoolean(5),result.getString(6),result.getString(7),result.getString(8),result.getString(9),result.getBigDecimal(10),(result.getDate(11) != null)?result.getDate(11).toLocalDate():null,(result.getDate(12) != null)?result.getDate(12).toLocalDate():null,null,null,null,null,null,null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				if(result != null )
				result.close();
				if(p != null)
				p.close();
			this.cp.releaseConnection(con, "Tour_Operator");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			
		return tmp;
	}

	@Override
	public boolean updateTourOperator(TourOperator t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTourOperator(TourOperator t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
