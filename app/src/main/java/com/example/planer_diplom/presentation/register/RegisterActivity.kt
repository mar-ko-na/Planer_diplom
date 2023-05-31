package com.example.planer_diplom.presentation.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.planer_diplom.databinding.ActivityRegisterBinding
import com.example.planer_diplom.presentation.register.EnterPhoneFragment
import com.example.planer_diplom.utilits.initFirebase
import com.example.planer_diplom.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        toolbar = binding.registerToolbar
        setSupportActionBar(toolbar)
        replaceFragment(EnterPhoneFragment())

    }


}