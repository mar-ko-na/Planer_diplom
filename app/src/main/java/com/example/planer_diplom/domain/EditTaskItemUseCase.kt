package com.example.planer_diplom.domain

class EditTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun editTaskItem (taskItem: TaskItem){
        taskListRepository.editTaskItem(taskItem)
    }
}