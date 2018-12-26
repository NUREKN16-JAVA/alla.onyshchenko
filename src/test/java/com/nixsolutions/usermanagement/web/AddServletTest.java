package com.nixsolutions.usermanagement.web;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nixsolutions.usermanagement.User;

class AddServletTest extends MockServletTestCase {

	@Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    @Test
    public void testAdd() {
        User user = new User(1000L, "John", "Doe", LocalDate.now());
        User newUser = new User("John", "Doe", LocalDate.now());
        getMockUserDao().expectAndReturn("create", newUser, user);

        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", String.valueOf(LocalDate.now()));
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    @Test
    public void testAddEmptyFirstName() {
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", String.valueOf(LocalDate.now()));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    @Test
    public void testAddEmptyLastName() {
        addRequestParameter("firstName", "John");
        addRequestParameter("date", String.valueOf(LocalDate.now()));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    @Test
    public void testAddEmptyEmptyDate() {
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    @Test
    public void testAddEmptyDataIncorect() {
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", "123456789");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

}
