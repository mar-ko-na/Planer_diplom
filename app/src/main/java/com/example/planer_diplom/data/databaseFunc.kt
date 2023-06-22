package com.example.planer_diplom.data

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.domain.models.WorkerItem
import com.example.planer_diplom.presentation.MainActivity
import com.example.planer_diplom.presentation.task_list.TaskListAdapter
import com.example.planer_diplom.presentation.worker_list.fragments.ChangeNameFragment
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.NODE_WORKER_TASK
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.TASK_LIST
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.WORKER_TASK_LIST
import com.example.planer_diplom.utilits.getTaskModel
import com.example.planer_diplom.utilits.logD
import com.example.planer_diplom.utilits.showToast
import kotlin.reflect.typeOf

inline fun initWorker(crossinline function: () -> Unit) {
    /* Функция высшего порядка, инициализация текущей модели USER */
    REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            WORKER =
                it.getValue(WorkerItem::class.java)
                    ?: WorkerItem()
            if (WORKER.firstName == "") {
                APP_ACTIVITY.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(
                        R.id.activityNavHost,
                        ChangeNameFragment()
                    ).commit()
            }
            function()
        })
}

inline fun getTaskList(crossinline function: () -> Unit) {
    var taskIdArray: Array<String> = arrayOf()
//    val list = arrayListOf<TaskItem>()
    REF_DATABASE_ROOT.child(NODE_WORKER_TASK)
        .addListenerForSingleValueEvent(AppValueEventListener { node ->
            for (taskSnapshot in node.children) {
                for (idSnapshot in taskSnapshot.children) {
                    val taskId = idSnapshot.value.toString()
                    taskIdArray += taskId
                }
            }
            for (id in taskIdArray) {
                REF_DATABASE_ROOT.child(NODE_TASKS)
                    .child(id)
                    .addValueEventListener(AppValueEventListener { it ->
                        logD("AppVEL $it")
                            val task = it.getTaskModel()
//                            TTASK_LIST.distinctBy { it.name to it.name }
//                            list.add(task)st.distinctBy { it.name to it.name }
//                            list.distinctBy { it.name to it.name }
                            TASK_LIST.add(task)
                            TASK_LIST = TASK_LIST.distinctBy { it.name to it.name } as ArrayList<TaskItem>
                            logD("b $TASK_LIST")
                            logD("bo ${TASK_LIST.distinctBy { it.name to it.name } as ArrayList<TaskItem>}")

//                            logD(TASK_LIST.toString())
//                        logD("W manager stutus ${WORKER.managerStatus}")
//                        if (!WORKER.managerStatus) {
//                            logD("fun")
////                            getWorkerTaskList()
//                        }
            function()
                    })
            }

        })

}

fun getWorkerTaskList(fio : String){
    var taskIdArray: Array<String> = arrayOf()
    logD("TL1 $TASK_LIST")

    REF_DATABASE_ROOT.child(NODE_WORKER_TASK).child(fio)
        .addValueEventListener(AppValueEventListener { node ->
            for (idSnapshot in node.children) {
                val taskId = idSnapshot.value.toString()
                taskIdArray += taskId
                logD(idSnapshot.toString())
            }


            for (id in taskIdArray) {
                val tid = id.toInt()
                logD("TL2 $TASK_LIST")
                logD(tid.toString())

                val i = TASK_LIST.indexOfFirst { it.id == tid }
                var task = TaskItem()
                if (i != -1) {
                    task = TASK_LIST[i]
                } else logD("ОШИБКА databaseFunc 91стр")

                WORKER_TASK_LIST.add(task)
            }
            TASK_LIST = WORKER_TASK_LIST.distinctBy { it.name to it.name } as ArrayList<TaskItem>
            logD(TASK_LIST.toString())
        })
}