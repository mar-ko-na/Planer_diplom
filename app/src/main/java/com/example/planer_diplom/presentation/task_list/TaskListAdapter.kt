package com.example.planer_diplom.presentation.task_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ItemTaskBinding
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment.Companion.ID_SELECTED

class TaskListAdapter(private val taskList: ArrayList<TaskItem>, val workerStatus: Boolean) :
    RecyclerView.Adapter<TaskItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskItemViewHolder(binding)
    }

    override fun getItemCount(): Int = taskList.size


    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val currentItem = taskList[position]
        val binding = holder.binding
        holder.tvTaskName.text = currentItem.name
        holder.tvWorkerName.text = currentItem.workerName
        if (position != 0){
            if (currentItem.workerName == taskList[position - 1].workerName) {
                binding.nameWorker.visibility = View.GONE
            }
        }


        val bundle = Bundle()
        bundle.putInt(ID_SELECTED, currentItem.id)
        binding.root.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.taskItemFragment, bundle)
        )

//        for (i in 0..taskList.size) {
//            if (taskList[i].workerName == taskList[i + 1].workerName) {
//                taskList[i + 1].workerName
//            }
//        }

//        holder.cbEnabled.isChecked = currentItem.enabled

//        holder.itemView.setOnClickListener {
//            listener.onClick(currentItem)
//        }

    }


}