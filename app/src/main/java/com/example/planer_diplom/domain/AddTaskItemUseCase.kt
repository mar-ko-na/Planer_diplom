package com.example.planer_diplom.domain

import com.example.planer_diplom.domain.models.TaskItem

class AddTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun addTaskItem (taskItem: TaskItem) {
        taskListRepository.addTaskItem(taskItem)
    }
}