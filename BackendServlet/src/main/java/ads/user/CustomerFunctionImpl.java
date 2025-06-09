package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.netlib.util.booleanW;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;
import ads.objects.Customer;
import ads.objects.TourUnit;

public class CustomerFunctionImpl implements CustomerFunction{

	private static ConnectionPool cp = ConnectionPoolImpl.getInstance();

	// Đối tượng kết nối
	private Connection con;

	private final int MAX_ITEM_OF_PAGE = 500;

	private static volatile CustomerFunctionImpl instance;

	// Singleton Pattern
	public static CustomerFunctionImpl getInstance() {
		if (instance == null) {
			Class var0 = CustomerFunctionImpl.class;
			synchronized (CustomerFunctionImpl.class) {
				if (instance == null) {
					instance = new CustomerFunctionImpl();
				}
			}
		}

		return instance;
	}

	public CustomerFunctionImpl() {
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
			this.con = CustomerFunctionImpl.cp.getConnection("Customer");

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
	public ArrayList<Customer> getCustomers(int page) {
		Customer customer = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    StringBuilder sql =  new StringBuilder("");
	    sql.append("SELECT * FROM customer LIMIT ");
	    ArrayList<Customer> all = new ArrayList<Customer>();	
	    sql.append((page-1)*MAX_ITEM_OF_PAGE).append(",").append(page*MAX_ITEM_OF_PAGE);
	    try{
	        
	        con = getConnection(this.cp);
	        stmt = con.prepareStatement(sql.toString());
	        rs = stmt.executeQuery();

	            while (rs.next()) {
	                customer = Customer.builder()
	                    .id(rs.getInt("c_id"))
	                    .firstname(rs.getString("firstname"))
	                    .lastname(rs.getString("lastname"))
	                    .dateOfBirth(rs.getDate("date_of_birth") != null ? 
	                        rs.getDate("date_of_birth").toLocalDate() : null)
	                    .gender(rs.getByte("gender") == 1)
	                    .nationality(rs.getString("nationality"))
	                    .citizenId(rs.getString("citizen_id"))
	                    .passport(rs.getString("passport"))
	                    .phoneNumber(rs.getString("phone_number"))
	                    .note(rs.getString("note"))
	                    .address(rs.getString("address"))
	                    .build();
	                all.add(customer);
	            }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
			try {
				if(stmt!= null)
					stmt.close();
				if(rs!= null)
					rs.close();
				this.cp.releaseConnection(this.con, "Customer");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    return all;
	}

	@Override
	public ArrayList<Customer> getByName(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByName(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Customer> getObjects(TourUnit similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getById(String id) {
		Customer customer = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    String sql = "SELECT * FROM customer WHERE c_id = ?";

	    try{
	        
	        con = getConnection(this.cp);
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, id);
	        rs = stmt.executeQuery();

	            if (rs.next()) {
	                customer = Customer.builder()
	                    .id(rs.getInt("c_id"))
	                    .firstname(rs.getString("firstname"))
	                    .lastname(rs.getString("lastname"))
	                    .dateOfBirth(rs.getDate("date_of_birth") != null ? 
	                        rs.getDate("date_of_birth").toLocalDate() : null)
	                    .gender(rs.getByte("gender") == 1)
	                    .nationality(rs.getString("nationality"))
	                    .citizenId(rs.getString("citizen_id"))
	                    .passport(rs.getString("passport"))
	                    .phoneNumber(rs.getString("phone_number"))
	                    .note(rs.getString("note"))
	                    .address(rs.getString("address"))
	                    .build();
	            }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
			try {
				if(stmt!= null)
					stmt.close();
				if(rs!= null)
					rs.close();
				this.cp.releaseConnection(this.con, "Customer");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    return customer;
	}

	@Override
	public boolean updateCustomer(Customer t) {
	    String sql = "UPDATE customer SET firstname = ?, lastname = ?, gender = ?,date_of_birth = ?, passport = ?, phone_number = ?, nationality = ?, address = ?, note = ? WHERE c_id = ?";
	    con = getConnection(cp);
        PreparedStatement ps = null;
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setString(1, t.getFirstname());
	        ps.setString(2, t.getLastname());
	        ps.setBoolean(3, t.getGender());
	        ps.setDate(4, java.sql.Date.valueOf(t.getDateOfBirth()));

	        ps.setString(5, t.getPassport());
	        ps.setString(6, t.getPhoneNumber());
	        ps.setString(7, t.getNationality());
	        ps.setString(8, t.getAddress());
	        ps.setString(9, t.getNote());
	        ps.setInt(10, t.getId());

	        boolean rowsAffected = exe(ps);
	        return rowsAffected ;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
			try {
				if(ps!= null)
					ps.close();
				
				this.cp.releaseConnection(this.con, "Customer");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    return false;
	}


	@Override
	public boolean addTourUnit(TourUnit t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		String checkDel = 
			    "SELECT c_id FROM booking WHERE c_id = ? " +
			    "UNION " +
			    "SELECT c_id FROM tour_rating WHERE c_id = ? " +
			    "UNION " +
			    "SELECT c_id FROM user_account WHERE c_id = ? " +
			    "UNION " +
			    "SELECT c_id FROM companion_customer WHERE c_id = ? " +
			    "LIMIT 1";
		String query = "Delete from customer where c_id like ?";
		boolean rs = false;
		try{
			this.con = getConnection(this.cp);
			PreparedStatement r = con.prepareStatement(checkDel);
			r.setString(1, id);
			r.setString(2, id);
			r.setString(3, id);
			r.setString(4, id);
			ResultSet isAvaiable = r.executeQuery();
			if(isAvaiable.next())
			{
				return false;
			}

			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, id);
			
			rs = exe(p);
		
		} catch (SQLException e) {
			e.printStackTrace();
			 
		}
		finally{
			try {
				ConnectionPoolImpl.getInstance().releaseConnection(con, "Customer");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return rs;
	}

	@Override
	public int getMaxPage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
