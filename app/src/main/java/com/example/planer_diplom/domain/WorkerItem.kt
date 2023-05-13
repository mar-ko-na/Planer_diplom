package com.example.planer_diplom.domain

data class WorkerItem(
    var firstname: String = "",
    var lastname: String = "",
    var patronymic: String = "",
    var phone: String = "",
    var managerstatus: Boolean = false,
    var fio: String = "",
//    var fio: String = "$lastname ${firstname[0]}.${patronymic[0]}",
    var id: String = ""
)
