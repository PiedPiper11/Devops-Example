package com.revature.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;

public class LoginServiceImpl implements LoginService {

	private final LoginDao dao = new LoginDaoImpl();
	
	@Override
	public void attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
		if (username == null || password == null || username.equals("") || password.equals("")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (dao.isUserValid(username, password)) {
			// Our user is authenticated
			request.getSession().setAttribute("currentUser", username);
			try {
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

}
