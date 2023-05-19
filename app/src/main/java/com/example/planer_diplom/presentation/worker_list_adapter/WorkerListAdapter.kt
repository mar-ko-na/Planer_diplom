package com.example.planer_diplom.presentation.worker_list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemWorkerBinding
import com.example.planer_diplom.domain.models.WorkerItem

class WorkerListAdapter(private val workerList: ArrayList<WorkerItem>) :
    RecyclerView.Adapter<WorkerItemViewHolder>() {

    private lateinit var binding: ItemWorkerBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerItemViewHolder {
        binding = ItemWorkerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WorkerItemViewHolder(binding)
    }

    override fun getItemCount(): Int = workerList.size

    override fun onBindViewHolder(holder: WorkerItemViewHolder, position: Int) {
        val currentItem = workerList[position]
        holder.fioWorker.text = currentItem.fio
        holder.workerPhone.text = currentItem.phone

        holder.itemView.setOnClickListener {

        }

    }
}
