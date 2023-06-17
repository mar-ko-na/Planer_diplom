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
import com.example.planer_diplom.databinding.ItemTaskBinding
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment.Companion.ID_EDIT
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment.Companion.ID_SELECTED
import com.example.planer_diplom.presentation.worker_list.WorkerListAdapter
import com.example.planer_diplom.presentation.worker_list.fragments.WorkerListFragment
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.getCommonModel
import com.example.planer_diplom.utilits.getTaskModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TaskItemFragment() : Fragment() {
    private lateinit var binding: FragmentTaskItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idSelected = arguments?.getInt(ID_SELECTED)

        val bundle = Bundle()
        bundle.putInt(ID_EDIT, idSelected ?: -1)

        binding.btnEditTask.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.taskEditFragment, bundle)
        )

        REF_DATABASE_ROOT.child(NODE_TASKS).child(idSelected.toString()).addValueEventListener(
            AppValueEventListener {
                val task = it.getTaskModel()
                initScreen(task)
            }
        )
    }

    private fun initScreen(task: TaskItem) {
        binding.tvTaskName.text = task.name
        binding.tvDescription.text = task.description
        val content = SpannableString(task.workerName)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.tvWorker.text = content

    }
}