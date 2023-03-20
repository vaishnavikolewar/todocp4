package com.example.todo.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
 import java.util.*;

 import com.example.todo.model.Todo;
 import com.example.todo.repository.TodoJpaRepository;
 import com.example.todo.repository.TodoRepository;


@Service
public class TodoJpaService implements TodoRepository{

    @Autowired
    private TodoJpaRepository todoRep;

    @Override
    public ArrayList<Todo> getAllTodos(){
        List<Todo> list = todoRep.findAll();
        return new ArrayList<>(list);
    }

    @Override 
    public Todo getTodoById(int id){
        try{
            Todo todo = todoRep.findById(id).get();
            return todo;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public Todo addTodo(Todo todo){
        todoRep.save(todo);
        return todo;
        
    }

    @Override 
    public Todo updateTodo(int id,Todo todo){
        try{
            Todo existingTodo = todoRep.findById(id).get();

            if(todo.getTodo()!=null){
                existingTodo.setTodo(todo.getTodo());
            }
            if(todo.getPriority()!=null){
                existingTodo.setPriority(todo.getPriority());
            }
            if(todo.getStatus()!=null){
                existingTodo.setStatus(todo.getStatus());
            }
            todoRep.save(existingTodo);
            return existingTodo;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public void deleteTodo(int id){
        try{
            todoRep.deleteById(id);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}