package com.task.noteapp.modules.settings.ui

import android.os.Bundle
import android.view.View
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentSettingsBinding
import com.task.noteapp.modules.core.ui.BaseFragment
import com.task.noteapp.modules.core.ui.model.FragmentConfiguration
import com.task.noteapp.modules.core.ui.model.ToolbarConfiguration
import com.task.noteapp.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.task.noteapp.utils.openOnuralpGithub
import com.task.noteapp.utils.openOnuralpLinkedIn
import com.task.noteapp.utils.show
import com.task.noteapp.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val toolbarConfiguration = ToolbarConfiguration(
        titleStringResId = R.string.settings
    )
    override val fragmentConfiguration = FragmentConfiguration(
        toolbarConfiguration = toolbarConfiguration,
        isBottomNavigationViewVisible = true
    )

    private val binding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        binding.screenState.configure(
            ScreenStateConfiguration(
                titleResId = R.string.settings_on_their,
                startImageResId = R.drawable.ic_sitting_man,
                descriptionResId = R.string.setting_are_under
            )
        )
        binding.screenState.show()
        binding.githubSettingItem.setOnClickListener {
            context?.openOnuralpGithub()
        }
        binding.linkedInSettingItem.setOnClickListener {
            context?.openOnuralpLinkedIn()
        }
    }
}
