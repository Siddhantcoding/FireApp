package com.digi.fireapp.ui.screens.upload

import android.net.Uri

enum class UploadState {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}

data class DocState(
    val isLoading: Boolean = false,
    val error: String = "",
    val uploadState: UploadState = UploadState.IDLE,
)