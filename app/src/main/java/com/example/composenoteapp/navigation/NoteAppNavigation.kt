package com.example.composenoteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composenoteapp.screens.AddNoteScreen
import com.example.composenoteapp.screens.HomeScreen
import com.example.composenoteapp.screens.NoteSearchScreen


@Composable
fun NoteAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {
            HomeScreen(navController = navController)
        }

        composable("add_note_screen?title={title}?desc={desc}",
            arguments = listOf(
                navArgument(name = "title") {
                defaultValue = ""
            },
            navArgument(name = "desc"){
                defaultValue = ""
            })
        ) {
            val title = it.arguments?.getString("title")
            val desc = it.arguments?.getString("desc")
            AddNoteScreen(navController, title,desc)
        }

        composable("search_screen") {
            NoteSearchScreen(navController = navController)
        }
    }
}