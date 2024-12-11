package com.example.ca.csfoy.tp2_sb_gt

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ca.csfoy.tp2_sb_gt.database.connectDatabase
import com.example.ca.csfoy.tp2_sb_gt.screens.DetailRecipeView
import com.example.ca.csfoy.tp2_sb_gt.screens.FavoriteRecipesList
import com.example.ca.csfoy.tp2_sb_gt.screens.Routes
import com.example.ca.csfoy.tp2_sb_gt.screens.ShowRecipes
import com.example.ca.csfoy.tp2_sb_gt.ui.theme.TP2_SamuelBaribault_GabrielTremblayTheme
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2_SamuelBaribault_GabrielTremblayTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(modifier = Modifier,
                        title = { Text("TP2") }
                    )
                }) { innerPadding ->
                    InitApp(innerPadding, applicationContext)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun InitApp(innerPadding: PaddingValues, context: Context) {
    val db = connectDatabase(context)

    val recipeViewModel: RecipeViewModel = viewModel(factory = viewModelFactory {
        initializer { RecipeViewModel(db.favoriteRecipeDao()) }
    })

    val refreshScope = rememberCoroutineScope()

    fun refresh() {
        recipeViewModel.isLoading = true
        refreshScope.launch {
            recipeViewModel.reloadRecipes()
            recipeViewModel.isLoading = false
        }
    }

    val state = rememberPullRefreshState(recipeViewModel.isLoading, ::refresh)
    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .padding(innerPadding)
            .pullRefresh(
                state = rememberPullRefreshState(
                    refreshing = recipeViewModel.isLoading,
                    onRefresh = { refresh() })
            ),
    ) {
        Column {
            Row(modifier = Modifier.padding(bottom = 20.dp)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = recipeViewModel.searchText,
                    onValueChange = { recipeViewModel.searchText = it },
                    placeholder = { Text(text = stringResource(R.string.search_text)) }
                )
            }

            NavHost(navController = navController, startDestination = Routes.Main.title) {
                composable(Routes.Main.title) {
                    ShowRecipes(recipeViewModel, onClick = {
                        navController.navigate(Routes.DetailedView.title)
                    })
                }
                composable(Routes.DetailedView.title) {
                    DetailRecipeView(
                        recipeViewModel,
                        onClickReturn = {
                            navController.popBackStack()
                        },
                        onClickFavorite = {
                            recipeViewModel.isCurrentRecipeFavorite = !recipeViewModel.isCurrentRecipeFavorite
                            if (recipeViewModel.isCurrentRecipeFavorite) {
                                recipeViewModel.addFavorite(recipeViewModel.currentRecipe)
                            }else {
                                recipeViewModel.removeFavorite(recipeViewModel.currentRecipe)
                            }
                            recipeViewModel.favoriteRecipes.remove(recipeViewModel.currentRecipe)
                        }
                    )
                }
                composable(Routes.Favorites.title){
                    FavoriteRecipesList(
                        recipeViewModel,
                        onClick = {
                            navController.navigate(Routes.DetailedView.title)
                        }
                    )
                }
            }
        }
        Row(modifier = Modifier.align(alignment = Alignment.BottomStart)) {
            val buttonModifier = Modifier.size(width = 150.dp, height = 40.dp)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = buttonModifier,
                        onClick = { navController.popBackStack()}) {
                        Text(text = stringResource(R.string.discover_button_text))
                    }
                    Button(
                        modifier = buttonModifier,
                        onClick = {
                            navController.navigate(Routes.Favorites.title)
                            recipeViewModel.getFavoriteRecipes()
                        }) {
                        Text(text = stringResource(R.string.favorites_button_text))
                    }
                }
            }
        }
        PullRefreshIndicator(
            recipeViewModel.isLoading,
            state,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

