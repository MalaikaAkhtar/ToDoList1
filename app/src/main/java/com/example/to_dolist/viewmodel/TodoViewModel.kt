package com.example.to_dolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_dolist.dataclass.TodoItem
import com.example.to_dolist.database.TodoDatabase
import com.example.to_dolist.database.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    val todos: LiveData<List<TodoItem>>

    init {
        val todoDao = TodoDatabase.getInstance(application).todoDao()
        repository = TodoRepository(todoDao)
        todos = repository.todos
    }
    fun insert(todo: TodoItem) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun delete(todo: TodoItem) = viewModelScope.launch {
        repository.delete(todo)
    }

}