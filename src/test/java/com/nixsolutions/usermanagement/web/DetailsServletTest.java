package com.nixsolutions.usermanagement.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nixsolutions.usermanagement.User;

class DetailsServletTest extends MockServletTestCase {

	@Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    @Test
    public void testDetails() {
        addRequestParameter("cancelButton", "cancel");
        User user = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNull("User is in session", user);
    }
}
