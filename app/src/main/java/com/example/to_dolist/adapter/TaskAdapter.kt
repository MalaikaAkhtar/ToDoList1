package com.example.to_dolist.adapter


import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.to_dolist.R
import com.example.to_dolist.databinding.ItemTaskBinding
import com.example.to_dolist.dataclass.TodoItem

class TaskAdapter(private val onDelete:(TodoItem) -> Unit,
                  private val onUpdate:(TodoItem) ->Unit) : ListAdapter<TodoItem, TaskAdapter.MyViewHolder>(MyDiffCallback()) {

    class MyViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){
        val deleteIV: ImageView = itemView.findViewById(R.id.deleteIV)

        fun bind(item: TodoItem) {
            binding.apply {
                taskTitle.text = item.title
                taskDescription.text = item.description

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)

        holder.deleteIV.setOnClickListener {
            onDelete.invoke(item)
        }

        holder.itemView.setOnClickListener {
            onUpdate.invoke(item)
        }

    }
}

class MyDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }

}