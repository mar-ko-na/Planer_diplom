package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.databinding.FragmentWorkersBinding
import com.example.planer_diplom.domain.models.WorkerItem
import com.example.planer_diplom.presentation.worker_list_adapter.WorkersAdapter
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class WorkersFragment : Fragment() {
    private lateinit var binding: FragmentWorkersBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var workersArrayList: ArrayList<WorkerItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkersBinding.inflate(layoutInflater)
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

                        val worker = userSnapshot.getValue(WorkerItem::class.java)
                        workersArrayList.add(worker!!)

                    }

                    recyclerView.adapter = WorkersAdapter(workersArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}