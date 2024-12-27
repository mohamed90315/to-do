package com.example.to_doapp

data class Task(
    val name: String,
    val details: String,
    val category: String, // Category of the task
    val description: String, // Task description
    var isCompleted: Boolean = false // Default value set for convenience
)
