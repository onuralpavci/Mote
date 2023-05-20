package com.avci.mote.modules.createnote.domain.model

sealed class BaseNoteComponent {
    abstract val id: Int
    abstract val order: Int
    abstract val noteId: Int

    // TODO: Create base class TextComponent which has abstract text
    // Sub-classes will be FreeTextArea - Heading - TodoItem
    data class TextAreaComponent(
        override val id: Int,
        override val order: Int,
        override val noteId: Int,
        val text: String?,
    ) : BaseNoteComponent()

    data class HeadingComponent(
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
