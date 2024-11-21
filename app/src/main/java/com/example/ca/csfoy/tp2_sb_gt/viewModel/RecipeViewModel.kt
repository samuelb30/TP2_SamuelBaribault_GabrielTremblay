package com.example.ca.csfoy.tp2_sb_gt.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ca.csfoy.tp2_sb_gt.database.RecipeDao
import com.example.ca.csfoy.tp2_sb_gt.model.Recipe

class RecipeViewModel (recipeDao: RecipeDao): ViewModel() {
    val recipes = mutableListOf<Recipe>()
    var currentRecipe by mutableStateOf<Recipe?>(null)
}