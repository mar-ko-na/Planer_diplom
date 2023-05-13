package com.example.planer_diplom.presentation.worker_list_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.ItemTaskBinding
import com.example.planer_diplom.databinding.ItemWorkerBinding

class WorkerListAdapter: RecyclerView.Adapter<WorkerListAdapter.WorkerItemViewHolder>() {
    private lateinit var binding: ItemWorkerBinding
    //    private var listTask = emptyList<TaskItem>()
    private val firstName = arrayOf("d116df5",
        "36ffc75", "f5cfe78", "5b87628",
        "db8d14e", "9913dc4", "e120f96",
        "466251b")

    private val lastName = arrayOf("Kekayaan", "Teknologi",
        "Keluarga", "Bisnis",
        "Keluarga", "Hutang",
        "Teknologi", "Pidana")

    private val patronymic = arrayOf("Kekayaan", "Teknologi",
        "Keluarga", "Bisnis",
        "Keluarga", "Hutang",
        "Teknologi", "Pidana")
//    val fio: String = "$lastname ${firstname[0]}.${patronymic[0]}"

    fun fio(lastName: String, firstName: String, patronymic: String) = "$lastName ${firstName[0]}.${patronymic[0]}"


    private val number = arrayOf(24365, 5643,
        563543, 7856,
        5632, 8796,
        4683, 6578)
    private val id = arrayOf(24365, 5643,
        563543, 7856,
        5632, 8796,
        4683, 6578)


    class WorkerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerItemViewHolder {
        binding = ItemWorkerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return WorkerItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int = id.size


    override fun onBindViewHolder(holder: WorkerItemViewHolder, position: Int) {
//        [position]
        binding.tvFioWorker.text = fio(lastName[position], firstName[position], patronymic[position])
        binding.tvPhone.text = number[position].toString()

    }

//    fun setList(list: List<TaskItem>){
//        listTask = list
//        notifyDataSetChanged()
//    }


}