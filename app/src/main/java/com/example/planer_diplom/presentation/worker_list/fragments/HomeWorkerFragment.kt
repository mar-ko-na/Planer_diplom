package com.example.planer_diplom.presentation.worker_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentHomeWorkerBinding
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.getCommonModel


class HomeWorkerFragment  : Fragment() {
    private lateinit var binding: FragmentHomeWorkerBinding
    private var idWorker =  -1


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
        idWorker = arguments?.getInt(TaskListFragment.ID_WORKER) ?: -1
    }

    override fun onResume() {
        super.onResume()
//        binding.ibtnEditWorker.setOnClickListener {
//            replaceFragment(ChangeNameFragment())
//        }

//        binding.ibtnEditWorker.setOnClickListener {view ->
//            view.findNavController().navigate(R.id.)
//        }
        binding.ibtnEditWorker.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.changeNameFragment, null)
        )
    }

    private fun initFields() {
        if (idWorker == -1){
            binding.tvLastName.text = WORKER.lastName
            binding.tvName.text = WORKER.firstName
            binding.tvPatronymic.text = WORKER.patronymic
            binding.tvPhone.text = WORKER.phone
        }else{
            REF_DATABASE_ROOT.child(NODE_WORKERS).child(idWorker.toString()).addValueEventListener(
                AppValueEventListener {
                    val worker = it.getCommonModel()

                    binding.tvLastName.text = worker.lastName
                    binding.tvName.text = worker.firstName
                    binding.tvPatronymic.text = worker.patronymic
                    binding.tvPhone.text = worker.phone
                }
            )
        }

    }
}