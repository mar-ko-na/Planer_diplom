package com.example.planer_diplom.presentation.task_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemTaskBinding
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.domain.models.TaskItem

class TaskListAdapter(private val taskList: ArrayList<CommonModel>) :
    RecyclerView.Adapter<TaskItemViewHolder>() {

    private lateinit var binding: ItemTaskBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskItemViewHolder(binding)
    }

    override fun getItemCount(): Int = taskList.size


    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.tvTaskName.text = currentItem.name
        holder.tvWorkerName.text = currentItem.workerName
        holder.cbEnabled.isChecked = currentItem.enabled

//        holder.itemView.setOnClickListener {
//            listener.onClick(currentItem)
//        }

    }



}