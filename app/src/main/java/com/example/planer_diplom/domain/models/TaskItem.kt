package com.example.planer_diplom.domain.models

data class TaskItem (
    var name: String = "",
    var workerName: String = "",
    var description: String = "",
    var enabled: Boolean = false,
    var id: Int = UNDEFINED_ID,
){
    companion object{

        const val UNDEFINED_ID = -1
    }
}