package com.example.ca.csfoy.tp2_sb_gt.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca.csfoy.tp2_sb_gt.database.FavoriteRecipeDao
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
import com.example.ca.csfoy.tp2_sb_gt.service.SpoonAcular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel (private val recipeDao: FavoriteRecipeDao): ViewModel() {
    var isLoading by mutableStateOf(false)
    var searchText by mutableStateOf("")
    val randomRecipes = mutableStateListOf<Recipe>()
    val favoriteRecipes = mutableStateListOf<Recipe>()
    var currentRecipe by mutableStateOf(Recipe(-1, "", "", listOf(), listOf(), "", "", "", "", false))
    var isCurrentRecipeFavorite by mutableStateOf(false)

    init {
        reloadRecipes()
        currentRecipe = randomRecipes[0]
    }
    fun reloadRecipes(){
        viewModelScope.launch(Dispatchers.IO) {
            randomRecipes.clear()
            randomRecipes.addAll(SpoonAcular.fetchRandomRecipes())

        }
    }
}


