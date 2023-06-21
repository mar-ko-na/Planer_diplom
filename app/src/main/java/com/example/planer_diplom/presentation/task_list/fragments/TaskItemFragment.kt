package com.example.planer_diplom.presentation.task_list.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentTaskItemBinding
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment.Companion.ID_EDIT
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment.Companion.ID_SELECTED
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.NODE_ID_FIO
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKER_TASK
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.getTaskModel
import com.example.planer_diplom.utilits.logD
import com.example.planer_diplom.utilits.showToast

class TaskItemFragment() : Fragment() {
    private lateinit var binding: FragmentTaskItemBinding
    private var count = 0
    private var idSelected : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idSelected = arguments?.getInt(ID_SELECTED)

        if (!WORKER.managerStatus){
            binding.tvDelete.visibility = View.GONE
            binding.btnEditTask.visibility = View.GONE
        }


        clickListener()


        REF_DATABASE_ROOT.child(NODE_TASKS).child(idSelected.toString()).addValueEventListener(
            AppValueEventListener {
                val task = it.getTaskModel()

                binding.tvDelete.setOnClickListener {
                    ifDeleteTask(task)
                }
                initScreen(task)
            }
        )
    }

    private fun clickListener() {

        val bundleEdit = Bundle()
        bundleEdit.putInt(ID_EDIT, idSelected ?: -1)
        binding.btnEditTask.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.taskEditFragment, bundleEdit)
        )

        getWorkerId()


    }

    private fun getWorkerId(){

        REF_DATABASE_ROOT.child(NODE_ID_FIO).child(binding.tvWorker.text.toString()).addValueEventListener(
            AppValueEventListener{
//                val id =  it.getId()
                logD(it.value.toString())
                val bundleWorker = Bundle()
//                bundleWorker.putInt(ID_WORKER,id)
//                binding.root.setOnClickListener(
//                    Navigation.createNavigateOnClickListener(R.id.taskItemFragment, bundleWorker)
//                )
            }
        )
    }

    private fun ifDeleteTask( task: TaskItem) {
        if (task.enabled){
            deleteTask(task.id, task.workerName)
        }else{
            count++
            if (count<2) {
                showToast(getString(R.string.taskNoEnabled))
            }else deleteTask(task.id, task.workerName)
        }
    }

    private fun deleteTask(id : Int, workerName: String) {
            parentFragmentManager.popBackStack()
            REF_DATABASE_ROOT.child(NODE_TASKS).child(id.toString()).removeValue()
            REF_DATABASE_ROOT.child(NODE_WORKER_TASK).child(workerName).child(id.toString())
                .removeValue()
    }

    private fun initScreen(task: TaskItem) {
        binding.tvTaskName.text = task.name
        binding.tvDescription.text = task.description
        val content = SpannableString(task.workerName)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.tvWorker.text = content

    }
}