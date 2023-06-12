package com.example.planer_diplom.presentation.status

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentStatusBinding
import com.example.planer_diplom.domain.models.WorkerItem
import com.example.planer_diplom.presentation.MainActivity
import com.example.planer_diplom.presentation.register.RegisterActivity
import com.example.planer_diplom.presentation.task_list.TaskListAdapter
import com.example.planer_diplom.presentation.worker_list.fragments.ChangeNameFragment
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AUTH
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.CHILD_ID
import com.example.planer_diplom.utilits.CHILD_PHONE
import com.example.planer_diplom.utilits.CHILD_WORKER_STATUS
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.NODE_CODE_MANAGER
import com.example.planer_diplom.utilits.NODE_PHONES
import com.example.planer_diplom.utilits.NODE_PHONES_ID
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.getCommonModel
import com.example.planer_diplom.utilits.logD
import com.example.planer_diplom.utilits.replaceActivity
import com.example.planer_diplom.utilits.reversStatus
import com.example.planer_diplom.utilits.showToast
import com.example.planer_diplom.utilits.toChangeVisibility
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class StatusFragment(private val status: Boolean) : Fragment() {
    private lateinit var binding: FragmentStatusBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStatusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (status) {
            binding.tvStatusExit.visibility = View.VISIBLE
            binding.etStatusCode.visibility = View.GONE
            binding.btnOk.setOnClickListener {
                exitManager()
            }
        } else {
            binding.tvStatusExit.visibility = View.GONE
            binding.etStatusCode.visibility = View.VISIBLE
            binding.btnOk.setOnClickListener {
                logInManager()
            }
        }
    }


    private fun exitManager() {
        APP_ACTIVITY.reversFun()
        parentFragmentManager.popBackStack()
    }


    private fun logInManager() {
        val code = binding.etStatusCode.text.toString()

        REF_DATABASE_ROOT.child(NODE_CODE_MANAGER)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        if (code == snapshot.value.toString()) {
                            APP_ACTIVITY.reversFun()
                            logD("${code == snapshot.value.toString()}")
                            parentFragmentManager.popBackStack()
                        } else{
                            showToast("Неверный пароль")
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    logD(error.message)
                }

            })

    }
}
