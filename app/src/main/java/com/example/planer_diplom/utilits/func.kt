package com.example.planer_diplom.utilits

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.planer_diplom.R
import com.example.planer_diplom.presentation.MainActivity
import com.google.firebase.database.DatabaseReference


fun showToast(message: String) {
        /* Функция показывает сообщение */
        Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
    }
//
//    fun restartActivity() {
//        /* Функция расширения для AppCompatActivity, позволяет запускать активити */
//        val intent = Intent(APP_ACTIVITY, MainActivity::class.java)
//        APP_ACTIVITY.startActivity(intent)
//        APP_ACTIVITY.finish()
//    }
//
//    fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
//        /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
//        if (addStack) {
//            APP_ACTIVITY.supportFragmentManager.beginTransaction()
//                .addToBackStack(null)
//                .replace(
//                    R.id.data_container,
//                    fragment
//                ).commit()
//        } else {
//            APP_ACTIVITY.supportFragmentManager.beginTransaction()
//                .replace(
//                    R.id.data_container,
//                    fragment
//                ).commit()
//        }
//
//    }

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity){
    val intent =Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}


fun AppCompatActivity.replaceFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.data_container,
        fragment
        ).commit()
}

fun AppCompatActivity.replaceFragmentNav(fragment: Fragment){
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.activityNavHost,
        fragment
        ).commit()
}


fun Fragment.replaceFragment(fragment: Fragment){
    parentFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.data_container,
            fragment
        ).commit()
}
fun Fragment.replaceFragmentNav(fragment: Fragment){
    parentFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.activityNavHost,
            fragment
        ).commit()
}

fun toChangeVisibility(element: View): Int {
    return if (element.isVisible){
        View.GONE
    }else View.VISIBLE
}

fun reversStatus(status: Boolean): Boolean = !status

fun logD(string: String){
    Log.d("MyLog", string)
}
