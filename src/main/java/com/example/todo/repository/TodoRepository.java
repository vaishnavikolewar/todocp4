package com.example.todo.repository;

import java.util.*;
import com.example.todo.model.Todo;

public interface TodoRepository{

    ArrayList<Todo> getAllTodos();

    Todo getTodoById(int id);

    Todo addTodo(Todo todo);

    Todo updateTodo(int id,Todo todo);

    void deleteTodo(int id);
}