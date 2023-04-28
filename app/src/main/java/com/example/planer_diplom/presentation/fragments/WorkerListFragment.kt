package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.FragmentWorkerListBinding
import com.example.planer_diplom.presentation.task_list_adapter.TaskListAdapter
import com.example.planer_diplom.presentation.worker_list_adapter.WorkerListAdapter

class WorkerListFragment : Fragment() {
    private lateinit var binding: FragmentWorkerListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkerListBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvWorkerList.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = WorkerListAdapter()
        }
    }


}