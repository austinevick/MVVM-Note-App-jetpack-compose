package com.example.composenoteapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenoteapp.model.Note


@Composable
fun AddNoteScreen(
    navController: NavController,
    title: String?,
    desc: String?,
    noteViewModel: NoteViewModel = hiltViewModel()) {
    val isUpdating = remember {
        mutableStateOf(false)
    }
        val noteTitleController = remember {
            mutableStateOf("$title")
        }
        val noteContentController = remember {
            mutableStateOf("$desc")
        }
    val text =if (isUpdating.value) "Edit Note" else "Add Note";

    LaunchedEffect(key1 = "1", block = {
        if(title!!.isNotEmpty()||desc!!.isNotEmpty()){
            isUpdating.value=true
        }
    })



    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, start = 16.dp, end = 16.dp),
            ) {

                Row {
                    IconButton(
                        modifier = Modifier.size(35.dp),
                        onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Note", fontWeight = FontWeight.Bold, fontSize = 23.sp)
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = noteTitleController.value,
                onValueChange = { noteTitleController.value = it },
                placeholder = { Text(text = "Enter title here") },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = noteContentController.value,
                onValueChange = { noteContentController.value = it },
                placeholder = { Text(text = "Write something here") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.Black),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(50.dp))

            Button(colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xff16a464)
            ), shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(55.dp), onClick = {
                    navController.popBackStack()
                    val note = Note(title = noteTitleController.value,
                        description = noteContentController.value)

                    if(isUpdating.value){
                        noteViewModel.updateNote(note)
                    }else{
                    noteViewModel.addNote(note)
                    }

                    println(note)
                }) {
                Text(text =text, fontSize = 22.sp, color = Color.White)
            }

        }
    }
}