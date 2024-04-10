package com.example.fireapp.ui.screens.booksearch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var maxResults by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        TopAppBar(
            title = {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search for books") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset((-15).dp),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search Icon",
                            modifier = Modifier.offset((-4.dp))
                        )
                    },
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = maxResults,
            onValueChange = { maxResults = it },
            label = { Text("Max Results") },
            modifier = Modifier
                .fillMaxWidth()
            .offset((-8.dp))
                .padding(6.dp),
            leadingIcon = {
                Icon(
                    Icons.Default.List,
                    contentDescription = "list icon"
                )
            },

        )


        LazyColumn() {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookSearchScreenPreview() {
    BookSearchScreen()
}