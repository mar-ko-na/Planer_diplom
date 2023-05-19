package com.example.planer_diplom.domain

import com.example.planer_diplom.domain.models.TaskItem

class DeleteTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun deleteTaskItem (taskItem: TaskItem){
        taskListRepository.deleteTaskItem(taskItem)
    }
}