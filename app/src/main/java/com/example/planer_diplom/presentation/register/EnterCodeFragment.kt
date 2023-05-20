package com.example.planer_diplom.presentation.register

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
import com.example.planer_diplom.databinding.FragmentEnterCodeBinding
import com.example.planer_diplom.domain.models.WorkerStatus
import com.example.planer_diplom.presentation.MainActivity
import com.example.planer_diplom.utilits.AUTH
import com.example.planer_diplom.utilits.CHILD_ID
import com.example.planer_diplom.utilits.CHILD_PHONE
import com.example.planer_diplom.utilits.CHILD_WORKER_FIRSTNAME
import com.example.planer_diplom.utilits.CHILD_WORKER_LASTNAME
import com.example.planer_diplom.utilits.CHILD_WORKER_PATRONYMIC
import com.example.planer_diplom.utilits.CHILD_WORKER_STATUS
import com.example.planer_diplom.utilits.NODE_PHONES
import com.example.planer_diplom.utilits.NODE_PHONES_ID
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.replaceActivity
import com.google.firebase.auth.PhoneAuthProvider

class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    private lateinit var binding: FragmentEnterCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterCodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        binding.etEnterCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val string = binding.etEnterCode.text.toString()
                if (string.length == 6) {
                    enterCode()
                }
            }

        })

    }

    private fun enterCode() {
        Toast.makeText(activity, "Ok", Toast.LENGTH_SHORT).show()
        val code = binding.etEnterCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_WORKER_PATRONYMIC] = uid
                dateMap[CHILD_WORKER_FIRSTNAME] = uid
                dateMap[CHILD_WORKER_LASTNAME] = uid
                dateMap[CHILD_WORKER_STATUS] = WorkerStatus.S_MANAGER

                REF_DATABASE_ROOT.child(NODE_PHONES).child(phoneNumber).setValue(uid)
                    .addOnFailureListener {
                        Toast.makeText(activity, it.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                        Log.d("MyLog", it.message.toString())
                    }
                    .addOnSuccessListener {

                        REF_DATABASE_ROOT.child(NODE_WORKERS).child(uid).updateChildren(dateMap)
                            .addOnSuccessListener {
                                Toast.makeText(activity, "Добро пожаловать", Toast.LENGTH_SHORT)
                                    .show()
                                (activity as RegisterActivity).replaceActivity(MainActivity())
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    activity, it.message.toString(), Toast.LENGTH_SHORT
                                ).show()
                                Log.d("MyLog", it.message.toString())
                            }
                    }
                REF_DATABASE_ROOT.child(NODE_PHONES_ID).child(uid).setValue(phoneNumber)
                    .addOnFailureListener {
                        Toast.makeText(
                            activity,
                            it.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("MyLog", it.message.toString())
                    }
            } else {
                Toast.makeText(activity, it.exception?.message.toString(), Toast.LENGTH_SHORT)
                    .show()
                Log.d("MyLog", it.exception?.message.toString())
            }
        }
    }
}