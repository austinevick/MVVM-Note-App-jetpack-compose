package com.example.composenoteapp.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenoteapp.model.Note
import com.example.composenoteapp.screens.NoteViewModel
import formatDate


@Composable
fun NoteListCard(
    noteViewModel: NoteViewModel = hiltViewModel(),
    navController: NavController, note: Note
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate(route = "add_note_screen?title=${note.title}?desc=${note.description}")
        }
        .padding(vertical = 10.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(size = 15.dp)))
        .background(color = Color(0xff32343d))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom

        ) {
            Column() {
                Text(text = note.title, fontWeight = FontWeight.W700)
                Text(text = note.description)
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = formatDate(note.entryDate.time),
                    color = Color.Gray,
                    fontWeight = FontWeight.W600,
                    fontSize = 12.sp
                )
            }
            IconButton(onClick = {
                noteViewModel.deleteNote(note)
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Icon"
                )
            }


        }
    }

}