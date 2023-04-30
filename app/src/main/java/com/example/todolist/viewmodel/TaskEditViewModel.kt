package com.example.todolist.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.Task
import com.example.todolist.database.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskEditViewModel(application: Application) : AndroidViewModel(application) {

    private val _task: MutableLiveData<Task> = MutableLiveData()

    val db = getDatabase(application.applicationContext)

    val task
        get() = _task

    fun getTask(id:Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                task.postValue(db.getTaskDao().getTaskById(id))
            }
        }
    }




    fun saveChanges(paragraph: String, text: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.getTaskDao().updateTask(Task(task.value?.id,paragraph,text))
            }
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.getTaskDao().delete(task.value)
            }
        }
    }

}