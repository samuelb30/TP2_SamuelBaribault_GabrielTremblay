package com.example.ca.csfoy.tp2_sb_gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ca.csfoy.tp2_sb_gt.database.connectDatabase
import com.example.ca.csfoy.tp2_sb_gt.screens.Routes
<<<<<<< Updated upstream
=======
import com.example.ca.csfoy.tp2_sb_gt.screens.ShowRecipes
>>>>>>> Stashed changes
import com.example.ca.csfoy.tp2_sb_gt.ui.theme.TP2_SamuelBaribault_GabrielTremblayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2_SamuelBaribault_GabrielTremblayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InitApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun InitApp(modifier: Modifier) {
        val db = connectDatabase(applicationContext)
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======

        val viewModel: RecipeViewModel = viewModel(factory = viewModelFactory {
            initializer { RecipeViewModel(db.favoriteRecipeDao()) }
        })
>>>>>>> Stashed changes

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Main.title){
            composable(Routes.Main.title){
<<<<<<< Updated upstream
                
=======
        val viewModel: RecipeViewModel = viewModel(factory = viewModelFactory {
            initializer { RecipeViewModel(db.favoriteRecipeDao()) }
        })
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Main.title){
            composable(Routes.Main.title){
                ShowRecipes(modifier, viewModel)
>>>>>>> Stashed changes
=======
                ShowRecipes(modifier, viewModel)
>>>>>>> Stashed changes
            }
        }


    }
}
