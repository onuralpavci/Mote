package com.avci.mote.modules.savednotes.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.avci.mote.R
import com.avci.mote.databinding.FragmentSavedNotesBinding
import com.avci.mote.modules.core.ui.BaseFragment
import com.avci.mote.modules.core.ui.model.FragmentConfiguration
import com.avci.mote.modules.core.ui.model.ToolbarConfiguration
import com.avci.mote.modules.notes.ui.NotesFragment
import com.avci.mote.modules.notes.ui.viewholder.SpacesItemDecoration
import com.avci.mote.modules.savednotes.ui.model.SavedNotesPreview
import com.avci.mote.modules.searchnotes.ui.SearchNotesFragmentDirections
import com.avci.mote.modules.searchnotes.ui.adapter.SearchNotesAdapter
import com.avci.mote.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedNotesFragment : BaseFragment(R.layout.fragment_saved_notes) {

    private val toolbarConfiguration = ToolbarConfiguration(
        titleStringResId = R.string.saved_notes
    )
    override val fragmentConfiguration = FragmentConfiguration(
        toolbarConfiguration = toolbarConfiguration,
        isBottomNavigationViewVisible = true
    )

    private val binding by viewBinding(FragmentSavedNotesBinding::bind)

    private val savedNotesViewModel by viewModels<SavedNotesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObservers()
    }

    private val searchNotesAdapterListener = SearchNotesAdapter.SearchNotesAdapterListener {
        nav(SearchNotesFragmentDirections.actionGlobalCreateNoteFragment(it))
    }

    private val searchNotesAdapter = SearchNotesAdapter(searchNotesAdapterListener)

    private fun initUi() {
        binding.notesRecyclerView.apply {
            val columnSpacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_small)
            addItemDecoration(SpacesItemDecoration(columnSpacingInPixels))
            adapter = searchNotesAdapter
            layoutManager = GridLayoutManager(context, NotesFragment.NOTES_COLUMN_COUNT)
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            savedNotesViewModel.notesPreviewFlow.collectLatest { preview ->
                updateUiWithPreview(preview)
            }
        }
    }

    private fun updateUiWithPreview(preview: SavedNotesPreview) {
        with(binding) {
            screenState.configure(preview.screenStateConfiguration)
            screenState.isVisible = preview.isEmptyStateVisible
            notesRecyclerView.isVisible = preview.isEmptyStateVisible.not()
            searchNotesAdapter.submitList(preview.noteList)
        }
    }

    companion object {
        const val NOTES_COLUMN_COUNT = 2
    }
}
