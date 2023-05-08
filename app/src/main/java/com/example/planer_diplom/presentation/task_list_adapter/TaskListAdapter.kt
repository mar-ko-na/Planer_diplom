package com.example.planer_diplom.presentation.task_list_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemTaskBinding

class TaskListAdapter: RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>() {
    private lateinit var binding: ItemTaskBinding

    private val name = arrayOf("Иван",
    "Дима", "Жора", "Петр",
    "Коля", "Никита", "Елена",
    "Катя")

    private val worker = arrayOf("Kekayaan", "Teknologi",
        "Keluarga", "Bisnis",
        "Keluarga", "Hutang",
        "Teknologi", "Pidana")

    private val enabled = arrayOf(true, true,
        false, true,
        false, false,
        true, false)

    private val id = arrayOf(24365, 5643,
        563543, 7856,
        5632, 8796,
        4683, 6578)


    class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
       binding = ItemTaskBinding.inflate(
           LayoutInflater.from(parent.context), parent, false)
        return TaskItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int = id.size


    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        binding.tvTaskName.text = name[position]
        binding.tvNameWorker.text = worker[position]
        binding.cbEnabled.isChecked = enabled[position]

    }

}