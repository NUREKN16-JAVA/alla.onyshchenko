package com.nixsolutions.usermanagement.web;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nixsolutions.usermanagement.User;

class EditServletTest extends MockServletTestCase {

	 @Override
	    public void setUp() throws Exception {
	        super.setUp();
	        createServlet(EditServlet.class);
	    }

	    @Test
	    public void testEdit() {
	        User user = new User(1000L, "John", "Doe", LocalDate.now());
	        getMockUserDao().expect("update", user);

	        addRequestParameter("id", "1000");
	        addRequestParameter("firstName", "John");
	        addRequestParameter("lastName", "Doe");
	        addRequestParameter("date", String.valueOf(LocalDate.now()));
	        addRequestParameter("okButton", "Ok");
	        doPost();
	    }

	    @Test
	    public void testEditEmptyFirstName() {
	        addRequestParameter("id", "1000");
	        addRequestParameter("lastName", "Doe");
	        addRequestParameter("date", String.valueOf(LocalDate.now()));
	        addRequestParameter("okButton", "Ok");
	        doPost();
	        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	        assertNotNull("Could not find error message in session scope", errorMessage);
	    }

	    @Test
	    public void testEditEmptyLastName() {
	        addRequestParameter("id", "1000");
	        addRequestParameter("firstName", "John");
	        addRequestParameter("date", String.valueOf(LocalDate.now()));
	        addRequestParameter("okButton", "Ok");
	        doPost();
	        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	        assertNotNull("Could not find error message in session scope", errorMessage);
	    }

	    @Test
	    public void testEditEmptyEmptyDate() {
	        addRequestParameter("id", "1000");
	        addRequestParameter("firstName", "John");
	        addRequestParameter("lastName", "Doe");
	        addRequestParameter("okButton", "Ok");
	        doPost();
	        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	        assertNotNull("Could not find error message in session scope", errorMessage);
	    }

	    @Test
	    public void testEditEmptyDataIncorect() {
	        addRequestParameter("id", "1000");
	        addRequestParameter("firstName", "John");
	        addRequestParameter("lastName", "Doe");
	        addRequestParameter("date", "123456789");
	        addRequestParameter("okButton", "Ok");
	        doPost();
	        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	        assertNotNull("Could not find error message in session scope", errorMessage);
	    }
}
