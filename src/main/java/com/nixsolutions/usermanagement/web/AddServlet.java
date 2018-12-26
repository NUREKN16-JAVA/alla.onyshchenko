package com.nixsolutions.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.usermanagement.User;
import com.nixsolutions.usermanagement.db.DaoFactory;
import com.nixsolutions.usermanagement.db.DatabaseException;

public class AddServlet extends EditServlet  {
	private static final String ADD_JSP = "/add,jsp";

    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(ADD_JSP).forward(req, resp);
    }

    @Override
    protected void processUser(User user) throws ReflectiveOperationException, DatabaseException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
}
