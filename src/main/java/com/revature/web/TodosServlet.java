package com.revature.web;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TodosServlet extends HttpServlet {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Dispatcher.process(request, response);
		} catch (IOException e) {
			response.setContentType("application/json");
			response.setStatus(422);
			response.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("message", "Invalid JSON format")));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
