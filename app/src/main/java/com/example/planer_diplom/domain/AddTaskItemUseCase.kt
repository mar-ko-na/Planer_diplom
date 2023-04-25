package com.example.planer_diplom.domain

class AddTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun addTaskItem (taskItem: TaskItem) {
        taskListRepository.addTaskItem(taskItem)
    }
}