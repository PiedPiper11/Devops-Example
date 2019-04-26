package com.revature.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.TodoDao;
import com.revature.dao.TodoDaoImpl;
import com.revature.model.Todo;

public class TodoServiceImpl implements TodoService {

	private final TodoDao dao = new TodoDaoImpl();
	
	@Override
	public List<Todo> getAllTodosByUsername(HttpServletRequest request, HttpServletResponse response) {
		final String username = (String) request.getSession().getAttribute("currentUser");
		System.out.println(username + " attempting access all todos");
		return dao.getAllTodosByUsername(username);
	}

	@Override
	public void createTodo(HttpServletRequest request, HttpServletResponse response) {
		final String username = (String) request.getSession().getAttribute("currentUser");
		final String title = (String) request.getParameter("title");
		final String description = (String) request.getParameter("description");
		Todo todo = dao.createTodo(new Todo(0, title, description), username);
			try {
				if (todo == null) {
					request.getRequestDispatcher("/home.jsp?error").forward(request, response);
				} else {
					request.getRequestDispatcher("/home.jsp").forward(request, response);
				}
				
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			} 
	}

}
