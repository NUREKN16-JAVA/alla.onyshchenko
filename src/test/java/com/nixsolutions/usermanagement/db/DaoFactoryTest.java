package com.nixsolutions.usermanagement.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class DaoFactoryTest {

	@Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
	
	@Test
	void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao instance is null", userDao);
		} catch (RuntimeException e) {
			fail(e.toString());
		}
	}

}
