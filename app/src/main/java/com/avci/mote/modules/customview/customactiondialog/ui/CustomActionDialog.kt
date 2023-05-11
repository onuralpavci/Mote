package com.avci.mote.modules.customview.customactiondialog.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.avci.mote.R
import com.avci.mote.databinding.DialogActionBinding
import com.avci.mote.modules.customview.customactiondialog.ui.model.ActionDialogParams
import com.avci.mote.utils.getScreenWidth
import com.avci.mote.utils.setTextAndVisibility
import com.avci.mote.utils.show
import com.avci.mote.utils.viewbinding.viewBinding

class CustomActionDialog private constructor(context: Context) : Dialog(context) {

    private val binding = viewBinding(DialogActionBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        updateDialogWindow()
        updateDialogDecorView()
    }

    private fun updateDialogWindow() {
        window?.apply {
            setBackgroundDrawableResource(R.drawable.bg_action_dialog)
            setDimAmount(DIALOG_DIM_AMOUNT)
        }
    }

    private fun updateDialogDecorView() {
        window?.decorView?.apply {
            window?.setLayout(
                (context.getScreenWidth() * SCREEN_WIDTH_PERCENT).toInt(),
                WRAP_CONTENT
            )
        }
    }

    fun applyParams(tutorialDialogParams: ActionDialogParams) {
        with(tutorialDialogParams) {
            setImageView(imageRes = startImageRes)
            setTitleTextView(textRes = titleRes)
            setDescriptionTextView(textRes = descriptionRes)
            setPrimaryButton(textRes = primaryButtonTextRes, onClick = primaryButtonClickListener)
            setSecondaryButton(textRes = secondaryButtonTextRes, onClick = secondaryButtonClickListener)
        }
    }

    private fun setImageView(@DrawableRes imageRes: Int?) {
        if (imageRes == null) return
        with(binding.startImageView) {
            setImageResource(imageRes)
            this.show()
        }
    }

    private fun setTitleTextView(@StringRes textRes: Int?) {
        binding.titleTextView.setTextAndVisibility(textRes)
    }

    private fun setDescriptionTextView(@StringRes textRes: Int?) {
        binding.descriptionTextView.setTextAndVisibility(textRes)
    }

    private fun setPrimaryButton(@StringRes textRes: Int?, onClick: (() -> Unit)?) {
        if (textRes == null) return
        with(binding.primaryActionButton) {
            setText(textRes)
            setOnClickListener { onClick?.invoke(); dismiss() }
            this.show()
        }
    }

    private fun setSecondaryButton(@StringRes textRes: Int?, onClick: (() -> Unit)?) {
        if (textRes == null) return
        with(binding.secondaryActionButton) {
            setText(textRes)
            setOnClickListener { onClick?.invoke(); dismiss() }
            this.show()
        }
    }

    companion object {
        private const val DIALOG_DIM_AMOUNT = 0.3f
        private const val SCREEN_WIDTH_PERCENT = 0.85

        fun create(context: Context): CustomActionDialog {
            return CustomActionDialog(context)
        }
    }
}
