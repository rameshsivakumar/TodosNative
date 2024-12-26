package com.sample.todosnative.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.todosnative.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos")
    fun getTodos(): List<TodoItem>

    @Insert(onConflict = 1)
    suspend fun insertAll(todos: List<TodoItem>)
}