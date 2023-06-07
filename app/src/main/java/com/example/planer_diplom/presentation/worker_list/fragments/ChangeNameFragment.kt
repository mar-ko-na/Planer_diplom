package com.example.planer_diplom.presentation.worker_list.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentChangeNameBinding
import com.example.planer_diplom.utilits.CHILD_WORKER_FIO
import com.example.planer_diplom.utilits.CHILD_WORKER_FIRSTNAME
import com.example.planer_diplom.utilits.CHILD_WORKER_FULLNAME
import com.example.planer_diplom.utilits.CHILD_WORKER_LASTNAME
import com.example.planer_diplom.utilits.CHILD_WORKER_PATRONYMIC
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.NODE_FIO_ID
import com.example.planer_diplom.utilits.NODE_PHONES
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.showToast

class ChangeNameFragment : Fragment() {
    private lateinit var binding: FragmentChangeNameBinding

    override fun onResume() {
        super.onResume()
        binding.ibtnSave.setOnClickListener {
            changeName()
        }

        binding.etFirstName.setText(WORKER.firstName)
        binding.etLastName.setText(WORKER.lastName)
        binding.etPatronymic.setText(WORKER.patronymic)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeNameBinding.inflate(layoutInflater)
        return binding.root


    }

    private fun changeName() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()
        if (firstName.isEmpty() or lastName.isEmpty() or patronymic.isEmpty()) {
            showToast(getString(R.string.allFields))
        } else {
            val fio = "$lastName ${firstName[0]} ${patronymic[0]}"
            REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID).child(CHILD_WORKER_FIO)
                .setValue(fio).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Данные обновлены")
                        WORKER.fio = fio
                        parentFragmentManager.popBackStack()
                    }
                }
            REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID).child(CHILD_WORKER_FIRSTNAME)
                .setValue(firstName).addOnCompleteListener {
                    if (it.isSuccessful) {
                        WORKER.firstName = firstName
                    }
                }
            REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID).child(CHILD_WORKER_LASTNAME)
                .setValue(lastName).addOnCompleteListener {
                    if (it.isSuccessful) {
                        WORKER.lastName = lastName
                    }
                }
            REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID).child(CHILD_WORKER_PATRONYMIC)
                .setValue(patronymic).addOnCompleteListener {
                    if (it.isSuccessful) {
                        WORKER.patronymic = patronymic
                    }
                }

            REF_DATABASE_ROOT.child(NODE_FIO_ID).child(fio).setValue(CURRENT_UID)
                .addOnFailureListener {
                    Toast.makeText(activity, it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                    Log.d("MyLog", it.message.toString())
                }
        }
    }
}


