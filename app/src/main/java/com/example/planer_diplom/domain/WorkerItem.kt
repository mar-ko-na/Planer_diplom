package com.example.planer_diplom.domain

import com.example.planer_diplom.domain.TaskItem.Companion.UNDEFINED_ID

data class WorkerItem(
    var firstName: String = "",
    var lastName: String = "",
    var patronymic: String = "",
    var number: String = "",
    var status: WorkerStatus = WorkerStatus.WORKER,
    var fio: String = "",
//    var fio: String = "$lastName ${firstName[0]}.${patronymic[0]}",
    var id: String = ""
)
