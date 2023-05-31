package com.example.planer_diplom.presentation.worker_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentHomeWorkerBinding
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.replaceFragment


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
//        binding.ibtnEditWorker.setOnClickListener {
//            replaceFragment(ChangeNameFragment())
//        }

//        binding.ibtnEditWorker.setOnClickListener {view ->
//            view.findNavController().navigate(R.id.)
//        }
        binding.ibtnEditWorker.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.changeNameFragment, null))
    }

    private fun initFields() {
        binding.tvLastName.text = WORKER.lastName
        binding.tvName.text = WORKER.firstName
        binding.tvPatronymic.text = WORKER.patronymic
        binding.tvPhone.text = WORKER.phone
    }
}