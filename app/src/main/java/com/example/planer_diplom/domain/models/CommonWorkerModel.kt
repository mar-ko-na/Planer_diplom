package com.example.planer_diplom.domain.models

data class CommonWorkerModel(
    val firstName: String = "",
    val Lastname: String = "",
    val userPatronymic: String = "",
    val phone: String = "",
    val managerStatus: Boolean = false,
    val fio: String = "",
//    var fio: String = "$lastName ${firstName[0]}.${patronymic[0]}",
    val id: String = ""
){
//    override fun equals(other: Any?): Boolean {
//        return (other as CommonWorkerModel).id == id
//    }

}