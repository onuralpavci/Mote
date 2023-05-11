package com.avci.mote.modules.createnote.domain.model

data class Note(
    val id: Int,
    val title: String?,
    val createTimeStamp: Long,
    val updateTimeStamp: Long?,
    val noteComponents: List<BaseNoteComponent>,
    val isSaved: Boolean
)
