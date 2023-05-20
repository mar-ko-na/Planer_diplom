package com.example.planer_diplom.presentation.task_list

import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemTaskBinding

class TaskItemViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){
    val tvTaskName = binding.tvTaskName
    val tvWorkerName = binding.tvNameWorker
    val cbEnabled = binding.cbEnabled
}