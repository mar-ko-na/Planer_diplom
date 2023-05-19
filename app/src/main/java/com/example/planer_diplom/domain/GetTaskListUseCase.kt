package com.example.planer_diplom.domain

import androidx.lifecycle.LiveData
import com.example.planer_diplom.domain.models.TaskItem

class GetTaskListUseCase(private val taskListRepository: TaskListRepository) {

    fun getTaskList(): LiveData<List<TaskItem>> {
        return taskListRepository.getTaskList()
    }
}