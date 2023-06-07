package com.example.planer_diplom.presentation.worker_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ItemWorkerBinding
import com.example.planer_diplom.databinding.ItemWorkerFullBinding
import com.example.planer_diplom.domain.models.CommonModel
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.domain.models.WorkerItem

//class WorkerListAdapter(private val workerList: ArrayList<CommonModel>) :
class WorkerListAdapter(private val workerList: ArrayList<CommonModel>, val listener: Listener) :
    RecyclerView.Adapter<WorkerItemViewHolder>() {
    private lateinit var binding: ItemWorkerBinding


//    var onWorkerItemClickListener: ((CommonModel) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerItemViewHolder {
        val binding = ItemWorkerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WorkerItemViewHolder(binding)
    }

    override fun getItemCount(): Int = workerList.size

    override fun onBindViewHolder(holder: WorkerItemViewHolder, position: Int) {
        val currentItem = workerList[position]

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
        holder.itemView.setOnClickListener {
            listener.onClick(currentItem)

            binding.tvWorkerLastName.visibility = toChangeVisibility(binding.tvWorkerLastName)
            binding.tvWorkerLastName.visibility = toChangeVisibility(binding.tvWorkerLastName)
            binding.tvWorkerName.visibility = toChangeVisibility(binding.tvWorkerLastName)
            binding.tvWorkerPartonymic.visibility = toChangeVisibility(binding.tvWorkerLastName)
            binding.tvFioWorker.visibility = toChangeVisibility(binding.tvWorkerLastName)
        }

//        holder.binding.root.setOnClickListener {
//            onWorkerItemClickListener?.invoke(currentItem)
//
//        }

    }

    private fun toChangeVisibility(element: TextView): Int {
        return if (element.isVisible){
            View.GONE
        }else View.VISIBLE
    }

    companion object {

        const val VIEW_TYPE_NORMAL = 100
        const val VIEW_TYPE_FULL = 101
    }

    interface Listener {
        fun onClick(item: CommonModel)
    }
}
