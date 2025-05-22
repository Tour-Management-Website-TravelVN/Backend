package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Category;
import ads.objects.Image;
import ads.objects.Tour;
import ads.objects.TourOperator;
import ads.objects.TourProgram;
import ads.util.Format;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TourFunctionImpl implements TourFunction {

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	// Đối tượng kết nối
	private Connection con;

	private final int MAX_ITEM_OF_PAGE = 20;

	private static volatile TourFunctionImpl instance;

	// Singleton Pattern
	public static TourFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = TourFunctionImpl.class;
			synchronized (TourFunctionImpl.class) {
				if (instance == null) {
					instance = new TourFunctionImpl();
				}
			}
		}

		return instance;
	}

	public TourFunctionImpl() {
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
			this.con = TourFunctionImpl.cp.getConnection("Tour");

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

	/**
	 * Lấy danh sách tour phân trang
	 * 
	 * @param page Số trang
	 */
	@Override
	public List<Tour> getToursList(short page, String keyword) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;

		try {
			List<Tour> list = new ArrayList<Tour>();

			this.con = getConnection(this.cp);
			StringJoiner sql = new StringJoiner(" ");

			sql.add("SELECT category.category_name, tour.tour_id, tour.tour_name, tour.duration, tour.vehicle, ").add(
					"tour.departure_place, tour.places_to_visit, tour.target_audience, tour.ideal_time, tour.created_time, tour.last_updated_time, image.`url` ")
					.add("FROM tour ").add("JOIN category ON tour.category_id = category.category_id ")
					.add("JOIN image ON tour.tour_id = image.tour_id ").add("JOIN (")
					.add("SELECT tour_id, MIN(image_id) AS fii ").add("FROM image ").add("GROUP BY tour_id ")
					.add("LIMIT ").add((page - 1) * MAX_ITEM_OF_PAGE + "").add(",").add(page * MAX_ITEM_OF_PAGE + "")
					.add(") AS temp ").add("ON image.image_id = temp.fii ").add("WHERE tour.tour_name LIKE ?");

			log.info("SQL ToursList", sql.toString());

			pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, "%" + keyword + "%");

			rs = pre.executeQuery();

			while (rs.next()) {
				String categoryName = rs.getString(1);
				String tourId = rs.getString(2);
				String tourName = rs.getString(3);
				String duration = rs.getString(4);
				String vehicle = rs.getString(5);
				String departurePlace = rs.getString(6);
				String placesToVisit = rs.getString(7);
				String targetAudience = rs.getString(8);
				String idealTime = rs.getString(9);
				Instant createdTime = rs.getTimestamp(10).toInstant();
				Instant lastUpdatedTime = rs.getTimestamp(11) == null ? null : rs.getTimestamp(11).toInstant();
				String imgUrl = rs.getString(12);

				Set<Image> imgSet = new HashSet<>();
				imgSet.add(new Image().builder().url(imgUrl).build());

				list.add(new Tour().builder().category(new Category().builder().categoryName(categoryName).build())
						.tourId(tourId).tourName(tourName).duration(duration).vehicle(vehicle)
						.departurePlace(departurePlace).placesToVisit(placesToVisit).targetAudience(targetAudience)
						.idealTime(idealTime).createdTime(createdTime).lastUpdatedTime(lastUpdatedTime).imageSet(imgSet)
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

	/**
	 * Lấy số trang tối đa
	 */
	@Override
	public int getMaxToursPage(String keyword) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;

		try {
			this.con = getConnection(this.cp);
			String sql = "SELECT COUNT(*) AS max_item FROM tour WHERE tour_name LIKE ?";
			pre = this.con.prepareStatement(sql);
			pre.setString(1, "%" + keyword + "%");
			rs = pre.executeQuery();

			if (rs.next()) {
				return (int) Math.ceil((double) rs.getInt(1) / MAX_ITEM_OF_PAGE);
			}

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

		return 0;
	}

	/**
	 * Lấy thông tin chi tiết tour
	 * 
	 * @param tourId Mã tour
	 * @return Thông tin chi tiết tour
	 */
	@Override
	public Tour getTourByTourId(String tourID) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;

		try {

			this.con = getConnection(this.cp);
			StringJoiner sql = new StringJoiner(" ");

			sql.add("SELECT category.category_name, tour.tour_id, tour.tour_name, tour.duration, tour.vehicle, ").add(
					"tour.departure_place, tour.places_to_visit, tour.target_audience, tour.ideal_time, tour.created_time, tour.last_updated_time, image.`url`, ")
					.add("toc.firstname, toc.lastname, tou.firstname, tou.lastname, tour.cuisine, tour.`description`, ")
					.add("tour.inclusions, tour.exclusions ")
					.add("FROM tour ").add("JOIN category ON tour.category_id = category.category_id ")
					.add("JOIN image ON tour.tour_id = image.tour_id ")
					.add("JOIN tour_operator AS toc ON toc.tour_operator_id = tour.tour_operator_id ")
					.add("JOIN tour_operator AS tou ON tou.tour_operator_id = tour.last_updated_operator")
					.add("WHERE tour.tour_id = ?;");

			sql.add("SELECT `day`, locations, meal_description, desciption ").add("FROM tour_program ")
					.add("WHERE tour_id = ?")
					.add("ORDER BY `day` asc;");

			sql.add("SELECT COUNT(*) ").add("FROM tour").add("JOIN tour_unit ON tour.tour_id = tour_unit.tour_id ")
					.add("WHERE tour.tour_id = ?");

			log.info("SQL ToursList", sql.toString());

			pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, tourID);
			pre.setString(2, tourID);
			pre.setString(3, tourID);

			boolean results = pre.execute();

			rs = pre.executeQuery();

			Tour tour = null;
			Set<Image> imgSet = new LinkedHashSet();

			for (int i = 1; i <= 2; i++) {
				ResultSet result = pre.getResultSet();
				switch (i) {
				case 1:
					tour = getTour(result, imgSet);
					break;
				case 2:
					tour.setTourProgramSet(getTourProgramsByTour(result));
					break;
				}

				results = pre.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);
			}

//					if(rs.next()) {
//						String categoryName = rs.getString(1);
//						String tourId = rs.getString(2);
//						String tourName = rs.getString(3);
//						String duration = rs.getString(4);
//						String vehicle = rs.getString(5);
//						String departurePlace = rs.getString(6);
//						String placesToVisit = rs.getString(7);
//						String targetAudience = rs.getString(8);
//						String idealTime = rs.getString(9);
//						Instant createdTime = rs.getTimestamp(10).toInstant();
//						Instant lastUpdatedTime = rs.getTimestamp(11)==null?null:rs.getTimestamp(11).toInstant();
//						String imgUrl = rs.getString(12);
//						
//						TourOperator toc = TourOperator.builder()
//								.firstname(rs.getString(13))
//								.lastname(rs.getString(14))
//								.build();
//						
//						TourOperator tou = TourOperator.builder()
//								.firstname(rs.getString(15))
//								.lastname(rs.getString(16))
//								.build();
//						
//						String cuisine = rs.getString(17);
//						String description = rs.getString(18);
//						String inclusions = rs.getString(19);
//						String exclusions = rs.getString(20);
//						
//						imgSet.add(new Image().builder()
//								.url(imgUrl)
//								.build());
//						
//						tour = new Tour().builder()
//								.category(new Category().builder()
//										.categoryName(categoryName)
//										.build())
//								.tourId(tourId)
//								.tourName(tourName)
//								.duration(duration)
//								.vehicle(vehicle)
//								.departurePlace(departurePlace)
//								.placesToVisit(placesToVisit)
//								.targetAudience(targetAudience)
//								.idealTime(idealTime)
//								.createdTime(createdTime)
//								.lastUpdatedTime(lastUpdatedTime)
//								.imageSet(imgSet)
//								.build();
//					} else {
//						return null;
//					}	
			return tour;
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

	private Tour getTour(ResultSet rs, Set<Image> imgSet) {
		try {
			if (rs.next()) {
				String categoryName = rs.getString(1);
				String tourId = rs.getString(2);
				String tourName = rs.getString(3);
				String duration = rs.getString(4);
				String vehicle = rs.getString(5);
				String departurePlace = rs.getString(6);
				String placesToVisit = rs.getString(7);
				String targetAudience = rs.getString(8);
				String idealTime = rs.getString(9);
				Instant createdTime = rs.getTimestamp(10).toInstant();
				Instant lastUpdatedTime = rs.getTimestamp(11) == null ? null : rs.getTimestamp(11).toInstant();
				String imgUrl = rs.getString(12);

				TourOperator toc = TourOperator.builder().firstname(rs.getString(13)).lastname(rs.getString(14))
						.build();

				TourOperator tou = TourOperator.builder().firstname(rs.getString(15)).lastname(rs.getString(16))
						.build();

				String cuisine = rs.getString(17);
				String description = rs.getString(18);
				String inclusions = rs.getString(19);
				String exclusions = rs.getString(20);

				imgSet.add(new Image().builder().url(imgUrl).build());

				Tour tour = new Tour().builder().category(new Category().builder().categoryName(categoryName).build())
						.tourId(tourId).tourName(tourName).duration(duration).vehicle(vehicle)
						.departurePlace(departurePlace).placesToVisit(placesToVisit).targetAudience(targetAudience)
						.idealTime(idealTime).createdTime(createdTime).lastUpdatedTime(lastUpdatedTime).imageSet(imgSet)
						.cuisine(cuisine)
						.description(description)
						.inclusions(inclusions)
						.exclusions(exclusions)
						.tourOperator(toc)
						.lastUpdatedOperator(tou)
						.build();
				
				while(rs.next()) {
					imgSet.add(new Image().builder()
							.url(rs.getString(12))
							.build());
				}
				return tour;
				
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Set<TourProgram> getTourProgramsByTour(ResultSet result) {
		try {
			Set<TourProgram> tourProgramSet = new LinkedHashSet<TourProgram>();
			while (result.next()) {
				tourProgramSet.add(new TourProgram().builder()
						.day((byte)result.getInt(1))
						.locations(result.getString(2))
						.mealDescription(result.getString(3))
						.desciption(result.getString(4))
						.build());
			}
			return tourProgramSet;
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public int countTourUnit(String tourId) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;

		try {
			this.con = getConnection(this.cp);
			String sql = "SELECT COUNT(*) FROM tour JOIN tour_unit ON tour.tour_id = tour_unit.tour_id WHERE tour.tour_id = ?";
			pre = this.con.prepareStatement(sql);
			pre.setString(1, tourId);
			rs = pre.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

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

		return 0;
	}

	
	
	@Override
	public boolean deleteTourByTourId(String tourId) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement preCheckTour = null;
		PreparedStatement pre = null;

		try {
			this.con = getConnection(this.cp);
			
			preCheckTour = this.con.prepareStatement("SELECT tour_id FROM tour WHERE tour_id = ?");
			preCheckTour.setString(1, tourId);
			if(preCheckTour.executeQuery().next()) return false;
			
			String sql = "DELETE FROM tour WHERE tour.tour_id = ?";
			pre = this.con.prepareStatement(sql);
			pre.setString(1, tourId);
			
			int del = pre.executeUpdate();

			return del>0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.cp.releaseConnection(this.con, "Tour");
				rs.close();
				preCheckTour.close();
				pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}
}
