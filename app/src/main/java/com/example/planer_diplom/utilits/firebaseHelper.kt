package com.example.planer_diplom.utilits

import com.example.planer_diplom.R
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.domain.models.WorkerItem
import com.example.planer_diplom.presentation.worker_list.fragments.ChangeNameFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.collections.ArrayList

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var WORKER: WorkerItem
lateinit var CURRENT_UID: String
lateinit var REF_db_worker: DatabaseReference

const val NODE_WORKERS = "workers"
const val NODE_PHONES = "phones"
const val NODE_PHONES_ID = "phonesContact"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_WORKER_FIRSTNAME = "firstName"
const val CHILD_WORKER_LASTNAME = "lastName"
const val CHILD_WORKER_PATRONYMIC = "patronymic"
const val CHILD_WORKER_FIO = "fio"
const val CHILD_WORKER_STATUS = "managerStatus"
const val CHILD_TASK_NAME = "name"
const val CHILD_TASK_WORKER = "worker"
const val CHILD_TASK_ENABLED = "enabled"
const val CHILD_TASK_DESCRIPTION = "description"


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    WORKER = WorkerItem()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    REF_db_worker = FirebaseDatabase.getInstance().getReference("workers")

}

fun DataSnapshot.getCommonWorkerModel(): CommonModel =
    this.getValue(CommonModel::class.java) ?: CommonModel()

fun DataSnapshot.getWorkerModel(): WorkerItem =
    this.getValue(WorkerItem::class.java) ?: WorkerItem()

fun initWorkers() {
    REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEvenListener {
            WORKER = it.getValue(WorkerItem::class.java) ?: WorkerItem()

        })



}




//fun initWorkerList() {
//    if (WORKER.managerStatus == WorkerStatus.S_MANAGER) {
//        var arrayWorker = arrayListOf<CommonWorkerModel>()
//
//        val cursor = APP_ACTIVITY.contentResolver.query(
//            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//            null,
//            null,
//            null,
//            null
//        )
//        cursor?.let {
//            while (cursor.moveToNext()){
////                val fio = it.getString(it.getColumnIndex(ContactsContract.))
////        val phone
////            урок 30. 5:26
//            }
//
//        }
//        updatePhonesToDatabase(arrayWorker)
//    }
//}
//fun updatePhonesToDatabase(arrayContacts: ArrayList<CommonModel>) {
//    // Функция добавляет номер телефона с id в базу данных.
//    if (AUTH.currentUser != null) {
//        REF_DATABASE_ROOT.child(NODE_PHONES).addListenerForSingleValueEvent(
//            AppValueEventListener {
//                it.children.forEach { snapshot ->
//                    arrayContacts.forEach { contact ->
//                        if (snapshot.key == contact.phone) {
//                            REF_DATABASE_ROOT.child(
//                                NODE_PHONES_CONTACTS
//                            ).child(CURRENT_UID)
//                                .child(snapshot.value.toString())
//                                .child(CHILD_ID)
//                                .setValue(snapshot.value.toString())
//                                .addOnFailureListener {
//                                    showToast(
//                                        it.message.toString()
//                                    )
//                                }
//
//                            REF_DATABASE_ROOT.child(
//                                NODE_PHONES_CONTACTS
//                            ).child(CURRENT_UID)
//                                .child(snapshot.value.toString())
//                                .child(CHILD_FULLNAME)
//                                .setValue(contact.fullname)
//                                .addOnFailureListener {
//                                    showToast(
//                                        it.message.toString()
//                                    )
//                                }
//                        }
//                    }
//                }
//            })
//    }
//}
//
//fun updatePhonesToDatabase(arrayWorker: ArrayList<CommonWorkerModel>) {
//    REF_DATABASE_ROOT.child(NODE_PHONES).addListenerForSingleValueEvent(AppValueEvenListener{
//        it.children.forEach {snapshot ->
//        arrayWorker.forEach{contact ->
//            if (snapshot.key == contact.phone){
//                REF_DATABASE_ROOT.child(NODE_PHONES_CONTACTS).child(CURRENT_UID)
//                    .child(snapshot.value.toString()).child(CHILD_ID)
//                    .setValue(snapshot.value.toString())
//                    .addOnFailureListener{ showToast(it.message.toString()) }
//            }
//        }
//
//        }
//    })
//}


