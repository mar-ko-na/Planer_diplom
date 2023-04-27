package com.example.planer_diplom.presentation.task_list_adapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.planer_diplom.domain.DeleteTaskItemUseCase
import com.example.planer_diplom.domain.EditTaskItemUseCase
import com.example.planer_diplom.domain.GetTaskListUseCase
import com.example.planer_diplom.domain.TaskItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel (application: Application) : AndroidViewModel(application)  {

    private val repository = com.example.planer_diplom.data.TaskListRepositoryImpl(application)

    private val getTaskListUseCase = GetTaskListUseCase(repository)
    private val deleteTaskItemUseCase = DeleteTaskItemUseCase(repository)
    private val editTaskItemUseCase = EditTaskItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.Default)

    val taskList = getTaskListUseCase.getTaskList()

    fun deleteTaskItem(taskItem: TaskItem) {
        scope.launch {
            deleteTaskItemUseCase.deleteTaskItem(taskItem)
        }
    }

    fun changeEnableState(taskItem: TaskItem) {
        scope.launch {
            val newItem = taskItem.copy(enabled = !taskItem.enabled)
            editTaskItemUseCase.editTaskItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}