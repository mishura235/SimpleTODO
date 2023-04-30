package com.example.todolist

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.recyclerview.TaskRecyclerViewAdapter
import com.example.todolist.database.Task
import com.example.todolist.viewmodel.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var createNewTask:Button
    lateinit var taskRecyclerView: RecyclerView
    lateinit var adapter: TaskRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNewTask = findViewById(R.id.create_new_task_button)
        taskRecyclerView = findViewById(R.id.task_recycler_view)

        val viewModel by viewModels<TaskViewModel>()

        adapter = TaskRecyclerViewAdapter(this)
        taskRecyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        taskRecyclerView.adapter = adapter
        viewModel.tasksList.observe(this, Observer {
            adapter.tasks = viewModel.tasksList.value ?: listOf()
            adapter.notifyDataSetChanged()
        })

        createNewTask.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
            val idChannel = Channel<Long>()
            viewModel.createTask(channel = idChannel)
            val id = idChannel.receive()
            val intent = Intent(applicationContext,TaskEditActivity::class.java)
            intent.putExtra(TaskEditActivity.TASK_ID, id)
            startActivity(intent)
        }
        }
    }

    override fun onStart() {
        super.onStart()
        val viewModel by viewModels<TaskViewModel>()
        viewModel.loadAllTasks()
        Log.d("MainACtivity", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        val viewModel by viewModels<TaskViewModel>()
        viewModel.loadAllTasks()
        Log.d("MainACtivity", "onRestart: ")
    }


}