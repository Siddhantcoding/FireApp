package com.example.fireapp.ui.screens.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.digi.fireapp.ui.screens.upload.DocState
import com.digi.fireapp.ui.screens.upload.UploadState

@Composable
fun DocScreen(
    state: DocState = DocState(),
    onEvent: (DocEvent) -> Unit,
    onBack: () -> Unit,
) {
    val result = remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        bottomBar = {
            UploadBar(state, onEvent, result)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            result.value?.let { image ->
                //Use Coil to display the selected image
                val painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = image)
                        .build()
                )
                LazyHorizontalGrid(rows = GridCells.Fixed(2)) {
                    items(state.Documents) { doc ->
                        Card {
                            AsyncImage(
                                model = doc.url, contentDescription = doc.name,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                        }
                    }
                    Box {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(shape = MaterialTheme.shapes.extraLarge),
                            contentScale = ContentScale.Crop
                        )
                        FilledIconButton(
                            onClick = { DocEvent.UploadImage(image) },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp),
                            enabled = state.uploadState == UploadState.IDLE
                        ) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Confirm Upload Image",
                            )

                        }

                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(100.dp),
                            strokeWidth = 10.dp,
                            trackColor = MaterialTheme.colorScheme.secondaryContainer,
                        )

                    }
                }

            }

        }

    }


    @Composable
    fun UploadBar(state: DocState, onEvent: (DocEvent) -> Unit, result: MutableState<Uri?>) {
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
                result.value = it

            }
        BottomAppBar {
            ExtendedFloatingActionButton(onClick = {
                onEvent(DocEvent.Reset)
                launcher.launch(
                    PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Browse")
                Text(text = "Select Image")

            }
        }

    }