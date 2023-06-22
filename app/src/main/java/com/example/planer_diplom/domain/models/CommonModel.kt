package com.example.planer_diplom.domain.models

data class CommonModel(
    val firstName: String = "",
    val lastName: String = "",
    val patronymic: String = "",
    val phone: String = "",
    val managerStatus: Boolean = false,
    val fio: String = "",
    val mId: String = "",
//    var fio: String = "$lastName ${firstName[0]}.${patronymic[0]}",
    val id: String = "",
//    TaskItemModel
    val name: String = "",
    val workerName: String = "",
    val enabled: Boolean = false,
    val description: String = ""
) {
//    override fun equals(other: Any?): Boolean {
//        return (other as CommonWorkerModel).id == id
//    }

}