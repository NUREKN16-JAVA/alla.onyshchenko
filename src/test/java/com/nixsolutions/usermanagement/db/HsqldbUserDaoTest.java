package com.nixsolutions.usermanagement.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Date;

import junit.framework.TestCase;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.jupiter.api.Test;

import com.nixsolutions.usermanagement.User;

public class HsqldbUserDaoTest extends DatadaseTestCase{
	
	private User user;
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	private static final Long ID = 0L;
	
	@Test
    public void testFind () throws DatabaseException {
        User testUser = dao.find(ID);
        assertNotNull(testUser);
        assertEquals( user.getFirstname(),testUser.getFirstname());
        assertEquals(user.getLastname(),testUser.getLastname());
    }


    @Test
    public void testUpdate() throws DatabaseException {
        User user = new User("Ivan", "Komarov", new Date());
        user.setId(0L);
        dao.update(user);
        User testUser = dao.find(user.getId());
        assertNotNull(testUser);
        assertEquals(user.getLastname(), testUser.getLastname());
    }

    @Test
    public void testDelete() throws DatabaseException {
        User testUser = new User(ID, "Ivan", "Ivanov", new Date());
        dao.delete(testUser);
        assertNull(dao.find(ID));
    }
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connectionFactory);
	}

	
	@Test
	void testCreate() {
		try {
			User user =new User();
			user.setFirstname("John");
			user.setLastname("Doe");
			user.setDateOfBithd(new Date());
			assertNull (user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
		}
	}

}
	
public void testFindAll() {
	try {
		Collection collection = dao.findAll();
		assertNotNull("Collection is null", collection);
		assertEquals("Collection size.", 2, collection.size());
	} catch (DatabaseException e) {
		e.printStackTrace();
		fail(e.toString());
	}
}

	@Override
	protected IDatabaseConnection getConnection() throws Exception{
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver",
				"jdbc:hsqldb:file:db/usermanagement", "sa", "");
		return new DatabaseConnection (connectionFactory.createConnection());
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
				.getResourceAsStream("usersDataSet.xml"));
		return DataSet;
	}
	
