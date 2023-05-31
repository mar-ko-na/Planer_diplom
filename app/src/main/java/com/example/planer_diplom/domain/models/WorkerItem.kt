package com.example.planer_diplom.domain.models

data class WorkerItem(
    var firstName: String ?= null,
    var lastName: String ?= null,
    var patronymic: String ?= null,
    var phone: String ?= null,
    var managerStatus: Boolean = false,
    var fio: String ?= null,
    var id: String ?= null
)
