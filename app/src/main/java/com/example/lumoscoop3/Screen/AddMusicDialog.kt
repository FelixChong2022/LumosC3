package com.example.lumoscoop3.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lumoscoop3.Model.MusicEvent
import com.example.lumoscoop3.Model.MusicState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMusicDialog(
    state: MusicState,
    onEvent: (MusicEvent) -> Unit,
    modifier: Modifier = Modifier
)
{
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(MusicEvent.HideDialog) },
        title = { Text(text = "Add Music") },
        text = {
            TextField(
                value = state.title,
                onValueChange = {
                    onEvent(MusicEvent.SetTitle(it))
                },
                placeholder = {
                    Text(text = "Music Title")
                }
            )
        },
        confirmButton={
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(MusicEvent.SaveMusic)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}