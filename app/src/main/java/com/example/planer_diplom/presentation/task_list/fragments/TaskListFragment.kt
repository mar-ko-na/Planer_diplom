package com.example.planer_diplom.presentation.task_list.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.FragmentTaskListBinding
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.presentation.MainActivity
import com.example.planer_diplom.presentation.task_list.TaskItemActivity
import com.example.planer_diplom.presentation.task_list.TaskListAdapter
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.getCommonTaskModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding

    //   private var launcher: ActivityResultLauncher<Intent>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskListAdapter: TaskListAdapter
    private lateinit var taskArrayList: ArrayList<CommonModel>
//    private var adapter: RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>? = null
//    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            result: ActivityResult ->
//            if(result.resultCode == RESULT_OK){
//                val text = result.data?.getStringExtra("key1")
//            }
//        }
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        recyclerView = binding.rvTaskList
//        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
//        recyclerView.setHasFixedSize(true)
        taskArrayList = ArrayList()
        getTaskList()
//        binding.rvTaskList.apply {
//            layoutManager = LinearLayoutManager(activity)
////            adapter = TaskListAdapter()
//        }
//        binding.fabAddTask.setOnClickListener {
//            APP_ACTIVITY.replaceActivity(TaskItemActivity())
//        }
        binding.fabAddTask.setOnClickListener {
            val intent = TaskItemActivity.newIntentAddItem(activity as MainActivity)
            startActivity(intent)
        }

    }

    private fun getTaskList() {

        REF_DATABASE_ROOT.child(NODE_TASKS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val task = userSnapshot.getCommonTaskModel()
                        taskArrayList.add(task)
                    }
//                    workerListAdapter = WorkerListAdapter(workersArrayList)
//                    taskListAdapter = TaskListAdapter(taskArrayList)
//                    recyclerView.adapter = taskListAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MyLog", error.message)
            }

        })

    }


}