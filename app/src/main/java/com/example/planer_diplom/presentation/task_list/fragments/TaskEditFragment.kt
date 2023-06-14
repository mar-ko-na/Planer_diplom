package com.example.planer_diplom.presentation.task_list.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentTaskEditBinding
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.CHILD_TASK_DESCRIPTION
import com.example.planer_diplom.utilits.CHILD_TASK_ID
import com.example.planer_diplom.utilits.CHILD_TASK_NAME
import com.example.planer_diplom.utilits.CHILD_TASK_WORKER
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.NODE_FIO_ID
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKER_TASK
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.TASK
import com.example.planer_diplom.utilits.logD
import com.example.planer_diplom.utilits.showToast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TaskEditFragment() : Fragment() {
    private lateinit var binding: FragmentTaskEditBinding

    //    private lateinit var workersArrayList: ArrayList<String>
    private lateinit var workersArrayList: Array<String>
    lateinit var spinner: Spinner

    //    var workersArrayList = arrayOf("1", "2", "3")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workersArrayList = arrayOf()
        getWorkerFio()

    }

    private fun getWorkerFio() {

        REF_DATABASE_ROOT.child(NODE_FIO_ID)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val workerFio = userSnapshot.value.toString()
                            logD(workerFio)
                            workersArrayList += workerFio
                        }
                        initSpinner(workersArrayList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println(error.message)
                }
            })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.ibtnSave.setOnClickListener {
            enterCode()
        }
//        binding.etTaskName.setText(TASK.name)
//        binding.etDescription.setText(TASK.description)
////        установить значение спиннера из кода
//        TODO()
//        TASK.workerName = binding.spinner.selectedItem.toString()


    }

    private fun initSpinner(list: Array<String>) {
        spinner = binding.spinner
        val arrayAdapter = ArrayAdapter(
            APP_ACTIVITY,
            android.R.layout.simple_spinner_item,
            list
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner

        spinner.adapter = arrayAdapter
// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            APP_ACTIVITY,
//            array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner.adapter = adapter
//        }

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
//            Toast.makeText(TaskItemActivity(), getString(R.string.allFields), Toast.LENGTH_SHORT).show()
            showToast(getString(R.string.allFields))
        } else {
            TASK.id += 1

            REF_DATABASE_ROOT.child(NODE_TASKS).child(TASK.id.toString()).child(CHILD_TASK_NAME)
                .setValue(taskName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.name = taskName

                    }
                }
            REF_DATABASE_ROOT.child(NODE_TASKS).child(TASK.id.toString())
                .child(CHILD_TASK_DESCRIPTION).setValue(description)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.description = description
                    }
                }
            REF_DATABASE_ROOT.child(NODE_TASKS).child(TASK.id.toString()).child(CHILD_TASK_WORKER)
                .setValue(workerName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.workerName = workerName

                    }
                }

            REF_DATABASE_ROOT.child(NODE_TASKS).child(TASK.id.toString()).child(CHILD_TASK_ID)
                .setValue(TASK.id)
                .addOnCompleteListener {
                    parentFragmentManager.popBackStack()
                }

            REF_DATABASE_ROOT.child(NODE_WORKER_TASK).child(workerName).child(TASK.id.toString())
                .setValue(TASK.id).addOnCompleteListener {
                }
        }
    }
}