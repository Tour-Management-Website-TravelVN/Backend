package ads;

import java.sql.*;
import java.util.Enumeration;
import java.util.Stack;
//import java.sql.Connection;
//import java.sql.SQLException;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class ConnectionPoolImpl implements ConnectionPool {
	//Trinh dieu khien lam viec
	private String driver;
	
	//Tai khoan ket noi
	private String username;
	private String userpass;
	
	private final String USER_NAME = "root";
	private final String PWD = "abc123!";
	
	private static volatile ConnectionPool instance;
	
	//Duong dan thuc thi
	private String url;
	
	//Doi tuong luu tru ket noi
	private Stack<Connection> pool = new Stack<Connection>();
	
	public ConnectionPoolImpl() {
		// TODO Auto-generated method stub
		//Xac dinh trinh dieu khien
		this.driver = "com.mysql.cj.jdbc.Driver";
		
		//Nap trinh dieu khien
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Xac dinh tai khoan
		this.username = USER_NAME;
		this.userpass = PWD;
		
		//Xac dinh duong dan thuc thi
		this.url = "jdbc:mysql://localhost:3306/tour_booking_system?allowMultiQueries=true";
		
		//Khoi tao bo nho doi tuong luu tru
		this.pool = new Stack<>();
	}
	

    public static ConnectionPool getInstance() {
        if (instance == null) {
        	synchronized (ConnectionPoolImpl.class) {
				if(instance==null) {
					instance = new ConnectionPoolImpl();
				}
			}
        }
        return instance;
    }

//    @Override
//    public synchronized Connection getConnection(String objectName) throws SQLException {
//        if (pool.isEmpty()) {
//            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } else {
//            return pool.pop();
//        }
//    }
	
	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		if(this.pool.isEmpty()) {
//			System.out.println("STACK SIZE: "+pool.size());
			System.out.println(objectName+" da khoi tao 1 ket noi moi");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		} else {
//			System.out.println("STACK SIZE: "+pool.size());
			System.out.println(objectName+" da lay ra mot ket noi");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		System.out.println(objectName+" da tra ve 1 ket noi");
		this.pool.push(con);
//		System.out.println("STACK SIZE: "+pool.size());
	}

//	protected void finalize() throws Throwable {
//		this.pool.clear();
//		
//		this.pool = null;
//		
//		System.out.println("CPool is Closed");
//	}
	
//	public void close() {
//	    try {
//	        this.pool.clear();
//	        this.pool = null;
//	        System.out.println("CPool is Closed");
//	    } catch (RuntimeException e) {
//	        e.printStackTrace();
//	    }
//	}
	
	public void close() {
        try {
            // Đóng tất cả kết nối còn lại trong pool
            for (Connection conn : pool) {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
            pool.clear();

            // Gỡ bỏ driver JDBC khỏi DriverManager để tránh memory leak
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                try {
                    DriverManager.deregisterDriver(driver);
                    System.out.println("Deregistered JDBC driver: " + driver);
                } catch (SQLException e) {
                    System.err.println("Error deregistering driver: " + e.getMessage());
                }
            }

            // Shutdown thread cleanup của MySQL
            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("Connection pool shutdown complete.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
