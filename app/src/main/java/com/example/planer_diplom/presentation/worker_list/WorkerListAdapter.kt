package com.example.planer_diplom.presentation.worker_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemWorkerBinding
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.utilits.toChangeVisibility

//class WorkerListAdapter(private val workerList: ArrayList<CommonModel>) :
class WorkerListAdapter(private val workerList: ArrayList<CommonModel>, private val listener: Listener) :
    RecyclerView.Adapter<WorkerItemViewHolder>() {



//    var onWorkerItemClickListener: ((CommonModel) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerItemViewHolder {
        val binding = ItemWorkerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WorkerItemViewHolder(binding)
    }


    override fun getItemCount(): Int = workerList.size

    override fun onBindViewHolder(holder: WorkerItemViewHolder, position: Int) {
        val binding = holder.binding
        val currentItem = workerList[position]



//        binding.tvFioWorker.text = currentItem.fio
//        binding.tvPhone.text = currentItem.phone
//        binding.tvWorkerLastName.text = currentItem.lastName
//        binding.tvWorkerName.text = currentItem.firstName
//        binding.tvWorkerPartonymic.text = currentItem.patronymic
        holder.tvFioWorker.text = currentItem.fio
        holder.tvPhone.text = currentItem.phone
        holder.tvWorkerLastName.text = currentItem.lastName
        holder.tvWorkerName.text = currentItem.firstName
        holder.tvWorkerPartonymic.text = currentItem.patronymic


//        when (val binding = holder.binding) {
//            is ItemWorkerBinding -> {
//                binding.tvFioWorker.text = currentItem.name
//                binding.tvPhone.text = currentItem.phone
//            }
//            is ItemWorkerFullBinding -> {
//                binding.tvWorkerPartonymic.text = currentItem.patronymic
//                binding.tvWorkerName.text = currentItem.firstName
//                binding.tvWorkerLastName.text = currentItem.lastName
//                binding.tvPhone.text = currentItem.phone
//            }
//        }
//        holder.itemView.setOnClickListener {
//            listener.onClick(currentItem)
//        }

        binding.root.setOnClickListener {
            holder.tvFioWorker.visibility = toChangeVisibility(binding.tvFioWorker)
            holder.llFullName.visibility = toChangeVisibility(binding.llFullName)
        }

    }

    interface Listener {
        fun onClick(item: CommonModel)
    }
}
