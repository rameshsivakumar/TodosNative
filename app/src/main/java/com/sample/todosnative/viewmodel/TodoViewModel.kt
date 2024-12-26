package com.sample.todosnative.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.todosnative.model.TodoItem
import com.sample.todosnative.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
)  : ViewModel() {
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchTodos().collect { fetchedTodos ->
                Log.i("TodoViewModel","fetchedTodos $fetchedTodos")
                _todos.value = fetchedTodos
            }
        }
    }
}