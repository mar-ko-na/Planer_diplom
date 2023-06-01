package com.example.planer_diplom.presentation.task_list.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planer_diplom.databinding.FragmentTaskListBinding
import com.example.planer_diplom.presentation.MainActivity
import com.example.planer_diplom.presentation.task_list.TaskItemActivity
import com.example.planer_diplom.presentation.task_list.TaskListAdapter
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.replaceActivity

class TaskListFragment : Fragment() {
   private lateinit var binding: FragmentTaskListBinding
   private var launcher: ActivityResultLauncher<Intent>? = null
//    private var adapter: RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>? = null
//    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
            if(result.resultCode == RESULT_OK){
                val text = result.data?.getStringExtra("key1")
            }
        }
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTaskList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TaskListAdapter()
        }
//        binding.fabAddTask.setOnClickListener {
//            APP_ACTIVITY.replaceActivity(TaskItemActivity())
//        }
        binding.fabAddTask.setOnClickListener {
            val intent = TaskItemActivity.newIntentAddItem(activity as MainActivity)
            startActivity(intent)
        }

    }





}