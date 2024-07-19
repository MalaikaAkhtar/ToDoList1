package com.example.to_dolist.database

import androidx.lifecycle.LiveData
import com.example.to_dolist.dataclass.TodoItem

class TodoRepository (private val todoDao: TodoDao) {
    val todos: LiveData<List<TodoItem>> = todoDao.getAllTodos()

    suspend fun insert(todo: TodoItem) {
        todoDao.insert(todo)
    }
    suspend fun delete(todo: TodoItem) {
        todoDao.delete(todo)
    }

}
