package com.example.todolist.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.Task
import com.example.todolist.database.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskEditViewModel(private val context: Context, private val id: Long) : ViewModel() {

    private val _task: MutableLiveData<Task> = MutableLiveData()

    val db = getDatabase(context)

    val task
        get() = _task

    
    init {
        viewModelScope.launch{
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

    class TaskEditViewModelFactory(private val context: Context, private val id: Long):ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TaskEditViewModel(context,id) as T
        }
    }

}