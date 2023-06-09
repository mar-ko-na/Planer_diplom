package com.example.planer_diplom.domain.models

data class WorkerItem(
    var firstName: String ?= "",
    var lastName: String ?= "",
    var patronymic: String ?= "",
    var phone: String ?= "",
    var managerStatus: Boolean = false,
    var fio: String ?= "",
//    var fio: String = "$lastName ${firstName[0]}.${patronymic[0]}",
    var id: String ?= ""
)
