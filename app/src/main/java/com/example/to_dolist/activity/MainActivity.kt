package com.example.to_dolist.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_dolist.adapter.TaskAdapter
import com.example.to_dolist.databinding.ActivityMainBinding
import com.example.to_dolist.dataclass.TodoItem
import com.example.to_dolist.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        val recyclerView = binding.recyclerViewTask
        recyclerView.layoutManager = LinearLayoutManager(this)

           taskAdapter = TaskAdapter(onDelete = { todoItem->
           todoViewModel.delete(todoItem)
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show() },

            onUpdate = { todoItem ->
            val intent = Intent(this@MainActivity, AddTodos::class.java)
            intent.putExtra("title",todoItem.title)
            intent.putExtra("discription",todoItem.description)
            startActivity(intent)

        })

        recyclerView.adapter = taskAdapter

        todoViewModel.todos.observe(this, Observer {
            taskAdapter.submitList(it)
        })

        binding.addBtn.setOnClickListener {
          startActivity(Intent(this@MainActivity,AddTodos::class.java))
        }

    }
}