package com.example.planer_diplom.presentation.task_list.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentTaskEditBinding
import com.example.planer_diplom.presentation.task_list.TaskItemActivity
import com.example.planer_diplom.utilits.CHILD_TASK_DESCRIPTION
import com.example.planer_diplom.utilits.CHILD_TASK_NAME
import com.example.planer_diplom.utilits.CHILD_TASK_WORKER
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.TASK

class TaskEditFragment : Fragment() {
    private lateinit var binding: FragmentTaskEditBinding
    private lateinit var workerId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        getWorkerId()
//    }

//    private fun getWorkerId() {
//
//        REF_DATABASE_ROOT.child(NODE_FIO_ID).child(workerName)
//            .addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()) {
//                        for (userSnapshot in snapshot.children) {
//                            workerId = userSnapshot.value.toString()
//                        }
//                        Log.d("MyLog", workerId)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    println(error.message)
//                }
//            })
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.ibtnSave.setOnClickListener {
            enterCode()
            initSpinner()
        }
    }

    private fun initSpinner() {
        val spinner: Spinner = binding.spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            (activity as TaskItemActivity),
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    private fun enterCode() {
        val taskName = binding.etTaskName.text.toString()
        val description = binding.etDescription.text.toString()
        val workerName = binding.spinner.selectedItem.toString()


        Log.d("MyLog", "workerID")
//        for (data in dataSnapshot.getChildren()) {
//            val userName = data.child("uname").value.toString()
//            driverlist.add(userName)
//        }


        if (taskName.isEmpty() or description.isEmpty() or workerName.isEmpty()) {
            Toast.makeText(TaskItemActivity(), "jib,", Toast.LENGTH_SHORT).show()
        } else {
            REF_DATABASE_ROOT.child(NODE_TASKS).child(workerName).child(CHILD_TASK_NAME).setValue(taskName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.name = taskName

                    }
                }
            REF_DATABASE_ROOT.child(NODE_TASKS).child(workerName).child(CHILD_TASK_DESCRIPTION).setValue(description)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.description = description
                    }
                }
            REF_DATABASE_ROOT.child(NODE_TASKS).child(workerName).child(CHILD_TASK_WORKER).setValue(workerName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.workerName = workerName
                        parentFragmentManager.popBackStack()
                    }
                }
        }
    }
}