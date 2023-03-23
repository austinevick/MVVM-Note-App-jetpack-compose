package com.example.composenoteapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenoteapp.widget.NoteListCard


@Composable
fun HomeScreen(
    noteViewModel: NoteViewModel = hiltViewModel(),
    navController: NavController
) {

    val noteList = noteViewModel.noteList.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Notes", fontWeight = FontWeight.Bold, fontSize = 23.sp)
                IconButton(onClick = {
                    navController.navigate(route = "search_screen")
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color(0xff16a464),
                onClick = { navController.navigate(route = "add_note_screen") }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon", tint = Color.White
                )
            }
        },
    ) {
        it.calculateTopPadding()

        if (noteList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "No Note Added\nTap on the + icon to begin!!!",
                    textAlign = TextAlign.Center, color = Color.Gray, fontSize = 18.sp
                )
            }

        } else {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                content = {

                    items(noteList) { note ->
                        NoteListCard(navController = navController, note = note)

                    }
                })

        }

    }

}


