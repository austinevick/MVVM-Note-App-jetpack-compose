package com.example.composenoteapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenoteapp.model.Note
import com.example.composenoteapp.widget.NoteListCard

@Composable
fun NoteSearchScreen(
    noteViewModel: NoteViewModel = hiltViewModel(),
    navController: NavController
) {
    val noteList = noteViewModel.noteList.collectAsState(initial = emptyList()).value
    val controller = remember {
        mutableStateOf("")
    }
    var filteredNotes: ArrayList<Note>
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = "textField", block = { focusRequester.requestFocus() })

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = controller.value,
                onValueChange = { controller.value = it },
                placeholder = { Text(text = "Search") },
                maxLines = 1,
                leadingIcon = {
                    IconButton(
                        modifier = Modifier.size(35.dp),
                        onClick = navController::popBackStack
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Search Icon")
                    }
                }, shape = RoundedCornerShape(corner = CornerSize(0.dp)),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .background(color = Color.Black),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(25.dp))
            LazyColumn() {

                filteredNotes = if (controller.value.isEmpty()) {
                    ArrayList()
                } else {
                    val resultList = ArrayList<Note>()
                    for (note in noteList) {
                        if (note.title.lowercase().contains(controller.value)) {
                            resultList.add(note)
                        }
                        if (note.description.lowercase().contains(controller.value)) {
                            resultList.add(note)
                        }
                    }
                    resultList
                }
                items(filteredNotes) {
                   NoteListCard(navController = navController, note = it)
                }
            }

        }
    }

}