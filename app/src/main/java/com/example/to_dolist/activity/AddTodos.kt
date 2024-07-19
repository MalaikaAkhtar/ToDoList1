package com.example.to_dolist.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.to_dolist.databinding.ActivityAddTodosBinding
import com.example.to_dolist.dataclass.TodoItem
import com.example.to_dolist.viewmodel.TodoViewModel

class AddTodos : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodosBinding
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.saveBtn.setOnClickListener {
            val title = binding.idEdtTitle.text.toString()
            val description = binding.idEdtDescription.text.toString()
                if (title.isNotEmpty() && description != null){
                    val todo = TodoItem(0,title,description)
                    todoViewModel.insert(todo)
                    Toast.makeText(this, "Todo Added", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Enter title and description", Toast.LENGTH_SHORT).show()
                }

            startActivity(Intent(this@AddTodos, MainActivity::class.java))
            this.finish()

        }
    }
}
