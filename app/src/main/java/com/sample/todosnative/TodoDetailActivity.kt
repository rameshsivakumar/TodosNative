package com.sample.todosnative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.todosnative.db.TodoItemEntity

class TodoDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todo = intent.getBundleExtra("todo")?.let {
            TodoItemEntity(
                it.getInt("id"),
                it.getInt("userId"),
                it.getString("title") ?: "",
                it.getBoolean("completed")
            )
        }
        setContent {
            todo?.let {
                TodoDetailScreen(it)
            }
        }
    }
}

@Composable
fun TodoDetailScreen(todo: TodoItemEntity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp)) // Add padding before the title
        Text(text = "Title: ${todo.title}", style = MaterialTheme.typography.headlineLarge.copy(
            fontSize = 30.sp // Increase font size
        ))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Completed: ${if (todo.completed) "Yes" else "No"}", style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 25.sp // Increase font size
        ))
    }
}