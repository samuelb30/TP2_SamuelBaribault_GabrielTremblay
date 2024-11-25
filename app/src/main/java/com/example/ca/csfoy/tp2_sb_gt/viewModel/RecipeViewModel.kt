package com.example.ca.csfoy.tp2_sb_gt.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca.csfoy.tp2_sb_gt.database.FavoriteRecipeDao
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
import com.example.ca.csfoy.tp2_sb_gt.service.SpoonAcular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel (recipeDao: FavoriteRecipeDao): ViewModel() {
    val recipes = mutableListOf<Recipe>()
    var currentRecipe by mutableStateOf<Recipe?>(null)
    init{
        viewModelScope.launch (Dispatchers.IO){
           reloadRecipes()
        }
    }
    fun reloadRecipes(){
        recipes.clear()
        recipes.addAll(SpoonAcular.fetchRandomRecipes())
    }
}