package com.example.sevenminworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminworkout.databinding.TaskItemBinding

class TaskAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    inner class TaskViewHolder(val itemBinding: TaskItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun bindItem(task: Task) {
            itemBinding.taskTitle.text = task.title
            itemBinding.taskSubttl.text = task.subttitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bindItem(task)
    }


}