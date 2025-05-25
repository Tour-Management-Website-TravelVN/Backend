package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Category;

public class CategoryFunctionImpl implements CategoryFunction {
	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	private static volatile CategoryFunctionImpl instance;

	// Singleton Pattern
	public static CategoryFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = CategoryFunctionImpl.class;
			synchronized (CategoryFunctionImpl.class) {
				if (instance == null) {
					instance = new CategoryFunctionImpl();
				}
			}
		}

		return instance;
	}

	public CategoryFunctionImpl() {
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
			Connection con = ImageFunctionImpl.cp.getConnection("Category");

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
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;
		Connection con = null;
		try {
			List<Category> list = new ArrayList<Category>();
			con = getConnection(cp);
			pre = con.prepareStatement("SELECT category_id, category_name FROM category ORDER BY category_id ");
			rs = pre.executeQuery();
			
			while(rs.next()) {
				list.add(new Category().builder()
						.id(rs.getInt(1))
						.categoryName(rs.getString(2))
						.build());
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(con != null) this.cp.releaseConnection(con, "Category");
				if(rs!=null) rs.close();
				if (pre != null)
					pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
