package com.avci.mote.modules.createnote.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.avci.mote.R
import com.avci.mote.databinding.FragmentCreateNoteBinding
import com.avci.mote.modules.core.ui.BaseFragment
import com.avci.mote.modules.core.ui.model.FragmentConfiguration
import com.avci.mote.modules.core.ui.model.ToolbarConfiguration
import com.avci.mote.modules.createnote.ui.adapter.CreateNoteAdapter
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.createnote.ui.model.CreateNotePreview
import com.avci.mote.modules.customview.customactiondialog.ui.providers.showDeleteNoteActionDialog
import com.avci.mote.modules.customview.customactiondialog.ui.providers.showNoteSavedActionDialog
import com.avci.mote.utils.openChatGPT
import com.avci.mote.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateNoteFragment : BaseFragment(R.layout.fragment_create_note) {

    private val toolbarConfiguration = ToolbarConfiguration(
        onBackButtonClicked = ::navBack
    )
    override val fragmentConfiguration = FragmentConfiguration(
        isBottomNavigationViewVisible = false,
        toolbarConfiguration = toolbarConfiguration
    )

    private val binding by viewBinding(FragmentCreateNoteBinding::bind)

    private val createNoteViewModel by viewModels<CreateNoteViewModel>()

    private val createNoteAdapterListener = object : CreateNoteAdapter.CreateNoteAdapterListener {
        override fun onTitleChanged(title: String) {
            createNoteViewModel.onTitleChanged(title)
        }

        override fun onActionItemClicked(item: BaseCreateNoteListItem.BaseActionItem) {
            createNoteViewModel.onActionItemClicked(item)
        }

        override fun onTextAreaTextChanged(text: String, componentId: Int) {
            createNoteViewModel.onTextAreaInputChanged(text = text, componentId = componentId)
        }

        override fun onTextAreaEmptyTextDeleted(componentId: Int) {
            createNoteViewModel.onTextAreaEmptyTextDeleted(componentId)
        }

        override fun onImageEditButtonClicked(componentId: Int) {
            // TODO: Find a better way to get update image component id
            createNoteViewModel.imageComponentIdToUpdate = componentId
            openImagePicker()
        }

        override fun onImageLoadImageFailed(componentId: Int) {
            // TODO: Handle error
        }

        override fun onImageDeleteButtonClicked(componentId: Int) {
            createNoteViewModel.onImageDeleteClicked(componentId)
        }
    }

    private val createNoteAdapter = CreateNoteAdapter(createNoteAdapterListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObservers()
    }

    private fun initUi() {
        with(binding) {
            createNoteRecyclerView.adapter = createNoteAdapter
            taskbar.apply {
                setOnDeleteButtonClickListener {
                    context?.showDeleteNoteActionDialog(onDeleteClickListener = ::deleteNote)
                }
                setOnBookmarkButtonClickListener {
                    createNoteViewModel.updateIsnNoteSaved()
                }
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            createNoteViewModel.createNotePreviewFlow.collectLatest { preview ->
                updateUiWithPreview(preview)
            }
        }
    }

    private fun updateUiWithPreview(preview: CreateNotePreview) {
        with(preview) {
            createNoteAdapter.submitList(preview.createNoteListItems)
            binding.taskbar.apply {
                setContent(preview.updateDate ?: preview.createDate)
                setBookMarkChecked(preview.isSaved)
            }
            openChatGPTEvent?.consume()?.let { context?.openChatGPT() }
            navBackEvent?.consume()?.let { navBack() }
            showSaveSuccessDialogEvent?.consume()?.let { context?.showNoteSavedActionDialog() }
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    private fun deleteNote() {
        createNoteViewModel.deleteNote()
        navBack()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            if (selectedImageUri != null) {
                createNoteViewModel.onImageSelected(selectedImageUri)
            }
        }
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 4
    }
}
