package com.example.planer_diplom.utilits

import android.util.Log
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.domain.models.CommonWorkerModel
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.domain.models.WorkerItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var WORKER: WorkerItem
lateinit var TASK: TaskItem
lateinit var CURRENT_UID: String
lateinit var REF_db_worker: DatabaseReference

const val NODE_WORKERS = "workers"
const val NODE_TASKS = "tasks"
const val NODE_PHONES = "phones"
const val NODE_PHONES_ID = "phonesContact"
const val NODE_WORKER_TASK = "workerTasks"
const val NODE_FIO_ID = "fioId"
const val NODE_ID = "id"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_WORKER_FIRSTNAME = "firstName"
const val CHILD_WORKER_LASTNAME = "lastName"
const val CHILD_WORKER_PATRONYMIC = "patronymic"
const val CHILD_WORKER_FIO = "fio"
const val CHILD_WORKER_STATUS = "managerStatus"
const val CHILD_TASK_DESCRIPTION = "description"
const val CHILD_TASK_NAME = "name"
const val CHILD_TASK_WORKER = "workerName"
const val CHILD_TASK_ID = "taskId"
const val CHILD_TASK_ENABLED = "enabled"
const val NODE_CODE_MANAGER = "codeManager"


fun initFirebase() {
    Log.d("MyTag", "init firebase")
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    WORKER = WorkerItem()
    TASK = TaskItem()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
initID()
}

fun initID() {
    REF_DATABASE_ROOT.child(NODE_ID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            TASK.id = it.getValue(Int ::class.java) ?: TASK.id
        })
}

fun DataSnapshot.getCommonModel(): CommonModel =
    this.getValue(CommonModel::class.java) ?: CommonModel()


fun DataSnapshot.getUserModel(): CommonWorkerModel =
    this.getValue(CommonWorkerModel::class.java) ?: CommonWorkerModel()

fun initWorkers() {
    REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            WORKER = it.getValue(WorkerItem::class.java) ?: WorkerItem()
        })



}





