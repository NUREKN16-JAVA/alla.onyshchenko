package com.nixsolutions.usermanagement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private User user;
	private Date dateOfBithd;

	@BeforeEach
	void setUp() throws Exception {
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1984, Calendar.MAY, 26);
		dateOfBithd = calendar.getTime();
		}
	
		@Test
		public void testGetFullName() {
			user.setFirstname("John");
			user.setLastname("Doe");
			assertEquals("Doe, John", user.getFullName ());
		}
		
		@Test
		public void testGetAge1(){

			Calendar calendar = Calendar.getInstance();
			calendar.set(1999, Calendar.MAY, 12);
			dateOfBithd = calendar.getTime();
			user.setDateOfBithd(dateOfBithd);
			assertEquals(19, user.getAge());
		}
		
		@Test
		public void testGetAge2(){

			Calendar calendar = Calendar.getInstance();
			calendar.set(1999, Calendar.NOVEMBER, 12);
			dateOfBithd = calendar.getTime();
			user.setDateOfBithd(dateOfBithd);
			assertEquals(18, user.getAge());
		}
		
		@Test
		public void testGetAge3(){

			Calendar calendar = Calendar.getInstance();
			calendar.set(1999, Calendar.OCTOBER, 12);
			dateOfBithd = calendar.getTime();
			user.setDateOfBithd(dateOfBithd);
			assertEquals(19, user.getAge());
		}
		@Test
		public void testGetAge4(){

			Calendar calendar = Calendar.getInstance();
			calendar.set(1999, Calendar.NOVEMBER, 1);
			dateOfBithd = calendar.getTime();
			user.setDateOfBithd(dateOfBithd);
			assertEquals(19, user.getAge());
		}
		
}
