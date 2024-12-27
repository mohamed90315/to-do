package com.example.to_doapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val taskList: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.completeCheckBox)
        val taskName: TextView = itemView.findViewById(R.id.taskName)
        val taskDetails: TextView = itemView.findViewById(R.id.taskDetails)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = taskList[position]

        // Set task details
        holder.taskName.text = currentTask.name
        holder.taskDetails.text = currentTask.details

        // Update CheckBox state
        holder.checkBox.isChecked = currentTask.isCompleted

        // Apply or remove strikethrough based on completion status
        toggleStrikeThrough(holder.taskName, currentTask.isCompleted)

        // Handle CheckBox changes
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            currentTask.isCompleted = isChecked // Update task completion status
            toggleStrikeThrough(holder.taskName, isChecked)
        }

        // Handle delete button click
        holder.deleteButton.setOnClickListener {
            taskList.removeAt(position) // Remove the task from the list
            notifyItemRemoved(position) // Notify that the item was removed
            notifyItemRangeChanged(position, taskList.size) // Update remaining items
        }
    }

    override fun getItemCount() = taskList.size

    // Helper function to apply/remove strikethrough
    private fun toggleStrikeThrough(textView: TextView, isCompleted: Boolean) {
        if (isCompleted) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}