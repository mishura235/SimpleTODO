package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var createNewTask:Button
    lateinit var taskRecyclerView: RecyclerView
    lateinit var adapter: TaskRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNewTask = findViewById(R.id.create_new_task_button)
        taskRecyclerView = findViewById(R.id.task_recycler_view)

        //TODO перенести в Room
        var tasks = listOf(
            Task("Задание 1","текст задания 1"),
            Task("Задание 2","текст задания 2"),
            Task("Задание 3","текст задания 3"),
        )

        adapter = TaskRecyclerViewAdapter(tasks)
        taskRecyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        taskRecyclerView.adapter = adapter

        createNewTask.setOnClickListener{
            //TODO функция создания задания
        }
    }
}