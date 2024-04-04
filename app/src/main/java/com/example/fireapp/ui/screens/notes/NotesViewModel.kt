package com.example.fireapp.ui.screens.notes

import com.digi.fireapp.ui.screens.home.NoteListState
import com.example.fireapp.Data.CNote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NotesViewModel(
    private val  auth : FirebaseAuth = Firebase.auth,
    private val db : FirebaseFirestore = Firebase.firestore,
) {
    private fun loadAllNotes() {}
    private fun saveNewNote() {
        val note = CNote(uid = "", tittle = "", content = "", importance = 1)
        db.collection(COLL_NOTES)
            .add(note)
            .addOnSuccessListener {

            }
            .addOnFailureListener {
            }


    }

    companion object {
        const val COLL_NOTES = "notes"
        const val COLL_UPLOADS = "uploads"
    }
}