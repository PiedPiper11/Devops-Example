package com.revature.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Todo;

public interface TodoService {

	// GET ALL, for when a GET REQUEST is sent to /todos
	List<Todo> getAllTodosByUsername(HttpServletRequest request, HttpServletResponse response);
	
	// CREATE, for when a POST REQUEST is sent to /todos
	void createTodo(HttpServletRequest request, HttpServletResponse response);
}
