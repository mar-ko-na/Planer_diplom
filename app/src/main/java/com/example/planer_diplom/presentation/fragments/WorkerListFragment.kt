package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentWorkerListBinding
import com.example.planer_diplom.domain.WorkerItem
import com.example.planer_diplom.presentation.task_list_adapter.TaskListAdapter
import com.example.planer_diplom.presentation.worker_list_adapter.WorkerListAdapter
import com.example.planer_diplom.utilits.CHILD_ID
import com.example.planer_diplom.utilits.NODE_PHONES
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.UID
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference

class WorkerListFragment : Fragment() {
    private lateinit var binding: FragmentWorkerListBinding
    private lateinit var adapter: FirebaseRecyclerAdapter<WorkerItem, WorkerListViewHolder>
    private lateinit var refWorkers: DatabaseReference
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkerListBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        initRecyclerView()
        binding.rvWorkerList.layoutManager = GridLayoutManager(activity, 2)
//            adapter = WorkerListAdapter()

    }

    private fun initRecyclerView() {
        recyclerView = binding.rvWorkerList
        refWorkers = REF_DATABASE_ROOT.child(NODE_PHONES).child()

        val option = FirebaseRecyclerOptions.Builder<WorkerItem>()
            .setQuery(refWorkers, WorkerItem::class.java)
            .build()
        adapter = object : FirebaseRecyclerAdapter<WorkerItem,WorkerListViewHolder>(option){
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): WorkerListViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_worker,parent,false)
                return WorkerListViewHolder(view)
            }

            override fun onBindViewHolder(
                holder: WorkerListViewHolder,
                position: Int,
                model: WorkerItem
            ) {
                holder.fioWorker.text = model.fio
                holder.workerPhone.text = model.phone
            }

        }
        recyclerView.adapter = adapter
        adapter.startListening()
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
    }

    class WorkerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fioWorker: TextView = view.findViewById(R.id.tvFioWorker)
        val workerPhone: TextView = view.findViewById(R.id.tvFioWorker)
    }


}