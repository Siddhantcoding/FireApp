package com.example.fireapp.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MyApp(navController: NavController) {
    val notes = listOf("Note 1", "Note 2", "Note 3")
    NoteScreen(
        onBack = { navController.popBackStack() },
        notes = notes
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    onBack: () -> Unit, // Add a callback for back button
    notes: List<String> // Add a list of notes
) {
    Column (){
        TopAppBar(
            title = { Text("Notes") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Text(
                        text = note,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp),
            content = {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteScreenPreview() {
    NoteScreen(onBack = {}, notes = listOf("Note 1", "Note 2", "Note 3"))
}