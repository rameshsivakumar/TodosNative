package com.sample.todosnative

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sample.todosnative.db.TodoItemEntity
import com.sample.todosnative.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp(todoViewModel) { todo ->
                val intent = Intent(this, TodoDetailActivity::class.java).apply {
                   val todoItem = Bundle().apply {
                        putInt("id", todo.id)
                        putInt("userId", todo.userId)
                        putString("title", todo.title)
                        putBoolean("completed", todo.completed)
                    }
                    putExtra("todo", todoItem)
                }
                startActivity(intent)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(todoViewModel: TodoViewModel = viewModel(), onTodoClick: (TodoItemEntity) -> Unit) {
    val todos by todoViewModel.todos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Todo List") })
        }
    ) { _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(todos) { todo: TodoItemEntity ->
                TodoItemView(todo, onClick = onTodoClick)
            }
        }
    }
}

@Composable
fun TodoItemView(todo: TodoItemEntity, onClick: (TodoItemEntity) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(todo) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = todo.completed,
            onCheckedChange = null // Read-only
        )
        Text(
            text = todo.title,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 25.sp // Increase font size
            )
        )
    }
}