package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Todo;
import com.revature.util.ConnectionFactory;

public class TodoDaoImpl implements TodoDao {

	@Override
	public List<Todo> getAllTodosByUsername(String username) {
		List<Todo> todos = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos WHERE username = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) 
				todos.add(new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description")));
			return todos;
		} catch (SQLException e) {
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
			throw new RuntimeException("Failed to get all todos");
		}
	}

	@Override
	public Todo createTodo(Todo todo, String username) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			CallableStatement stmt = conn.prepareCall("CALL CREATE_TODO(?, ?, ?)");
			stmt.setString(1, todo.getTitle());
			stmt.setString(2, todo.getDescription());
			stmt.setString(3, username);
			if (stmt.executeUpdate() == 1) 
				return todo;
			else 
				return null;
		} catch (SQLException e) {
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
			throw new RuntimeException("Failed to get all todos");
		}
	}

//	@Override
//	public List<Todo> getAllTodos() {
//		final List<Todo> todos = new ArrayList<>();
//		try (Connection conn = ConnectionFactory.getConnection()) {
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM todos");
//			while (rs.next())
//				todos.add(new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description")));
//			return todos;
//		} catch (SQLException e) {
//			System.err.println("SQL State: " + e.getSQLState());
//			System.err.println("Error Code: " + e.getErrorCode());
//			throw new RuntimeException("Failed to get all todos");
//		}
//	}

	
}
