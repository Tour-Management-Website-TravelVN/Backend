package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hc.client5.http.entity.mime.StringBody;
import org.netlib.util.booleanW;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Booking;
import ads.objects.Customer;
import ads.objects.TourUnit;

public class BookingFunctionImpl  implements BookingFunction{
	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	// Đối tượng kết nối
	private Connection con;

	private final int MAX_ITEM_OF_PAGE = 100;

	private static volatile BookingFunctionImpl instance;

	// Singleton Pattern
	public static BookingFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = BookingFunctionImpl.class;
			synchronized (BookingFunctionImpl.class) {
				if (instance == null) {
					instance = new BookingFunctionImpl();
				}
			}
		}

		return instance;
	}

	public BookingFunctionImpl() {
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
			this.con = BookingFunctionImpl.cp.getConnection("Booking");

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
	public boolean addBooking(Booking item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editBookingCancelRequest(List<String> bookingIds, boolean isApproved) {
	    String sql = "UPDATE booking SET status = ? WHERE booking_id = ? AND status = 'W'";
	    String status = isApproved ? "C" : "P";

	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = getConnection(cp);
	        con.setAutoCommit(false); // Tạo transaction

	        ps = con.prepareStatement(sql);

	        for (String id : bookingIds) {
	            ps.setString(1, status);
	            ps.setString(2, id);
	            ps.addBatch();
	        }

	        ps.executeBatch();
	        con.commit();
	        return true;

	    } catch (SQLException e) {
	        if (con != null) try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        e.printStackTrace();
	        return false;

	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) cp.releaseConnection(con, "Booking");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



	@Override
	public boolean delBooking(Booking item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Booking> getBookingsWait(int page) {
		
		StringBuilder sql  = new StringBuilder("");
		this.con = getConnection(this.cp);
		PreparedStatement p  = null;
		ResultSet result = null;
		sql.append("SELECT "
				+ "    b.tour_unit_id, b.booking_id, b.booking_date, b.c_id, "
				+ "    b.baby_number, b.toddler_number, b.child_number, b.adult_number, "
				+ "    b.status, b.note, b.payment_id, b.total_amount, b.order_code, "
				+ "    b.expired_at, b.private_room_number, "
				+ "    c.firstname, c.lastname, c.date_of_birth, c.gender "
				+ "FROM booking b "
				+ "JOIN customer c ON b.c_id = c.c_id "
				+ "WHERE b.status LIKE 'W' "
				+ "LIMIT ")
		.append((page-1)*MAX_ITEM_OF_PAGE).append(",").append(page*MAX_ITEM_OF_PAGE);
		ArrayList<Booking> list = new ArrayList<Booking>();
		try {
			 p = con.prepareStatement(sql.toString());
			 result = p.executeQuery();
			while(result.next())
			{
				Timestamp ts = result.getTimestamp("booking_date");
				list.add(Booking.builder()
						.bookingId(result.getString("booking_id"))
						.c(Customer.builder()
								.lastname(result.getString("lastname"))
								.firstname(result.getString("firstname"))
								.dateOfBirth(result.getDate("date_of_birth") != null ? 
				                        result.getDate("date_of_birth").toLocalDate() : null)
			                    .gender(result.getByte("gender") == 1)
								.build())
					    .bookingDate(ts != null ? ts.toInstant() : null)
						.babyNumber(result.getByte("baby_number"))
						.toddlerNumber(result.getByte("toddler_number"))
						.childNumber(result.getByte("child_number"))
						.adultNumber(result.getByte("adult_number"))
						.status(result.getString("status"))
						.note(result.getString("note"))
						.paymentId(result.getString("payment_id"))
						.totalAmount(result.getBigDecimal("total_amount"))
						.orderCode(result.getLong("order_code"))
						.expiredAt(result.getLong("expired_at"))
						.privateRoomNumber(result.getByte("private_room_number"))
						.tourUnit(TourUnit.builder()
								.tourUnitId(result.getString("tour_unit_id"))
								.build())
						.build());
					
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
	        try {
	           this.cp.releaseConnection(this.con, "Booking");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		return null;
	}

	@Override
	public Booking getObject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking getObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking getObject(int orderCode, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> getBookings(int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
