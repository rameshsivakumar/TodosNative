package com.sample.todosnative.repository

import com.sample.todosnative.model.TodoItem

class TodoRepository {
    private val todos = mutableListOf<TodoItem>()
    private var nextId = 0

    fun getTodos(): List<TodoItem> {
        return todos
    }

    fun addTodo(title: String) {
        if (title.isNotBlank()) {
            todos.add(TodoItem(nextId++, title, false))
        }
    }

    fun removeTodoById(id: Int) {
        todos.removeAll { it.id == id }
    }

    fun toggleTodoDone(id: Int) {
        val index = todos.indexOfFirst { it.id == id }
        if (index != -1) {
            val todo = todos[index]
            todos[index] = todo.copy(isDone = !todo.isDone)
        }
    }
}