package com.avci.mote.modules.createnote.domain.model

sealed class BaseNoteComponentDTO {
    abstract val id: Int
    abstract val order: Int
    abstract val noteId: Int

    data class TextAreaComponentDTO(
        override val id: Int,
        override val order: Int,
        override val noteId: Int,
        val text: String?,
    ) : BaseNoteComponentDTO()

    data class HeadingComponentDTO(
        override val id: Int,
        override val order: Int,
        override val noteId: Int,
        val text: String?,
    ) : BaseNoteComponentDTO()

    data class ImageComponentDTO(
        override val id: Int,
        override val order: Int,
        override val noteId: Int,
        val uri: String?,
    ) : BaseNoteComponentDTO()
}
