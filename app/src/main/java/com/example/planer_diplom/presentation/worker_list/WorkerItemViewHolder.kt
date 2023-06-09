package com.example.planer_diplom.presentation.worker_list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemWorkerBinding

class WorkerItemViewHolder(val binding: ItemWorkerBinding) : RecyclerView.ViewHolder(binding.root) {
    val tvPhone = binding.tvPhone
    val tvWorkerLastName = binding.tvWorkerLastName
    val tvWorkerName = binding.tvWorkerName
    val tvWorkerPartonymic = binding.tvWorkerPartonymic
    val tvFioWorker = binding.tvFioWorker
    val llFullName = binding.llFullName
}

//class WorkerItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
//
//}