package com.example.planer_diplom.data

import androidx.navigation.Navigation
import com.example.planer_diplom.R
import com.example.planer_diplom.domain.models.WorkerItem
import com.example.planer_diplom.presentation.worker_list.fragments.ChangeNameFragment
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.WORKER

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
                    .replace(R.id.activityNavHost,
                        ChangeNameFragment()
                    ).commit()
            }
            function()
        })
}