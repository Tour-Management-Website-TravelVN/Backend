package ads.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.Pool;
import ads.PoolImpl;
import ads.objects.DiscountObject;

public class DiscountImpl implements Discount{

	public final static DiscountImpl Instance = new DiscountImpl();

	public static DiscountImpl getInstance()
	{
		return Instance;
	}
	
	private Connection con;

	public DiscountImpl() {
		try {
			this.con = PoolImpl.getInstance().getConnection("Discount");
			if (this.con == null) {
			    throw new IllegalStateException("Không lấy được Connection từ pool!");
			}
			if(this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<DiscountObject> getAllDiscount() {
		ArrayList<DiscountObject> discounts = new ArrayList<>();
        String sql = "SELECT discount_id, discount_name, discount_value, discount_unit FROM discount";

        try (
        		PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DiscountObject discount = new DiscountObject();
                discount.setId(rs.getInt("discount_id"));
                discount.setDiscountName(rs.getString("discount_name"));
                discount.setDiscountValue(rs.getBigDecimal("discount_value"));
                discount.setDiscountUnit(rs.getString("discount_unit"));

                discounts.add(discount);
            }

        } catch (SQLException e) {
        	
            e.printStackTrace(); // hoặc log lỗi
        }finally {
        	try {
				PoolImpl.getInstance().releaseConnection(con, "Discount");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return discounts;	}

	@Override
	public ArrayList<DiscountObject> getByName(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByName(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DiscountObject> getObjects(DiscountObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscountObject getById(String id) {
		 DiscountObject discount = null;
	        String sql = "SELECT discount_id, discount_name, discount_value, discount_unit FROM discount WHERE discount_id = ?";

	        try (
	        		PreparedStatement stmt =con.prepareStatement(sql)) {
	            stmt.setString(1, id);

	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    discount = new DiscountObject();
	                    discount.setId(rs.getInt("discount_id"));
	                    discount.setDiscountName(rs.getString("discount_name"));
	                    discount.setDiscountValue(rs.getBigDecimal("discount_value"));
	                    discount.setDiscountUnit(rs.getString("discount_unit"));
	                }
	            }

	        } catch (SQLException e) {
	        	
	            e.printStackTrace(); // hoặc log lỗi
	        }finally {
	        	try {
					PoolImpl.getInstance().releaseConnection(con, "Discount");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	        return discount;
	}

	@Override
	public boolean updateDiscount(DiscountObject t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDiscount(DiscountObject t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	public DiscountObject getDiscountByName(String name)
	{
		 DiscountObject discount = null;
	        String sql = "SELECT discount_id, discount_name, discount_value, discount_unit FROM discount WHERE discount_name = ?";

	        try (
	        		PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setString(1, name);

	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    discount = new DiscountObject();
	                    discount.setId(rs.getInt("discount_id"));
	                    discount.setDiscountName(rs.getString("discount_name"));
	                    discount.setDiscountValue(rs.getBigDecimal("discount_value"));
	                    discount.setDiscountUnit(rs.getString("discount_unit"));
	                }
	            }

	        } catch (SQLException e) {
	        	
	            e.printStackTrace(); // hoặc log lỗi
	        }finally {
	        	try {
					PoolImpl.getInstance().releaseConnection(con, "Discount");
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        return discount;
	}



}
