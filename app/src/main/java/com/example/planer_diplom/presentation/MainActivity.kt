package com.example.planer_diplom.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ActivityMainBinding
import com.example.planer_diplom.domain.models.WorkerStatus.Companion.S_MANAGER
import com.example.planer_diplom.presentation.register.RegisterActivity
import com.example.planer_diplom.presentation.worker_list.fragments.ChangeNameFragment
import com.example.planer_diplom.utilits.APP_ACTIVITY
import com.example.planer_diplom.utilits.AUTH
import com.example.planer_diplom.utilits.AppValueEvenListener
import com.example.planer_diplom.utilits.CHILD_WORKER_FIRSTNAME
import com.example.planer_diplom.utilits.CURRENT_UID
import com.example.planer_diplom.utilits.NODE_WORKERS
import com.example.planer_diplom.utilits.REF_DATABASE_ROOT
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.initFirebase
import com.example.planer_diplom.utilits.initWorkers
import com.example.planer_diplom.utilits.replaceActivity
import com.example.planer_diplom.utilits.showToast
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

    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY = this
        initFields()
        initFunc()
        initNavMenu()

    }

    private fun initNavMenu() {
//        if (WORKER.managerStatus == S_MANAGER){
        var clickCounter = 0
        val tvNoTask = findViewById<TextView>(R.id.tvNoTask)
        val imgCreateTask = findViewById<ImageView>(R.id.imgCreateTask)
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        tvNoTask.visibility = View.GONE

        binding.tvTitle.setOnClickListener {
            clickCounter++

            if (clickCounter == 5) {
                showToast(clickCounter.toString())
//                imgCreateTask.visibility = View.VISIBLE
                fabAddTask.visibility = View.VISIBLE
                binding.bottomNavView.menu.setGroupVisible(R.id.groupWorkerListFragment, true)
                binding.bottomNavView.menu.setGroupVisible(R.id.groupHomeWorkerFragment, false)

            }
        }

        if (WORKER.managerStatus == S_MANAGER) {
            imgCreateTask.visibility = View.VISIBLE
            fabAddTask.visibility = View.VISIBLE
            binding.bottomNavView.menu.setGroupVisible(R.id.groupWorkerListFragment, true)
            binding.bottomNavView.menu.setGroupVisible(R.id.groupHomeWorkerFragment, false)
        } else {
            binding.bottomNavView.menu.setGroupVisible(R.id.groupWorkerListFragment, false)
            binding.bottomNavView.menu.setGroupVisible(R.id.groupHomeWorkerFragment, true)
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
        initFirebase()
        initWorkers()
        initName()
//        CoroutineScope(Dispatchers.IO).launch {
//            initWorkerList()
//        }
    }

    private fun initName() {
        REF_DATABASE_ROOT.child(NODE_WORKERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEvenListener {
                if (!it.hasChild(CHILD_WORKER_FIRSTNAME)) {
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(
                            R.id.activityNavHost,
                            ChangeNameFragment()
                        ).commit()
                }
            })
    }


}
