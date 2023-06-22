package com.example.planer_diplom.domain.models

data class TaskItem(
    var name: String = "",
    var workerName: String = "",
    var description: String = "",
    var enabled: Boolean = false,
    var id: Int = -1
)