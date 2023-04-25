package com.example.planer_diplom.domain

import androidx.lifecycle.LiveData

class GetTaskListUseCase(private val taskListRepository: TaskListRepository) {

    fun getTaskList(): LiveData<List<TaskItem>> {
        return taskListRepository.getTaskList()
    }
}