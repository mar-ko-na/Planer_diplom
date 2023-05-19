package com.example.planer_diplom.presentation.fragments

import com.example.planer_diplom.presentation.worker_list_adapter.WorkerListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.FragmentWorkerListBinding
import com.example.planer_diplom.domain.models.WorkerItem
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.getCommonWorkerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class WorkerListFragment : Fragment() {
    private lateinit var binding: FragmentWorkerListBinding

    //    private lateionit var adapter: FirebaseRecyclerAdapter<CommonWorkerModel, WorkersHolder>
    private lateinit var recyclerView: RecyclerView
//    private lateinit var refWorkers: DatabaseReference
    private lateinit var workersArrayList: ArrayList<WorkerItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkerListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvWorkerList
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        workersArrayList = arrayListOf()
        getUserData()
    }

    private fun getUserData() {

        REF_DATABASE_ROOT.child(NODE_WORKERS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val worker = userSnapshot.getCommonWorkerModel()
                        workersArrayList.add(worker)
                    }
                    recyclerView.adapter = WorkerListAdapter(workersArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }


}

//    private fun initRecyclerView() {
//        recyclerView = binding.rvWorkerList
//        refWorkers = REF_DATABASE_ROOT.child(NODE_PHONES_ID)
//        val option = FirebaseRecyclerOptions.Builder<CommonWorkerModel>()
//            .setQuery(refWorkers, CommonWorkerModel::class.java)
//            .build()
//        adapter = object : FirebaseRecyclerAdapter<CommonWorkerModel, WorkersHolder>(option) {
//
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkersHolder {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_worker, parent, false)
//                return WorkersHolder(view)
//            }
//
//            override fun onBindViewHolder(
//                holder: WorkersHolder,
//                position: Int,
//                model: CommonWorkerModel
//            ) {
//                refWorkers = REF_DATABASE_ROOT.child(NODE_WORKERS).child(model.id)
//
//                refWorkers.addValueEventListener(
//                    AppValueEvenListener {
//                        val worker = it.getCommonWorkerModel()
//
//                        holder.fioWorker.text = worker.fio
//                        holder.workerPhone.text = worker.phone
//                    })
//            }
//        }
//            recyclerView.adapter = adapter
//            adapter.startListening()
//    }

//    class WorkersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val fioWorker: TextView = itemView.findViewById(R.id.tvFioWorker)
//        val workerPhone: TextView = itemView.findViewById(R.id.tvFioWorker)
//    }



