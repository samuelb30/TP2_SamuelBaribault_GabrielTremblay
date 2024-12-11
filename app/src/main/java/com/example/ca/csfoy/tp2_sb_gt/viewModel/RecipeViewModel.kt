package com.example.ca.csfoy.tp2_sb_gt.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.database.FavoriteRecipeDao
import com.example.ca.csfoy.tp2_sb_gt.model.FavoriteRecipe
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
import com.example.ca.csfoy.tp2_sb_gt.service.SpoonAcular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel (private val recipeDao: FavoriteRecipeDao): ViewModel() {
    val imagePlaceHolderId = R.drawable.recipe_placeholder

    var isLoading by mutableStateOf(false)
    var searchText by mutableStateOf("")
    val randomRecipes = mutableStateListOf<Recipe>()
    val favoriteRecipes = mutableStateListOf<Recipe>()
    var currentRecipe by mutableStateOf(Recipe(-1, "", "", listOf(), listOf(), "", "", "", "", false))
    var isCurrentRecipeFavorite by mutableStateOf(false)

    init {
        getFavoriteRecipes()
        reloadRecipes()
    }
    fun reloadRecipes(){
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            randomRecipes.clear()
            randomRecipes.addAll(SpoonAcular.fetchRandomRecipes(recipeDao))
            isLoading = false
        }
    }

    fun addFavorite(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.insert(FavoriteRecipe(recipe.id))
        }
    }

    fun removeFavorite(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.remove(recipe.id)
        }
    }

    fun getFavoriteRecipes(){
        viewModelScope.launch(Dispatchers.IO){
            recipeDao.getAll().collect{recipes ->
                favoriteRecipes.clear()
                recipes.forEach{recipe ->
                    val favoriteRecipe = SpoonAcular.fetchSpecificRecipe(recipe.recipeId)
                    favoriteRecipe.isFavorite = true
                    favoriteRecipes.add(favoriteRecipe)
                }
            }
        }
    }
}


