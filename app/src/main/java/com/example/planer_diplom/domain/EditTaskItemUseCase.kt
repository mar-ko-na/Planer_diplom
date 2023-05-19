package com.example.planer_diplom.domain

import com.example.planer_diplom.domain.models.TaskItem

class EditTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun editTaskItem (taskItem: TaskItem){
        taskListRepository.editTaskItem(taskItem)
    }
}