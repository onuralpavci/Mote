package com.task.noteapp.modules.customview.customactiondialog.ui.providers

import android.content.Context
import com.task.noteapp.R
import com.task.noteapp.modules.customview.customactiondialog.ui.CustomActionDialog
import com.task.noteapp.modules.customview.customactiondialog.ui.model.ActionDialogParams

fun Context.showDeleteNoteActionDialog(
    onDeleteClickListener: (() -> Unit),
    onCancelClickListener: (() -> Unit)? = null
) {
    CustomActionDialog.create(this).apply {
        applyParams(
            ActionDialogParams(
                startImageRes = R.drawable.ic_red_trash,
                titleRes = R.string.deleting_note,
                descriptionRes = R.string.are_you_sure,
                primaryButtonTextRes = R.string.delete,
                secondaryButtonTextRes = R.string.cancel,
                primaryButtonClickListener = onDeleteClickListener,
                secondaryButtonClickListener = onCancelClickListener
            )
        )
        show()
    }
}

fun Context.showNoteSavedActionDialog() {
    CustomActionDialog.create(this).apply {
        applyParams(
            ActionDialogParams(
                startImageRes = R.drawable.ic_green_check,
                titleRes = R.string.saved_exclamation,
                descriptionRes = R.string.your_note_is,
                secondaryButtonTextRes = R.string.close
            )
        )
        show()
    }
}
