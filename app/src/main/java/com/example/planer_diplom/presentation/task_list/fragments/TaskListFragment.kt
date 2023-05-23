package com.example.planer_diplom.presentation.task_list.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planer_diplom.databinding.FragmentTaskListBinding
import com.example.planer_diplom.presentation.task_list.TaskItemActivity
import com.example.planer_diplom.presentation.task_list.TaskListAdapter
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.replaceActivity

class TaskListFragment : Fragment() {
   private lateinit var binding: FragmentTaskListBinding
//    private var adapter: RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>? = null
//    private var layoutManager: RecyclerView.LayoutManager? = null

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
        binding.fabAddTask.setOnClickListener {
            APP_ACTIVITY.replaceActivity(TaskItemActivity())
        }

    }




}