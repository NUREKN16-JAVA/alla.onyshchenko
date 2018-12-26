package com.nixsolutions.usermanagement.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.usermanagement.db.DaoFactory;
import com.nixsolutions.usermanagement.db.DatabaseException;

public class BrowseServlet extends HttpServlet {

	private Object req;
	private Object reap;
	private Object resp;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) 
			throws ServletException, IOException {
		if (req.getParameter("addButton") != null) {
			add(req, resp);
		}else if (req.get{Parameter("editButton") != null) {
			edit(req, resp);
		}else if (req.getParameter("deleteButton") != null) {
			delete(req, resp);
		}else if (req.getParameter("detailsButton") != null) {
			details(req, resp);
		}else {
		browse(req, reap);
		}
	}

	private void details(Object req2, Object resp2) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}

	private void delete(Object req2, Object resp2) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}

	private void edit(Object req2, Object resp2) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}

	private void add(Object req2, Object resp2) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Collection users;
		try {
			users = DaoFactory.getInstance().getUserDao().findAll();
			req.getSession().setAttribute("users", users);
			req.getRequestDispatcher("/browse.jpg").forward(req,  resp);
		} catch (DatabaseException e) {
			throw new ServletException(e);
		}
		
	}

}
