package com.sample.todosnative.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.todosnative.model.TodoItem
import com.sample.todosnative.repository.TodoRepository

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todos = MutableLiveData<List<TodoItem>>()
    val todos: LiveData<List<TodoItem>> get() = _todos

    init {
        loadTodos()
    }

    private fun loadTodos() {
        _todos.value = repository.getTodos()
    }

    fun addTodo(title: String) {
        repository.addTodo(title)
        loadTodos()
    }

    fun removeTodoById(id: Int) {
        repository.removeTodoById(id)
        loadTodos()
    }

    fun toggleTodoDone(id: Int) {
        repository.toggleTodoDone(id)
        loadTodos()
    }
}