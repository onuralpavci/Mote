package com.avci.mote.modules.customview.customactiondialog.ui.providers

import android.content.Context
import com.avci.mote.R
import com.avci.mote.modules.customview.customactiondialog.ui.CustomActionDialog
import com.avci.mote.modules.customview.customactiondialog.ui.model.ActionDialogParams

fun Context.showDeleteNoteActionDialog(
    onDeleteClickListener: ((data: String?) -> Unit),
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

fun Context.showEnterUrlActionDialog(
    onDoneClickListener: ((url: String?) -> Unit),
    onCancelClickListener: (() -> Unit)? = null
) {
    CustomActionDialog.create(this).apply {
        applyParams(
            ActionDialogParams(
                startImageRes = R.drawable.ic_image,
                titleRes = R.string.enter_image_url,
                descriptionRes = R.string.save_url_requires,
                primaryButtonTextRes = R.string.done,
                secondaryButtonTextRes = R.string.close,
                primaryButtonClickListener = onDoneClickListener,
                secondaryButtonClickListener = onCancelClickListener,
                textInputLayoutHintTextRes = R.string.enter_image_url
            )
        )
        show()
    }
}
