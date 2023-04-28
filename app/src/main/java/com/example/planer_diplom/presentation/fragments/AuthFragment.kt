package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentAuthBinding
import com.example.planer_diplom.databinding.FragmentEnterCodeBinding


class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        binding.btnLogIn.setOnClickListener{ sendCode()}
    }

    private fun sendCode() {
        if (binding.etEnterNumber.text.toString().isEmpty()){
            Toast.makeText(activity, getString(R.string.enterNumberPhone), Toast.LENGTH_SHORT).show()
        }else{
            fragmentManager?.beginTransaction()
                ?.replace(R.id.register_data_container,EnterCodeFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}


