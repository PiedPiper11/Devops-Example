package com.revature.dao;

import java.util.List;

import com.revature.model.Todo;

public interface TodoDao {

	List<Todo> getAllTodosByUsername(String username);
	Todo createTodo(Todo todo, String username);
}
