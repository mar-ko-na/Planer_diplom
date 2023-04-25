package com.example.planer_diplom.domain

import androidx.lifecycle.LiveData

interface TaskListRepository {

    fun addTaskItem (taskItem: TaskItem)

    fun deleteTaskItem (taskItem: TaskItem)

    fun editTaskItem (taskItem: TaskItem)

    fun getTaskItem (taskItemId: Int): TaskItem

    fun getTaskList(): LiveData<List<TaskItem>>

}