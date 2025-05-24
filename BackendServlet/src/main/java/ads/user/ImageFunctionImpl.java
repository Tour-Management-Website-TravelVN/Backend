package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Category;
import ads.objects.Image;
import ads.objects.Tour;
import ads.objects.TourProgram;
import ads.util.Generator;

public class ImageFunctionImpl implements ImageFunction {

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	// Đối tượng kết nối
	private Connection con;

	private static volatile ImageFunctionImpl instance;

	// Singleton Pattern
	public static ImageFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = ImageFunctionImpl.class;
			synchronized (ImageFunctionImpl.class) {
				if (instance == null) {
					instance = new ImageFunctionImpl();
				}
			}
		}

		return instance;
	}

	public ImageFunctionImpl() {
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
			this.con = ImageFunctionImpl.cp.getConnection("Image");

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

	/**
	 * Thực hiện thực thi 1 câu lệnh
	 * 
	 * @param pre Câu truy vấn (thêm, sửa, xóa)
	 * @return Kết quả (thêm, sửa, xóa)
	 */
	private boolean exe(PreparedStatement pre) {
		// pre đã được buiên dịch và truyền giá trị đầy đủ cho các tham số
		if (pre != null) {
			try {
				int results = pre.executeUpdate();
				if (results == 0) {
					this.con.rollback();
					return false;
				}

				// Xac nhan thuc thi sau cung
				this.con.commit();

				return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				pre = null;
			}
		}

		return false;
	}

	/**
	 * Thực hiện thực thi nhiều câu lệnh
	 * 
	 * @param statements Các câu truy vấn (thêm, sửa, xóa)
	 * @return Kết quả (thêm, sửa, xóa)
	 */
	public boolean exeBatch(List<PreparedStatement> statements) {
		if (statements == null || statements.isEmpty())
			return false;

		try {
			this.con.setAutoCommit(false);

			for (PreparedStatement pre : statements) {
				int result = pre.executeUpdate();
				if (result == 0) {
					this.con.rollback();
					return false;
				}
			}

			this.con.commit();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean updateImgsByTourId(String tourId, String delImages, List<String> imgUrls, int touId) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		PreparedStatement pre3 = null;
		PreparedStatement pre4 = null;
		PreparedStatement pre5 = null;
		try {
			this.con = getConnection(this.cp);

			String tourName = "";
			if (!StringUtils.isBlank(delImages) || imgUrls.size() != 0) {
				pre3 = this.con.prepareStatement("SELECT tour_name FROM tour WHERE tour_id = ?");
				pre3.setString(1, tourId);

				rs = pre3.executeQuery();
				if (rs.next()) tourName = rs.getString(1);
				else {
					this.con.rollback();
					return false;
				}
			} else {
				return true;
			}

			pre4 = this.con.prepareStatement("UPDATE tour SET last_updated_operator = ?, last_updated_time = ? WHERE tour_id = ?");
			pre4.setInt(1, touId);
			pre4.setTimestamp(2, Timestamp.from(Instant.now()));
			pre4.setString(3, tourId);
			
			if(pre4.executeUpdate()==0) {
				this.con.rollback();
				return false;
			}
			
			if (!StringUtils.isBlank(delImages)) {
				String[] delImgs = delImages.split(",");
				List<Integer> idImgList = Arrays.stream(delImgs).map(Integer::parseInt).collect(Collectors.toList());

				pre5 = this.con.prepareStatement("SELECT COUNT(*) FROM image WHERE tour_id = ?");
				pre5.setString(1, tourId);
				rs = pre5.executeQuery();
				
				int count = 0;
				if(rs.next()) count = rs.getInt(1);
				else {
					this.con.rollback();
					return false;
				}
				
				//Tour có ít nhất 1 ảnh
				if(count == idImgList.size() && imgUrls.size() == 0) {
					this.con.rollback();
					return false;
				}
				
				StringBuilder sql = new StringBuilder();
				sql.append("DELETE FROM image WHERE tour_id = ? AND image_id = ? ");

				pre = this.con.prepareStatement(sql.toString());

				for (Integer idImg : idImgList) {
					pre.setString(1, tourId);
					pre.setInt(2, idImg);
					pre.addBatch();
				}

				int[] dels = pre.executeBatch();

				if (dels.length == 0) {
					this.con.rollback();
					return false;
				}
			}

			if (imgUrls.size() != 0) {
				String sql2 = "INSERT INTO image (image_name, url, tour_id) VALUES(?,?,?)";
				pre2 = this.con.prepareStatement(sql2);

				for (String url : imgUrls) {
					pre2.setString(1, Generator.generateImgName(tourName));
					pre2.setString(2, url);
					pre2.setString(3, tourId);
					pre2.addBatch();
				}

				int[] inserts = pre2.executeBatch();
				if (inserts.length != imgUrls.size()) {
					System.out.println("ERROR IMGUPDATE");
					this.con.rollback();
					return false;
				}
			}

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				this.cp.releaseConnection(this.con, "Image");
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				if (pre2 != null)
					pre2.close();
				if (pre3 != null)
					pre3.close();
				if (pre4 != null)
					pre4.close();
				if (pre5 != null)
					pre5.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public List<Image> getImgsByTourId(String tourId) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;

		try {
			List<Image> list = new ArrayList<>();

			this.con = getConnection(this.cp);
			
			pre = this.con.prepareStatement("SELECT image_id, url FROM image WHERE tour_id = ?");
			pre.setString(1, tourId);

			rs = pre.executeQuery();

			while (rs.next()) {
				list.add(new Image().builder()
						.id(rs.getInt(1))
						.url(rs.getString(2))
						.build());
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.cp.releaseConnection(this.con, "Tour");
				rs.close();
				pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
