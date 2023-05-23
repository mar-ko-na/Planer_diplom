package com.example.planer_diplom.presentation.worker_list

import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemWorkerBinding
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.presentation.worker_list.WorkerListAdapter.*

class WorkerItemViewHolder (binding: ItemWorkerBinding) : RecyclerView.ViewHolder(binding.root) {
    val fioWorker = binding.tvFioWorker
    val workerPhone = binding.tvPhone


}