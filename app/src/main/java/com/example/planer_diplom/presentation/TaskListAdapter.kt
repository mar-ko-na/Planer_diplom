package com.example.planer_diplom.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ItemTaskBinding
import com.example.planer_diplom.domain.TaskItem

class TaskListAdapter: RecyclerView.Adapter<TaskItemViewHolder>() {

    var onTaskCheckBoxClickListener: ((TaskItem) -> Unit)? = null

    var taskList = listOf<TaskItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskItemViewHolder(binding)
    }

    override fun getItemCount(): Int = VIEW_TYPE_TASK

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {

        val taskItem = taskList[position]
        with(holder.binding){
            tvTaskName.text = taskItem.name
            tvNameWorker.text = taskItem.worker
            cbEnabled.isChecked= taskItem.enabled

            cbEnabled.setOnCheckedChangeListener(null)
            cbEnabled.isChecked = taskItem.enabled
            cbEnabled.setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("MyLog", "cb is checked: $isChecked")
                onTaskCheckBoxClickListener?.invoke(taskItem)

        }
        }
    }

    companion object{
        const val VIEW_TYPE_TASK = 100
    }
}