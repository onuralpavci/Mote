package com.avci.mote.modules.createnote.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.avci.mote.R
import com.avci.mote.databinding.FragmentCreateNoteBinding
import com.avci.mote.modules.core.ui.BaseFragment
import com.avci.mote.modules.core.ui.model.FragmentConfiguration
import com.avci.mote.modules.core.ui.model.ToolbarConfiguration
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.adapter.CreateNoteAdapter
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.createnote.ui.model.CreateNotePreview
import com.avci.mote.modules.createnote.util.NoteComponentSortItemTouchHelper
import com.avci.mote.modules.customview.customactiondialog.ui.providers.showDeleteEmptyNoteActionDialog
import com.avci.mote.modules.customview.customactiondialog.ui.providers.showDeleteNoteActionDialog
import com.avci.mote.modules.customview.customactiondialog.ui.providers.showEnterUrlActionDialog
import com.avci.mote.modules.customview.customactiondialog.ui.providers.showNoteSavedActionDialog
import com.avci.mote.utils.openChatGPT
import com.avci.mote.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateNoteFragment : BaseFragment(R.layout.fragment_create_note) {

    private val toolbarConfiguration = ToolbarConfiguration(
        onBackButtonClicked = ::onNavigateBack
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

        override fun onTextAreaEmptyTextDeleted(componentId: Int, adapterPosition: Int) {
            val previousItemPosition = adapterPosition - 1
            binding.createNoteRecyclerView.findViewHolderForAdapterPosition(previousItemPosition)
                ?.itemView?.requestFocus()
            createNoteViewModel.onTextAreaEmptyTextDeleted(componentId)
        }

        override fun onHeadingTextChanged(text: String, componentId: Int) {
            createNoteViewModel.onHeadingInputChanged(text = text, componentId = componentId)
        }

        override fun onHeadingEmptyTextDeleted(componentId: Int, adapterPosition: Int) {
            val previousItemPosition = adapterPosition - 1
            binding.createNoteRecyclerView.findViewHolderForAdapterPosition(previousItemPosition)
                ?.itemView?.requestFocus()
            createNoteViewModel.onHeadingEmptyTextDeleted(componentId)
        }

        override fun onImageEditButtonClicked(componentId: Int) {
            // TODO: Find a better way to get update image component id
            createNoteViewModel.imageComponentIdToUpdate = componentId
            openImagePicker()
        }

        override fun onDownloadEditButtonClicked(componentId: Int) {
            // TODO: Find a better way to get update image component id
            createNoteViewModel.imageComponentIdToUpdate = componentId
            openEnterImageUrlDialog()
        }

        override fun onImageLoadImageFailed(componentId: Int) {
            // TODO: Handle error
        }

        override fun onImageDeleteButtonClicked(componentId: Int) {
            createNoteViewModel.onImageDeleteClicked(componentId)
        }

        override fun onSortableItemPressed(viewHolder: BaseViewHolder<BaseCreateNoteListItem>) {
            dragDropItemTouchHelper.startDrag(viewHolder)
        }
    }

    private val createNoteAdapter = CreateNoteAdapter(createNoteAdapterListener)

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onNavigateBack()
        }
    }

    private val onItemMoveListener = object : NoteComponentSortItemTouchHelper.ItemMoveListener {
        override fun onItemMove(fromPosition: Int, toPosition: Int) {
            createNoteViewModel.onNoteComponentItemMoved(fromPosition, toPosition)
        }

        override fun onItemReleased() {
            createNoteViewModel.updateNoteComponentOrders()
        }
    }

    private val sortItemTouchHelper = NoteComponentSortItemTouchHelper(onItemMoveListener)

    private val dragDropItemTouchHelper = ItemTouchHelper(sortItemTouchHelper)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        onBackPressedCallback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        onBackPressedCallback.isEnabled = false
    }

    private fun initUi() {
        with(binding) {
            createNoteRecyclerView.apply {
                adapter = createNoteAdapter
                dragDropItemTouchHelper.attachToRecyclerView(this)
            }
            taskbar.apply {
                setOnDeleteButtonClickListener {
                    context?.showDeleteNoteActionDialog(onDeleteClickListener = ::deleteNote)
                }
                setOnBookmarkButtonClickListener {
                    createNoteViewModel.updateIsnNoteSaved()
                }
            }
            activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)
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
            openChatGPTEvent?.consume()?.let {
                context?.openChatGPT()
            }
            navBackEvent?.consume()?.let {
                navBack()
            }
            showSaveSuccessDialogEvent?.consume()?.let {
                context?.showNoteSavedActionDialog()
            }
            showDeleteEmptyNoteActionDialogEvent?.consume()?.let {
                context?.showDeleteEmptyNoteActionDialog(
                    onDeleteClickListener = ::deleteNote,
                    onKeepClickListener = ::navBack
                )
            }
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    private fun openEnterImageUrlDialog() {
        context?.showEnterUrlActionDialog(onDoneClickListener = ::onImageUrlEntered)
    }

    private fun deleteNote(data: String?) {
        createNoteViewModel.deleteNote()
        navBack()
    }

    private fun onImageUrlEntered(url: String?) {
        createNoteViewModel.onImageSelected(Uri.parse(url.orEmpty()))
    }

    private fun onNavigateBack() {
        createNoteViewModel.onNavigateBack()
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
