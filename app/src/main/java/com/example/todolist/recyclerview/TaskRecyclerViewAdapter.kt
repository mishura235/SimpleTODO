package com.example.todolist.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.TaskEditActivity
import com.example.todolist.database.Task

class TaskRecyclerViewAdapter (val context: Context): RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder>() {

    var tasks:List<Task> = listOf()
    class TaskViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val taskCardView:CardView = itemView.findViewById(R.id.task_card_view)
        val taskParagraph:TextView = itemView.findViewById(R.id.task_paragraph)
        val taskText:TextView = itemView.findViewById(R.id.task_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.task_recyclerview_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskParagraph.text = tasks[position].paragraph
        holder.taskText.text = tasks[position].text
        holder.taskCardView.setOnClickListener {
            val intent = Intent(context,TaskEditActivity::class.java)
            intent.putExtra(TaskEditActivity.TASK_ID,position.toLong()+1)
            context.startActivity(intent)
        }
    }

}