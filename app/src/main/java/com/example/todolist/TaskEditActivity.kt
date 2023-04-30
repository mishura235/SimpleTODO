package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.viewmodel.TaskEditViewModel

class TaskEditActivity : AppCompatActivity() {


    companion object{
        const val TASK_ID="task_id"
    }

    lateinit var editParagraph:EditText
    lateinit var editText:EditText
    lateinit var saveButton: Button
    lateinit var deleteButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_edit)

        editParagraph = findViewById(R.id.task_edit_paragraph)
        editText = findViewById(R.id.task_edit_text)
        saveButton = findViewById(R.id.save_button)
        deleteButton = findViewById(R.id.delete_button)

        val taskId = intent.getLongExtra(TASK_ID,0)
        val viewModel= ViewModelProvider(this,TaskEditViewModel.TaskEditViewModelFactory(this,taskId))[TaskEditViewModel::class.java]


        viewModel.task.observe(this) {
            editText.setText(it.text)
            editParagraph.setText(it.paragraph)
        }

        saveButton.setOnClickListener {
            viewModel.saveChanges(editParagraph.text.toString(),editText.text.toString())
            finish()
        }

        deleteButton.setOnClickListener {
            viewModel.deleteTask()
            finish()
        }

    }

}