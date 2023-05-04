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
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.UID
import com.example.planer_diplom.utilits.WORKER

class ChangeNameFragment : Fragment() {
    private lateinit var binding: FragmentChangeNameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeNameBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onStart() {
        super.onStart()
        binding.ibtnSave.setOnClickListener {
            changeName()
        }
    }

    private fun changeName() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()
        if (firstName.isEmpty()) {
            Toast.makeText(activity, getString(R.string.enterName), Toast.LENGTH_SHORT).show()
        } else {
            val fio = "$lastName ${firstName[0]}.${patronymic[0]}"
            REF_DATABASE_ROOT.child(NODE_WORKERS).child(UID).child(CHILD_WORKER_FIO)
                .setValue(fio).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "Данные обновлены", Toast.LENGTH_SHORT).show()
                        WORKER.fio = fio
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}


