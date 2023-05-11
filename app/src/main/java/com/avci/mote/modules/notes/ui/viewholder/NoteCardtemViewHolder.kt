package com.avci.mote.modules.notes.ui.viewholder

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemNoteCardBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import com.avci.mote.utils.getFormattedTime
import com.avci.mote.utils.hide
import com.avci.mote.utils.loadImageWithCachedFirst
import com.avci.mote.utils.show

class NoteCardItemViewHolder(
    private val binding: ItemNoteCardBinding,
    private val listener: NoteCardItemListener
) : BaseViewHolder<BaseNoteCardListItem>(binding.root) {

    override fun bind(item: BaseNoteCardListItem) {
        if (item !is BaseNoteCardListItem.NoteCardListItem) return
        with(binding) {
            root.setOnClickListener { listener.onNoteClicked(item.noteId) }
            titleTextView.apply {
                text = item.title ?: context.getString(item.emptyTitleResId)
            }
            noteContentTextView.apply {
                text = item.content ?: context.getString(item.emptyContentResId)

            }
            if (item.imageUri == null || item.imageUri.isBlank()) {
                configureNoImage()
            } else {
                noteImageView.apply {
                    context.loadImageWithCachedFirst(
                        uri = Uri.parse(item.imageUri),
                        cachedUri = Uri.parse(item.imageUri),
                        onCachedResourceReady = ::onResourceReady,
                        onResourceReady = ::onResourceReady,
                        onLoadFailed = ::onResourceFailed
                    )
                }
            }
            dateTimeTextView.apply {
                text = context.getFormattedTime(item.createUpdateDate)
            }
        }
    }

    private fun onResourceReady(drawable: Drawable) {
        with(binding) {
            noteImageView.setImageDrawable(drawable)
            configureWithImage()
        }
    }

    private fun onResourceFailed() {
        configureNoImage()
    }

    private fun configureNoImage() {
        with(binding) {
            noteImageView.hide()
            noteContentTextView.maxLines = CONTENT_MAX_LINES_WITHOUT_IMAGE
        }
    }

    private fun configureWithImage() {
        with(binding) {
            noteImageView.show()
            binding.noteContentTextView.maxLines = CONTENT_MAX_LINES_WITH_IMAGE
        }
    }
    companion object {
        const val CONTENT_MAX_LINES_WITH_IMAGE = 3
        const val CONTENT_MAX_LINES_WITHOUT_IMAGE = 10
        fun create(parent: ViewGroup, listener: NoteCardItemListener): NoteCardItemViewHolder {
            val binding = ItemNoteCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NoteCardItemViewHolder(binding, listener)
        }
    }

    fun interface NoteCardItemListener {
        fun onNoteClicked(noteId: Int)
    }
}
