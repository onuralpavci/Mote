package com.avci.mote.modules.createnote.ui.usecase

import android.net.Uri
import com.avci.mote.R
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponent
import com.avci.mote.modules.createnote.domain.model.Note
import com.avci.mote.modules.createnote.domain.usecase.CreateNewNoteImageUseCase
import com.avci.mote.modules.createnote.domain.usecase.CreateNewNoteTextAreaUseCase
import com.avci.mote.modules.createnote.domain.usecase.CreateNewNoteUseCase
import com.avci.mote.modules.createnote.domain.usecase.DeleteNoteImageUseCase
import com.avci.mote.modules.createnote.domain.usecase.DeleteNoteTextAreaUseCase
import com.avci.mote.modules.createnote.domain.usecase.DeleteNoteUseCase
import com.avci.mote.modules.createnote.domain.usecase.GetNoteFlowUseCase
import com.avci.mote.modules.createnote.domain.usecase.UpdateNoteImageUriUseCase
import com.avci.mote.modules.createnote.domain.usecase.UpdateNoteIsSavedUseCase
import com.avci.mote.modules.createnote.domain.usecase.UpdateNoteLastEditDateUseCase
import com.avci.mote.modules.createnote.domain.usecase.UpdateNoteTextAreaTextUseCase
import com.avci.mote.modules.createnote.domain.usecase.UpdateNoteTitleUseCase
import com.avci.mote.modules.createnote.ui.mapper.BaseNoteComponentMapper
import com.avci.mote.modules.createnote.ui.mapper.CreateNotePreviewMapper
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.BaseActionItem.AddImageActionItem
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.BaseActionItem.AddTextAreaActionItem
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.BaseActionItem.AskChatGPTActionItem
import com.avci.mote.modules.createnote.ui.model.CreateNotePreview
import com.avci.mote.modules.notes.ui.usecase.GetCreateNoteDateUseCase
import com.avci.mote.modules.notes.ui.usecase.GetUpdateNoteDateUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CreateNotePreviewUseCase @Inject constructor(
    private val createNewNoteUseCase: CreateNewNoteUseCase,
    private val getNoteFlowUseCase: GetNoteFlowUseCase,
    private val createNewNoteTextAreaUseCase: CreateNewNoteTextAreaUseCase,
    private val updateNoteTitleUseCase: UpdateNoteTitleUseCase,
    private val updateNoteTextAreaTextUseCase: UpdateNoteTextAreaTextUseCase,
    private val deleteNoteTextAreaUseCase: DeleteNoteTextAreaUseCase,
    private val createNewNoteImageUseCase: CreateNewNoteImageUseCase,
    private val deleteNoteImageUseCase: DeleteNoteImageUseCase,
    private val updateNoteImageUriUseCase: UpdateNoteImageUriUseCase,
    private val getCreateNoteDateUseCase: GetCreateNoteDateUseCase,
    private val getUpdateNoteDateUseCase: GetUpdateNoteDateUseCase,
    private val updateNoteLastEditDateUseCase: UpdateNoteLastEditDateUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteIsSavedUseCase: UpdateNoteIsSavedUseCase,
    private val createNotePreviewMapper: CreateNotePreviewMapper,
    private val baseNoteComponentMapper: BaseNoteComponentMapper
) {

    fun getInitialPreview() = createNotePreviewMapper.mapToInitialPreview()

    suspend fun createNewNote(): Int {
        return createNewNoteUseCase.invoke()
    }

    suspend fun getCreateNotePreviewFlow(noteId: Int): Flow<CreateNotePreview> {
        return getNoteFlowUseCase.invoke(noteId).map {
            if (it == null) {
                createNotePreviewMapper.mapToErrorPreview()
            } else {
                val createDate = getCreateNoteDateUseCase.invoke(it.createTimeStamp)
                val updateDate = it.updateTimeStamp?.let { getUpdateNoteDateUseCase.invoke(it) }
                createNotePreviewMapper.mapTo(
                    id = it.id,
                    createNoteListItems = createNoteListItems(it),
                    createDate = createDate,
                    updateDate = updateDate,
                    isSaved = it.isSaved
                )
            }
        }
    }

    suspend fun updateNoteWithActionItem(
        noteId: Int,
        item: BaseCreateNoteListItem.BaseActionItem
    ) {
        when (item) {
            is AddTextAreaActionItem -> addNewTextAreaComponent(noteId)
            is AddImageActionItem -> addImageComponent(noteId)
        }
    }

    fun createOpenChatGPTEventPreview(previousPreview: CreateNotePreview): CreateNotePreview {
        return createNotePreviewMapper.mapToOpenChatGPTEventPreview(previousPreview)
    }

    fun createShowSaveSuccessDialogEventPreview(previousPreview: CreateNotePreview): CreateNotePreview {
        return createNotePreviewMapper.mapToShowSaveSuccessDialogEventPreview(previousPreview)
    }

    suspend fun updateNoteTitle(noteId: Int, newTitle: String?, shouldUpdateTime: Boolean = false) {
        updateNoteTitleUseCase.invoke(noteId = noteId, title = newTitle)
        if (shouldUpdateTime) updateNoteLastEditDateUseCase.invoke(noteId)
    }

    suspend fun updateNoteTextAreaText(
        noteId: Int,
        componentId: Int,
        newText: String?,
        shouldUpdateTime: Boolean = false
    ) {
        updateNoteTextAreaTextUseCase.invoke(componentId = componentId, newText = newText)
        if (shouldUpdateTime) updateNoteLastEditDateUseCase.invoke(noteId)
    }

    suspend fun deleteNoteTextArea(
        noteId: Int,
        componentId: Int,
        shouldUpdateTime: Boolean = false
    ) {
        deleteNoteTextAreaUseCase.invoke(componentId = componentId)
        if (shouldUpdateTime) updateNoteLastEditDateUseCase.invoke(noteId)
    }

    suspend fun deleteNoteImage(
        noteId: Int,
        componentId: Int,
        shouldUpdateTime: Boolean = false
    ) {
        deleteNoteImageUseCase.invoke(componentId = componentId)
        if (shouldUpdateTime) updateNoteLastEditDateUseCase.invoke(noteId)
    }

    suspend fun deleteNote(noteId: Int) {
        deleteNoteUseCase.invoke(noteId)
    }

    suspend fun updateNoteImageUri(noteId: Int, componentId: Int, uri: Uri, shouldUpdateTime: Boolean = false) {
        updateNoteImageUriUseCase.invoke(componentId, uri.toString())
        if (shouldUpdateTime) updateNoteLastEditDateUseCase.invoke(noteId)
    }

    suspend fun updateNoteIsSavedUseCase(noteId: Int, isSaved: Boolean) {
        updateNoteIsSavedUseCase.invoke(noteId, isSaved)
    }

    private fun createNoteListItems(note: Note): List<BaseCreateNoteListItem> {
        return mutableListOf<BaseCreateNoteListItem>().apply {
            val titleItem = getTitleItem(note.title)
            val labelItem = getLabelItem()
            val dividerItem = getDividerItem()
            val addTextAreaActionItem = getAddTextAreaActionItem()
            val addImageActionItem = getAddImageActionItem()
            val askChatGPTActionItem = getAskChatGPTActionItem()
            val noteComponents = createNoteComponentItems(note.noteComponents)
            add(titleItem)
            addAll(noteComponents)
            add(dividerItem)
            add(labelItem)
            add(addTextAreaActionItem)
            add(addImageActionItem)
            add(askChatGPTActionItem)
        }
    }

    private fun getTitleItem(title: String?): BaseCreateNoteListItem.TitleItem {
        return BaseCreateNoteListItem.TitleItem(title.orEmpty())
    }

    private fun getDividerItem(): BaseCreateNoteListItem.DividerItem {
        return BaseCreateNoteListItem.DividerItem
    }

    private fun getLabelItem(): BaseCreateNoteListItem.LabelItem {
        return BaseCreateNoteListItem.LabelItem(R.string.actions)
    }

    private fun getAddTextAreaActionItem(): AddTextAreaActionItem {
        return AddTextAreaActionItem
    }

    private fun getAddImageActionItem(): AddImageActionItem {
        return AddImageActionItem
    }

    private fun getAskChatGPTActionItem(): AskChatGPTActionItem {
        return AskChatGPTActionItem
    }

    private fun createNoteComponentItems(
        noteComponents: List<BaseNoteComponent>
    ): List<BaseCreateNoteListItem.BaseNoteComponentItem> {
        return noteComponents.map { noteComponent ->
            baseNoteComponentMapper.mapTo(noteComponent)
        }.sortedBy { it.order }
    }

    private suspend fun addNewTextAreaComponent(noteId: Int) {
        createNewNoteTextAreaUseCase.invoke(noteId = noteId)
    }

    private suspend fun addImageComponent(noteId: Int) {
        createNewNoteImageUseCase.invoke(noteId)
    }
}
