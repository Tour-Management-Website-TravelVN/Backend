package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import ads.util.Generator;
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
					.add("SELECT tour.tour_id, MIN(image_id) AS fii ").add("FROM image ")
					.add("JOIN tour ON tour.tour_id  = image.tour_id WHERE LOWER(tour.tour_name) LIKE LOWER(?)")
					.add("GROUP BY tour_id ").add("LIMIT ").add((page - 1) * MAX_ITEM_OF_PAGE + "").add(",")
					.add(page * MAX_ITEM_OF_PAGE + "").add(") AS temp ").add("ON image.image_id = temp.fii ");

			log.info("SQL ToursList: {}", sql.toString());

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

		log.info("tourID = {}", tourID);

		try {

			this.con = getConnection(this.cp);
			StringJoiner sql = new StringJoiner(" ");

			sql.add("SELECT category.category_name, tour.tour_id, tour.tour_name, tour.duration, tour.vehicle, ").add(
					"tour.departure_place, tour.places_to_visit, tour.target_audience, tour.ideal_time, tour.created_time, tour.last_updated_time, image.`url`, ")
					.add("toc.firstname, toc.lastname, tou.firstname, tou.lastname, tour.cuisine, tour.`description`, ")
					.add("tour.inclusions, tour.exclusions ").add("FROM tour ")
					.add("JOIN category ON tour.category_id = category.category_id ")
					.add("JOIN image ON tour.tour_id = image.tour_id ")
					.add("JOIN tour_operator AS toc ON toc.tour_operator_id = tour.tour_operator_id ")
					.add("LEFT JOIN tour_operator AS tou ON tou.tour_operator_id = tour.last_updated_operator")
					.add("WHERE tour.tour_id = ?;");

			sql.add("SELECT tour_program_id, `day`, locations, meal_description, desciption ").add("FROM tour_program ")
					.add("WHERE tour_id = ?").add("ORDER BY `day` asc;");

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
						.cuisine(cuisine).description(description).inclusions(inclusions).exclusions(exclusions)
						.tourOperator(toc).lastUpdatedOperator(tou).build();

				while (rs.next()) {
					imgSet.add(new Image().builder().url(rs.getString(12)).build());
				}
				return tour;

			} else {
				log.info("THIS ME !");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("THIS ME");
		return null;
	}

	private Set<TourProgram> getTourProgramsByTour(ResultSet result) {
		try {
			Set<TourProgram> tourProgramSet = new LinkedHashSet<TourProgram>();
			while (result.next()) {
				tourProgramSet
						.add(new TourProgram().builder()
								.id(result.getInt(1))
								.day((byte) result.getInt(2)).locations(result.getString(3))
								.mealDescription(result.getString(4)).desciption(result.getString(5)).build());
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
			if (preCheckTour.executeQuery().next())
				return false;

			String sql = "DELETE FROM tour WHERE tour.tour_id = ?";
			pre = this.con.prepareStatement(sql);
			pre.setString(1, tourId);

			int del = pre.executeUpdate();

			return del > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.cp.releaseConnection(this.con, "Tour");
				if (rs != null)
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

	@Override
	public String addTour(Tour tour, List<String> imgUrls, List<TourProgram> programs, int categoryId, int tocId) {
		// TODO Auto-generated method stub
		String tourId = Generator.geneareTourId(tour);
		int count = 0;

		if (tourId.equalsIgnoreCase(""))
			return "";

		System.out.println("COUNT: " + count++);

		tour.setTourId(tourId);

		PreparedStatement preCheckCate = null;
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		PreparedStatement pre3 = null;

		try {
			this.con = getConnection(this.cp);

			preCheckCate = this.con.prepareStatement("SELECT category_id FROM tour WHERE category_id = ?");
			preCheckCate.setInt(1, categoryId);

			System.out.println("COUNT: " + count++);
			if (!preCheckCate.executeQuery().next())
				return "";

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tour (tour_id, category_id, tour_operator_id, tour_name, duration, ");
			sql.append("vehicle, target_audience, departure_place, places_to_visit, cuisine, ");
			sql.append("ideal_time, description, created_time, inclusions, exclusions) ");
			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, tourId);
			pre.setInt(2, categoryId);
			pre.setInt(3, tocId);
			pre.setString(4, tour.getTourName());
			pre.setString(5, tour.getDuration());
			pre.setString(6, tour.getVehicle());
			pre.setString(7, tour.getTargetAudience());
			pre.setString(8, tour.getDeparturePlace());
			pre.setString(9, tour.getPlacesToVisit());
			pre.setString(10, tour.getCuisine());
			pre.setString(11, tour.getIdealTime());
			pre.setString(12, tour.getDescription());
			pre.setTimestamp(13, Timestamp.from(Instant.now())); // created_time hiện tại
			pre.setString(14, tour.getInclusions());
			pre.setString(15, tour.getExclusions());

			int insert = pre.executeUpdate();

			if (insert == 0) {
				System.out.println("COUNT: " + count++);
				this.con.rollback();
				return "";
			}

			String sql2 = "INSERT INTO image (image_name, url, tour_id) VALUES(?,?,?)";
			pre2 = this.con.prepareStatement(sql2);

			for (String url : imgUrls) {
				pre2.setString(1, Generator.generateImgName(tour));
				pre2.setString(2, url);
				pre2.setString(3, tourId);
				pre2.addBatch();
			}

			int[] inserts = pre2.executeBatch();
			if (inserts.length != imgUrls.size()) {
				System.out.println("COUNT: " + count++);
				this.con.rollback();
				return "";
			}

			String sql3 = "INSERT INTO tour_program (tour_id, locations, day, meal_description, desciption) VALUES(?,?,?,?,?)";
			pre3 = this.con.prepareStatement(sql3);

			for (TourProgram tp : programs) {
				pre3.setString(1, tourId);
				pre3.setString(2, tp.getLocations());
				pre3.setByte(3, tp.getDay());
				pre3.setString(4, tp.getMealDescription());
				pre3.setString(5, tp.getDesciption());
				pre3.addBatch();
			}

			inserts = pre3.executeBatch();

			if (inserts.length != programs.size()) {
				System.out.println("COUNT: " + count++);
				this.con.rollback();
				return "";
			} else {
				this.con.commit();
				return tourId;
			}

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
				this.cp.releaseConnection(this.con, "Tour");
				preCheckCate.close();
				pre.close();
				pre2.close();
				pre3.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "";
	}

	@Override
	public String getTourNameByTourId(String tourId) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;

		try {
			this.con = getConnection(this.cp);

			pre = this.con.prepareStatement("SELECT tour_name FROM tour WHERE tour_id = ?");
			pre.setString(1, tourId);

			rs = pre.executeQuery();
			if (rs.next())
				return rs.getString(1);
			else
				return "";

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

		return "";
	}

	// [ERROR]
	@Override
	public boolean updateTour(Tour tour, String delImages, List<String> imgUrls, List<TourProgram> programs,
			int categoryId, int touId) {
		// TODO Auto-generated method stub
		PreparedStatement preCheckCate = null;
		PreparedStatement pre = null;
		PreparedStatement pre3 = null;
		try {
			this.con = getConnection(this.cp);
			if(!updateImgsByTourId(tour.getTourName(), this.con, tour.getTourId(), delImages, imgUrls)) {
				this.con.rollback();
				return false;
			}
			
			preCheckCate = this.con.prepareStatement("SELECT category_id FROM tour WHERE category_id = ?");
			preCheckCate.setInt(1, categoryId);

			if (!preCheckCate.executeQuery().next())
				return false;

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tour SET category_id=?, last_updated_operator=?, tour_name=?, ");
			sql.append("vehicle = ?, target_audience=?, cuisine=?, ");
			sql.append("ideal_time=?, description=?, last_updated_time=?, inclusions=?, exclusions=? ");
			sql.append("WHERE tour_id = ?");
			pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, categoryId);
			pre.setInt(2, touId);
			pre.setString(3, tour.getTourName());
			pre.setString(4, tour.getVehicle());
			pre.setString(5, tour.getTargetAudience());
			pre.setString(6, tour.getCuisine());
			pre.setString(7, tour.getIdealTime());
			pre.setString(8, tour.getDescription());
			pre.setTimestamp(9, Timestamp.from(Instant.now())); // created_time hiện tại
			pre.setString(10, tour.getInclusions());
			pre.setString(11, tour.getExclusions());
			pre.setString(12, tour.getTourId());

		
			String sql3 = "UPDATE tour_program SET locations = ?, meal_description=?, desciption=? WHERE tour_program_id=? and tour_id=?";
			pre3 = this.con.prepareStatement(sql3);

			for (TourProgram tp : programs) {
				pre3.setString(1, tp.getLocations());
				pre3.setString(2, tp.getMealDescription());
				pre3.setString(3, tp.getDesciption());
				log.info("DESCRIPTION DB {}", tp.getDesciption());
				pre3.setInt(4, tp.getId());
				pre3.setString(5, tour.getTourId());
				pre3.addBatch();
			}

			pre3.executeBatch();
			
			this.con.commit();
			return true;

		} catch (Exception e) {
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				this.cp.releaseConnection(this.con, "Tour");
				if (preCheckCate != null)
					preCheckCate.close();
				if (pre != null)
					pre.close();
				if (pre3 != null)
					pre3.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}
	
	

	// Hàm cập nhật ảnh, hãy truyền connection vào trong để lấy connection đó xử lý
	// không tạo thêm con tránh rollback fail
	// Cần nhấc hàm update tour ra khỏi đây pre4 => out
	public boolean updateImgsByTourId(String tourName, Connection con, String tourId, String delImages,
			List<String> imgUrls) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		PreparedStatement pre3 = null;
		PreparedStatement pre5 = null;
		try {
			if (StringUtils.isBlank(delImages) && imgUrls.size() == 0)
				return true;

			if (!StringUtils.isBlank(delImages)) {
				String[] delImgs = delImages.split(",");
				List<Integer> idImgList = Arrays.stream(delImgs).map(Integer::parseInt).collect(Collectors.toList());

				pre5 = con.prepareStatement("SELECT COUNT(*) FROM image WHERE tour_id = ?");
				pre5.setString(1, tourId);
				rs = pre5.executeQuery();

				int count = 0;
				if (rs.next())
					count = rs.getInt(1);
				else {
					con.rollback();
					return false;
				}

				// Tour có ít nhất 1 ảnh
				if (count == idImgList.size() && imgUrls.size() == 0) {
					con.rollback();
					return false;
				}

				StringBuilder sql = new StringBuilder();
				sql.append("DELETE FROM image WHERE tour_id = ? AND image_id = ? ");

				pre = con.prepareStatement(sql.toString());

				for (Integer idImg : idImgList) {
					pre.setString(1, tourId);
					pre.setInt(2, idImg);
					pre.addBatch();
				}

				int[] dels = pre.executeBatch();

				if (dels.length == 0) {
					con.rollback();
					return false;
				}
			}

			if (imgUrls.size() != 0) {
				String sql2 = "INSERT INTO image (image_name, url, tour_id) VALUES(?,?,?)";
				pre2 = con.prepareStatement(sql2);

				for (String url : imgUrls) {
					pre2.setString(1, Generator.generateImgName(tourName));
					pre2.setString(2, url);
					pre2.setString(3, tourId);
					pre2.addBatch();
				}

				int[] inserts = pre2.executeBatch();
				if (inserts.length != imgUrls.size()) {
					System.out.println("ERROR IMGUPDATE");
					con.rollback();
					return false;
				}
			}

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				if (pre2 != null)
					pre2.close();
				if (pre3 != null)
					pre3.close();
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
	public List<Tour> getAllTourNameAndId() {
		// TODO Auto-generated method stub
		String sql = "Select tour_name, tour_id from tour";
		ArrayList<Tour> allTour = new ArrayList<Tour>();
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		try {
			this.con = getConnection(this.cp);
			pre = con.prepareStatement(sql);
			rs  = pre.executeQuery();
			while(rs.next())
			{
				
				allTour.add(new Tour().builder().tourId(rs.getString(2)).tourName(rs.getString(1)).build());
				
			}
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			try {
				this.cp.releaseConnection(this.con, "Tour");
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return allTour;
	}

	@Override
	public List<Map<String, Object>> getStatsForEchart(String filter) {
		List<Map<String, Object>> stats = new ArrayList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
        con = getConnection(cp);

	    try {

	        String condition = "";
	        switch (filter) {
	            case "day":
	                condition = "DATE(b.booking_date) = CURDATE()";
	                break;
	            case "month":
	                condition = "DATE_FORMAT(b.booking_date, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')";
	                break;
	            case "year":
	            default:
	                condition = "YEAR(b.booking_date) = YEAR(NOW())";
	                break;
	        }

	        String sql = "SELECT c.category_name AS category, COUNT(*) AS total " +
	                     "FROM booking b " +
	                     "JOIN tour_unit tu ON b.tour_unit_id = tu.tour_unit_id " +
	                     "JOIN tour t ON tu.tour_id = t.tour_id " +
	                     "JOIN category c ON t.category_id = c.category_id " +
	                     "WHERE " + condition + " " +
	                     "GROUP BY c.category_name";

	        stmt = con.prepareStatement(sql);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Map<String, Object> row = new HashMap<>();
	            row.put("category", rs.getString("category"));
	            row.put("total", rs.getInt("total"));
	            stats.add(row);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if(stmt != null )
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        if(rs != null )
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

	    return stats;

	}
	
}
