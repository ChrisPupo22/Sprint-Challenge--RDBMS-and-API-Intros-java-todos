package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todorepos;

    @Override
    public Todo update(Todo todo, long id) {

        Todo currentTodo = todorepos.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Long.toString(id)));
        if(todo.getDescription() != null)
        {
            currentTodo.setDescription(todo.getDescription());
        }
        if(todo.getDatestarted() != null)
        {
            currentTodo.setDatestarted(todo.getDatestarted());
        }
        if(todo.isCompleted())
        {
            currentTodo.setCompleted(true);
        }
        else {
            currentTodo.setCompleted(false);
        }
        if(todo.getUser() != null)
        {
            currentTodo.setUser(todo.getUser());
        }
        return todorepos.save(currentTodo);
    }
}
