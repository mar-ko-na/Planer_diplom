package com.example.planer_diplom.presentation.task_list.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentTaskEditBinding
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment.Companion.ID_ADD
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment.Companion.ID_EDIT
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AppValueEventListener
import com.example.planer_diplom.utilits.CHILD_TASK_DESCRIPTION
import com.example.planer_diplom.utilits.CHILD_TASK_ENABLED
import com.example.planer_diplom.utilits.CHILD_TASK_ID
import com.example.planer_diplom.utilits.CHILD_TASK_NAME
import com.example.planer_diplom.utilits.CHILD_TASK_WORKER
import com.example.planer_diplom.utilits.NODE_ID_FIO
import com.example.planer_diplom.utilits.NODE_ID
import com.example.planer_diplom.utilits.NODE_TASKS
import com.example.planer_diplom.utilits.NODE_WORKER_TASK
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.TASK
import com.example.planer_diplom.utilits.getTaskModel
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
    private var idEdit: Int = -1

//    private var idAdd: Boolean = false

    //    var workersArrayList = arrayOf("1", "2", "3")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workersArrayList = arrayOf()


    }

    private fun getWorkerFio() {

        REF_DATABASE_ROOT.child(NODE_ID_FIO)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val workerFio = userSnapshot.value.toString()
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
        getWorkerFio()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
//        idAdd = arguments?.getBoolean(ID_ADD) == true
        idEdit = arguments?.getInt(ID_EDIT) ?: -1
//        logD("idAdd $idAdd")
        binding.ibtnSave.setOnClickListener {
            enterCode()
        }
    }

    private fun editMode(list: Array<String>) {

//        val idEdit = arguments?.getInt(ID_EDIT)
//
        logD("idEdit $idEdit")
//        logD("idAdd $idAdd")
        if ((idEdit != -1)) {
            REF_DATABASE_ROOT.child(NODE_TASKS).child(idEdit.toString()).addValueEventListener(
                AppValueEventListener {
                    val task = it.getTaskModel()

                    initScreen(task, list)
                }
            )
        }
    }


    private fun initScreen(task: TaskItem, list: Array<String>) {
        binding.etTaskName.setText(task.name)
        binding.etDescription.setText(task.description)
//        binding.spinner.post {
//            fun run() {
//                binding.spinner.setSelection(2)
//            }
//        }
        logD(" index worker ${list.indexOf(task.workerName).toString()}")
        val workerIndexInList = list.indexOf(task.workerName)
        binding.spinner.setSelection(workerIndexInList, false)
    }


    private fun initSpinner(list: Array<String>) {
        spinner = binding.spinner
        val arrayAdapter = ArrayAdapter(
            APP_ACTIVITY,
            android.R.layout.simple_spinner_item,
            list
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = arrayAdapter
        editMode(list)
    }

    private fun enterCode() {
        val taskName = binding.etTaskName.text.toString()
        val description = binding.etDescription.text.toString()
        val workerName = binding.spinner.selectedItem.toString()


        if (taskName.isEmpty() or description.isEmpty() or workerName.isEmpty()) {
//            Toast.makeText(TaskItemActivity(), getString(R.string.allFields), Toast.LENGTH_SHORT).show()
            showToast(getString(R.string.allFields))
        } else {
            val idAdd = arguments?.getBoolean(ID_ADD)
            logD("idAdd $idAdd")
            logD("idEdit $idEdit")

            var idTask = -1
            when (idAdd) {
                true -> {
                    idTask = ++TASK.id
                }
                false -> {
                    idTask = idEdit
                }
                else -> logD("ошибка")
            }

            if (idAdd == true) {
                REF_DATABASE_ROOT.child(NODE_TASKS).child(idTask.toString()).child(CHILD_TASK_ID)
                    .setValue(TASK.id)
                    .addOnCompleteListener {
                    }

                REF_DATABASE_ROOT.child(NODE_WORKER_TASK).child(workerName).child(idTask.toString())
                    .setValue(TASK.id).addOnCompleteListener {
                        logD("node worker task - id ${TASK.id} ")
                    }

                REF_DATABASE_ROOT.child(NODE_ID)
                    .setValue(TASK.id).addOnCompleteListener {
                    }

            }

            REF_DATABASE_ROOT.child(NODE_TASKS).child(idTask.toString()).child(CHILD_TASK_NAME)
                .setValue(taskName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.name = taskName

                    }
                }
            REF_DATABASE_ROOT.child(NODE_TASKS).child(idTask.toString())
                .child(CHILD_TASK_DESCRIPTION).setValue(description)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.description = description
                        parentFragmentManager.popBackStack()
                    }
                }
            REF_DATABASE_ROOT.child(NODE_TASKS).child(idTask.toString()).child(CHILD_TASK_WORKER)
                .setValue(workerName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.workerName = workerName

                    }
                }

            REF_DATABASE_ROOT.child(NODE_TASKS).child(idTask.toString()).child(CHILD_TASK_ENABLED)
                .setValue(false)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        TASK.enabled = false

                    }
                }


        }
    }
}