package com.task.noteapp.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

private const val ONURALP_AVCI_GITHUB_URL = "https://github.com/onuralpavci"
private const val ONURALP_AVCI_LINKED_URL = "https://www.linkedin.com/in/onuralpavci/"
private const val CHATGPT_URL = "https://chat.openai.com/"

fun Context.openOnuralpGithub() {
    openUrl(ONURALP_AVCI_GITHUB_URL)
}

fun Context.openOnuralpLinkedIn() {
    openUrl(ONURALP_AVCI_LINKED_URL)
}

fun Context.openChatGPT() {
    openUrl(CHATGPT_URL)
}

fun Context.openUrl(url: String) {
    try {
        CustomTabsIntent.Builder()
            .build()
            .launchUrl(this, Uri.parse(url))
    } catch (runtimeException: RuntimeException) {
        // TODO: Log exception
    }
}
