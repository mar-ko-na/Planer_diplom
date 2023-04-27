package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.FragmentTaskListBinding
import com.example.planer_diplom.domain.TaskItem
import com.example.planer_diplom.presentation.TaskListAdapter

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
//        initial()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTaskList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TaskListAdapter()
        }
    }

    //    private fun initial() {
//        recyclerView = binding.rvTaskList
//        adapter = TaskListAdapter()
//        recyclerView.adapter = adapter
//        adapter.setList(myItems())
//    }
//
//    fun myItems(): ArrayList<TaskItem>{
//        val taskList = ArrayList<TaskItem>()
//
//        val taskItem1 = TaskItem("name1", "worker1", true, 1)
//        taskList.add(taskItem1)
//        val taskItem2 = TaskItem("name2", "worker2", true, 2)
//        taskList.add(taskItem2)
//        val taskItem3 = TaskItem("name3", "worker3", true, 3)
//        taskList.add(taskItem3)
//        val taskItem4 = TaskItem("name4", "worker4", true, 4)
//        taskList.add(taskItem4)
//        return taskList
//    }

    override fun onResume() {
        super.onResume()
    }

}