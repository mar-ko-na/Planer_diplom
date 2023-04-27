package com.example.planer_diplom.domain

import com.example.planer_diplom.domain.TaskItem.Companion.UNDEFINED_ID

data class WorkerItem(
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val number: Int,
    val fio: String = "$lastName ${firstName[0]}.${patronymic[0]}",
    var id: Int = UNDEFINED_ID,
){

}

