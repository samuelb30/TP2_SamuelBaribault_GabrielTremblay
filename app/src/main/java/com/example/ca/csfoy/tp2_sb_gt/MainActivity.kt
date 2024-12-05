package com.example.ca.csfoy.tp2_sb_gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.ca.csfoy.tp2_sb_gt.screens.Routes
import com.example.ca.csfoy.tp2_sb_gt.screens.ShowRecipes
import com.example.ca.csfoy.tp2_sb_gt.ui.theme.TP2_SamuelBaribault_GabrielTremblayTheme
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel
import kotlinx.coroutines.delay
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
                    InitApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun InitApp(modifier: Modifier) {
        val db = connectDatabase(applicationContext)

        val recipeViewModel: RecipeViewModel = viewModel(factory = viewModelFactory {
            initializer { RecipeViewModel(db.favoriteRecipeDao()) }
        })

        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }
        fun refresh() {
            refreshing = true
            refreshScope.launch {
                recipeViewModel.reloadRecipes()
                delay(1500)
                refreshing = false
            }
        }

        val state = rememberPullRefreshState(refreshing, ::refresh)
        val navController = rememberNavController()
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .pullRefresh(
                    state = rememberPullRefreshState(
                        refreshing = refreshing,
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
                        ShowRecipes(modifier, recipeViewModel)
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
                    shape = CircleShape,
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = buttonModifier,
                            onClick = { navController.navigate(Routes.Favorites.title) }) {
                            Text(text = stringResource(R.string.favorites_button_text))
                        }
                    }
                }
            }
            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
        }

    }
}
