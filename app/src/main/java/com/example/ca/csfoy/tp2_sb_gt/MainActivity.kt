package com.example.ca.csfoy.tp2_sb_gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ca.csfoy.tp2_sb_gt.database.connectDatabase
import com.example.ca.csfoy.tp2_sb_gt.screens.Routes
import com.example.ca.csfoy.tp2_sb_gt.screens.ShowRecipes
import com.example.ca.csfoy.tp2_sb_gt.service.SpoonAcular
import com.example.ca.csfoy.tp2_sb_gt.ui.theme.TP2_SamuelBaribault_GabrielTremblayTheme
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel

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
        val viewModel: RecipeViewModel = viewModel(factory = viewModelFactory {
            initializer { RecipeViewModel(db.recipeDao()) }
        })

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Main.title){
            composable(Routes.Main.title){
                ShowRecipes(modifier, SpoonAcular.fetchRandomRecipes(), viewModel)
            }
        }


    }
}
