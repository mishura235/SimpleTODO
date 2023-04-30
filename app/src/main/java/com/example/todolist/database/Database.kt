package com.example.todolist.database

import android.content.Context
import androidx.room.*

@Dao
interface TaskDAO {


    @Insert
    fun insert(task: Task):Long

    @Delete
    fun delete(task: Task?)

    @Query("SELECT * FROM tasks")
    fun getAllTasks():List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(task:Task?)

    @Query("SELECT * FROM tasks WHERE id=:id")
    fun getTaskById(id: Long): Task
}

@Database(entities = [Task::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {
    abstract fun getTaskDao():TaskDAO
}

fun getDatabase(context: Context): TasksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TasksDatabase::class.java,
            "tasks"
        ).build()

}