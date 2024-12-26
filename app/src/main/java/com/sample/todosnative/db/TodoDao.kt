package com.sample.todosnative.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos")
    fun getTodos(): List<TodoItemEntity>

    @Insert(onConflict = 1)
    suspend fun insertAll(todos: List<TodoItemEntity>)
}