package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.FragmentTaskListBinding
import com.example.planer_diplom.presentation.task_list_adapter.TaskListAdapter

class TaskListFragment : Fragment() {
   private lateinit var binding: FragmentTaskListBinding
    private var adapter: RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTaskList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TaskListAdapter()
        }
    }




}