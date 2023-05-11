package com.task.noteapp.modules.searchnotes.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.task.noteapp.MainActivity
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentSearchNotesBinding
import com.task.noteapp.modules.core.ui.BaseFragment
import com.task.noteapp.modules.core.ui.model.FragmentConfiguration
import com.task.noteapp.modules.core.ui.model.ToolbarConfiguration
import com.task.noteapp.modules.notes.ui.NotesFragment
import com.task.noteapp.modules.notes.ui.viewholder.SpacesItemDecoration
import com.task.noteapp.modules.searchnotes.ui.adapter.SearchNotesAdapter
import com.task.noteapp.modules.searchnotes.ui.model.SearchNotesPreview
import com.task.noteapp.utils.delegation.bottomnavbarvisibility.BottomNavBarVisibilityDelegation
import com.task.noteapp.utils.delegation.bottomnavbarvisibility.BottomNavBarVisibilityDelegationImpl
import com.task.noteapp.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNotesFragment : BaseFragment(R.layout.fragment_search_notes),
    BottomNavBarVisibilityDelegation by BottomNavBarVisibilityDelegationImpl() {

    private val toolbarConfiguration = ToolbarConfiguration(
        onSearchBarTextChangeListener = ::updateQuery
    )
    override val fragmentConfiguration = FragmentConfiguration(
        toolbarConfiguration = toolbarConfiguration,
        isBottomNavigationViewVisible = true
    )

    private val binding by viewBinding(FragmentSearchNotesBinding::bind)

    private val searchNotesViewModel by viewModels<SearchNotesViewModel>()

    private val searchNotesAdapterListener = SearchNotesAdapter.SearchNotesAdapterListener {
        nav(SearchNotesFragmentDirections.actionGlobalCreateNoteFragment(it))
    }

    private val searchNotesAdapter = SearchNotesAdapter(searchNotesAdapterListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initDelegations()
        initObservers()
    }

    private fun initUi() {
        binding.searchNotesMotionLayout.setTransitionDuration(SCREEN_STATE_FADE_ANIMATION_DURATION)
        binding.notesRecyclerView.apply {
            val columnSpacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_small)
            addItemDecoration(SpacesItemDecoration(columnSpacingInPixels))
            adapter = searchNotesAdapter
            layoutManager = GridLayoutManager(context, NotesFragment.NOTES_COLUMN_COUNT)
        }
    }

    private fun updateQuery(query: String) {
        searchNotesViewModel.updateQuery(query)
    }

    private fun initDelegations() {
        registerBottomNavBarVisibilityDelegation(this)
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchNotesViewModel.searchNotesPreviewFlow.collectLatest { preview ->
                updateUiWithPreview(preview)
            }
        }
    }

    private fun updateUiWithPreview(preview: SearchNotesPreview) {
        with(binding) {
            screenState.configure(preview.screenStateConfiguration)
            notesRecyclerView.isVisible = preview.isEmptyStateVisible.not()
            searchNotesAdapter.submitList(preview.noteList)
            (activity as? MainActivity)?.setToolbarSearchText(preview.query)
            if (preview.isEmptyStateVisible) {
                searchNotesMotionLayout.transitionToStart()
            } else {
                searchNotesMotionLayout.transitionToEnd()
            }
        }
    }

    companion object {
        const val SCREEN_STATE_FADE_ANIMATION_DURATION = 600
    }
}
