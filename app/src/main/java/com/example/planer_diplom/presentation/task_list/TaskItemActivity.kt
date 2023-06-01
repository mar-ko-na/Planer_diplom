package com.example.planer_diplom.presentation.task_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.planer_diplom.R
import com.example.planer_diplom.databinding.ActivityTaskItemBinding
import com.example.planer_diplom.domain.models.TaskItem
import com.example.planer_diplom.presentation.task_list.fragments.TaskEditFragment

class TaskItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskItemBinding

//    private var screenMode = MODE_UNKNOWN
    private var taskItemId = TaskItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }

        setSupportActionBar(binding.taskItemToolbar)
    }

//    override fun onEditingFinished() {
//        finish()
//    }


    private fun launchRightMode() {
//        val fragment = when (screenMode) {
//            MODE_EDIT -> TaskEditFragment.newInstanceEditItem(taskItemId)
//            MODE_ADD  -> TaskEditFragment.newInstanceAddItem()
//            else      -> throw RuntimeException("Unknown screen mode $screenMode")
//        }
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.taskEditContainer, fragment)
//            .commit()
    }

//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD) {
//            throw RuntimeException("Unknown screen mode $mode")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
//                throw RuntimeException("Param shop item id is absent")
//            }
//           taskItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, TaskItem.UNDEFINED_ID)
//        }
//    }
//
    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, TaskItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, TaskItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}
