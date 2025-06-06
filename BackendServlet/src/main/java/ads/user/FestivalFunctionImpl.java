package ads.user;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Discount;
import ads.objects.Festival;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FestivalFunctionImpl implements FestivalFunction {
	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	private final int MAX_ITEM_OF_PAGE = 100;

	private static volatile FestivalFunctionImpl instance;

	// Singleton Pattern
	public static FestivalFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = FestivalFunctionImpl.class;
			synchronized (FestivalFunctionImpl.class) {
				if (instance == null) {
					instance = new FestivalFunctionImpl();
				}
			}
		}

		return instance;
	}

	public FestivalFunctionImpl() {
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
			con = FestivalFunctionImpl.cp.getConnection("Festival");

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
	public List<Festival> getFestivals(int page) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			List<Festival> discounts = new ArrayList<>();
			
			con = getConnection(cp);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT festival_id, festival_name, description, display_status FROM festival LIMIT ")
			.append((page-1)*MAX_ITEM_OF_PAGE).append(",").append(page*MAX_ITEM_OF_PAGE);
			pre = con.prepareStatement(sql.toString());
			rs = pre.executeQuery();
			while(rs.next()) {
				discounts.add(new Festival().builder()
						.id(rs.getInt(1))
						.festivalName(rs.getString(2))
						.description(rs.getString(3))
						.displayStatus(rs.getBoolean(4))
						.build());
			}
			return discounts;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(con!=null)
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} finally {
			try {
				if(con!=null) this.cp.releaseConnection(con, "Festival");
				if (pre != null)
					pre.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public boolean addFestival(Festival festival) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("INSERT INTO festival (festival_name, description, display_status) VALUES (?,?,?)");
			pre.setString(1, festival.getFestivalName());
			pre.setString(2, festival.getDescription());
			pre.setBoolean(3, festival.getDisplayStatus());
			pre.executeUpdate();

			con.commit();
			return true;
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
				if(con!=null) this.cp.releaseConnection(con, "Festival");
				if(pre!=null) pre.close();
				if(pre2!=null) pre2.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateFestival(Festival festival) {
		// TODO Auto-generated method stub
//		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
//			pre = con.prepareStatement("SELECT tour_unit_id FROM tour_unit WHERE discount_id = ? LIMIT 1");
//			pre.setInt(1, discount.getId());
//			rs = pre.executeQuery();
//			if(!rs.next()) return false;
			
			pre2 = con.prepareStatement("UPDATE festival SET festival_name = ?, description = ?, display_status = ? WHERE festival_id = ?");
			pre2.setString(1, festival.getFestivalName());
			pre2.setString(2, festival.getDescription());
			pre2.setBoolean(3, festival.getDisplayStatus());
			pre2.setInt(4, festival.getId());
			pre2.executeUpdate();
			
			con.commit();
			return true;
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
				if(con!=null) this.cp.releaseConnection(con, "Festival");
//				if(pre!=null) pre.close();
				if(pre2!=null) pre2.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteFestival(int festivalId) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("SELECT tour_unit_id FROM tour_unit WHERE festival_id = ? LIMIT 1");
			pre.setInt(1, festivalId);
			rs = pre.executeQuery();
			if(rs.next()) return false;
			
			pre2 = con.prepareStatement("DELETE FROM festival WHERE festival_id = ?");
			pre2.setInt(1, festivalId);
			pre2.executeUpdate();
			
			con.commit();
			return true;
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
				if(con!=null) this.cp.releaseConnection(con, "Festival");
				if(pre!=null) pre.close();
				if(pre2!=null) pre2.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public int getMaxPage() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			rs = con.prepareStatement("SELECT COUNT(*) FROM festival").executeQuery();
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
				if(con!=null) this.cp.releaseConnection(con, "Festival");
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return -1;
	}

	@Override
	public boolean canDel(int festivalId) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("SELECT tour_unit_id FROM tour_unit WHERE festival_id = ? LIMIT 1");
			pre.setInt(1, festivalId);
			rs = pre.executeQuery();
			if(rs.next()) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) this.cp.releaseConnection(con, "Festival");
				if(pre!=null) pre.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean updateVisible(int festivalId, boolean isShow) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("UPDATE festival SET display_status=? WHERE festival_id = ?");
			pre.setBoolean(1, isShow);
			pre.setInt(2, festivalId);
			pre.executeUpdate();
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(pre!=null) pre.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public Festival getById(int id) {
		PreparedStatement pre = null;		
		ResultSet rs = null;	
		Connection con = null;
		Festival festival = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("Select * from festival WHERE festival_id = ?");
			pre.setInt(1,id);
			rs = pre.executeQuery();
			if(rs.next())
			{
				festival = new Festival();
	            festival.setId(rs.getInt("festival_id"));
	            festival.setFestivalName(rs.getString("festival_name"));
	            festival.setDescription(rs.getString("description"));
	            festival.setDisplayStatus(rs.getBoolean("display_status"));

			}
			
			
		}catch(Exception e )
		{
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)this.cp.releaseConnection(con, "Dicount");
				if(pre!=null) pre.close();
				if(rs!=null) rs.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}

		return festival;
	}

}
