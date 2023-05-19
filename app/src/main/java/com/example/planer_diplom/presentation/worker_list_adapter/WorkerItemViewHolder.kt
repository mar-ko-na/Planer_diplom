package com.example.planer_diplom.presentation.worker_list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemWorkerBinding

class WorkerItemViewHolder (val binding: ItemWorkerBinding) : RecyclerView.ViewHolder(binding.root) {
    val fioWorker = binding.tvFioWorker
    val workerPhone = binding.tvPhone
}