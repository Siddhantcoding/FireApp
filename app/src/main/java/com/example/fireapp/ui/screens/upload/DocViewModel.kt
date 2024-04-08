package com.example.fireapp.ui.screens.upload

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.digi.fireapp.ui.screens.upload.DocState
import com.digi.fireapp.ui.screens.upload.DocumentsStatus
import com.digi.fireapp.ui.screens.upload.UploadState
import com.example.fireapp.Data.Cdoc
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DocViewModel(
    private val auth: FirebaseAuth = Firebase.auth,
    private val db: FirebaseFirestore = Firebase.firestore,
    private val store: FirebaseStorage = Firebase.storage,
) : ViewModel() {
    private val _state = MutableStateFlow(DocState())
    val state = _state.asStateFlow()

    init {
        loadDocuments()
    }

    fun onEvent(event: DocEvent) {
        when (event) {
            is DocEvent.UploadImage -> uploadToCloud(event.uri)
            DocEvent.Reset -> {
                _state.update {
                    it.copy(
                        uploadState = UploadState.IDLE,
                        progress = 0,
                        error = ""
                    )
                }
            }
        }

    }

    private fun uploadToCloud(
        uri: Uri
    ) {
        val bucket = store.reference.child(COLL_UPLOADS)
        val filename = uri.lastPathSegment ?: "unknown"
        val mimeType = filename.substringAfterLast(".")
        Log.d("DocViewModel", "Uploading $filename to $bucket")
        bucket.child(filename).putFile(uri)
            .addOnSuccessListener { url ->
                val doc = Cdoc(
                    uid = auth.currentUser?.uid ?: "Admin",
                    url = url.toString(),
                    name = filename,
                    mimetype = mimeType,
                    timestamp = System.currentTimeMillis()
                )
                db.collection(COLL_UPLOADS)
                    .add(doc)
                    .addOnSuccessListener {
                        _state.update { state -> state.copy(uploadState = UploadState.SUCCESS) }
                    }
                    .addOnFailureListener {
                        _state.update { state ->
                            state.copy(
                                uploadState = UploadState.ERROR,
                                error = it.message ?: "Some error occurred"
                            )
                        }
                    }
            }
            .addOnFailureListener {
                _state.update {
                    it.copy(
                        uploadState = UploadState.ERROR
                    )
                }
            }
            .addOnProgressListener {
                val totalSize = it.totalByteCount
                val transferred = it.bytesTransferred
                _state.update {
                    it.copy(
                        uploadState = UploadState.UPLOADING,
                        progress = (transferred * 100 / totalSize).toInt()
                    )
                }


            }

    }

    private fun loadDocuments() {
        db.collection(COLL_UPLOADS)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (doc in querySnapshot) {
                    val cDoc = doc.toObject(Cdoc::class.java)
                    _state.update { state ->
                        state.copy(
                            Documents = state.Documents + cDoc
                        )
                    }
                }
                _state.update { state -> state.copy(DocumentStatus = DocumentsStatus.SUCCESS) }
            }
            .addOnFailureListener {
                Log.e("DocViewModel", "Error loading documents ${it.message}")
                _state.update { state -> state.copy(DocumentStatus = DocumentsStatus.ERROR) }
            }
    }

    companion object {
        const val COLL_UPLOADS = "uploads"
        const val COLL_NOTES = "notes"
    }
}