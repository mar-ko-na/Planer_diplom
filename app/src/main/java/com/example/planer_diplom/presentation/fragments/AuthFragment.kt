package com.example.planer_diplom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.planer_diplom.R
import com.example.planer_diplom.presentation.RegisterActivity
import com.example.planer_diplom.databinding.FragmentAuthBinding
import com.example.planer_diplom.presentation.MainActivity
import com.example.planer_diplom.utilits.AUTH
import com.example.planer_diplom.utilits.replaceActivity
import com.example.planer_diplom.utilits.replaceFragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var phoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        AUTH = FirebaseAuth.getInstance()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "добро пожаловать", Toast.LENGTH_SHORT).show()
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else Toast.makeText(
                        activity,
                        it.exception?.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(activity, p0.message.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(phoneNumber, id))
            }
        }
        binding.btnLogIn.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.etEnterNumber.text.toString().isEmpty()) {
            Toast.makeText(activity, getString(R.string.enterNumberPhone), Toast.LENGTH_LONG).show()
        } else {
            authUser()
        }
    }

    private fun authUser() {
        phoneNumber = binding.etEnterNumber.text.toString()
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity as RegisterActivity) // Activity (for callback binding)
            .setCallbacks(mCallback) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}


