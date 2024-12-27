package com.example.to_doapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapp.R
import com.example.to_doapp.Task
import com.example.to_doapp.TaskAdapter

class HomeFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private val taskList: MutableList<Task> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize task list
        taskList.addAll(getTaskList())

        // Setup RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize adapter with the mutable task list
        taskAdapter = TaskAdapter(taskList)
        recyclerView.adapter = taskAdapter

        // Update total tasks and status
        val totalTasksTextView: TextView = view.findViewById(R.id.totalTasksTextView)
        totalTasksTextView.text = "Total Tasks: ${taskList.size}"

        // Display task status based on the number of tasks
        val taskStatusTextView: TextView = view.findViewById(R.id.taskStatusTextView)
        taskStatusTextView.text = getTaskStatus(taskList.size)

        // Add Task Button
        val addButton: ImageButton = view.findViewById(R.id.addTaskButton)
        addButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.main, AddTaskFragment())
                addToBackStack(null)
            }
        }

        return view
    }

    // Sample task data
    private fun getTaskList(): List<Task> {
        return listOf(
            Task("Workout", "Morning exercise", "Sport", "Do a 30-minute cardio routine"),
            Task("Grocery Shopping", "Weekly grocery list", "Food", "Buy fruits, vegetables, and snacks"),
            Task("Math Homework", "Solve Assignment", "Study", "Solve problems 1-10")
        )
    }

    // Logic to determine task status based on the task count
    private fun getTaskStatus(taskCount: Int): String {
        return when {
            taskCount < 3 -> "Free Day"
            taskCount in 3..6 -> "Busy Day"
            else -> "Very Busy Day"
        }
    }
}
