package com.example.planer_diplom.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ActivityMainBinding
import com.example.planer_diplom.databinding.FragmentTaskListBinding
import com.example.planer_diplom.databinding.ItemTaskBinding
import com.example.planer_diplom.domain.TaskItem

class TaskListAdapter: RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>() {
    private lateinit var binding: ItemTaskBinding
//    private var listTask = emptyList<TaskItem>()
    private val name = arrayOf("d116df5",
    "36ffc75", "f5cfe78", "5b87628",
    "db8d14e", "9913dc4", "e120f96",
    "466251b")

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
//           binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        val binding = DataBindingUtil.inflate<ViewDataBinding>(
//            LayoutInflater.from(parent.context),
//            layout,
//            parent,
//            false
//        )
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_task, parent, false)
        return TaskItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int = id.size


    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        binding.tvTaskName.text = name[position]
        binding.tvNameWorker.text = worker[position]

    }

//    fun setList(list: List<TaskItem>){
//        listTask = list
//        notifyDataSetChanged()
//    }


}