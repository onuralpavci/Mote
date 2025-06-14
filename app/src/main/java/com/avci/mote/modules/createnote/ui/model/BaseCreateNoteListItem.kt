package com.avci.mote.modules.createnote.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.avci.mote.R
import com.avci.mote.modules.core.ui.model.RecyclerListItem

sealed class BaseCreateNoteListItem : RecyclerListItem {

    abstract val itemType: ItemType

    enum class ItemType {
        TITLE,
        LABEL,
        ADD_TEXT_AREA_ACTION,
        ADD_HEADING_ACTION,
        ADD_IMAGE_ACTION,
        ASK_CHATGPT_ACTION,
        TEXT_AREA,
        HEADING,
        IMAGE,
        DIVIDER
    }

    data class TitleItem(
        var title: String
    ) : BaseCreateNoteListItem() {

        override val itemType: ItemType
            get() = ItemType.TITLE

        override fun areItemsTheSame(other: RecyclerListItem): Boolean {
            return other is TitleItem && title == other.title
        }

        override fun areContentsTheSame(other: RecyclerListItem): Boolean {
            return other is TitleItem && other.title == this.title
        }
    }

    data class LabelItem(
        @StringRes
        val labelResId: Int
    ) : BaseCreateNoteListItem() {

        override val itemType: ItemType
            get() = ItemType.LABEL

        override fun areItemsTheSame(other: RecyclerListItem): Boolean {
            return other is LabelItem && labelResId == other.labelResId
        }

        override fun areContentsTheSame(other: RecyclerListItem): Boolean {
            return other is LabelItem && other == this
        }
    }

    sealed class BaseActionItem : BaseCreateNoteListItem() {
        abstract val actionIconResId: Int
        abstract val textResId: Int

        object AddTextAreaActionItem : BaseActionItem() {

            override val itemType: ItemType
                get() = ItemType.ADD_TEXT_AREA_ACTION

            @DrawableRes
            override val actionIconResId: Int = R.drawable.ic_plus

            @StringRes
            override val textResId: Int = R.string.add_free_text

            override fun areItemsTheSame(other: RecyclerListItem): Boolean {
                return other is AddTextAreaActionItem &&
                    actionIconResId == other.actionIconResId &&
                    textResId == other.textResId
            }

            override fun areContentsTheSame(other: RecyclerListItem): Boolean {
                return other is AddTextAreaActionItem && other == this
            }
        }

        object AddHeadingActionItem : BaseActionItem() {

            override val itemType: ItemType
                get() = ItemType.ADD_HEADING_ACTION

            @DrawableRes
            override val actionIconResId: Int = R.drawable.ic_heading

            @StringRes
            override val textResId: Int = R.string.add_heading

            override fun areItemsTheSame(other: RecyclerListItem): Boolean {
                return other is AddHeadingActionItem &&
                    actionIconResId == other.actionIconResId &&
                    textResId == other.textResId
            }

            override fun areContentsTheSame(other: RecyclerListItem): Boolean {
                return other is AddHeadingActionItem && other == this
            }
        }

        object AddImageActionItem : BaseActionItem() {

            override val itemType: ItemType
                get() = ItemType.ADD_IMAGE_ACTION

            @DrawableRes
            override val actionIconResId: Int = R.drawable.ic_image

            @StringRes
            override val textResId: Int = R.string.add_image

            override fun areItemsTheSame(other: RecyclerListItem): Boolean {
                return other is AddImageActionItem &&
                    actionIconResId == other.actionIconResId &&
                    textResId == other.textResId
            }

            override fun areContentsTheSame(other: RecyclerListItem): Boolean {
                return other is AddImageActionItem && other == this
            }
        }

        object AskChatGPTActionItem : BaseActionItem() {

            override val itemType: ItemType
                get() = ItemType.ASK_CHATGPT_ACTION

            @DrawableRes
            override val actionIconResId: Int = R.drawable.ic_chatgpt

            @StringRes
            override val textResId: Int = R.string.ask_chatgpt

            override fun areItemsTheSame(other: RecyclerListItem): Boolean {
                return other is AskChatGPTActionItem &&
                    actionIconResId == other.actionIconResId &&
                    textResId == other.textResId
            }

            override fun areContentsTheSame(other: RecyclerListItem): Boolean {
                return other is AskChatGPTActionItem && other == this
            }
        }
    }

    sealed class BaseNoteComponentItem : BaseCreateNoteListItem() {
        abstract val componentId: Int

        data class TextAreaItem(
            var text: String,
            override val componentId: Int
        ) : BaseNoteComponentItem() {

            override val itemType: ItemType
                get() = ItemType.TEXT_AREA

            override fun areItemsTheSame(other: RecyclerListItem): Boolean {
                return other is TextAreaItem && this.componentId == other.componentId
            }

            override fun areContentsTheSame(other: RecyclerListItem): Boolean {
                return other is TextAreaItem && other.text == this.text
            }
        }

        data class HeadingItem(
            var text: String,
            override val componentId: Int
        ) : BaseNoteComponentItem() {

            override val itemType: ItemType
                get() = ItemType.HEADING

            override fun areItemsTheSame(other: RecyclerListItem): Boolean {
                return other is HeadingItem && this.componentId == other.componentId
            }

            override fun areContentsTheSame(other: RecyclerListItem): Boolean {
                return other is HeadingItem && other.text == this.text
            }
        }

        data class ImageItem(
            val uri: String,
            override val componentId: Int
        ) : BaseNoteComponentItem() {

            override val itemType: ItemType
                get() = ItemType.IMAGE

            override fun areItemsTheSame(other: RecyclerListItem): Boolean {
                return other is ImageItem && componentId == other.componentId
            }

            override fun areContentsTheSame(other: RecyclerListItem): Boolean {
                return other is ImageItem && other.uri == this.uri
            }
        }
    }

    object DividerItem : BaseCreateNoteListItem() {

        override val itemType: ItemType
            get() = ItemType.DIVIDER

        override fun areItemsTheSame(other: RecyclerListItem): Boolean {
            return other is DividerItem
        }

        override fun areContentsTheSame(other: RecyclerListItem): Boolean {
            return other is DividerItem && other == this
        }
    }

}
