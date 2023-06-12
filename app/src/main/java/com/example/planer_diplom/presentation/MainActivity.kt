package com.example.planer_diplom.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.planer_diplom.R
import com.example.planer_diplom.data.initWorker
import com.example.planer_diplom.databinding.ActivityMainBinding
import com.example.planer_diplom.domain.models.WorkerStatus.Companion.S_MANAGER
import com.example.planer_diplom.presentation.register.RegisterActivity
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AUTH
import com.example.planer_diplom.utilits.CHILD_WORKER_STATUS
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.initFirebase
import com.example.planer_diplom.utilits.initWorkers
import com.example.planer_diplom.utilits.replaceActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var bottomBar: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ibtnExit.setOnClickListener {
            AUTH.signOut()
            replaceActivity(RegisterActivity())
        }
        APP_ACTIVITY = this
        initFirebase()
        initWorker {

            initFields()
            initFunc()
            initStatus()
        }
    }

    override fun onStart() {
        super.onStart()
        titleListener()
    }


    private fun titleListener() {

        var clickCounter = 0

        binding.tvTitle.setOnClickListener {
            clickCounter++

            if (clickCounter == 10) {
                clickCounter = 0
                val rStatus = reversStatus(WORKER.managerStatus)
                REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID)
                    .child(CHILD_WORKER_STATUS).setValue(rStatus)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            WORKER.managerStatus = rStatus
                            initStatus()
                        }
                    }
            }
        }
    }

    private fun reversStatus(status: Boolean): Boolean = !status


    private fun initStatus() {
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        if (WORKER.managerStatus) {
            binding.bottomNavView.menu.setGroupVisible(R.id.groupWorkerListFragment, true)
            binding.bottomNavView.menu.setGroupVisible(R.id.groupHomeWorkerFragment, false)
            fabAddTask.visibility = View.VISIBLE
        } else {
            binding.bottomNavView.menu.setGroupVisible(R.id.groupWorkerListFragment, false)
            binding.bottomNavView.menu.setGroupVisible(R.id.groupHomeWorkerFragment, true)
            fabAddTask.visibility = View.GONE

        }
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(toolbar)
            bottomBar.setupWithNavController(
                navController =
                findNavController(R.id.activityNavHost)
            )
        } else {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initFields() {
        toolbar = binding.mainToolbar
        bottomBar = binding.bottomNavView
//        initWorkers()

    }


}
