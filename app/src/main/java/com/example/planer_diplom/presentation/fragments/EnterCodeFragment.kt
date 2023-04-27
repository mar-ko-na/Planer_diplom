package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentEnterCodeBinding
import com.example.planer_diplom.databinding.FragmentTaskListBinding

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    private lateinit var binding: FragmentEnterCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterCodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.etEnterCode.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val string = binding.etEnterCode.text.toString()
                if (string.length==6){
                    verificateCode()
                }
            }

        })

    }

    private fun verificateCode() {
Toast.makeText(activity, "Ok", Toast.LENGTH_SHORT).show()
    }
}