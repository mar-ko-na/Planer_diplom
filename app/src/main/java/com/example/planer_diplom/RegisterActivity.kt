package com.example.planer_diplom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.planer_diplom.databinding.ActivityRegisterBinding
import com.example.planer_diplom.presentation.fragments.AuthFragment
import com.example.planer_diplom.utilits.AUTH

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        toolbar = binding.registerToolbar
        setSupportActionBar(toolbar)
        title = "register"
        supportFragmentManager.beginTransaction()
            .add(R.id.register_data_container, AuthFragment())
            .commit()

    }


}