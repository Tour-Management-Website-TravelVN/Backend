package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.BookingAggregate;

public class StatisticFunctionImpl implements StatisticFunction {

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	private final int MAX_ITEM_OF_PAGE = 100;

	private static volatile StatisticFunctionImpl instance;

	// Singleton Pattern
	public static StatisticFunction getInstance() {
		if (instance == null) {
			Class var0 = StatisticFunctionImpl.class;
			synchronized (StatisticFunctionImpl.class) {
				if (instance == null) {
					instance = new StatisticFunctionImpl();
				}
			}
		}

		return instance;
	}

	public StatisticFunctionImpl() {
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
			Connection con = null;
			// Xin ket noi de lam viec
			con = StatisticFunctionImpl.cp.getConnection("Statistic");

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

	// Lấy danh sách các thống kế số lượng người theo độ tuổi theo các tháng trong
	// năm
	@Override
	public List<BookingAggregate> getStackedBarChartData() {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<BookingAggregate> list = new ArrayList<BookingAggregate>();
			
			con = StatisticFunctionImpl.cp.getConnection("Statistic");
			pre = con.prepareStatement("SELECT YEAR(booking_date), MONTH(booking_date), SUM(adult_number) AS adultQuants, SUM(child_number) AS childQuants, SUM(toddler_number) AS toddlerQuants, SUM(baby_number) AS babyQuants FROM booking WHERE `status` IN ('O', 'D', 'P') GROUP BY YEAR(booking_date), MONTH(booking_date) ORDER BY YEAR(booking_date) DESC, MONTH(booking_date) ASC");
			rs = pre.executeQuery();
			while(rs.next()) {
				list.add(BookingAggregate.builder()
						.year(rs.getInt(1))
						.month(rs.getInt(2))
						.adultQuants(rs.getInt(3))
						.childQuants(rs.getInt(4))
						.toddlerQuants(rs.getInt(5))
						.babyQuants(rs.getInt(6))
						.build());
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (pre != null)
					pre.close();
				if(con!=null) con.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

}
