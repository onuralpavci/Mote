package com.avci.mote.modules.settings.ui

import android.os.Bundle
import android.view.View
import com.avci.mote.R
import com.avci.mote.databinding.FragmentSettingsBinding
import com.avci.mote.modules.core.ui.BaseFragment
import com.avci.mote.modules.core.ui.model.FragmentConfiguration
import com.avci.mote.modules.core.ui.model.ToolbarConfiguration
import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.avci.mote.utils.openOnuralpGithub
import com.avci.mote.utils.openOnuralpLinkedIn
import com.avci.mote.utils.show
import com.avci.mote.utils.viewbinding.viewBinding
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
