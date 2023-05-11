package com.avci.mote.modules.createnote.domain.model

data class NoteDTO(
    val id: Int,
    val title: String?,
    val createTimeStamp: Long,
    val updateTimeStamp: Long?,
    val isSaved: Boolean
)
