package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentChangeNameBinding
import com.example.planer_diplom.utilits.CHILD_WORKER_FIO
import com.example.planer_diplom.utilits.CHILD_WORKER_FIRSTNAME
import com.example.planer_diplom.utilits.CHILD_WORKER_LASTNAME
import com.example.planer_diplom.utilits.CHILD_WORKER_PATRONYMIC
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.WORKER

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
        var patronymic = binding.etPatronymic.text.toString()
        if (firstName.isEmpty() and lastName.isEmpty() and patronymic.isEmpty()) {
            Toast.makeText(activity, getString(R.string.enterName), Toast.LENGTH_SHORT).show()
        } else if (lastName.isEmpty()){
            Toast.makeText(activity, getString(R.string.enterLastName), Toast.LENGTH_SHORT).show()
        } else if (patronymic.isEmpty()){
            patronymic = ""
        } else {
            val fio = "$lastName ${firstName[0]}.${patronymic[0]}"
            REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID).child(CHILD_WORKER_FIO)
                .setValue(fio).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "Данные обновлены", Toast.LENGTH_SHORT).show()
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
                        WORKER.patronymic = patronymic

                }
        }
    }
}


