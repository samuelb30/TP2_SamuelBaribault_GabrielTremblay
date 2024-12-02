package com.example.ca.csfoy.tp2_sb_gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ca.csfoy.tp2_sb_gt.database.connectDatabase
import com.example.ca.csfoy.tp2_sb_gt.screens.DetailRecipeView
import com.example.ca.csfoy.tp2_sb_gt.screens.Routes
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel
import com.example.ca.csfoy.tp2_sb_gt.screens.ShowRecipes
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
import com.example.ca.csfoy.tp2_sb_gt.ui.theme.TP2_SamuelBaribault_GabrielTremblayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2_SamuelBaribault_GabrielTremblayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InitApp(innerPadding)
                }
            }
        }
    }

    @Composable
    private fun InitApp(paddingValues: PaddingValues) {
        val db = connectDatabase(applicationContext)

        val viewModel: RecipeViewModel = viewModel(factory = viewModelFactory {
            initializer { RecipeViewModel(db.favoriteRecipeDao()) }
        })

        val recipe = Recipe(
            912391,
            "Dole Whip At Home :)",
            "https://img.spoonacular.com/recipes/912391-556x370.jpg",
            listOf("bob", "bob2"),
            "Simple Ranchy Breaded Fish Fillets is a <b>pescatarian</b> main course. One serving contains <b>308 calories</b>, <b>26g of protein</b>, and <b>14g of fat</b>. This recipe serves 4 and costs \$2.58 per serving. 1 person has made this recipe and would make it again. Head to the store and pick up butter, ranch dressing mix, tilapia fillet, and a few other things to make it today. From preparation to the plate, this recipe takes approximately <b>25 minutes, ",
            "15"
        )


        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Main.title){
            composable(Routes.Main.title){
                DetailRecipeView(recipe, paddingValues)
                //ShowRecipes(modifier, viewModel)
            }
        }


    }
}
