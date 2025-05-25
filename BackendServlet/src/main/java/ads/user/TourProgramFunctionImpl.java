package ads.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.TourProgram;

public class TourProgramFunctionImpl implements TourProgramFunction {

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	private static volatile TourProgramFunctionImpl instance;

	// Singleton Pattern
	public static TourProgramFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = TourProgramFunctionImpl.class;
			synchronized (TourProgramFunctionImpl.class) {
				if (instance == null) {
					instance = new TourProgramFunctionImpl();
				}
			}
		}

		return instance;
	}

	public TourProgramFunctionImpl() {
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
			Connection con = TourProgramFunctionImpl.cp.getConnection("Tour Program");

			// Kiem tra che do thuc thi tu dong
			if (con.getAutoCommit()) {
				// Cham dut che do thuc thi tu dong

				con.setAutoCommit(false);
			}

			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public List<TourProgram> getTourProgramsByTourId(String tourId) {
		// TODO Auto-generated method stub
		return null;
	}

}
