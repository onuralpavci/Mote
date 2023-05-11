package com.task.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.task.noteapp.databinding.ActivityMainBinding
import com.task.noteapp.modules.createnote.ui.CreateNoteViewModel.Companion.NEW_NOTE_ID
import com.task.noteapp.modules.customview.custoomtoolbar.ui.CustomToolbar
import com.task.noteapp.utils.navigateSafe
import com.task.noteapp.utils.setupWithNavController
import com.task.noteapp.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    lateinit var navController: NavController

    var isBottomBarNavigationVisible by Delegates.observable(false) { _, oldValue, newValue ->
        if (newValue != oldValue) {
            binding.bottomNavigationViewGroup.isVisible = newValue
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navController = (supportFragmentManager.findFragmentById(binding.navigationHostFragment.id) as NavHostFragment)
            .navController
        startNavigation()
        initUi()
    }

    fun getToolbar(): CustomToolbar {
        return binding.toolbar
    }

    fun navBack() {
        navController.navigateUp()
    }

    fun nav(directions: NavDirections, onError: (() -> Unit)? = null) {
        navController.navigateSafe(directions, onError)
    }

    fun setToolbarSearchText(text: String?) {
        binding.toolbar.setSearchText(text)
    }

    private fun initUi() {
        binding.addNoteButton.setOnClickListener {
            nav(HomeNavigationDirections.actionGlobalCreateNoteFragment(NEW_NOTE_ID))
        }
        binding.bottomNavigationView.setupWithNavController(navController, {})
    }

    private fun startNavigation() {
        with(navController) {
            graph = navInflater.inflate(R.navigation.main_navigation)
        }
    }
}
