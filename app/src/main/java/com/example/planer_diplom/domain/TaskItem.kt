package com.example.planer_diplom.domain

data class TaskItem (
    val name: String,
    val worker: String,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID,
){
    companion object{

        const val UNDEFINED_ID = -1
    }
}