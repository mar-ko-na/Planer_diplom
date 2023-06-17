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
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.presentation.task_list.TaskListAdapter
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.CHILD_TASK_WORKER
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKER_TASK
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.getCommonModel
import com.example.planer_diplom.utilits.getTaskModel
import com.example.planer_diplom.utilits.logD
import com.example.planer_diplom.utilits.replaceFragmentNav
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
//    private lateinit var taskArrayList: ArrayList<TaskItem>
//    private lateinit var taskIdArray: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFab()
        recyclerView = binding.rvTaskList
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)
        initInterface(recyclerView)
//        taskArrayList = ArrayList()
//        taskIdArray = arrayOf()



    }

    private fun initFab() {
        if (WORKER.managerStatus) {
            binding.fabAddTask.visibility = View.VISIBLE
        } else {
            binding.fabAddTask.visibility = View.GONE
        }
    }

    fun initInterface(recyclerView: RecyclerView){
        if (WORKER.managerStatus) {
            getTaskList(recyclerView)
//            binding.fabAddTask.visibility = View.VISIBLE
        } else {
            getTaskListWorker(recyclerView, WORKER.fio)
//            binding.fabAddTask.visibility = View.GONE
        }
    }


    override fun onResume() {
        super.onResume()

        val bundle = Bundle()
        bundle.putBoolean(ID_ADD, true)
        binding.fabAddTask.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.taskEditFragment, bundle)
        )

    }


    private fun hideImg(list: ArrayList<TaskItem>) {
        if (list.size == 0) {
            binding.tvNoTask.visibility = View.VISIBLE
            binding.imgCreateTask.visibility = View.VISIBLE
        } else {
            binding.tvNoTask.visibility = View.GONE
            binding.imgCreateTask.visibility = View.GONE
        }

    }

    //    private fun getTaskList() {
//
//        REF_DATABASE_ROOT.child(NODE_TASKS).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (userSnapshot in snapshot.children) {
//                        val task = userSnapshot.getTaskModel()
//                        taskArrayList.add(task)
//                    }
//                    hideImg(taskArrayList)
//                    taskListAdapter = TaskListAdapter(taskArrayList)
//                    recyclerView.adapter = taskListAdapter
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("MyLog", error.message)
//            }
//
//        })
//
//    }
//
    private fun getTaskList(recyclerView: RecyclerView) {
        var taskIdArray : Array<String> = arrayOf()
        val taskArrayList: ArrayList<TaskItem> = ArrayList()
        REF_DATABASE_ROOT.child(NODE_WORKER_TASK)
            .addValueEventListener(AppValueEventListener { node ->
                for (taskSnapshot in node.children) {
                    for (idSnapshot in taskSnapshot.children) {
                        val taskId = idSnapshot.value.toString()
                        taskIdArray += taskId
                    }
                }
                for (id in taskIdArray) {
                    REF_DATABASE_ROOT.child(NODE_TASKS).child(id)
                        .addValueEventListener(AppValueEventListener { it ->
                            val task = it.getTaskModel()
                            taskArrayList.add(task)
//                            hideImg(taskArrayList)
                            taskListAdapter = TaskListAdapter(taskArrayList, WORKER.managerStatus)
                            recyclerView.adapter = taskListAdapter
                        })
                }
            })
    }

    private fun getTaskListWorker(recyclerView: RecyclerView, workerName: String?) {
        var taskIdArray : Array<String> = arrayOf()
        val taskArrayList: ArrayList<TaskItem> = ArrayList()
        REF_DATABASE_ROOT.child(NODE_WORKER_TASK).child(workerName.toString())
            .addValueEventListener(AppValueEventListener { node ->
                for (idSnapshot in node.children) {
                    val taskId = idSnapshot.value.toString()
                    taskIdArray += taskId
                }

                for (id in taskIdArray) {
                    REF_DATABASE_ROOT.child(NODE_TASKS).child(id)
                        .addValueEventListener(AppValueEventListener { it ->
                            val task = it.getTaskModel()
                            taskArrayList.add(task)
//                            hideImg(taskArrayList)
                            taskListAdapter = TaskListAdapter(taskArrayList, WORKER.managerStatus)
                            recyclerView.adapter = taskListAdapter
                        })
                }
            })
    }

    //    private fun getTaskList() {
//
//        REF_DATABASE_ROOT.child(NODE_TASKS).addValueEventListener(
//            object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (userSnapshot in snapshot.children) {
//                        val task = userSnapshot.getCommonModel()
//                        taskArrayList.add(task)
//                    }
//                    hideImg(taskArrayList)
//                    taskListAdapter = TaskListAdapter(taskArrayList)
//                    recyclerView.adapter = taskListAdapter
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("MyLog", error.message)
//            }
//
//        })
//
//    }
    companion object {
        const val ID_EDIT = "idEdit"
        const val ID_SELECTED = "idSelected"
        const val ID_ADD = "idAdd"
    }
}