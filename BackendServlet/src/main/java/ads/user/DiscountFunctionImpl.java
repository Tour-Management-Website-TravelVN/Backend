package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Discount;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscountFunctionImpl implements DiscountFunction {

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	private final int MAX_ITEM_OF_PAGE = 100;

	private static volatile DiscountFunctionImpl instance;

	// Singleton Pattern
	public static DiscountFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = DiscountFunctionImpl.class;
			synchronized (DiscountFunctionImpl.class) {
				if (instance == null) {
					instance = new DiscountFunctionImpl();
				}
			}
		}

		return instance;
	}

	public DiscountFunctionImpl() {
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
			con = DiscountFunctionImpl.cp.getConnection("Discount");

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
	public List<Discount> getDiscounts(int page) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			List<Discount> discounts = new ArrayList<Discount>();
			
			con = getConnection(cp);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT discount_id, discount_name, discount_value, discount_unit FROM discount LIMIT ")
			.append((page-1)*MAX_ITEM_OF_PAGE).append(",").append(page*MAX_ITEM_OF_PAGE);
			pre = con.prepareStatement(sql.toString());
			rs = pre.executeQuery();
			while(rs.next()) {
				discounts.add(new Discount().builder()
						.id(rs.getInt(1))
						.discountName(rs.getString(2))
						.discountValue(rs.getBigDecimal(3))
						.discountUnit(rs.getString(4))
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
				if(con!=null) this.cp.releaseConnection(con, "Discount");
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
	public Discount addDiscount(Discount discount) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("INSERT INTO discount (discount_name, discount_value, discount_unit) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, discount.getDiscountName());
			pre.setBigDecimal(2, discount.getDiscountValue());
			pre.setString(3, discount.getDiscountUnit());
			int insert = pre.executeUpdate();
			
			if(insert>0) {
				rs = pre.getGeneratedKeys();
				if(rs.next()) discount.setId(rs.getInt(1));
				con.commit();
				
				return discount;
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
				if(con!=null) this.cp.releaseConnection(con, "Discount");
				if(pre!=null) pre.close();
				if(pre2!=null) pre2.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean updateDiscount(Discount discount) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("SELECT tour_unit_id FROM tour_unit WHERE discount_id = ? LIMIT 1");
			pre.setInt(1, discount.getId());
			rs = pre.executeQuery();
			if(rs.next()) return false;
			
			pre2 = con.prepareStatement("UPDATE discount SET discount_name = ?, discount_value = ?, discount_unit = ? WHERE discount_id = ?");
			pre2.setString(1, discount.getDiscountName());
			pre2.setBigDecimal(2, discount.getDiscountValue());
			pre2.setString(3, discount.getDiscountUnit());
			pre2.setInt(4, discount.getId());
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
				if(con!=null) this.cp.releaseConnection(con, "Discount");
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
	public boolean deleteDiscount(int discountId) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("SELECT tour_unit_id FROM tour_unit WHERE discount_id = ? LIMIT 1");
			pre.setInt(1, discountId);
			rs = pre.executeQuery();
			if(rs.next()) return false;
			
			pre2 = con.prepareStatement("DELETE FROM discount WHERE discount_id = ?");
			pre2.setInt(1, discountId);
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
				if(con!=null) this.cp.releaseConnection(con, "Discount");
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
			rs = con.prepareStatement("SELECT COUNT(*) FROM discount").executeQuery();
			if(rs.next()) {
				return (int)Math.ceil((double)rs.getInt(1)/MAX_ITEM_OF_PAGE);
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
				if(con!=null) this.cp.releaseConnection(con, "Discount");
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return -1;
	}

	@Override
	public boolean canUpOrDel(int discountId) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection(cp);
			pre = con.prepareStatement("SELECT tour_unit_id FROM tour_unit WHERE discount_id = ? LIMIT 1");
			pre.setInt(1, discountId);
			rs = pre.executeQuery();
			if(rs.next()) return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)this.cp.releaseConnection(con, "Dicount");
				if(pre!=null) pre.close();
				if(rs!=null) rs.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return true;
	}

}
