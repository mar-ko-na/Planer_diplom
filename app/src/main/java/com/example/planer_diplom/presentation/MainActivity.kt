package com.example.planer_diplom.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ActivityMainBinding
import com.example.planer_diplom.utilits.AUTH
import com.example.planer_diplom.utilits.initFirebase
import com.example.planer_diplom.utilits.replaceActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

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
        initFields()
        initFunc()
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

    }


}