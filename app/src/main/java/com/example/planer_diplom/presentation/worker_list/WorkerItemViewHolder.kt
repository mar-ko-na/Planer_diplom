package com.example.planer_diplom.presentation.worker_list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.planer_diplom.databinding.ItemWorkerBinding
import com.example.planer_diplom.databinding.ItemWorkerFullBinding
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.domain.models.WorkerItem
import com.example.planer_diplom.presentation.worker_list.WorkerListAdapter.*

class WorkerItemViewHolder(binding: ItemWorkerBinding) : RecyclerView.ViewHolder(binding.root) {
            val tvPhone = binding.tvPhone
            val tvWorkerLastName = binding.tvWorkerLastName
            val tvWorkerName = binding.tvWorkerName
            val tvWorkerPartonymic = binding.tvWorkerPartonymic
            val tvFioWorker = binding.tvFioWorker
}