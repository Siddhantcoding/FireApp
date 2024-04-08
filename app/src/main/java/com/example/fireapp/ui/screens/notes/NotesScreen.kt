package com.example.fireapp.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
) {
    TopAppBar(title = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .offset(10.dp, 100.dp),
        {
            IconButton(onClick = { /*TODO*/ }) {
                Icons.Default.ArrowBack

            }
        }


        )
}






@Preview(showBackground = true)
@Composable
private fun NoteScreenPreview() {
    NoteScreen()

}