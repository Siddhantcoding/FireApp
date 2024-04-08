package com.digi.fireapp.ui.screens.upload

import com.example.fireapp.Data.Cdoc

enum class UploadState {
    IDLE,
    UPLOADING,
    SUCCESS,
    ERROR
}
enum class DocumentsStatus {
    LOADING,
    SUCCESS,
    ERROR
}

data class DocState(
    val isLoading: Boolean = false,
    val error: String = "",
    val uploadState: UploadState = UploadState.IDLE,
    val progress: Int = 0,
    val Documents: List<Cdoc> = emptyList(),
    val DocumentStatus: DocumentsStatus = DocumentsStatus.LOADING
)