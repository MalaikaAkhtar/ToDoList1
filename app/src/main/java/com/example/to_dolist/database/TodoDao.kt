package com.example.to_dolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.to_dolist.dataclass.TodoItem

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_items")
    fun getAllTodos(): LiveData<List<TodoItem>>

    @Insert
    suspend fun insert(todo: TodoItem)

    @Update
    suspend fun update(todo: TodoItem)

    @Delete
    suspend fun delete(todo: TodoItem)
}
