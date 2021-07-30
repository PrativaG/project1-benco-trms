package Benco.TRMS.testDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.security.KeyStore.TrustedCertificateEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import BencoTRMS.dao.EventDaoImpl;
import BencoTRMS.pojos.Employee;
import BencoTRMS.pojos.Event;
import BencoTRMS.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class EventDaoImplTest {
	
	@Mock
	private ConnectionUtil conUtil;
	
	@Mock
	private Connection con;
	
	private Connection realConn;
	private PreparedStatement stmt;
	private PreparedStatement trueStmt;
	private PreparedStatement spy;
	
	private EventDaoImpl eventDao;
	private Event event;
	
	 @Mock
	 private Employee emp;
	 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		realConn = new ConnectionUtil().getConnection();
		
		eventDao = new EventDaoImpl();
		eventDao.setConnUtil(conUtil);
		
		Mockito.when(emp.getEmployeeId()).thenReturn(1);
		
		event = new Event();
	
	}

	@After
	public void tearDown() throws Exception {
		realConn.close();
	}
	
	private void createSpyConnection(String sql) throws SQLException{
		
		
		//creating a real statement from a connection
		stmt = realConn.prepareStatement(sql);

		//spying on that real statement
		spy = Mockito.spy(stmt);
		
		//mock our connection and util, so we will only use the statement we are spying on
		when(conUtil.getConnection()).thenReturn(con);
		when(con.prepareStatement(sql)).thenReturn(spy);
	
	}

	@Test
	public void testInsertEvent() {
		
		String sql = "insert into emp_event (event_cost, event_type, start_date, end_date, presentation, emp_id) "
				+ "values(?, ?, ?, ?, ?, ?)";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		
		try{
			
			Event e = eventDao.insertEvent(event);
			
			Mockito.verify(spy).setDouble(1, event.getCost());
			Mockito.verify(spy).setString(2, event.getType());
			Mockito.verify(spy).setDate(3, java.sql.Date.valueOf(event.getStartDate()));
			Mockito.verify(spy).setDate(4, java.sql.Date.valueOf(event.getEndDate()));
			//need to find way to parse file to byte or largeobject in postgres
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
