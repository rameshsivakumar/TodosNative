package com.sample.todosnative.repository

import android.util.Log
import com.sample.todosnative.db.TodoDao
import com.sample.todosnative.db.TodoItemEntity
import com.sample.todosnative.network.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.HttpException
import java.io.IOException

@ExperimentalCoroutinesApi
class TodoRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var todoDao: TodoDao
    private lateinit var todoRepository: TodoRepository

    @Before
    fun setup() {
        clearAllCaches() // Clear existing static mocks
        apiService = mock(ApiService::class.java)
        todoDao = mock(TodoDao::class.java)
        todoRepository = TodoRepository(apiService, todoDao)
        mockStatic(Log::class.java)
    }

    @Test
    fun fetchTodos_success() = runTest {
        val todos = listOf(TodoItemEntity(id = 1, 0, title = "Test Todo", completed = false))
        `when`(apiService.getTodos()).thenReturn(todos)

        try {
            val result = todoRepository.fetchTodos().first()
            verify(todoDao).insertAll(todos)
            assertEquals(todos, result)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Test failed due to unexpected exception: ${e.message}", e)
        }
    }

    @Test
    fun fetchTodos_networkError() = runTest {
        val todos = listOf(TodoItemEntity(id = 1, 0, title = "Test Todo", completed = false))
        `when`(apiService.getTodos()).thenAnswer { throw IOException() }
        `when`(todoDao.getTodos()).thenReturn(todos)

        val result = todoRepository.fetchTodos().first()

        verify(todoDao, never()).insertAll(todos)
        assertEquals(todos, result)
    }

    @Test
    fun fetchTodos_httpError() = runTest {
        val todos = listOf(TodoItemEntity(id = 1, 0,  title = "Test Todo", completed = false))
        `when`(apiService.getTodos()).thenThrow(HttpException::class.java)
        `when`(todoDao.getTodos()).thenReturn(todos)

        val result = todoRepository.fetchTodos().first()

        verify(todoDao, never()).insertAll(todos)
        assertEquals(todos, result)
    }
}