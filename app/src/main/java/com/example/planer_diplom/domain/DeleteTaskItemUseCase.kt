package com.example.planer_diplom.domain

class DeleteTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun deleteTaskItem (taskItem: TaskItem){
        taskListRepository.deleteTaskItem(taskItem)
    }
}