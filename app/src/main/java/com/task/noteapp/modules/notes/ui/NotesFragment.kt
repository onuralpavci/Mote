package com.task.noteapp.modules.notes.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNotesBinding
import com.task.noteapp.modules.core.ui.BaseFragment
import com.task.noteapp.modules.core.ui.model.FragmentConfiguration
import com.task.noteapp.modules.core.ui.model.ToolbarConfiguration
import com.task.noteapp.modules.createnote.ui.CreateNoteViewModel.Companion.NEW_NOTE_ID
import com.task.noteapp.modules.notes.ui.adapter.NotesAdapter
import com.task.noteapp.modules.notes.ui.model.NotesPreview
import com.task.noteapp.modules.notes.ui.viewholder.SpacesItemDecoration
import com.task.noteapp.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : BaseFragment(R.layout.fragment_notes) {

    private val toolbarConfiguration = ToolbarConfiguration(
        titleStringResId = R.string.my_notes
    )
    override val fragmentConfiguration = FragmentConfiguration(
        toolbarConfiguration = toolbarConfiguration,
        isBottomNavigationViewVisible = true
    )

    private val binding by viewBinding(FragmentNotesBinding::bind)

    private val notesViewModel by viewModels<NotesViewModel>()

    private val notesAdapterListener = object : NotesAdapter.NotesAdapterListener {
        override fun onNoteClicked(noteId: Int) {
            onNoteCardItemClicked(noteId)
        }

        override fun onAddNoteClicked() {
            onAddNoteCardItemClicked()
        }
    }

    private val notesAdapter = NotesAdapter(notesAdapterListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObservers()
    }

    private fun initUi() {
        binding.notesRecyclerView.apply {
            val columnSpacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_small)
            addItemDecoration(SpacesItemDecoration(columnSpacingInPixels))
            adapter = notesAdapter
            layoutManager = GridLayoutManager(context, NOTES_COLUMN_COUNT)
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            notesViewModel.notesPreviewFlow.collectLatest { preview ->
                updateUiWithPreview(preview)
            }
        }
    }

    private fun onNoteCardItemClicked(noteId: Int) {
        nav(NotesFragmentDirections.actionNotesFragmentToCreateNoteFragment(noteId))
    }

    private fun onAddNoteCardItemClicked() {
        nav(NotesFragmentDirections.actionNotesFragmentToCreateNoteFragment(NEW_NOTE_ID))
    }

    private fun updateUiWithPreview(preview: NotesPreview) {
        with(binding) {
            screenState.configure(preview.screenStateConfiguration)
            screenState.isVisible = preview.isEmptyStateVisible
            notesRecyclerView.isVisible = preview.isEmptyStateVisible.not()
            notesAdapter.submitList(preview.noteList)
        }
    }

    companion object {
        const val NOTES_COLUMN_COUNT = 2
    }
}
