package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.FragmentHomeWorkerBinding
import com.example.planer_diplom.utilits.WORKER
import com.example.planer_diplom.utilits.replaceFragment


class HomeWorkerFragment : Fragment() {
    private lateinit var binding: FragmentHomeWorkerBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeWorkerBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onStart() {
        super.onStart()
        initFields()
    }

    override fun onResume() {
        super.onResume()

        binding.ibtnEditWorker.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activityNavHost,
                    ChangeNameFragment()
                ).commit()


        }
    }

    private fun initFields() {
        binding.tvLastName.text = WORKER.lastName
        binding.tvName.text = WORKER.firstName
        binding.tvPatronymic.text = WORKER.patronymic
        binding.tvNumber.text = WORKER.number
    }
}