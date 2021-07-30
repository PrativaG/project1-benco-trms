package BencoTRMS.testUtil;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import BencoTRMS.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionUtilTest {

	private ConnectionUtil connectionUtil = new ConnectionUtil();

	@Test
	public void test() throws SQLException {
		Connection conn = connectionUtil.getConnection();
		conn.close();
		
		try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
