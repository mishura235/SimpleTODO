package com.example.todolist.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.database.Task

class TaskDiffUtil(private val oldList: List<Task>, private val newList: List<Task>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].text != newList[newItemPosition].text -> {
                false
            }
            oldList[oldItemPosition].paragraph != newList[newItemPosition].paragraph -> {
                false
            }
            else -> true
        }
    }
}