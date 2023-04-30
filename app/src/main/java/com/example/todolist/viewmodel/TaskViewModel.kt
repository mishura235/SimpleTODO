package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.Task
import com.example.todolist.database.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(application: Application): AndroidViewModel(application) {

    private val _tasksList = MutableLiveData<List<Task>>(listOf())

    private val db = getDatabase(context = application.applicationContext)

    val tasksList
        get() = _tasksList


    fun createTask(paragraph:String="paragraph", text:String="text",channel: Channel<Long>){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                channel.send(db.getTaskDao().insert(Task(paragraph=paragraph, text=text)))
            }
        }
    }

    fun loadAllTasks(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _tasksList.postValue(db.getTaskDao().getAllTasks())
            }
        }
    }

}