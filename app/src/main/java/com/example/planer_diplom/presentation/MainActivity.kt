package com.example.planer_diplom.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.planer_diplom.R
import com.example.planer_diplom.data.getTaskList
import com.example.planer_diplom.data.getWorkerTaskList
import com.example.planer_diplom.data.initWorker
import com.example.planer_diplom.databinding.ActivityMainBinding
import com.example.planer_diplom.presentation.register.RegisterActivity
import com.example.planer_diplom.presentation.status.StatusFragment
import com.example.planer_diplom.presentation.task_list.fragments.TaskListFragment
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AUTH
import com.example.planer_diplom.utilits.CHILD_WORKER_STATUS
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.TASK_LIST
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.initFirebase
import com.example.planer_diplom.utilits.logD
import com.example.planer_diplom.utilits.replaceActivity
import com.example.planer_diplom.utilits.replaceFragmentNav
import com.example.planer_diplom.utilits.reversStatus
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
            getTaskList {
                logD(" oVC in TLF 55 wwor fio ${WORKER.fio}")
                if (!WORKER.managerStatus) {
                    getWorkerTaskList(WORKER.fio.toString())
                }
                initTaskAdapter()
            }
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
//                supportFragmentManager.beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.activityNavHost,
//                        StatusFragment(WORKER.managerStatus)
//                    ).commit()
                replaceFragmentNav(StatusFragment(WORKER.managerStatus))

            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        getTaskList {
            logD(" task list fr wwor fio ${WORKER.fio}")
            if (!WORKER.managerStatus) {
                getWorkerTaskList(WORKER.fio.toString())
            }
        }
    }

    fun reversFun() {
        val rStatus = reversStatus(WORKER.managerStatus)
        REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID)
            .child(CHILD_WORKER_STATUS).setValue(rStatus)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    WORKER.managerStatus = rStatus
                    initStatus()
                    logD("reversFun")
                }
            }
    }


    private fun initStatus() {
        val fabAddTask = findViewById<ImageButton>(R.id.fabAddTask)
        val imgCreateTask = findViewById<ImageView>(R.id.imgCreateTask)
        val tvDelete = findViewById<TextView>(R.id.tvDelete)
        val tvNoTask = findViewById<TextView>(R.id.tvNoTask)

        if (WORKER.managerStatus) {
            binding.bottomNavView.menu.setGroupVisible(R.id.groupWorkerListFragment, true)
            binding.bottomNavView.menu.setGroupVisible(R.id.groupHomeWorkerFragment, false)
            fabAddTask.visibility = View.VISIBLE
        } else {
            binding.bottomNavView.menu.setGroupVisible(R.id.groupWorkerListFragment, false)
            binding.bottomNavView.menu.setGroupVisible(R.id.groupHomeWorkerFragment, true)
            fabAddTask.visibility = View.GONE
        }
        TaskListFragment().hideImg(tvNoTask, imgCreateTask)

    }

     fun initTaskAdapter() {
        logD("init task adapter")

        val recyclerView = findViewById<RecyclerView>(R.id.rvTaskList)

        TaskListFragment().initInterface(recyclerView)
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
