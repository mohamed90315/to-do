package com.example.to_doapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
            Task("Sport", "Morning workout"),
            Task("Food", "Buy groceries"),
            Task("Study", "Solve math problems"),
        )
    }
}
