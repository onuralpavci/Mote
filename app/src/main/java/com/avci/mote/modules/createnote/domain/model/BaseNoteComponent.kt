package com.avci.mote.modules.createnote.domain.model

sealed class BaseNoteComponent {
    abstract val id: Int
    abstract val order: Int
    abstract val noteId: Int

    data class TextAreaComponent(
        override val id: Int,
        override val order: Int,
        override val noteId: Int,
        val text: String?,
    ) : BaseNoteComponent()

    data class ImageComponent(
        override val id: Int,
        override val order: Int,
        override val noteId: Int,
        val uri: String?,
    ) : BaseNoteComponent()
}
