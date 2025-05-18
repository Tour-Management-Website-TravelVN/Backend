package ads;

import java.sql.Connection;
import java.sql.SQLException;

public interface Pool {
	public Connection getConnection(String objectName) throws SQLException;
	public void releaseConnection(Connection con, String objectName) throws SQLException;
	
}
