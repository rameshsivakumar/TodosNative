package com.sample.todosnative.repository

import android.util.Log
import com.sample.todosnative.db.TodoDao
import com.sample.todosnative.network.ApiService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TodoRepository (private val apiService: ApiService, private val todoDao: TodoDao) {

    fun fetchTodos() = flow {
        try {
            val fetchedTodos = apiService.getTodos()
            todoDao.insertAll(fetchedTodos)
            Log.i("TodoRepository","inserted into DB")
            emit(fetchedTodos)
        } catch (e: IOException) {
            // Network error, fallback to local database
            Log.e("TodoRepository","IOException")
            emit(todoDao.getTodos())
        } catch (e: HttpException) {
            // HTTP error, fallback to local database
            Log.e("TodoRepository","HttpException")
            emit(todoDao.getTodos())
        }
    }
}