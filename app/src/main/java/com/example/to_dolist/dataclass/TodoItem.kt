package com.example.to_dolist.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val title: String,
    val description: String,
)
