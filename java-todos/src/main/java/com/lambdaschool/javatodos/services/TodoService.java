package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Todo;

public interface TodoService {

    Todo update(Todo todo, long id);
}
