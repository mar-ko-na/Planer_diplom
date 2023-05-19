package com.example.planer_diplom.presentation.worker_list_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ItemBinding
import com.example.planer_diplom.domain.models.WorkerItem

class WorkersAdapter (private val userList : ArrayList<WorkerItem>): RecyclerView.Adapter<WorkersAdapter.MyHolder>() {
    private lateinit var binding: ItemBinding

    class MyHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        val phone = binding.tvPhoneinItem
        val fio = binding.nameinItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task,
//            parent, false)
        binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentItem = userList[position]
        holder.phone.text = currentItem.phone
        holder.fio.text = currentItem.firstName
    }

}