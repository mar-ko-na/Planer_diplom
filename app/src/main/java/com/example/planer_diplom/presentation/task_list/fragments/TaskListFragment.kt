package com.example.planer_diplom.presentation.task_list.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentTaskListBinding
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.domain.models.WorkerStatus.Companion.S_MANAGER
import com.example.planer_diplom.presentation.MainActivity
import com.example.planer_diplom.presentation.task_list.TaskItemActivity
import com.example.planer_diplom.presentation.task_list.TaskListAdapter
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.getCommonModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskListAdapter: TaskListAdapter
    private lateinit var taskArrayList: ArrayList<CommonModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvTaskList
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)
        taskArrayList = ArrayList()
        Log.d("MyLog", "status in onViewCreated ${WORKER.managerStatus.toString()}")
        if (WORKER.managerStatus) {
            binding.fabAddTask.visibility = View.VISIBLE
        } else binding.fabAddTask.visibility = View.GONE
        CoroutineScope(Dispatchers.IO).launch {
            getTaskList()
        }
    }


    private fun hideImg(list: ArrayList<CommonModel>) {
        Log.d("MyLog", list.size.toString())
        if (list.size == 0) {
            binding.tvNoTask.visibility = View.VISIBLE
            binding.imgCreateTask.visibility = View.VISIBLE
        } else {
            binding.tvNoTask.visibility = View.GONE
            binding.imgCreateTask.visibility = View.GONE
        }
    }

    private fun getTaskList() {

        REF_DATABASE_ROOT.child(NODE_TASKS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val task = userSnapshot.getCommonModel()
                        taskArrayList.add(task)
                    }
                    hideImg(taskArrayList)
                    taskListAdapter = TaskListAdapter(taskArrayList)
                    recyclerView.adapter = taskListAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MyLog", error.message)
            }

        })

    }
}