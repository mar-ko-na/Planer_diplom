package com.example.planer_diplom.domain

import com.example.planer_diplom.domain.models.TaskItem

class GetTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun getTaskItem (taskItemId: Int): TaskItem {
        return taskListRepository.getTaskItem(taskItemId)
    }
}