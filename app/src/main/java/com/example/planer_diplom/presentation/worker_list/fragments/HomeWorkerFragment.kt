package com.example.planer_diplom.presentation.worker_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentHomeWorkerBinding
import com.example.planer_diplom.utilits.WORKER


class HomeWorkerFragment : Fragment() {
    private lateinit var binding: FragmentHomeWorkerBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeWorkerBinding.inflate(layoutInflater)
        initFields()
        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

        binding.ibtnEditWorker.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activityNavHost,
                    ChangeNameFragment()
                ).commit()
        }
    }

    private fun initFields() {
        binding.tvLastName.text = WORKER.lastName
        binding.tvName.text = WORKER.firstName
        binding.tvPatronymic.text = WORKER.patronymic
        binding.tvPhone.text = WORKER.phone
    }
}