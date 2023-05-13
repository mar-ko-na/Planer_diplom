package com.example.planer_diplom.utilits

import com.example.planer_diplom.domain.WorkerItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var WORKER: WorkerItem
lateinit var UID: String

const val NODE_WORKERS="workers"
const val NODE_PHONES = "phones"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_WORKER_FIRSTNAME = "username"
const val CHILD_WORKER_LASTNAME = "userLastname"
const val CHILD_WORKER_PATRONYMIC = "userPatronymic"
const val CHILD_WORKER_FIO = "fio"
const val CHILD_WORKER_STATUS = "managerStatus"


fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    WORKER = WorkerItem()
    UID = AUTH.currentUser?.uid.toString()

}